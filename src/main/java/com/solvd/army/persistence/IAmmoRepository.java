package com.solvd.army.persistence;

import com.solvd.army.domain.resources.Ammo;

import java.util.List;

public interface IAmmoRepository {

    void create(Ammo ammo, Long militaryUnitId);

    void update(List<Ammo> ammo, List<Long> ammoIds, Long militaryUnitId);

    List<Ammo> getByMilitaryUnitName(String militaryUnitName);

    List<Long> getId(Long militaryUnitId);

}
