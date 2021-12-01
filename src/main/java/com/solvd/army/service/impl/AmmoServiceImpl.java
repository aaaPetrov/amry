package com.solvd.army.service.impl;

import com.solvd.army.domain.exception.NoDataException;
import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.persistence.IAmmoRepository;
import com.solvd.army.persistence.impl.AmmoMapperImpl;
import com.solvd.army.service.IAmmoService;

import java.util.List;

public class AmmoServiceImpl implements IAmmoService {

    private final IAmmoRepository ammoRepository;

    public AmmoServiceImpl() {
        /*ammoRepository = new AmmoRepositoryImpl();*/
        ammoRepository = new AmmoMapperImpl();
    }

    @Override
    public Ammo update(Ammo ammo, Long ammoId, Long militaryUnitId) {
        ammoRepository.update(ammo, ammoId, militaryUnitId);
        return ammo;
    }

    @Override
    public Ammo create(Ammo ammo, Long militaryUnitId) {
        ammoRepository.create(ammo, militaryUnitId);
        return ammo;
    }

    @Override
    public List<Long> getId(Long militaryUnitId) {
        List<Long> ammoIds = ammoRepository.getId(militaryUnitId);
        if (ammoIds == null) {
            throw new NoDataException("ammoRepository.getId() was returned null-value in AmmoServiceImpl.");
        }
        return ammoIds;
    }

    @Override
    public List<Ammo> getByMilitaryUnitName(String militaryUnitName) {
        List<Ammo> ammunition = ammoRepository.getByMilitaryUnitName(militaryUnitName);
        if (ammunition == null) {
            throw new NoDataException("ammoRepository.getByMilitaryUnitName() was returned null-value in AmmoServiceImpl.");
        }
        return ammunition;
    }

}
