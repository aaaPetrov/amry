package com.solvd.army.persistence.impl;

import com.solvd.army.domain.Army;
import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.persistence.ConnectionPool;
import com.solvd.army.persistence.IArmyRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArmyRepositoryImpl implements IArmyRepository {

    private final static String SELECT_ALL = SELECT_ALL();

    @Override
    public void update(Army army) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "update armies set country = ? where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, army.getCountry());
            preparedStatement.setLong(2, army.getId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                army.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void create(Army army) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "insert into armies(country) value(?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, army.getCountry());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                army.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Army army) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "delete from armies where country = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, army.getCountry());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Army get(String country) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "select id as army_id, country from armies where country = ?;";
        Army army = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, country);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                army = new Army();
                army.setId(resultSet.getLong(1));
                army.setCountry(resultSet.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
        return army;
    }

    @Override
    public List<Army> getAll() {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        List<Army> armies = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            armies = fillArmies(resultSet, armies);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
        return armies;
    }

    private static List<Army> fillArmies(ResultSet resultSet, List<Army> armies) {
        if (armies == null) {
            armies = new ArrayList<>();
        }
        try {
            while (resultSet.next()) {
                Long armyId = resultSet.getLong("army_id");
                Army army = createIfNotExist(armyId, armies);
                army.setCountry(resultSet.getString("country"));

                List<MilitaryUnit> militaryUnits = MilitaryUnitRepositoryImpl.fillMilitaryUnits(resultSet, armies);
                army.setMilitaryUnits(militaryUnits);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return armies;
    }

    private static Army createIfNotExist(Long armyId, List<Army> armies) {
        Army result = null;
        if (!armies.isEmpty()) {
            for (Army army : armies) {
                if (armyId.equals(army.getId())) {
                    result = army;
                }
            }
            if (result == null) {
                Army newArmy = new Army();
                newArmy.setId(armyId);
                armies.add(newArmy);
                result = newArmy;
            }
        } else {
            Army newArmy = new Army();
            newArmy.setId(armyId);
            armies.add(newArmy);
            result = newArmy;
        }
        return result;
    }

    private static String SELECT_ALL() {
        return "select A.id as army_id, A.country, M.army_id as military_unit_army_id, M.id military_unit_id, M.name, M.latitude, M.longitude, "
                + "AM.id as ammo_id, AM.type as ammo_type, MAM.ammo_id as military_unit_ammo_id, MAM.military_unit_id as military_unit_ammo_military_unit_id, MAM.amount, "
                + "W.id as weapon_id, W.type as weapon_type, MAW.weapon_id as military_unit_weapon_id, MAW.military_unit_id as military_unit_weapon_military_unit_id, MAW.amount, "
                + "T.id as tank_id, T.type as tank_type, MAT.tank_id as military_unit_tank_id, MAT.military_unit_id as military_unit_tank_military_unit_id, MAT.amount, "
                + "P.id as plane_id, P.type as plane_type, MAP.plane_id as military_unit_plane_id, MAP.military_unit_id as military_unit_plane_military_unit_id, MAP.amount, "
                + "REC.id as recruit_id, S.id as soldier_id, S.recruit_id as soldier_recruit_id, S.military_unit_id as soldier_military_unit_id, REC.first_name, REC.last_name, REC.birthday, R.id as rank_id, R.type as rank_type, S.entered_the_service, S.end_of_service "
                + "from armies as A "
                + "inner join military_units as M on A.id = M.army_id "
                + "inner join military_unit_ammo as MAM on M.id = MAM.military_unit_id "
                + "inner join military_unit_weapon as MAW on M.id = MAW.military_unit_id "
                + "inner join military_unit_tank as MAT on M.id = MAT.military_unit_id "
                + "inner join military_unit_plane as MAP on M.id = MAP.military_unit_id "
                + "inner join soldiers as S on M.id = S.military_unit_id "
                + "inner join ammo as AM on MAM.ammo_id = AM.id "
                + "inner join weapons as W on MAW.weapon_id = W.id "
                + "inner join tanks as T on MAT.tank_id = T.id "
                + "inner join planes as P on MAP.plane_id = P.id "
                + "inner join recruits as REC on S.recruit_id = REC.id "
                + "inner join ranks as R on S.rank_id = R.id;";
    }

}
