package com.solvd.army;

import com.solvd.army.domain.soldier.ServiceTerm;
import com.solvd.army.domain.soldier.Soldier;
import com.solvd.army.service.IMilitaryUnitService;
import com.solvd.army.service.ISoldierService;
import com.solvd.army.service.impl.MilitaryUnitServiceImpl;
import com.solvd.army.service.impl.SoldierServiceImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SoldierTest extends BeforeAfter {

    private static final Logger LOGGER = LogManager.getLogger(SoldierTest.class);

    private List<Soldier> soldiers;
    private ISoldierService soldierService;
    private IMilitaryUnitService militaryUnitService;

    public SoldierTest() {
        this.soldiers = new ArrayList<>();
        this.soldierService = new SoldierServiceImpl();
        this.militaryUnitService = new MilitaryUnitServiceImpl();
    }

    @DataProvider(name = "createSoldierDataProvider")
    public Object[][] fillCreateSoldiers() {
        ServiceTerm serviceTerm11 = new ServiceTerm(LocalDate.of(2015, 1, 1), 5);
        Soldier soldier11 = new Soldier("SOLDIER_ONE_INSERTED", "SOLDIER_ONE_INSERTED", LocalDate.of(1950, 1, 1), Soldier.Rank.MAJOR_GENERAL, serviceTerm11);
        ServiceTerm serviceTerm12 = new ServiceTerm(LocalDate.of(2016, 2, 2), 5);
        Soldier soldier12 = new Soldier("SOLDIER_TWO_INSERTED", "SOLDIER_TWO_INSERTED", LocalDate.of(1950, 2, 2), Soldier.Rank.MAJOR_GENERAL, serviceTerm12);
        return new Object[][]{{soldier11}, {soldier12}};
    }

    @Test(dataProvider = "createSoldierDataProvider")
    public void checkCreateSoldierTest(Soldier soldier) {
        LOGGER.info("\n\n\t\t\t\tcheckCreateSoldierTest");
        Long id = this.militaryUnitService.getFirstMilitaryUnitId();
        Soldier newSoldier = this.soldierService.createSoldier(soldier, id);
        this.soldiers.add(newSoldier);
        Assert.assertNotNull(soldier, "Soldier is null after createSoldier()");
        Assert.assertNotNull(soldier.getId(), "Soldier id is null after createSoldier()");
    }

    @DataProvider(name = "updateSoldierDataProvider")
    public Object[][] fillUpdateSoldiers() {
        ServiceTerm serviceTerm11 = new ServiceTerm(LocalDate.of(2015, 1, 1), 5);
        Soldier soldier11 = new Soldier("SOLDIER_ONE_UPDATED", "SOLDIER_ONE_UPDATED", LocalDate.of(1950, 1, 1), Soldier.Rank.MAJOR_GENERAL, serviceTerm11);
        ServiceTerm serviceTerm12 = new ServiceTerm(LocalDate.of(2016, 2, 2), 5);
        Soldier soldier12 = new Soldier("SOLDIER_TWO_UPDATED", "SOLDIER_TWO_UPDATED", LocalDate.of(1950, 2, 2), Soldier.Rank.MAJOR_GENERAL, serviceTerm12);
        return new Object[][]{{soldier11}, {soldier12}};
    }

    @Test(dependsOnMethods = "checkCreateSoldierTest", dataProvider = "updateSoldierDataProvider")
    public void checkUpdateSoldierTest(Soldier soldier) {
        LOGGER.info("\n\t\t\t\tcheckUpdateSoldierTest");
        for(Soldier soldierElement : soldiers) {
            soldier.setId(soldierElement.getId());
            this.soldierService.updateRecruit(soldier);
            Soldier selectAfterUpdate = this.soldierService.getById(soldier.getId());
            Assert.assertEquals(selectAfterUpdate.getFirstName(), soldier.getFirstName(), "Soldier firstName wasn't updated.");
            Assert.assertEquals(selectAfterUpdate.getLastName(), soldier.getLastName(), "Soldier lastName wasn't updated.");
        }
    }

    @Test(dependsOnMethods = "checkUpdateSoldierTest")
    public void checkDeleteSoldierTest() {
        LOGGER.info("\n\t\t\t\tcheckDeleteSoldierTest");
        for(Soldier soldierElement : soldiers) {
            this.soldierService.deleteById(soldierElement.getId());
            Soldier selectAfterDelete = this.soldierService.getById(soldierElement.getId());
            Assert.assertNull(selectAfterDelete, "Soldier not deleted.");
        }
    }

}
