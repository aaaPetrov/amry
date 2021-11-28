package com.solvd.army.service.impl;

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
    public List<Soldier> select(String militaryUnitName) {
        return soldierRepository.select(militaryUnitName);
    }

    @Override
    public void delete(Soldier soldier) {
        soldierRepository.delete(soldier);
    }

    @Override
    public Soldier insert(Soldier soldier, Long militaryUnitId) {
        soldierRepository.insert(soldier, militaryUnitId);
        return soldier;
    }

    @Override
    public Soldier update(Soldier soldier, Long militaryUnitId) {
        soldierRepository.update(soldier, militaryUnitId);
        return soldier;
    }

}
