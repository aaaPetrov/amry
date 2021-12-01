package com.solvd.army.persistence.impl;

import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.exception.ProcessingException;
import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.persistence.ConnectionPool;
import com.solvd.army.persistence.IAmmoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AmmoRepositoryImpl implements IAmmoRepository {

    private final static String SQL_COMMAND = SELECT_COMMAND();

    @Override
    public List<Ammo> getByMilitaryUnitName(String militaryUnitName) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        List<Ammo> ammunition = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_COMMAND)) {
            preparedStatement.setString(1, militaryUnitName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ammo newAmmo = new Ammo();
                Ammo.AmmoType ammoType = Ammo.AmmoType.valueOf(resultSet.getString("type"));
                newAmmo.setAmmoType(ammoType);
                newAmmo.setAmount(resultSet.getInt("amount"));
                ammunition.add(newAmmo);
            }
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
        return ammunition;
    }

    @Override
    public List<Long> getId(Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommandSelect = "select ammo_id from military_unit_ammo where military_unit_id = ?;";
        List<Long> ids = null;
        try (PreparedStatement preparedStatementSelect = connection.prepareStatement(sqlCommandSelect)) {
            preparedStatementSelect.setLong(1, militaryUnitId);
            ResultSet resultSet = preparedStatementSelect.executeQuery();
            if (resultSet != null) {
                ids = new ArrayList<>();
            }
            while (resultSet.next()) {
                ids.add(resultSet.getLong("ammo_id"));
            }
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
        return ids;
    }

    @Override
    public void update(Ammo ammo, Long ammoId, Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommandUpdate = "update military_unit_ammo set ammo_id = ?, amount = ? where military_unit_id = ? and ammo_id = ?;";
        try (PreparedStatement preparedStatementUpdate = connection.prepareStatement(sqlCommandUpdate)) {
            preparedStatementUpdate.setLong(1, ammo.getAmmoType().getAmmoId());
            preparedStatementUpdate.setInt(2, ammo.getAmount());
            preparedStatementUpdate.setLong(3, militaryUnitId);
            preparedStatementUpdate.setLong(4, ammoId);
            preparedStatementUpdate.executeUpdate();
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void create(Ammo ammo, Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "insert into military_unit_ammo(ammo_id, military_unit_id, amount) value(?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setLong(1, ammo.getAmmoType().getAmmoId());
            preparedStatement.setLong(2, militaryUnitId);
            preparedStatement.setInt(3, ammo.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public static List<Ammo> fillAmmo(ResultSet resultSet, List<MilitaryUnit> militaryUnits) {
        List<Ammo> ammunnition = null;
        try {
            Long militaryUnitAmmoId = resultSet.getLong("military_unit_ammo_id");
            Long militaryUnitAmmoMilitaryUnitId = resultSet.getLong("military_unit_ammo_military_unit_id");
            for (MilitaryUnit militaryUnit : militaryUnits) {
                if (militaryUnit.getId() == militaryUnitAmmoMilitaryUnitId) {
                    ammunnition = militaryUnit.getAmmunition();
                    Ammo ammo = createIfNotExist(militaryUnitAmmoId, ammunnition);
                    Ammo.AmmoType ammoType = Ammo.AmmoType.valueOf(resultSet.getString("ammo_type"));
                    ammo.setAmmoType(ammoType);
                    ammo.setAmount(resultSet.getInt("amount"));
                }
            }
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        }
        return ammunnition;
    }

    private static Ammo createIfNotExist(Long militaryUnitAmmoId, List<Ammo> ammunition) {
        Ammo result = null;
        if (!ammunition.isEmpty()) {
            for (Ammo ammo : ammunition) {
                if (militaryUnitAmmoId.equals(ammo.getAmmoType().getAmmoId())) {
                    result = ammo;
                }
            }
            if (result == null) {
                Ammo newAmmo = new Ammo();
                ammunition.add(newAmmo);
                result = newAmmo;
            }
        } else {
            Ammo newAmmo = new Ammo();
            ammunition.add(newAmmo);
            result = newAmmo;
        }
        return result;
    }

    private static String SELECT_COMMAND() {
        return "select MUAM.ammo_id, MUAM.military_unit_id, MUAM.amount, AM.id, AM.type from military_unit_ammo as MUAM "
                + "inner join ammo as AM on MUAM.ammo_id = AM.id "
                + "where military_unit_id = (select id from military_units where name = ?);";
    }

}
