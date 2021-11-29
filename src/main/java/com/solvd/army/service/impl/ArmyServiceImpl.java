package com.solvd.army.service.impl;

import com.solvd.army.domain.Army;
import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.exception.NoDataException;
import com.solvd.army.domain.soldier.Soldier;
import com.solvd.army.persistence.impl.ArmyRepositoryImpl;
import com.solvd.army.service.IArmyService;

import java.util.List;


public class ArmyServiceImpl implements IArmyService {

    private final ArmyRepositoryImpl armyRepository;
    private final MilitaryUnitServiceImpl militaryUnitService;
    private final SoldierServiceImpl soldierService;

    public ArmyServiceImpl() {
        armyRepository = new ArmyRepositoryImpl();
        militaryUnitService = new MilitaryUnitServiceImpl();
        soldierService = new SoldierServiceImpl();
    }

    @Override
    public Army update(Army army) {
        armyRepository.update(army);
        if (army.getMilitaryUnits() != null) {
            for (MilitaryUnit militaryUnit : army.getMilitaryUnits()) {
                militaryUnitService.update(militaryUnit, army.getId());
            }
        }
        return army;
    }

    @Override
    public Army create(Army army) {
        armyRepository.create(army);
        if (army.getMilitaryUnits() != null) {
            for (MilitaryUnit militaryUnit : army.getMilitaryUnits()) {
                militaryUnitService.create(militaryUnit, army.getId());
            }
        }
        return army;
    }

    @Override
    public void delete(Army army) {
        armyRepository.delete(army);
        if (army.getMilitaryUnits() != null) {
            for (MilitaryUnit militaryUnit : army.getMilitaryUnits()) {
                if (militaryUnit.getSoldiers() != null) {
                    for (Soldier soldier : militaryUnit.getSoldiers()) {
                        soldierService.delete(soldier);
                    }
                }
            }
        }
    }

    @Override
    public Army get(String country) {
        Army army = armyRepository.get(country);
        if(army == null) {
            throw new NoDataException("armyRepository.get() was returned null-value in ArmyServiceImpl.");
        }
        List<MilitaryUnit> militaryUnits = militaryUnitService.get(army.getCountry());
        if(militaryUnits == null) {
            throw new NoDataException("militaryUnitService.get() was returned null-value in ArmyServiceImpl.");
        }
        army.setMilitaryUnits(militaryUnits);
        return army;
    }

    @Override
    public List<Army> getAll() {
        List<Army> armies = armyRepository.getAll();
        if(armies == null) {
            throw new NoDataException("armyRepository.getAll() was returned null-value in ArmyServiceImpl.");
        }
        return armies;
    }

}
