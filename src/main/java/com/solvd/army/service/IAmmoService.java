package com.solvd.army.service;

import com.solvd.army.domain.resources.Ammo;

import java.util.List;

public interface IAmmoService {

    Ammo create(Ammo ammo, Long militaryUnitId);

    List<Ammo> update(List<Ammo> ammo, List<Long> ammoIds, Long militaryUnitId);

    List<Ammo> getByMilitaryUnitName(String militaryUnitName);

    List<Long> getId(Long militaryUnitId);

}
