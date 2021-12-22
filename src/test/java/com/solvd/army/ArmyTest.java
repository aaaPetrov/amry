package com.solvd.army;

import com.solvd.army.domain.Army;
import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.exception.NoDataException;
import com.solvd.army.service.IArmyService;
import com.solvd.army.service.IMilitaryUnitService;
import com.solvd.army.service.ISoldierService;
import com.solvd.army.service.impl.ArmyServiceImpl;
import com.solvd.army.service.impl.MilitaryUnitServiceImpl;
import com.solvd.army.service.impl.SoldierServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class ArmyTest extends BeforeAfter {

    private List<Army> armies;

    @Test(priority = 1)
    public void checkGetArmiesTest() {
        IArmyService armyService = new ArmyServiceImpl();
        System.out.println("\t\t\t\tcheckGetArmiesTest");
        List<Army> armies = armyService.getArmyCountries();
        this.armies = armies;

        Assert.assertNotNull(armies, "armyCountries is null.");
    }

    @Test(priority = 2,  expectedExceptions = { NullPointerException.class, NoDataException.class })
    public void checkDeleteArmiesByCountry() {
        System.out.println("\t\t\t\tcheckDeleteArmiesByCountry");
        IArmyService armyService = new ArmyServiceImpl();
        for(Army army : this.armies) {
            Army selectedArmy = armyService.get(army.getCountry());
            armyService.delete(army);
            Army armyAfterDelete = armyService.get(army.getCountry());

            Assert.assertNotNull(selectedArmy, "selectedArmy is null.");
            Assert.assertNull(armyAfterDelete, "army wasn't deleted.");
        }
    }

    @Test(priority = 3)
    public void checkInsertArmies() {
        IArmyService armyService = new ArmyServiceImpl();
        System.out.println("\t\t\t\tcheckInsertArmies");
        int i = 1;
        for(Army army : this.armies) {
            army.setCountry("CHECK_INSERT" + i);
            armyService.create(army);
            Army insertedArmy = armyService.get(army.getCountry());

            Assert.assertNotNull(army.getId(), "army id is null.");
            Assert.assertNotNull(insertedArmy, "army wasn't inserted.");
            i++;
        }
    }

    @Test(priority = 4)
    public void checkUpdateArmies() {
        IArmyService armyService = new ArmyServiceImpl();
        System.out.println("\t\t\t\tcheckUpdateArmies");
        int i = 1;
        for(Army army : this.armies) {
            army.setCountry("CHECK_UPDATE" + i);
            armyService.update(army);
            Army updatedArmy = armyService.get(army.getCountry());
            armyService.delete(army);

            Assert.assertNotNull(updatedArmy, "Army wasn't updated.");
            i++;
        }

    }

    @Test(groups = {"checkCount"})
    public void checkArmiesCountTest() {
        IArmyService armyService = new ArmyServiceImpl();
        System.out.println("\t\t\t\tcheckArmiesCountTest");
        List<Army> armies = armyService.getAll();
        Integer  armiesCountActual = armies.size();
        Integer armiesCount = armyService.getCount();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(armies, "armies(List<Army>) is null.");
        softAssert.assertEquals(armiesCountActual, armiesCount, "The count of armies isn't equal.");
        softAssert.assertAll();
    }

    @Test(groups = {"checkCount"})
    void checkMilitaryUnitCountTest() {
        IArmyService armyService = new ArmyServiceImpl();
        IMilitaryUnitService militaryUnitService = new MilitaryUnitServiceImpl();
        System.out.println("\t\t\t\tcheckMilitaryUnitCountTest");
        List<Army> armies = armyService.getAll();
        Integer militaryUnitsCountActual = 0;

        for(Army army : armies) {
            militaryUnitsCountActual += army.getMilitaryUnits().size();
        }

        Integer militaryUnitsCount = militaryUnitService.getCount();
        Assert.assertEquals((militaryUnitsCountActual), militaryUnitsCount, "The count of military units isn't equal.");
    }

    @Test(groups = {"checkCount"})
    public void checkSoldiersCountTest() {
        IArmyService armyService = new ArmyServiceImpl();
        ISoldierService soldierService = new SoldierServiceImpl();
        System.out.println("\t\t\t\tcheckSoldiersCountTest");
        List<Army> armies = armyService.getAll();
        Integer soldiersCountActual = 0;
        for(Army army : armies) {
            for(MilitaryUnit militaryUnit : army.getMilitaryUnits()) {
                soldiersCountActual += militaryUnit.getSoldiers().size();
            }
        }

        Integer soldiersCount = soldierService.getCount();
        Assert.assertEquals(soldiersCountActual, soldiersCount, "The count of soldiers isn't equal.");
    }

}
