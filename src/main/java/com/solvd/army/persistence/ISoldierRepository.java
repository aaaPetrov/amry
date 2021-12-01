package com.solvd.army.persistence;

import com.solvd.army.domain.soldier.Soldier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISoldierRepository {

    void createSoldier(@Param("soldier") Soldier soldier, @Param("militaryUnitId") Long militaryUnitId);

    void createRecruit(@Param("soldier") Soldier soldier, @Param("militaryUnitId") Long militaryUnitId);

    void updateSoldier(@Param("soldier") Soldier soldier, @Param("militaryUnitId") Long militaryUnitId);

    void updateRecruit(@Param("soldier") Soldier soldier, @Param("militaryUnitId") Long militaryUnitId);

    void delete(Soldier soldier);

    List<Soldier> getByMilitaryUnitName(String militaryUnitName);

}
