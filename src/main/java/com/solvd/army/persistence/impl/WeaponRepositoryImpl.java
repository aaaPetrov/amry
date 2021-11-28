package com.solvd.army.persistence.impl;

import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.domain.resources.Weapon;
import com.solvd.army.persistence.ConnectionPool;
import com.solvd.army.persistence.IWeaponRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeaponRepositoryImpl implements IWeaponRepository {

    private final static String SQL_COMMAND = SELECT_COMMAND();

    @Override
    public List<Weapon> select(String militaryUnitName) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        List<Weapon> weapons = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_COMMAND)) {
            preparedStatement.setString(1, militaryUnitName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Weapon newWeapon = new Weapon();
                Weapon.WeaponType weaponType = weaponTypeByString(resultSet.getString("type"));
                newWeapon.setWeaponType(weaponType);
                newWeapon.setAmount(resultSet.getInt("amount"));
                weapons.add(newWeapon);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
        return weapons;
    }

    @Override
    public void update(List<Weapon> weapon, Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommandSelect = "select weapon_id from military_unit_weapon where military_unit_id = ?;";
        String sqlCommandUpdate = "update military_unit_weapon set weapon_id = ?, amount = ? where military_unit_id = ? and weapon_id = ?;";
        try(PreparedStatement preparedStatementSelect = connection.prepareStatement(sqlCommandSelect);
            PreparedStatement preparedStatementUpdate = connection.prepareStatement(sqlCommandUpdate)) {

            preparedStatementSelect.setLong(1,militaryUnitId);
            ResultSet resultSet = preparedStatementSelect.executeQuery();

            int i = 0;
            while(resultSet.next()) {
                preparedStatementUpdate.setLong(1, weapon.get(i).getWeaponType().getWeaponId());
                preparedStatementUpdate.setInt(2, weapon.get(i).getAmount());
                preparedStatementUpdate.setLong(3, militaryUnitId);
                preparedStatementUpdate.setLong(4, resultSet.getLong("weapon_id"));
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
    public void insert(Weapon weapon, Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "insert into military_unit_weapon(weapon_id, military_unit_id, amount) value(?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setLong(1, weapon.getWeaponType().getWeaponId());
            preparedStatement.setLong(2, militaryUnitId);
            preparedStatement.setLong(3, weapon.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public static List<Weapon> fillWeapons(ResultSet resultSet, List<MilitaryUnit> militaryUnits) {
        List<Weapon> weapons = null;
        try {
            Long militaryUnitWeaponId = resultSet.getLong("military_unit_weapon_id");
            Long militaryUnitWeaponMilitaryUnitId = resultSet.getLong("military_unit_weapon_military_unit_id");
            for (MilitaryUnit militaryUnit : militaryUnits) {
                if (militaryUnit.getId() == militaryUnitWeaponMilitaryUnitId) {
                    weapons = militaryUnit.getWeapons();
                    Weapon weapon = createIfNotExist(militaryUnitWeaponId, weapons);
                    Weapon.WeaponType weaponType = weaponTypeByString(resultSet.getString("weapon_type"));
                    weapon.setWeaponType(weaponType);
                    weapon.setAmount(resultSet.getInt("amount"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weapons;
    }

    private static Weapon createIfNotExist(Long militaryUnitWeaponId, List<Weapon> weapons) {
        Weapon result = null;
        if (!weapons.isEmpty()) {
            for (Weapon weapon : weapons) {
                if (militaryUnitWeaponId.equals(weapon.getWeaponType().getWeaponId())) {
                    result = weapon;
                }
            }
            if (result == null) {
                Weapon newWeapon = new Weapon();
                weapons.add(newWeapon);
                result = newWeapon;
            }
        } else {
            Weapon newWeapon = new Weapon();
            weapons.add(newWeapon);
            result = newWeapon;
        }
        return result;
    }

    private static Weapon.WeaponType weaponTypeByString(String typeInString) {
        Weapon.WeaponType weaponType = null;
        switch (typeInString) {
            case "P.APS":
                weaponType = Weapon.WeaponType.P_APS;
                break;
            case "P.SPS":
                weaponType = Weapon.WeaponType.P_SPS;
                break;
            case "P.MR-444":
                weaponType = Weapon.WeaponType.P_MR_444;
                break;
            case "P.MP-448":
                weaponType = Weapon.WeaponType.P_MP_448;
                break;
            case "P.P-96":
                weaponType = Weapon.WeaponType.P_P_96;
                break;
            case "A.AKM":
                weaponType = Weapon.WeaponType.A_AKM;
                break;
            case "A.AK-47":
                weaponType = Weapon.WeaponType.A_AK_47;
                break;
            case "A.AK-74M":
                weaponType = Weapon.WeaponType.A_AK_74M;
                break;
            case "A.9A91":
                weaponType = Weapon.WeaponType.A_9A91;
                break;
            case "A.A-91M":
                weaponType = Weapon.WeaponType.A_A_91M;
                break;
            case "SR.SVD":
                weaponType = Weapon.WeaponType.SR_SVD;
                break;
            case "SR.SVY_AS":
                weaponType = Weapon.WeaponType.SR_SVY_AS;
                break;
            case "SR.SV-98":
                weaponType = Weapon.WeaponType.SR_SV_98;
                break;
            case "SR.OSV-96":
                weaponType = Weapon.WeaponType.SR_OSV_96;
                break;
            case "SR.ASVK":
                weaponType = Weapon.WeaponType.SR_ASVK;
                break;
            case "MG.RPK":
                weaponType = Weapon.WeaponType.MG_RPK;
                break;
            case "MG.PK":
                weaponType = Weapon.WeaponType.MG_PK;
                break;
            case "MG.PKM":
                weaponType = Weapon.WeaponType.MG_PKM;
                break;
            case "MG.PKMT":
                weaponType = Weapon.WeaponType.MG_PKMT;
                break;
            case "MG.PKMB":
                weaponType = Weapon.WeaponType.MG_PKMB;
                break;
            case "MG.KPV":
                weaponType = Weapon.WeaponType.MG_KPV;
                break;
            case "GL.GP-25":
                weaponType = Weapon.WeaponType.GL_GP_25;
                break;
            case "GL.6G30":
                weaponType = Weapon.WeaponType.GL_6G30;
                break;
            case "GL.GM-94":
                weaponType = Weapon.WeaponType.GL_GM_94;
                break;
            case "GL.RMG":
                weaponType = Weapon.WeaponType.GL_RMG;
                break;
            case "GL.RPG26":
                weaponType = Weapon.WeaponType.GL_RPG_26;
                break;
            case "GL.RPG32":
                weaponType = Weapon.WeaponType.GL_RPG_32;
                break;
        }
        return weaponType;
    }

    private static String SELECT_COMMAND() {
        return "select MUW.weapon_id, MUW.military_unit_id, MUW.amount, W.id, W.type from military_unit_weapon as MUW "
                + "inner join weapons as W on MUW.weapon_id = W.id "
                + "where military_unit_id = (select id from military_units where name = ?);";
    }

}
