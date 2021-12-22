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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.time.LocalDate;
import java.util.Arrays;

public abstract class BeforeAfter {

    private static final Logger LOGGER = LogManager.getLogger(BeforeAfter.class);

    protected final Army army;
    protected final Army army1;
    private final IArmyService armyService;

    public BeforeAfter() {
        this.armyService = new ArmyServiceImpl();

        this.army = new Army();
        army.setCountry("NEW_ARMY_ONE");

        MilitaryUnit militaryUnit1 = new MilitaryUnit();
        MilitaryUnit militaryUnit2 = new MilitaryUnit();
        militaryUnit1.setName("NEW_ONE_UNIT1");
        militaryUnit2.setName("NEW_ONE_UNIT2");
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
        Soldier soldier11 = new Soldier("A_NEW_ONE", "A_NEW_ONE", LocalDate.of(2001, 1, 1), Soldier.Rank.MAJOR_GENERAL, serviceTerm11);
        ServiceTerm serviceTerm12 = new ServiceTerm(LocalDate.of(2002, 2, 2), 5);
        Soldier soldier12 = new Soldier("B_NEW_ONE", "B_NEW_ONE", LocalDate.of(2002, 2, 2), Soldier.Rank.CAPTAIN, serviceTerm12);
        ServiceTerm serviceTerm21 = new ServiceTerm(LocalDate.of(2003, 3, 3), 5);
        Soldier soldier21 = new Soldier("C_NEW_ONE", "C_NEW_ONE", LocalDate.of(2003, 3, 3), Soldier.Rank.COLONEL, serviceTerm21);
        ServiceTerm serviceTerm22 = new ServiceTerm(LocalDate.of(2004, 4, 4), 5);
        Soldier soldier22 = new Soldier("D_NEW_ONE", "D_NEW_ONE", LocalDate.of(2004, 4, 4), Soldier.Rank.LANCE_SERGEANT, serviceTerm22);
        militaryUnit1.setSoldiers(Arrays.asList(soldier11, soldier12));
        militaryUnit2.setSoldiers(Arrays.asList(soldier21, soldier22));

        this.army.setMilitaryUnits(Arrays.asList(militaryUnit1, militaryUnit2));


        this.army1 = new Army();
        this.army1.setCountry("NEW_ARMY_TWO");

        MilitaryUnit militaryUnit11 = new MilitaryUnit();
        MilitaryUnit militaryUnit22 = new MilitaryUnit();
        militaryUnit11.setName("NEW_TWO_UNIT1");
        militaryUnit22.setName("NEW_TWO_UNIT2");
        militaryUnit11.setLocation(new Location(53.111111, 27.111111));
        militaryUnit22.setLocation(new Location(53.222222, 27.222222));

        Ammo ammo1111 = new Ammo(Ammo.AmmoType.B_5_45x39, 11);
        Ammo ammo1212 = new Ammo(Ammo.AmmoType.B_5_56x45, 12);
        Ammo ammo2121 = new Ammo(Ammo.AmmoType.B_6x51, 21);
        Ammo ammo2222 = new Ammo(Ammo.AmmoType.B_7_62x39, 22);
        militaryUnit11.setAmmunition(Arrays.asList(ammo1111, ammo1212));
        militaryUnit22.setAmmunition(Arrays.asList(ammo2121, ammo2222));

        Weapon weapon1111 = new Weapon(Weapon.WeaponType.A_9A91, 11);
        Weapon weapon1212 = new Weapon(Weapon.WeaponType.A_A_91M, 12);
        Weapon weapon2121 = new Weapon(Weapon.WeaponType.A_AK_74M, 21);
        Weapon weapon2222 = new Weapon(Weapon.WeaponType.A_AK_47, 22);
        militaryUnit11.setWeapons(Arrays.asList(weapon1111, weapon1212));
        militaryUnit22.setWeapons(Arrays.asList(weapon2121, weapon2222));

        Tank tank1111 = new Tank(Tank.TankType.BMD_4, 11);
        Tank tank1212 = new Tank(Tank.TankType.T_2C25, 12);
        Tank tank2121 = new Tank(Tank.TankType.T_14, 21);
        Tank tank2222 = new Tank(Tank.TankType.T_90, 22);
        militaryUnit11.setTanks(Arrays.asList(tank1111, tank1212));
        militaryUnit22.setTanks(Arrays.asList(tank2121, tank2222));

        Plane plane1111 = new Plane(Plane.PlaneType.AN_124, 11);
        Plane plane1212 = new Plane(Plane.PlaneType.B_52, 12);
        Plane plane2121 = new Plane(Plane.PlaneType.CY_25, 21);
        Plane plane2222 = new Plane(Plane.PlaneType.CY_57, 22);
        militaryUnit11.setPlanes(Arrays.asList(plane1111, plane1212));
        militaryUnit22.setPlanes(Arrays.asList(plane2121, plane2222));

        ServiceTerm serviceTerm1111 = new ServiceTerm(LocalDate.of(2001, 1, 1), 5);
        Soldier soldier1111 = new Soldier("A_NEW_TWO", "A_NEW_TWO", LocalDate.of(2001, 1, 1), Soldier.Rank.MAJOR_GENERAL, serviceTerm1111);
        ServiceTerm serviceTerm1212 = new ServiceTerm(LocalDate.of(2002, 2, 2), 5);
        Soldier soldier1212 = new Soldier("B_NEW_TWO", "B_NEW_TWO", LocalDate.of(2002, 2, 2), Soldier.Rank.CAPTAIN, serviceTerm1212);
        ServiceTerm serviceTerm2121 = new ServiceTerm(LocalDate.of(2003, 3, 3), 5);
        Soldier soldier2121 = new Soldier("C_NEW_TWO", "C_NEW_TWO", LocalDate.of(2003, 3, 3), Soldier.Rank.COLONEL, serviceTerm2121);
        ServiceTerm serviceTerm2222 = new ServiceTerm(LocalDate.of(2004, 4, 4), 5);
        Soldier soldier2222 = new Soldier("D_NEW_TWO", "D_NEW_TWO", LocalDate.of(2004, 4, 4), Soldier.Rank.LANCE_SERGEANT, serviceTerm2222);
        militaryUnit11.setSoldiers(Arrays.asList(soldier1111, soldier1212));
        militaryUnit22.setSoldiers(Arrays.asList(soldier2121, soldier2222));

        this.army1.setMilitaryUnits(Arrays.asList(militaryUnit11, militaryUnit22));
    }

