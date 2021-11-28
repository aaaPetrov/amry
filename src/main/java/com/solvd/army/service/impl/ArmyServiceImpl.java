package com.solvd.army.service.impl;

import com.solvd.army.domain.Army;
import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.soldier.Soldier;
import com.solvd.army.persistence.impl.ArmyRepositoryImpl;
import com.solvd.army.service.IArmyService;
import java.util.List;


public class ArmyServiceImpl implements IArmyService {

    private ArmyRepositoryImpl armyRepository;
    private MilitaryUnitServiceImpl militaryUnitService;
    private SoldierServiceImpl soldierService;

    public ArmyServiceImpl() {
        armyRepository = new ArmyRepositoryImpl();
        militaryUnitService = new MilitaryUnitServiceImpl();
        soldierService = new SoldierServiceImpl();
    }

    @Override
    public Army update(Army army) {
        armyRepository.update(army);
        if(army.getMilitaryUnits() != null) {
            for(MilitaryUnit militaryUnit : army.getMilitaryUnits()) {
                militaryUnitService.update(militaryUnit, army.getId());
            }
        }
        return army;
    }

    @Override
    public Army insert(Army army) {
        armyRepository.insert(army);
        if(army.getMilitaryUnits() != null) {
            for(MilitaryUnit militaryUnit : army.getMilitaryUnits()) {
                militaryUnitService.insert(militaryUnit, army.getId());
            }
        }
        return army;
    }

    @Override
    public void delete(Army army) {
        armyRepository.delete(army);
        if(army.getMilitaryUnits() != null) {
            for (MilitaryUnit militaryUnit : army.getMilitaryUnits()) {
                if(militaryUnit.getSoldiers() != null) {
                    for (Soldier soldier : militaryUnit.getSoldiers()) {
                        soldierService.delete(soldier);
                    }
                }
            }
        }
    }

    @Override
    public Army select(String country) {
        Army army = armyRepository.select(country);
        army.setMilitaryUnits(militaryUnitService.select(army.getCountry()));
        return army;
    }

    @Override
    public List<Army> selectAll() {
        return armyRepository.selectAll();
    }

}
