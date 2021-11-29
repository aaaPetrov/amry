package com.solvd.army.persistence;

import com.solvd.army.domain.soldier.Soldier;

import java.util.List;

public interface ISoldierRepository {

    void createSoldier(Soldier soldier, Long militaryUnitId, Long recruitId);

    Long createRecruit(Soldier soldier, Long militaryUnitId);

    void updateSoldier(Soldier soldier, Long militaryUnitId);

    void updateRecruit(Soldier soldier, Long militaryUnitId);

    void delete(Soldier soldier);

    List<Soldier> getByMilitaryUnitName(String militaryUnitName);

}
