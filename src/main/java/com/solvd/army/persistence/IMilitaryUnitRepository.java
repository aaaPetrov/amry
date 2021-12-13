package com.solvd.army.persistence;

import com.solvd.army.domain.MilitaryUnit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IMilitaryUnitRepository {

    void create(@Param("militaryUnit") MilitaryUnit militaryUnit, @Param("armyId") Long armyId);

    void update(@Param("militaryUnit") MilitaryUnit militaryUnit, @Param("armyId") Long armyId);

    List<MilitaryUnit> get(String country);

}
