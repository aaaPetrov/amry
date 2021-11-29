package com.solvd.army.service.impl;

import com.solvd.army.domain.exception.NoDataException;
import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.domain.resources.Tank;
import com.solvd.army.persistence.impl.TankRepositoryImpl;
import com.solvd.army.service.ITankService;

import java.util.List;

public class TankServiceImpl implements ITankService {

    private final TankRepositoryImpl tankRepository;

    public TankServiceImpl() {
        tankRepository = new TankRepositoryImpl();
    }

    @Override
    public List<Tank> getByMilitaryUnitName(String militaryUnitName) {
        List<Tank> tanks = tankRepository.getByMilitaryUnitName(militaryUnitName);
        if(tanks == null) {
            throw new NoDataException("tankRepository.getByMilitaryUnitName() was returned null-value in TankServiceImpl.");
        }
        return tanks;
    }

    @Override
    public List<Tank> update(List<Tank> tank, List<Long> tankIds, Long militaryUnitId) {
        tankRepository.update(tank, tankIds, militaryUnitId);
        return tank;
    }

    @Override
    public List<Long> getId(Long militaryUnitId) {
        List<Long> ammoIds = tankRepository.getId(militaryUnitId);
        if(ammoIds == null) {
            throw new NoDataException("tankRepository.getId() was returned null-value in TankServiceImpl.");
        }
        return ammoIds;
    }

    @Override
    public Tank create(Tank tank, Long militaryUnitId) {
        tankRepository.create(tank, militaryUnitId);
        return tank;
    }

}
