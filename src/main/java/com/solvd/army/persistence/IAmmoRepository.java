package com.solvd.army.persistence;

import com.solvd.army.domain.resources.Ammo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAmmoRepository {

    void create(@Param("ammo") Ammo ammo, @Param("militaryUnitId") Long militaryUnitId);

    void update(@Param("ammo") Ammo ammo, @Param("ammoId") Long ammoId, @Param("militaryUnitId") Long militaryUnitId);

    List<Ammo> getByMilitaryUnitName(String militaryUnitName);

    List<Long> getId(Long militaryUnitId);

}
