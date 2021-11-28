package com.solvd.army.persistence;

import com.solvd.army.domain.resources.Ammo;

import java.util.List;

public interface IAmmoRepository {

    void insert(Ammo ammo, Long militaryUnitId);

    void update(List<Ammo> ammo, Long militaryUnitId);

    List<Ammo> select(String militaryUnitName);

}
