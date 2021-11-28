package com.solvd.army.service;

import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.resources.Weapon;

import java.util.List;

public interface IWeaponService {

    Weapon insert(Weapon weapon, Long militaryUnitId);

    List<Weapon> update(List<Weapon> weapon, Long militaryUnitId);

    List<Weapon> select(String militaryUnitName);

}
