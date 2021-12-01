package com.solvd.army.persistence.impl;

import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.exception.ProcessingException;
import com.solvd.army.domain.resources.Tank;
import com.solvd.army.persistence.ConnectionPool;
import com.solvd.army.persistence.ITankRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TankRepositoryImpl implements ITankRepository {

    private final static String SQL_COMMAND = SELECT_COMMAND();

    @Override
    public List<Tank> getByMilitaryUnitName(String militaryUnitName) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        List<Tank> tanks = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_COMMAND)) {
            preparedStatement.setString(1, militaryUnitName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Tank newTank = new Tank();
                Tank.TankType tankType = Tank.TankType.valueOf(resultSet.getString("type"));
                newTank.setTankType(tankType);
                newTank.setAmount(resultSet.getInt("amount"));
                tanks.add(newTank);
            }
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
        return tanks;
    }

    @Override
    public List<Long> getId(Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommandSelect = "select tank_id from military_unit_tank where military_unit_id = ?;";
        List<Long> ids = null;
        try (PreparedStatement preparedStatementSelect = connection.prepareStatement(sqlCommandSelect)) {
            preparedStatementSelect.setLong(1, militaryUnitId);
            ResultSet resultSet = preparedStatementSelect.executeQuery();
            if (resultSet != null) {
                ids = new ArrayList<>();
            }
            while (resultSet.next()) {
                ids.add(resultSet.getLong("tank_id"));
            }
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
        return ids;
    }

    @Override
    public void update(Tank tank, Long tankId, Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommandUpdate = "update military_unit_tank set tank_id = ?, amount = ? where military_unit_id = ? and tank_id = ?;";
        try (PreparedStatement preparedStatementUpdate = connection.prepareStatement(sqlCommandUpdate)) {
            preparedStatementUpdate.setLong(1, tank.getTankType().getTankId());
            preparedStatementUpdate.setInt(2, tank.getAmount());
            preparedStatementUpdate.setLong(3, militaryUnitId);
            preparedStatementUpdate.setLong(4, tankId);
            preparedStatementUpdate.executeUpdate();
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void create(Tank tank, Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "insert into military_unit_tank(tank_id, military_unit_id, amount) value(?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setLong(1, tank.getTankType().getTankId());
            preparedStatement.setLong(2, militaryUnitId);
            preparedStatement.setLong(3, tank.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public static List<Tank> fillTanks(ResultSet resultSet, List<MilitaryUnit> militaryUnits) {
        List<Tank> tanks = null;
        try {
            Long militaryUnitTankId = resultSet.getLong("military_unit_tank_id");
            Long militaryUnitTankMilitaryUnitId = resultSet.getLong("military_unit_tank_military_unit_id");
            for (MilitaryUnit militaryUnit : militaryUnits) {
                if (militaryUnit.getId() == militaryUnitTankMilitaryUnitId) {
                    tanks = militaryUnit.getTanks();
                    Tank tank = createIfNotExist(militaryUnitTankId, tanks);
                    Tank.TankType tankType = Tank.TankType.valueOf(resultSet.getString("tank_type"));
                    tank.setTankType(tankType);
                    tank.setAmount(resultSet.getInt("amount"));
                }
            }
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        }
        return tanks;
    }

    private static Tank createIfNotExist(Long militaryUnitTankId, List<Tank> tanks) {
        Tank result = null;
        if (!tanks.isEmpty()) {
            for (Tank tank : tanks) {
                if (militaryUnitTankId.equals(tank.getTankType().getTankId())) {
                    result = tank;
                }
            }
            if (result == null) {
                Tank newTank = new Tank();
                tanks.add(newTank);
                result = newTank;
            }
        } else {
            Tank newTank = new Tank();
            tanks.add(newTank);
            result = newTank;
        }
        return result;
    }

    private static String SELECT_COMMAND() {
        return "select MUT.tank_id, MUT.military_unit_id, MUT.amount, T.id, T.type from military_unit_tank as MUT "
                + "inner join tanks as T on MUT.tank_id = T.id "
                + "where military_unit_id = (select id from military_units where name = ?);";
    }

}
