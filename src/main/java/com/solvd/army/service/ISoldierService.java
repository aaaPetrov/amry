package com.solvd.army.service;

import com.solvd.army.domain.soldier.Soldier;

import java.util.List;

public interface ISoldierService {

    Soldier insert(Soldier soldier, Long militaryUnitId);

    Soldier update(Soldier soldier, Long militaryUnitId);

    void delete(Soldier soldier);

    List<Soldier> select(String militaryUnitName);

}
