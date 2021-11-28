package com.solvd.army.service.impl;

import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.persistence.impl.AmmoRepositoryImpl;
import com.solvd.army.service.IAmmoService;

import java.util.List;

public class AmmoServiceImpl implements IAmmoService {

    private AmmoRepositoryImpl ammoRepository;

    public AmmoServiceImpl() {
        ammoRepository = new AmmoRepositoryImpl();
    }

    @Override
    public List<Ammo> update(List<Ammo> ammo, Long militaryUnitId) {
        ammoRepository.update(ammo, militaryUnitId);
        return ammo;
    }

    @Override
    public Ammo insert(Ammo ammo, Long militaryUnitId) {
        ammoRepository.insert(ammo, militaryUnitId);
        return ammo;
    }

    @Override
    public List<Ammo> select(String militaryUnitName) {
        return  ammoRepository.select(militaryUnitName);
    }

}
