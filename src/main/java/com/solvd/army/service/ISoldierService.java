package com.solvd.army.service;

import com.solvd.army.domain.soldier.Soldier;

import java.util.List;

public interface ISoldierService {

    Soldier createSoldier(Soldier soldier, Long militaryUnitId);

    Soldier createRecruit(Soldier soldier, Long militaryUnitId);

    void updateSoldier(Soldier soldier, Long militaryUnitId);

    void updateRecruit(Soldier soldier);

    void delete(Soldier soldier);

    List<Soldier> getByMilitaryUnitName(String militaryUnitName);

    Soldier getById(Long id);

    void deleteById(Long id);

    Integer getCount();

}
