package com.solvd.army.service;

import com.solvd.army.domain.MilitaryUnit;

import java.util.List;

public interface IMilitaryUnitService {

    MilitaryUnit create(MilitaryUnit militaryUnit, Long armyId);

    MilitaryUnit update(MilitaryUnit militaryUnit, Long armyId);

    List<MilitaryUnit> get(String country);

    Integer getCount();

    Long getFirstMilitaryUnitId();

}
