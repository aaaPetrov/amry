package com.solvd.army.persistence.impl;

import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.resources.Ammo;
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
    public List<Tank> select(String militaryUnitName) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        List<Tank> tanks = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_COMMAND)) {
            preparedStatement.setString(1, militaryUnitName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Tank newTank = new Tank();
                Tank.TankType tankType = tankTypeByString(resultSet.getString("type"));
                newTank.setTankType(tankType);
                newTank.setAmount(resultSet.getInt("amount"));
                tanks.add(newTank);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
        return tanks;
    }

    @Override
    public void update(List<Tank> tank, Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommandSelect = "select tank_id from military_unit_tank where military_unit_id = ?;";
        String sqlCommandUpdate = "update military_unit_tank set tank_id = ?, amount = ? where military_unit_id = ? and tank_id = ?;";
        try(PreparedStatement preparedStatementSelect = connection.prepareStatement(sqlCommandSelect);
            PreparedStatement preparedStatementUpdate = connection.prepareStatement(sqlCommandUpdate)) {

            preparedStatementSelect.setLong(1,militaryUnitId);
            ResultSet resultSet = preparedStatementSelect.executeQuery();

            int i = 0;
            while(resultSet.next()) {
                preparedStatementUpdate.setLong(1, tank.get(i).getTankType().getTankId());
                preparedStatementUpdate.setInt(2, tank.get(i).getAmount());
                preparedStatementUpdate.setLong(3, militaryUnitId);
                preparedStatementUpdate.setLong(4, resultSet.getLong("tank_id"));
                preparedStatementUpdate.executeUpdate();
                i++;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void insert(Tank tank, Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "insert into military_unit_tank(tank_id, military_unit_id, amount) value(?, ?, ?);";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setLong(1, tank.getTankType().getTankId());
            preparedStatement.setLong(2, militaryUnitId);
            preparedStatement.setLong(3, tank.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
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
                    Tank.TankType tankType = tankTypeByString(resultSet.getString("tank_type"));
                    tank.setTankType(tankType);
                    tank.setAmount(resultSet.getInt("amount"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    private static Tank.TankType tankTypeByString(String typeInString) {
        Tank.TankType tankType = null;
        switch (typeInString) {
            case "2С25 Sproot-CD" :
                tankType = Tank.TankType.T_2C25;
                break;
            case "2С31 Vena" :
                tankType = Tank.TankType.T_2C31;
                break;
            case "BMD-4" :
                tankType = Tank.TankType.BMD_4;
                break;
            case "BMD-4M" :
                tankType = Tank.TankType.BMD_4M;
                break;
            case "BTR-90" :
                tankType = Tank.TankType.BTR_90;
                break;
            case "BTR-MD" :
                tankType = Tank.TankType.BTR_MD;
                break;
            case "T-14 Armata" :
                tankType = Tank.TankType.T_14;
                break;
            case "T-15" :
                tankType = Tank.TankType.T_15;
                break;
            case "T-90" :
                tankType = Tank.TankType.T_90;
                break;
            case "T-95 Object" :
                tankType = Tank.TankType.T_95;
                break;
            case "TOC-1 Pinocchio" :
                tankType = Tank.TankType.TOC_1;
                break;
            case "TOC-1A Sunfire" :
                tankType = Tank.TankType.TOC_1A;
                break;
            case "Black Eagle" :
                tankType = Tank.TankType.BLACK_EAGLE;
                break;
        }
        return tankType;
    }

    private static String SELECT_COMMAND() {
        return "select MUT.tank_id, MUT.military_unit_id, MUT.amount, T.id, T.type from military_unit_tank as MUT "
                + "inner join tanks as T on MUT.tank_id = T.id "
                + "where military_unit_id = (select id from military_units where name = ?);";
    }

}
