package com.solvd.army.persistence.impl;

import com.solvd.army.domain.Army;
import com.solvd.army.domain.Location;
import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.exception.ProcessingException;
import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.domain.resources.Plane;
import com.solvd.army.domain.resources.Tank;
import com.solvd.army.domain.resources.Weapon;
import com.solvd.army.domain.soldier.Soldier;
import com.solvd.army.persistence.ConnectionPool;
import com.solvd.army.persistence.IMilitaryUnitRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MilitaryUnitRepositoryImpl implements IMilitaryUnitRepository {

    @Override
    public List<MilitaryUnit> get(String country) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "select MU.id as military_unit_id, MU.army_id, MU.name, MU.longitude, MU.latitude from military_units as MU where army_id = "
                + "(select A.id from armies as A where country = ?);";
        List<MilitaryUnit> militaryUnits = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, country);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MilitaryUnit newMilitaryUnit = new MilitaryUnit();
                newMilitaryUnit.setId(resultSet.getLong("military_unit_id"));
                newMilitaryUnit.setName(resultSet.getString("name"));
                Location location = new Location(resultSet.getDouble("longitude"), resultSet.getDouble("latitude"));
                newMilitaryUnit.setLocation(location);
                militaryUnits.add(newMilitaryUnit);
            }
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
        return militaryUnits;
    }

    @Override
    public void update(MilitaryUnit militaryUnit, Long armyId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "update military_units set name = ?, longitude = ?, latitude = ? where army_id = ? and id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, militaryUnit.getName());
            preparedStatement.setDouble(2, militaryUnit.getLocation().getLongitude());
            preparedStatement.setDouble(3, militaryUnit.getLocation().getLatitude());
            preparedStatement.setLong(4, armyId);
            preparedStatement.setLong(5, militaryUnit.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void create(MilitaryUnit militaryUnit, Long armyId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "insert into military_units(army_id, name, longitude, latitude) value(?,?,?,?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, armyId);
            preparedStatement.setString(2, militaryUnit.getName());
            preparedStatement.setDouble(3, militaryUnit.getLocation().getLongitude());
            preparedStatement.setDouble(4, militaryUnit.getLocation().getLatitude());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                militaryUnit.setId(resultSet.getLong(1));
            }
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public static List<MilitaryUnit> fillMilitaryUnits(ResultSet resultSet, List<Army> armies) {
        List<MilitaryUnit> militaryUnits = null;
        try {
            Long militaryUnitId = resultSet.getLong("military_unit_id");
            Long militaryUnitArmyId = resultSet.getLong("military_unit_army_id");
            for (Army army : armies) {
                if (army.getId() == militaryUnitArmyId) {
                    militaryUnits = army.getMilitaryUnits();
                    MilitaryUnit militaryUnit = createIfNotExist(militaryUnitId, militaryUnits);
                    militaryUnit.setName(resultSet.getString("name"));
                    Location location = new Location();
                    location.setLatitude(resultSet.getDouble("latitude"));
                    location.setLongitude(resultSet.getDouble("longitude"));
                    militaryUnit.setLocation(location);

                    List<Ammo> ammo = AmmoRepositoryImpl.fillAmmo(resultSet, militaryUnits);
                    militaryUnit.setAmmunition(ammo);

                    List<Plane> planes = PlaneRepositoryImpl.fillPlanes(resultSet, militaryUnits);
                    militaryUnit.setPlanes(planes);

                    List<Tank> tanks = TankRepositoryImpl.fillTanks(resultSet, militaryUnits);
                    militaryUnit.setTanks(tanks);

                    List<Weapon> weapons = WeaponRepositoryImpl.fillWeapons(resultSet, militaryUnits);
                    militaryUnit.setWeapons(weapons);

                    List<Soldier> soldiers = SoldierRepositoryImpl.fillSoldiers(resultSet, militaryUnits);
                    militaryUnit.setSoldiers(soldiers);
                }
            }
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        }
        return militaryUnits;
    }

    private static MilitaryUnit createIfNotExist(Long militaryUnitId, List<MilitaryUnit> militaryUnits) {
        MilitaryUnit result = null;
        if (!militaryUnits.isEmpty()) {
            for (MilitaryUnit militaryUnit : militaryUnits) {
                if (militaryUnitId.equals(militaryUnit.getId())) {
                    result = militaryUnit;
                }
            }
            if (result == null) {
                MilitaryUnit newMilitaryUnit = new MilitaryUnit();
                newMilitaryUnit.setId(militaryUnitId);
                militaryUnits.add(newMilitaryUnit);
                result = newMilitaryUnit;
            }
        } else {
            MilitaryUnit newMilitaryUnit = new MilitaryUnit();
            newMilitaryUnit.setId(militaryUnitId);
            militaryUnits.add(newMilitaryUnit);
            result = newMilitaryUnit;
        }
        return result;
    }

}
