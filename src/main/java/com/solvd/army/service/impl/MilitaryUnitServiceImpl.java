package com.solvd.army.service.impl;

import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.exception.NoDataException;
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
            List<Long> ammoIds = ammoService.getId(militaryUnit.getId());
            if(ammoIds == null) {
                throw new NoDataException("ammoService.getId() was returned null-value in MilitaryUnitServiceImpl.");
            }
            ammoService.update(militaryUnit.getAmmunition(), ammoIds, militaryUnit.getId());
        }
        if (militaryUnit.getWeapons() != null) {
            List<Long> weaponIds = weaponService.getId(militaryUnit.getId());
            if(weaponIds == null) {
                throw new NoDataException("weaponService.getId() was returned null-value in MilitaryUnitServiceImpl.");
            }
            weaponService.update(militaryUnit.getWeapons(), weaponIds, militaryUnit.getId());
        }
        if (militaryUnit.getTanks() != null) {
            List<Long> tankIds = tankService.getId(militaryUnit.getId());
            if(tankIds == null) {
                throw new NoDataException("tankService.getId() was returned null-value in MilitaryUnitServiceImpl.");
            }
            tankService.update(militaryUnit.getTanks(), tankIds, militaryUnit.getId());
        }
        if (militaryUnit.getPlanes() != null) {
            List<Long> planeIds = planeService.getId(militaryUnit.getId());
            if(planeIds == null) {
                throw new NoDataException("planeService.getId() was returned null-value in MilitaryUnitServiceImpl.");
            }
            planeService.update(militaryUnit.getPlanes(), planeIds, militaryUnit.getId());
        }
        if (militaryUnit.getSoldiers() != null) {
            for (Soldier soldier : militaryUnit.getSoldiers()) {
                soldierService.updateRecruit(soldier, militaryUnit.getId());
                soldierService.updateSoldier(soldier, militaryUnit.getId());
            }
        }
        return militaryUnit;
    }

    @Override
    public MilitaryUnit create(MilitaryUnit militaryUnit, Long armyId) {
        militaryUnitRepository.create(militaryUnit, armyId);
        if (militaryUnit.getAmmunition() != null) {
            for (Ammo ammo : militaryUnit.getAmmunition()) {
                ammoService.create(ammo, militaryUnit.getId());
            }
        }
        if (militaryUnit.getWeapons() != null) {
            for (Weapon weapon : militaryUnit.getWeapons()) {
                weaponService.create(weapon, militaryUnit.getId());
            }
        }
        if (militaryUnit.getTanks() != null) {
            for (Tank tank : militaryUnit.getTanks()) {
                tankService.create(tank, militaryUnit.getId());
            }
        }
        if (militaryUnit.getPlanes() != null) {
            for (Plane plane : militaryUnit.getPlanes()) {
                planeService.create(plane, militaryUnit.getId());
            }
        }
        if (militaryUnit.getSoldiers() != null) {
            for (Soldier soldier : militaryUnit.getSoldiers()) {
                soldierService.createSoldier(soldier, militaryUnit.getId());
            }
        }
        return militaryUnit;
    }

    @Override
    public List<MilitaryUnit> get(String country) {
        List<MilitaryUnit> militaryUnits = militaryUnitRepository.get(country);
        if(militaryUnits == null) {
            throw new NoDataException("militaryUnitRepository.get() was returned null-value in MilitaryUnitServiceImpl.");
        }
        for (MilitaryUnit militaryUnit : militaryUnits) {
            List<Ammo> ammunition = ammoService.getByMilitaryUnitName(militaryUnit.getName());
            List<Weapon> weapons = weaponService.getByMilitaryUnitName(militaryUnit.getName());
            List<Tank> tanks = tankService.getByMilitaryUnitName(militaryUnit.getName());
            List<Plane> planes = planeService.getByMilitaryUnitName(militaryUnit.getName());
            List<Soldier> soldiers = soldierService.getByMilitaryUnitName(militaryUnit.getName());
            if(ammunition == null) {
                throw new NoDataException("ammoService.getByMilitaryUnitName() was returned null-value in MilitaryUnitServiceImpl.");
            } else if(ammunition == null) {
                throw new NoDataException("weaponService.getByMilitaryUnitName() was returned null-value in MilitaryUnitServiceImpl.");
            } else if(ammunition == null) {
                throw new NoDataException("tankService.getByMilitaryUnitName() was returned null-value in MilitaryUnitServiceImpl.");
            } else if(ammunition == null) {
                throw new NoDataException("planeService.getByMilitaryUnitName() was returned null-value in MilitaryUnitServiceImpl.");
            } else if(ammunition == null) {
                throw new NoDataException("soldierService.getByMilitaryUnitName() was returned null-value in MilitaryUnitServiceImpl.");
            }
            militaryUnit.setAmmunition(ammunition);
            militaryUnit.setWeapons(weapons);
            militaryUnit.setTanks(tanks);
            militaryUnit.setPlanes(planes);
            militaryUnit.setSoldiers(soldiers);
        }
        return militaryUnits;
    }

}