    @BeforeSuite
    public void armiesCountBeforeSuite() {
        Integer count = this.armyService.getCount();
        LOGGER.info("\nBefore suite. Armies count = " + count + "\n");
    }

    @AfterSuite
    public void armiesCountAfterSuite() {
        Integer count = this.armyService.getCount();
        LOGGER.info("\nAfter suite. Armies count = " + count);
    }

    @BeforeTest
    public void armiesCountBeforeTest() {
        Integer count = this.armyService.getCount();
        LOGGER.info("\n\tBefore test. Armies count = " + count);
    }

    @AfterTest
    public void armiesCountAfterTest() {
        Integer count = this.armyService.getCount();
        LOGGER.info("\n\tAfter test. Armies count = " + count + "\n");
    }

    @BeforeGroups("checkCount")
    public void armiesCountBeforeGroup() {
        Integer count = this.armyService.getCount();
        LOGGER.info("\n\tBefore groups. Armies count = " + count);
    }

    @AfterGroups("checkCount")
    public void armiesCountAfterGroup() {
        Integer count = this.armyService.getCount();
        LOGGER.info("\n\tAfter groups. Armies count = " + count + "\n");
    }

    @BeforeClass
    public void createAllDatabase() {
        this.armyService.create(this.army);
        this.armyService.create(this.army1);

        Integer count = this.armyService.getCount();
        LOGGER.info("\n\t\tBefore class. Armies count = " + count + "\n");
    }

    @AfterClass
    public void deleteAllDatabase() {
        this.armyService.delete(this.army);
        this.armyService.delete(this.army1);

        Integer count = this.armyService.getCount();
        LOGGER.info("\n\t\tAfter class. Armies count = " + count);
    }

    @BeforeMethod
    public void armiesCountBeforeMethod() {
        Integer count = this.armyService.getCount();
        LOGGER.info("\n\t\t\tBefore method. Armies count = " + count);
    }

    @AfterMethod
    public void armiesCountAfterMethod() {
        Integer count = this.armyService.getCount();
        LOGGER.info("\n\t\t\tAfter method. Armies count = " + count + "\n");
    }

}
