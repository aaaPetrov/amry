package com.solvd.army;

import com.solvd.army.domain.soldier.ServiceTerm;
import com.solvd.army.domain.soldier.Soldier;
import com.solvd.army.persistence.ConnectionPool;
import com.solvd.army.service.ISoldierService;
import com.solvd.army.service.impl.SoldierServiceImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SoldierTest extends BeforeAfter {

    private List<Soldier> soldiers;

    public SoldierTest() {
        this.soldiers = new ArrayList<>();
    }

    @Test(dataProvider = "createSoldierDataProvider")
    public void checkCreateSoldierTest(Soldier soldier) {
        System.out.println("\t\t\t\tcheckCreateSoldierTest");
        ISoldierService iSoldierService = new SoldierServiceImpl();
        Long id = getFirstMilitaryUnitId();
        Soldier newSoldier = iSoldierService.createSoldier(soldier, id);
        this.soldiers.add(newSoldier);
        Assert.assertNotNull(soldier, "Soldier is null after createSoldier()");
        Assert.assertNotNull(soldier.getId(), "Soldier id is null after createSoldier()");
    }

    @Test(dependsOnMethods = "checkCreateSoldierTest", dataProvider = "updateSoldierDataProvider")
    public void checkUpdateSoldierTest(Soldier soldier) {
        System.out.println("\t\t\t\tcheckUpdateSoldierTest");
        ISoldierService iSoldierService = new SoldierServiceImpl();
        for(Soldier soldierElement : soldiers) {
            soldier.setId(soldierElement.getId());
            iSoldierService.updateRecruit(soldier);
            Soldier selectAfterUpdate = iSoldierService.getById(soldier.getId());
            Assert.assertEquals(selectAfterUpdate.getFirstName(), soldier.getFirstName(), "Soldier firstName wasn't updated.");
            Assert.assertEquals(selectAfterUpdate.getLastName(), soldier.getLastName(), "Soldier lastName wasn't updated.");
        }
    }

    @Test(dependsOnMethods = "checkUpdateSoldierTest")
    public void checkDeleteSoldierTest() {
        System.out.println("\t\t\t\tcheckDeleteSoldierTest");
        ISoldierService iSoldierService = new SoldierServiceImpl();
        for(Soldier soldierElement : soldiers) {
            iSoldierService.deleteById(soldierElement.getId());
            Soldier selectAfterDelete = iSoldierService.getById(soldierElement.getId());
            Assert.assertNull(selectAfterDelete, "Soldier not deleted.");
        }
    }

    @DataProvider(name = "createSoldierDataProvider")
    public Object[][] fillCreateSoldiers() {
        ServiceTerm serviceTerm11 = new ServiceTerm(LocalDate.of(2015, 1, 1), 5);
        Soldier soldier11 = new Soldier("SOLDIER_ONE_INSERTED", "SOLDIER_ONE_INSERTED", LocalDate.of(1950, 1, 1), Soldier.Rank.MAJOR_GENERAL, serviceTerm11);
        ServiceTerm serviceTerm12 = new ServiceTerm(LocalDate.of(2016, 2, 2), 5);
        Soldier soldier12 = new Soldier("SOLDIER_TWO_INSERTED", "SOLDIER_TWO_INSERTED", LocalDate.of(1950, 2, 2), Soldier.Rank.MAJOR_GENERAL, serviceTerm12);
        return new Object[][]{{soldier11}, {soldier12}};
    }

    @DataProvider(name = "updateSoldierDataProvider")
    public Object[][] fillUpdateSoldiers() {
        ServiceTerm serviceTerm11 = new ServiceTerm(LocalDate.of(2015, 1, 1), 5);
        Soldier soldier11 = new Soldier("SOLDIER_ONE_UPDATED", "SOLDIER_ONE_UPDATED", LocalDate.of(1950, 1, 1), Soldier.Rank.MAJOR_GENERAL, serviceTerm11);
        ServiceTerm serviceTerm12 = new ServiceTerm(LocalDate.of(2016, 2, 2), 5);
        Soldier soldier12 = new Soldier("SOLDIER_TWO_UPDATED", "SOLDIER_TWO_UPDATED", LocalDate.of(1950, 2, 2), Soldier.Rank.MAJOR_GENERAL, serviceTerm12);
        return new Object[][]{{soldier11}, {soldier12}};
    }

    private Long getFirstMilitaryUnitId() {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "select id from military_units limit 1;";
        Long id = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
        return id;
    }

}
