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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class ArmyTest extends BeforeAfter {

    private static final Logger LOGGER = LogManager.getLogger(ArmyTest.class);

    private List<Army> armies;
    private final IArmyService armyService;
    private final IMilitaryUnitService militaryUnitService;
    private final ISoldierService soldierService;

    public ArmyTest() {
        this.armyService = new ArmyServiceImpl();
        this.militaryUnitService = new MilitaryUnitServiceImpl();
        this.soldierService = new SoldierServiceImpl();
    }

    @Test(priority = 1)
    public void checkGetArmiesTest() {

        LOGGER.info("\n\t\t\t\tcheckGetArmiesTest");

        List<Army> insertedArmies = Arrays.asList(this.army, this.army1);
        List<Army> armies = this.armyService.getArmyCountries();
        this.armies = armies;

        Assert.assertNotNull(armies, "armyCountries is null.");
        Assert.assertEquals(armies.size(), insertedArmies.size(), "Armies size is not equal.");
        for(int i = 0; i < armies.size(); i++) {
            Assert.assertEquals(armies.get(i).getCountry(), insertedArmies.get(i).getCountry(), "Selected army is not equal with inserted army.");
        }
    }

    @Test(priority = 2,  expectedExceptions = { NullPointerException.class, NoDataException.class })
    public void checkDeleteArmiesByCountry() {
        LOGGER.info("\n\t\t\t\tcheckDeleteArmiesByCountry");
        for(Army army : this.armies) {
            Army selectedArmy = this.armyService.get(army.getCountry());
            this.armyService.delete(army);
            Army armyAfterDelete = this.armyService.get(army.getCountry());

            Assert.assertNotNull(selectedArmy, "selectedArmy is null.");
            Assert.assertNull(armyAfterDelete, "army wasn't deleted.");
        }
    }

    @Test(priority = 3)
    public void checkInsertArmies() {
        LOGGER.info("\n\t\t\t\tcheckInsertArmies");
        int i = 1;
        for(Army army : this.armies) {
            army.setCountry("CHECK_INSERT" + i);
            this.armyService.create(army);
            Army insertedArmy = this.armyService.get(army.getCountry());

            Assert.assertNotNull(army.getId(), "army id is null.");
            Assert.assertNotNull(insertedArmy, "army wasn't inserted.");
            i++;
        }
    }

    @Test(priority = 4)
    public void checkUpdateArmies() {
        LOGGER.info("\n\t\t\t\tcheckUpdateArmies");
        int i = 1;
        for(Army army : this.armies) {
            army.setCountry("CHECK_UPDATE" + i);
            this.armyService.update(army);
            Army updatedArmy = this.armyService.get(army.getCountry());
            this.armyService.delete(army);

            Assert.assertNotNull(updatedArmy, "Army wasn't updated.");
            i++;
        }

    }

    @Test(groups = {"checkCount"})
    public void checkArmiesCountTest() {
        LOGGER.info("\n\t\t\t\tcheckArmiesCountTest");
        List<Army> armies = this.armyService.getAll();
        Integer  armiesCountActual = armies.size();
        Integer armiesCount = this.armyService.getCount();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(armies, "armies(List<Army>) is null.");
        softAssert.assertEquals(armiesCountActual, armiesCount, "The count of armies isn't equal.");
        softAssert.assertAll();
    }

    @Test(groups = {"checkCount"})
    void checkMilitaryUnitCountTest() {
        LOGGER.info("\n\t\t\t\tcheckMilitaryUnitCountTest");
        List<Army> armies = this.armyService.getAll();
        Integer militaryUnitsCountActual = 0;

        for(Army army : armies) {
            militaryUnitsCountActual += army.getMilitaryUnits().size();
        }

        Integer militaryUnitsCount = this.militaryUnitService.getCount();
        Assert.assertEquals((militaryUnitsCountActual), militaryUnitsCount, "The count of military units isn't equal.");
    }

    @Test(groups = {"checkCount"})
    public void checkSoldiersCountTest() {
        LOGGER.info("\n\t\t\t\tcheckSoldiersCountTest");
        List<Army> armies = this.armyService.getAll();
        Integer soldiersCountActual = 0;
        for(Army army : armies) {
            for(MilitaryUnit militaryUnit : army.getMilitaryUnits()) {
                soldiersCountActual += militaryUnit.getSoldiers().size();
            }
        }

        Integer soldiersCount = this.soldierService.getCount();
        Assert.assertEquals(soldiersCountActual, soldiersCount, "The count of soldiers isn't equal.");
    }

}
