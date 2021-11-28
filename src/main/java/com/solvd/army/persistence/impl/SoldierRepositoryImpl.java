package com.solvd.army.persistence.impl;

import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.domain.soldier.ServiceTerm;
import com.solvd.army.domain.soldier.Soldier;
import com.solvd.army.persistence.ConnectionPool;
import com.solvd.army.persistence.ISoldierRepository;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class SoldierRepositoryImpl implements ISoldierRepository {

    private final static String SQL_COMMAND = SELECT_COMMAND();

    @Override
    public List<Soldier> select(String militaryUnitName) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        List<Soldier> soldiers = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_COMMAND)) {
            preparedStatement.setString(1, militaryUnitName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Soldier newSoldier = new Soldier();
                Soldier.Rank rankType = rankTypeByString(resultSet.getString("type"));
                newSoldier.setId(resultSet.getLong("soldier_id"));
                newSoldier.setRank(rankType);
                newSoldier.setFirstName(resultSet.getString("first_name"));
                newSoldier.setLastName(resultSet.getString("last_name"));
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                newSoldier.setBirthday(birthday);
                int age = (int) ChronoUnit.YEARS.between(birthday, LocalDate.now());
                newSoldier.setAge(age);
                ServiceTerm serviceTerm = new ServiceTerm();
                serviceTerm.setEntered(resultSet.getDate("entered_the_service").toLocalDate());
                serviceTerm.setEnd(resultSet.getDate("end_of_service").toLocalDate());
                newSoldier.setTerm(serviceTerm);
                soldiers.add(newSoldier);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
        return soldiers;
    }

    @Override
    public void update(Soldier soldier, Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommandSoldier = "update soldiers set rank_id = ?, entered_the_service = ?, end_of_service = ? where military_unit_id = ? and id = ?;";
        String sqlCommandRecruit = "update recruits set first_name = ?, last_name = ?, birthday = ? where id = "
                + "(select recruit_id from soldiers where id = ?);";
        try (PreparedStatement preparedStatementSoldier = connection.prepareStatement(sqlCommandSoldier);
             PreparedStatement preparedStatementRecruit = connection.prepareStatement(sqlCommandRecruit)) {
            preparedStatementSoldier.setLong(1, soldier.getRank().getRankId());
            preparedStatementSoldier.setDate(2, Date.valueOf(soldier.getTerm().getEntered()));
            preparedStatementSoldier.setDate(3, Date.valueOf(soldier.getTerm().getEnd()));
            preparedStatementSoldier.setLong(4, militaryUnitId);
            preparedStatementSoldier.setLong(5, soldier.getId());
            preparedStatementSoldier.executeUpdate();

            preparedStatementRecruit.setString(1, soldier.getFirstName());
            preparedStatementRecruit.setString(2, soldier.getLastName());
            preparedStatementRecruit.setDate(3, Date.valueOf(soldier.getBirthday()));
            preparedStatementRecruit.setLong(4, soldier.getId());
            preparedStatementRecruit.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void insert(Soldier soldier, Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommandRecruit = "insert into recruits(first_name, last_name, birthday) value(?, ?, ?);";
        String sqlCommandSoldier = "insert into soldiers(recruit_id, rank_id, military_unit_id, entered_the_service, end_of_service) value(?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatementRecruit = connection.prepareStatement(sqlCommandRecruit, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedStatementSoldier = connection.prepareStatement(sqlCommandSoldier, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatementRecruit.setString(1, soldier.getFirstName());
            preparedStatementRecruit.setString(2, soldier.getLastName());
            preparedStatementRecruit.setDate(3, Date.valueOf(soldier.getBirthday()));

            preparedStatementRecruit.executeUpdate();
            ResultSet resultSetRecruit = preparedStatementRecruit.getGeneratedKeys();
            Long recruit_id = null;
            if (resultSetRecruit.next()) {
                recruit_id = resultSetRecruit.getLong(1);
            }
            if (recruit_id != null) {
                preparedStatementSoldier.setLong(1, recruit_id);
                preparedStatementSoldier.setLong(2, soldier.getRank().getRankId());
                preparedStatementSoldier.setLong(3, militaryUnitId);
                preparedStatementSoldier.setDate(4, Date.valueOf(soldier.getTerm().getEntered()));
                preparedStatementSoldier.setDate(5, Date.valueOf(soldier.getTerm().getEnd()));

                preparedStatementSoldier.executeUpdate();
                ResultSet resultSetSoldier = preparedStatementSoldier.getGeneratedKeys();
                if (resultSetSoldier.next()) {
                    soldier.setId(resultSetSoldier.getLong(1));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Soldier soldier) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "delete from recruits where first_name = ? and last_name = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, soldier.getFirstName());
            preparedStatement.setString(2, soldier.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public static List<Soldier> fillSoldiers(ResultSet resultSet, List<MilitaryUnit> militaryUnits) {
        List<Soldier> soldiers = null;
        try {
            Long soldierMilitaryUnitId = resultSet.getLong("soldier_military_unit_id");
            Long soldierId = resultSet.getLong("soldier_id");
            for (MilitaryUnit militaryUnit : militaryUnits) {
                if (militaryUnit.getId() == soldierMilitaryUnitId) {
                    soldiers = militaryUnit.getSoldiers();
                    Soldier soldier = createIfNotExist(soldierId, soldiers);
                    Soldier.Rank rankType = rankTypeByString(resultSet.getString("rank_type"));

                    soldier.setFirstName(resultSet.getString("first_name"));
                    soldier.setLastName(resultSet.getString("last_name"));
                    LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                    soldier.setBirthday(birthday);
                    int age = (int) ChronoUnit.YEARS.between(birthday, LocalDate.now());
                    soldier.setAge(age);
                    ServiceTerm serviceTerm = new ServiceTerm();
                    serviceTerm.setEntered(resultSet.getDate("entered_the_service").toLocalDate());
                    serviceTerm.setEnd(resultSet.getDate("end_of_service").toLocalDate());
                    soldier.setTerm(serviceTerm);
                    soldier.setRank(rankType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soldiers;
    }

    private static Soldier createIfNotExist(Long soldierId, List<Soldier> soldiers) {
        Soldier result = null;
        if (!soldiers.isEmpty()) {
            for (Soldier soldier : soldiers) {
                if (soldierId.equals(soldier.getId())) {
                    result = soldier;
                }
            }
            if (result == null) {
                Soldier newSoldier = new Soldier();
                newSoldier.setId(soldierId);
                soldiers.add(newSoldier);
                result = newSoldier;
            }
        } else {
            Soldier newSoldier = new Soldier();
            newSoldier.setId(soldierId);
            soldiers.add(newSoldier);
            result = newSoldier;
        }
        return result;
    }

    private static Soldier.Rank rankTypeByString(String typeInString) {
        Soldier.Rank rankType = null;
        switch (typeInString) {
            case "Squaddie":
                rankType = Soldier.Rank.SQUADDIE;
                break;
            case "Corporal":
                rankType = Soldier.Rank.CORPORAL;
                break;
            case "Lance-sergeant":
                rankType = Soldier.Rank.LANCE_SERGEANT;
                break;
            case "Sergeant":
                rankType = Soldier.Rank.SERGEANT;
                break;
            case "Staff-sergeant":
                rankType = Soldier.Rank.STAFF_SERGEANT;
                break;
            case "Senior-sergeant":
                rankType = Soldier.Rank.SENIOR_SERGEANT;
                break;
            case "Warrant":
                rankType = Soldier.Rank.WARRANT;
                break;
            case "Senior-warrant":
                rankType = Soldier.Rank.SENIOR_WARRANT;
                break;
            case "Sublieutenant":
                rankType = Soldier.Rank.SUBLIEUTENANT;
                break;
            case "Lieutenant":
                rankType = Soldier.Rank.LIEUTENANT;
                break;
            case "Senior-lieutenant":
                rankType = Soldier.Rank.SENIOR_LIEUTENANT;
                break;
            case "Captain":
                rankType = Soldier.Rank.CAPTAIN;
                break;
            case "Major":
                rankType = Soldier.Rank.MAJOR;
                break;
            case "Lieutenant-colonel":
                rankType = Soldier.Rank.LIEUTENANT_COLONEL;
                break;
            case "Colonel":
                rankType = Soldier.Rank.COLONEL;
                break;
            case "Major-general":
                rankType = Soldier.Rank.MAJOR_GENERAL;
                break;
            case "Lieutenant-general":
                rankType = Soldier.Rank.LIEUTENANT_GENERAL;
                break;
            case "Colonel-general":
                rankType = Soldier.Rank.COLONEL_GENERAL;
                break;
        }
        return rankType;
    }

    private static String SELECT_COMMAND() {
        return "select S.id as soldier_id, S.recruit_id, S.rank_id, S.military_unit_id, R.type, REC.first_name, REC.last_name, REC.birthday, S.entered_the_service, S.end_of_service "
                + "from soldiers as S "
                + "inner join recruits as REC on S.recruit_id = REC.id "
                + "inner join ranks as R on S.rank_id = R.id "
                + "where S.military_unit_id = (select id from military_units where name = ?);";
    }

}
