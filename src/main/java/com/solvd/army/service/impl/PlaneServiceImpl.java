package com.solvd.army.service.impl;

import com.solvd.army.domain.resources.Plane;
import com.solvd.army.persistence.impl.PlaneRepositoryImpl;
import com.solvd.army.service.IPlaneService;

import java.util.List;

public class PlaneServiceImpl implements IPlaneService {

    private PlaneRepositoryImpl planeRepository;

    public PlaneServiceImpl() {
        planeRepository = new PlaneRepositoryImpl();
    }

    @Override
    public List<Plane> select(String militaryUnitName) {
        return planeRepository.select(militaryUnitName);
    }

    @Override
    public List<Plane> update(List<Plane> plane, Long militaryUnitId) {
        planeRepository.update(plane, militaryUnitId);
        return plane;
    }

    @Override
    public Plane insert(Plane plane, Long militaryUnitId) {
        planeRepository.insert(plane, militaryUnitId);
        return plane;
    }

}
