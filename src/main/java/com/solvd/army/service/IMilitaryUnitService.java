package com.solvd.army.service;

import com.solvd.army.domain.MilitaryUnit;

import java.util.List;

public interface IMilitaryUnitService {

    MilitaryUnit insert(MilitaryUnit militaryUnit, Long armyId);

    MilitaryUnit update(MilitaryUnit militaryUnit, Long armyId);

    List<MilitaryUnit> select(String country);

}
