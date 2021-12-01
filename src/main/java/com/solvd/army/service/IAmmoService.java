package com.solvd.army.service;

import com.solvd.army.domain.resources.Ammo;

import java.util.List;

public interface IAmmoService {

    Ammo create(Ammo ammo, Long militaryUnitId);

    Ammo update(Ammo ammo, Long ammoId, Long militaryUnitId);

    List<Ammo> getByMilitaryUnitName(String militaryUnitName);

    List<Long> getId(Long militaryUnitId);

}
