package com.solvd.army.persistence;

import com.solvd.army.domain.resources.Weapon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IWeaponRepository {

    void create(@Param("weapon") Weapon weapon, @Param("militaryUnitId") Long militaryUnitId);

    void update(@Param("weapon") Weapon weapon, @Param("weaponId") Long weaponId, @Param("militaryUnitId") Long militaryUnitId);

    List<Weapon> getByMilitaryUnitName(String militaryUnitName);

    List<Long> getId(Long militaryUnitId);

}
