package com.solvd.army.service.impl;

import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.domain.resources.Plane;
import com.solvd.army.domain.resources.Tank;
import com.solvd.army.domain.resources.Weapon;
import com.solvd.army.domain.soldier.Soldier;
import com.solvd.army.persistence.impl.*;
import com.solvd.army.service.IMilitaryUnitService;

import java.util.List;


public class MilitaryUnitServiceImpl implements IMilitaryUnitService {

    private final MilitaryUnitRepositoryImpl militaryUnitRepository;
    private final AmmoServiceImpl ammoService;
    private final WeaponServiceImpl weaponService;
    private final TankServiceImpl tankService;
    private final PlaneServiceImpl planeService;
    private final SoldierServiceImpl soldierService;


    public MilitaryUnitServiceImpl() {
        militaryUnitRepository = new MilitaryUnitRepositoryImpl();
        ammoService = new AmmoServiceImpl();
        weaponService = new WeaponServiceImpl();
        tankService = new TankServiceImpl();
        planeService = new PlaneServiceImpl();
        soldierService = new SoldierServiceImpl();
    }

    @Override
    public MilitaryUnit update(MilitaryUnit militaryUnit, Long armyId) {
        militaryUnitRepository.update(militaryUnit, armyId);
        if (militaryUnit.getAmmunition() != null) {
            ammoService.update(militaryUnit.getAmmunition(), militaryUnit.getId());
        }
        if (militaryUnit.getWeapons() != null) {
            weaponService.update(militaryUnit.getWeapons(), militaryUnit.getId());
        }
        if (militaryUnit.getTanks() != null) {
            tankService.update(militaryUnit.getTanks(), militaryUnit.getId());
        }
        if (militaryUnit.getPlanes() != null) {
            planeService.update(militaryUnit.getPlanes(), militaryUnit.getId());
        }
        if (militaryUnit.getSoldiers() != null) {
            for (Soldier soldier : militaryUnit.getSoldiers()) {
                soldierService.update(soldier, militaryUnit.getId());
            }
        }
        return militaryUnit;
    }

    @Override
    public MilitaryUnit insert(MilitaryUnit militaryUnit, Long armyId) {
        militaryUnitRepository.insert(militaryUnit, armyId);
        if (militaryUnit.getAmmunition() != null) {
            for (Ammo ammo : militaryUnit.getAmmunition()) {
                ammoService.insert(ammo, militaryUnit.getId());
            }
        }
        if (militaryUnit.getWeapons() != null) {
            for (Weapon weapon : militaryUnit.getWeapons()) {
                weaponService.insert(weapon, militaryUnit.getId());
            }
        }
        if (militaryUnit.getTanks() != null) {
            for (Tank tank : militaryUnit.getTanks()) {
                tankService.insert(tank, militaryUnit.getId());
            }
        }
        if (militaryUnit.getPlanes() != null) {
            for (Plane plane : militaryUnit.getPlanes()) {
                planeService.insert(plane, militaryUnit.getId());
            }
        }
        if (militaryUnit.getSoldiers() != null) {
            for (Soldier soldier : militaryUnit.getSoldiers()) {
                soldierService.insert(soldier, militaryUnit.getId());
            }
        }
        return militaryUnit;
    }

    @Override
    public List<MilitaryUnit> select(String country) {
        List<MilitaryUnit> militaryUnits = militaryUnitRepository.select(country);
        for (MilitaryUnit militaryUnit : militaryUnits) {
            militaryUnit.setAmmunition(ammoService.select(militaryUnit.getName()));
            militaryUnit.setWeapons(weaponService.select(militaryUnit.getName()));
            militaryUnit.setTanks(tankService.select(militaryUnit.getName()));
            militaryUnit.setPlanes(planeService.select(militaryUnit.getName()));
            militaryUnit.setSoldiers(soldierService.select(militaryUnit.getName()));
        }
        return militaryUnits;
    }

}
