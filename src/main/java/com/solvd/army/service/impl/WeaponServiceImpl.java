package com.solvd.army.service.impl;

import com.solvd.army.domain.resources.Weapon;
import com.solvd.army.persistence.impl.WeaponRepositoryImpl;
import com.solvd.army.service.IWeaponService;

import java.util.List;

public class WeaponServiceImpl implements IWeaponService {

    private WeaponRepositoryImpl weaponRepository;

    public WeaponServiceImpl() {
        weaponRepository = new WeaponRepositoryImpl();
    }

    @Override
    public List<Weapon> select(String militaryUnitName) {
        return weaponRepository.select(militaryUnitName);
    }

    @Override
    public List<Weapon> update(List<Weapon> weapon, Long militaryUnitId) {
        weaponRepository.update(weapon, militaryUnitId);
        return weapon;
    }

    @Override
    public Weapon insert(Weapon weapon, Long militaryUnitId) {
        weaponRepository.insert(weapon, militaryUnitId);
        return weapon;
    }

}
