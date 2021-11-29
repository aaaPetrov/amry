package com.solvd.army.persistence;

import com.solvd.army.domain.MilitaryUnit;

import java.util.List;

public interface IMilitaryUnitRepository {

    void create(MilitaryUnit militaryUnit, Long armyId);

    void update(MilitaryUnit militaryUnit, Long armyId);

    List<MilitaryUnit> get(String country);

}
