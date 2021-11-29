package com.solvd.army.service.impl;

import com.solvd.army.domain.exception.NoDataException;
import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.domain.soldier.Soldier;
import com.solvd.army.persistence.impl.SoldierRepositoryImpl;
import com.solvd.army.service.ISoldierService;

import java.util.List;

public class SoldierServiceImpl implements ISoldierService {

    private final SoldierRepositoryImpl soldierRepository;

    public SoldierServiceImpl() {
        soldierRepository = new SoldierRepositoryImpl();
    }

    @Override
    public List<Soldier> getByMilitaryUnitName(String militaryUnitName) {
        List<Soldier> soldiers = soldierRepository.getByMilitaryUnitName(militaryUnitName);
        if(soldiers == null) {
            throw new NoDataException("soldierRepository.getByMilitaryUnitName() was returned null-value in SoldierServiceImpl.");
        }
        return soldiers;
    }

    @Override
    public void delete(Soldier soldier) {
        soldierRepository.delete(soldier);
    }

    @Override
    public Soldier createSoldier(Soldier soldier, Long militaryUnitId) {
        Long recruitId = soldierRepository.createRecruit(soldier, militaryUnitId);
        if(recruitId == null) {
            throw new NoDataException("soldierRepository.createRecruit() was returned null-value in SoldierServiceImpl.");
        }
        soldierRepository.createSoldier(soldier, militaryUnitId, recruitId);
        return soldier;
    }

    @Override
    public Soldier createRecruit(Soldier soldier, Long militaryUnitId) {
        soldierRepository.createRecruit(soldier, militaryUnitId);
        return soldier;
    }

    @Override
    public Soldier updateSoldier(Soldier soldier, Long militaryUnitId) {
        soldierRepository.updateSoldier(soldier,militaryUnitId);
        return soldier;
    }

    @Override
    public Soldier updateRecruit(Soldier soldier, Long militaryUnitId) {
        soldierRepository.updateRecruit(soldier, militaryUnitId);
        return soldier;
    }

}
