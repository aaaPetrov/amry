package com.solvd.army.service;

import com.solvd.army.domain.resources.Ammo;

import java.util.List;

public interface IAmmoService {

    Ammo insert(Ammo ammo, Long militaryUnitId);

    List<Ammo> update(List<Ammo> ammo, Long militaryUnitId);

    List<Ammo> select(String militaryUnitName);

}
