package com.solvd.army.persistence;

import com.solvd.army.domain.resources.Weapon;

import java.util.List;

public interface IWeaponRepository {

    void insert(Weapon weapon, Long military_unit_id);

    void update(List<Weapon> weapon, Long military_unit_id);

    List<Weapon> select(String militaryUnitName);

}
