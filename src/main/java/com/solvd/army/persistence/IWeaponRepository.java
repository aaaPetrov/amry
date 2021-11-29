package com.solvd.army.persistence;

import com.solvd.army.domain.resources.Weapon;

import java.util.List;

public interface IWeaponRepository {

    void create(Weapon weapon, Long militaryUnitId);

    void update(List<Weapon> weapon, List<Long> weaponIds, Long militaryUnitId);

    List<Weapon> getByMilitaryUnitName(String militaryUnitName);

    List<Long> getId(Long militaryUnitId);

}
