package com.solvd.army.persistence;

import com.solvd.army.domain.soldier.Soldier;

import java.util.List;

public interface ISoldierRepository {

    void insert(Soldier soldier, Long militaryUnitId);

    void update(Soldier soldier, Long militaryUnitId);

    void delete(Soldier soldier);

    List<Soldier> select(String militaryUnitName);

}
