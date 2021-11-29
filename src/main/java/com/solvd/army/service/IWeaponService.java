package com.solvd.army.service;

import com.solvd.army.domain.resources.Weapon;

import java.util.List;

public interface IWeaponService {

    Weapon create(Weapon weapon, Long militaryUnitId);

    List<Weapon> update(List<Weapon> weapon, List<Long> weaponIds, Long militaryUnitId);

    List<Weapon> getByMilitaryUnitName(String militaryUnitName);

    List<Long> getId(Long militaryUnitId);

}
