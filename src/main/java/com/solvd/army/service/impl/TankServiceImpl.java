package com.solvd.army.service.impl;

import com.solvd.army.domain.resources.Tank;
import com.solvd.army.persistence.impl.TankRepositoryImpl;
import com.solvd.army.service.ITankService;

import java.util.List;

public class TankServiceImpl implements ITankService {

    private TankRepositoryImpl tankRepository;

    public TankServiceImpl() {
        tankRepository = new TankRepositoryImpl();
    }

    @Override
    public List<Tank> select(String militaryUnitName) {
        return tankRepository.select(militaryUnitName);
    }

    @Override
    public List<Tank> update(List<Tank> tank, Long militaryUnitId) {
        tankRepository.update(tank, militaryUnitId);
        return tank;
    }

    @Override
    public Tank insert(Tank tank, Long militaryUnitId) {
        tankRepository.insert(tank, militaryUnitId);
        return tank;
    }

}
