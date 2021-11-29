package com.solvd.army;


import com.solvd.army.domain.Army;
import com.solvd.army.domain.Location;
import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.domain.resources.Plane;
import com.solvd.army.domain.resources.Tank;
import com.solvd.army.domain.resources.Weapon;
import com.solvd.army.domain.soldier.ServiceTerm;
import com.solvd.army.domain.soldier.Soldier;
import com.solvd.army.service.IArmyService;
import com.solvd.army.service.impl.ArmyServiceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        IArmyService armyService = new ArmyServiceImpl();

        System.out.println("\n\n========================SELECT ALL AND CREATE OBJECTS=================\n\n");
        //SELECT ALL
        List<Army> armies = armyService.getAll();
        System.out.println("Objects was created:\n");
        for (Army army : armies) {
            System.out.println(army.getCountry());
            for (MilitaryUnit militaryUnit : army.getMilitaryUnits()) {
                System.out.println("id: " + militaryUnit.getId());
                System.out.println("name: " + militaryUnit.getName());
                System.out.println("location: " + militaryUnit.getLocation().getLongitude() + ", " + militaryUnit.getLocation().getLatitude());
            }
            System.out.println("-------------------\n");
        }

        System.out.println("\n\n========================INSERT ARMY=================\n\n");
        Army army = new Army();
        army.setCountry("INSERTED");

        MilitaryUnit militaryUnit1 = new MilitaryUnit();
        MilitaryUnit militaryUnit2 = new MilitaryUnit();
        militaryUnit1.setName("INSERTED_UNIT1");
        militaryUnit2.setName("INSERTED_UNIT2");
        militaryUnit1.setLocation(new Location(53.111111, 27.111111));
        militaryUnit2.setLocation(new Location(53.222222, 27.222222));

        Ammo ammo11 = new Ammo(Ammo.AmmoType.B_5_45x39, 11);
        Ammo ammo12 = new Ammo(Ammo.AmmoType.B_5_56x45, 12);
        Ammo ammo21 = new Ammo(Ammo.AmmoType.B_6x51, 21);
        Ammo ammo22 = new Ammo(Ammo.AmmoType.B_7_62x39, 22);
        militaryUnit1.setAmmunition(Arrays.asList(ammo11, ammo12));
        militaryUnit2.setAmmunition(Arrays.asList(ammo21, ammo22));

        Weapon weapon11 = new Weapon(Weapon.WeaponType.A_9A91, 11);
        Weapon weapon12 = new Weapon(Weapon.WeaponType.A_A_91M, 12);
        Weapon weapon21 = new Weapon(Weapon.WeaponType.A_AK_74M, 21);
        Weapon weapon22 = new Weapon(Weapon.WeaponType.A_AK_47, 22);
        militaryUnit1.setWeapons(Arrays.asList(weapon11, weapon12));
        militaryUnit2.setWeapons(Arrays.asList(weapon21, weapon22));

        Tank tank11 = new Tank(Tank.TankType.BMD_4, 11);
        Tank tank12 = new Tank(Tank.TankType.T_2C25, 12);
        Tank tank21 = new Tank(Tank.TankType.T_14, 21);
        Tank tank22 = new Tank(Tank.TankType.T_90, 22);
        militaryUnit1.setTanks(Arrays.asList(tank11, tank12));
        militaryUnit2.setTanks(Arrays.asList(tank21, tank22));

        Plane plane11 = new Plane(Plane.PlaneType.AN_124, 11);
        Plane plane12 = new Plane(Plane.PlaneType.B_52, 12);
        Plane plane21 = new Plane(Plane.PlaneType.CY_25, 21);
        Plane plane22 = new Plane(Plane.PlaneType.CY_57, 22);
        militaryUnit1.setPlanes(Arrays.asList(plane11, plane12));
        militaryUnit2.setPlanes(Arrays.asList(plane21, plane22));

        ServiceTerm serviceTerm11 = new ServiceTerm(LocalDate.of(2001, 1, 1), 5);
        Soldier soldier11 = new Soldier("A_INSERTED", "A_INSERTED", LocalDate.of(2001, 1, 1), Soldier.Rank.MAJOR_GENERAL, serviceTerm11);
        ServiceTerm serviceTerm12 = new ServiceTerm(LocalDate.of(2002, 2, 2), 5);
        Soldier soldier12 = new Soldier("B_INSERTED", "B_INSERTED", LocalDate.of(2002, 2, 2), Soldier.Rank.CAPTAIN, serviceTerm12);
        ServiceTerm serviceTerm21 = new ServiceTerm(LocalDate.of(2003, 3, 3), 5);
        Soldier soldier21 = new Soldier("C_INSERTED", "C_INSERTED", LocalDate.of(2003, 3, 3), Soldier.Rank.COLONEL, serviceTerm21);
        ServiceTerm serviceTerm22 = new ServiceTerm(LocalDate.of(2004, 4, 4), 5);
        Soldier soldier22 = new Soldier("D_INSERTED", "D_INSERTED", LocalDate.of(2004, 4, 4), Soldier.Rank.LANCE_SERGEANT, serviceTerm22);
        militaryUnit1.setSoldiers(Arrays.asList(soldier11, soldier12));
        militaryUnit2.setSoldiers(Arrays.asList(soldier21, soldier22));

        army.setMilitaryUnits(Arrays.asList(militaryUnit1, militaryUnit2));
        //INSERT
        armyService.create(army);
        System.out.println("Object was inserted:\n");
        System.out.println(army.getCountry());
        for (MilitaryUnit militaryUnit : army.getMilitaryUnits()) {
            System.out.println("id: " + militaryUnit.getId());
            System.out.println("name: " + militaryUnit.getName());
            System.out.println("location: " + militaryUnit.getLocation().getLongitude() + ", " + militaryUnit.getLocation().getLatitude());
        }
        System.out.println("-------------------\n");

        System.out.println("\n\n========================UPDATE ARMY=================\n\n");

        army.setCountry("UPDATED");
        militaryUnit1.setName("UPDATED_UNIT1");
        militaryUnit2.setName("UPDATED_UNIT2");
        militaryUnit1.setLocation(new Location(53.555555, 27.555555));
        militaryUnit2.setLocation(new Location(53.999999, 27.999999));

        ammo11.setAmmoType(Ammo.AmmoType.B_8_6x70);
        ammo11.setAmount(1100);
        ammo12.setAmmoType(Ammo.AmmoType.B_5_45x39);
        ammo12.setAmount(1200);
        ammo21.setAmmoType(Ammo.AmmoType.B_7_62x54_R);
        ammo21.setAmount(2100);
        ammo22.setAmmoType(Ammo.AmmoType.B_8_6x70);
        ammo22.setAmount(2200);
        militaryUnit1.setAmmunition(Arrays.asList(ammo11, ammo12));
        militaryUnit2.setAmmunition(Arrays.asList(ammo21, ammo22));

        weapon11.setWeaponType(Weapon.WeaponType.GL_RPG_32);
        weapon11.setAmount(1100);
        weapon12.setWeaponType(Weapon.WeaponType.GL_RPG_26);
        weapon12.setAmount(1200);
        weapon21.setWeaponType(Weapon.WeaponType.GL_GM_94);
        weapon21.setAmount(2100);
        weapon22.setWeaponType(Weapon.WeaponType.GL_6G30);
        weapon22.setAmount(2200);
        militaryUnit1.setWeapons(Arrays.asList(weapon11, weapon12));
        militaryUnit2.setWeapons(Arrays.asList(weapon21, weapon22));

        tank11.setTankType(Tank.TankType.BLACK_EAGLE);
        tank11.setAmount(1100);
        tank12.setTankType(Tank.TankType.TOC_1);
        tank12.setAmount(1200);
        tank21.setTankType(Tank.TankType.TOC_1A);
        tank21.setAmount(2100);
        tank22.setTankType(Tank.TankType.T_95);
        tank22.setAmount(2200);
        militaryUnit1.setTanks(Arrays.asList(tank11, tank12));
        militaryUnit2.setTanks(Arrays.asList(tank21, tank22));

        plane11.setPlaneType(Plane.PlaneType.TY_22M3);
        plane11.setAmount(1100);
        plane12.setPlaneType(Plane.PlaneType.MIG_35);
        plane12.setAmount(1200);
        plane21.setPlaneType(Plane.PlaneType.CY_47);
        plane21.setAmount(2100);
        plane22.setPlaneType(Plane.PlaneType.AN_124);
        plane22.setAmount(2200);
        militaryUnit1.setPlanes(Arrays.asList(plane11, plane12));
        militaryUnit2.setPlanes(Arrays.asList(plane21, plane22));

        serviceTerm11 = new ServiceTerm(LocalDate.of(1991, 1, 1), 5);
        Long soldierId11 = soldier11.getId();
        soldier11 = new Soldier("A_UPDATED", "A_UPDATED", LocalDate.of(1991, 1, 1), Soldier.Rank.MAJOR_GENERAL, serviceTerm11);
        soldier11.setId(soldierId11);

        serviceTerm12 = new ServiceTerm(LocalDate.of(1992, 2, 2), 5);
        Long soldierId12 = soldier12.getId();
        soldier12 = new Soldier("B_UPDATED", "B_UPDATED", LocalDate.of(1992, 2, 2), Soldier.Rank.CAPTAIN, serviceTerm12);
        soldier12.setId(soldierId12);

        serviceTerm21 = new ServiceTerm(LocalDate.of(1993, 3, 3), 5);
        Long soldierId21 = soldier21.getId();
        soldier21 = new Soldier("C_UPDATED", "C_UPDATED", LocalDate.of(1993, 3, 3), Soldier.Rank.COLONEL, serviceTerm21);
        soldier21.setId(soldierId21);

        serviceTerm22 = new ServiceTerm(LocalDate.of(1994, 4, 4), 5);
        Long soldierId22 = soldier22.getId();
        soldier22 = new Soldier("D_UPDATED", "D_UPDATED", LocalDate.of(1994, 4, 4), Soldier.Rank.LANCE_SERGEANT, serviceTerm22);
        soldier22.setId(soldierId22);
        militaryUnit1.setSoldiers(Arrays.asList(soldier11, soldier12));
        militaryUnit2.setSoldiers(Arrays.asList(soldier21, soldier22));

        army.setMilitaryUnits(Arrays.asList(militaryUnit1, militaryUnit2));
        //UPDATE
        armyService.update(army);
        System.out.println("Object was updated:\n");
        System.out.println(army.getCountry());
        for (MilitaryUnit militaryUnit : army.getMilitaryUnits()) {
            System.out.println("id: " + militaryUnit.getId());
            System.out.println("name: " + militaryUnit.getName());
            System.out.println("location: " + militaryUnit.getLocation().getLongitude() + ", " + militaryUnit.getLocation().getLatitude());
        }
        System.out.println("-------------------\n");

        System.out.println("\n\n========================SELECT UPDATED ARMY AND CREATE OBJECT=================\n\n");

        Army selectedArmy = armyService.get(army.getCountry());
        System.out.println("New object was created from select(country) after update:\n");
        System.out.println(army.getCountry());
        for (MilitaryUnit militaryUnit : army.getMilitaryUnits()) {
            System.out.println("id: " + militaryUnit.getId());
            System.out.println("name: " + militaryUnit.getName());
            System.out.println("location: " + militaryUnit.getLocation().getLongitude() + ", " + militaryUnit.getLocation().getLatitude());
        }
        System.out.println("-------------------\n");

        System.out.println("\n\n========================DELETE UPDATED ARMY=================\n\n");
        if (army.equals(selectedArmy)) {
            System.out.println("object \"army\" was inserted, then updated.");
            System.out.println("Then data was selected by select(String country) in object \"selectedArmy\".");
            System.out.println("army  and selectedArmy are equals.");
            armyService.delete(army);
        }
    }

}
