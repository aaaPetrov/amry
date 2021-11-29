package com.solvd.army.service.impl;

import com.solvd.army.domain.exception.NoDataException;
import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.domain.resources.Weapon;
import com.solvd.army.persistence.impl.WeaponRepositoryImpl;
import com.solvd.army.service.IWeaponService;

import java.util.List;

public class WeaponServiceImpl implements IWeaponService {

    private final WeaponRepositoryImpl weaponRepository;

    public WeaponServiceImpl() {
        weaponRepository = new WeaponRepositoryImpl();
    }

    @Override
    public List<Weapon> getByMilitaryUnitName(String militaryUnitName) {
        List<Weapon> weapons = weaponRepository.getByMilitaryUnitName(militaryUnitName);
        if(weapons == null) {
            throw new NoDataException("weaponRepository.getByMilitaryUnitName() was returned null-value in WeaponServiceImpl.");
        }
        return weapons;
    }

    @Override
    public List<Weapon> update(List<Weapon> weapon, List<Long> weaponIds, Long militaryUnitId) {
        weaponRepository.update(weapon, weaponIds, militaryUnitId);
        return weapon;
    }

    @Override
    public List<Long> getId(Long militaryUnitId) {
        List<Long> ammoIds = weaponRepository.getId(militaryUnitId);
        if(ammoIds == null) {
            throw new NoDataException("weaponRepository.getId() was returned null-value in WeaponServiceImpl.");
        }
        return ammoIds;
    }

    @Override
    public Weapon create(Weapon weapon, Long militaryUnitId) {
        weaponRepository.create(weapon, militaryUnitId);
        return weapon;
    }

}
