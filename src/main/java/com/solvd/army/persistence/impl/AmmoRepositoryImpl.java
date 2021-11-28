package com.solvd.army.persistence.impl;

import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.persistence.ConnectionPool;
import com.solvd.army.persistence.IAmmoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AmmoRepositoryImpl implements IAmmoRepository {

    private final static String SQL_COMMAND = SELECT_COMMAND();

    @Override
    public List<Ammo> select(String militaryUnitName) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        List<Ammo> ammunition = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_COMMAND)) {
            preparedStatement.setString(1, militaryUnitName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ammo newAmmo = new Ammo();
                Ammo.AmmoType ammoType = ammoTypeByString(resultSet.getString("type"));
                newAmmo.setAmmoType(ammoType);
                newAmmo.setAmount(resultSet.getInt("amount"));
                ammunition.add(newAmmo);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
        return ammunition;
    }

    @Override
    public void update(List<Ammo> ammo, Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommandSelect = "select ammo_id from military_unit_ammo where military_unit_id = ?;";
        String sqlCommandUpdate = "update military_unit_ammo set ammo_id = ?, amount = ? where military_unit_id = ? and ammo_id = ?;";
        try (PreparedStatement preparedStatementSelect = connection.prepareStatement(sqlCommandSelect);
             PreparedStatement preparedStatementUpdate = connection.prepareStatement(sqlCommandUpdate)) {

            preparedStatementSelect.setLong(1, militaryUnitId);
            ResultSet resultSet = preparedStatementSelect.executeQuery();

            int i = 0;
            while (resultSet.next()) {
                preparedStatementUpdate.setLong(1, ammo.get(i).getAmmoType().getAmmoId());
                preparedStatementUpdate.setInt(2, ammo.get(i).getAmount());
                preparedStatementUpdate.setLong(3, militaryUnitId);
                preparedStatementUpdate.setLong(4, resultSet.getLong("ammo_id"));
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
    public void insert(Ammo ammo, Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "insert into military_unit_ammo(ammo_id, military_unit_id, amount) value(?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setLong(1, ammo.getAmmoType().getAmmoId());
            preparedStatement.setLong(2, militaryUnitId);
            preparedStatement.setInt(3, ammo.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
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
                    Ammo.AmmoType ammoType = ammoTypeByString(resultSet.getString("ammo_type"));
                    ammo.setAmmoType(ammoType);
                    ammo.setAmount(resultSet.getInt("amount"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    private static Ammo.AmmoType ammoTypeByString(String typeInString) {
        Ammo.AmmoType ammoType = null;
        switch (typeInString) {
            case "5.45х39mm":
                ammoType = Ammo.AmmoType.B_5_45x39;
                break;
            case "5.56х45mm":
                ammoType = Ammo.AmmoType.B_5_56x45;
                break;
            case "6х51mm":
                ammoType = Ammo.AmmoType.B_6x51;
                break;
            case "7.62х39mm":
                ammoType = Ammo.AmmoType.B_7_62x39;
                break;
            case "7.62х54mm R":
                ammoType = Ammo.AmmoType.B_7_62x54_R;
                break;
            case "7.92х57mm":
                ammoType = Ammo.AmmoType.B_7_92x57mm;
                break;
            case "8.6x70mm":
                ammoType = Ammo.AmmoType.B_8_6x70;
                break;
        }
        return ammoType;
    }

    private static String SELECT_COMMAND() {
        return "select MUAM.ammo_id, MUAM.military_unit_id, MUAM.amount, AM.id, AM.type from military_unit_ammo as MUAM "
                + "inner join ammo as AM on MUAM.ammo_id = AM.id "
                + "where military_unit_id = (select id from military_units where name = ?);";
    }

}
