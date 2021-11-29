package com.solvd.army.service.impl;

import com.solvd.army.domain.exception.NoDataException;
import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.domain.resources.Plane;
import com.solvd.army.persistence.impl.PlaneRepositoryImpl;
import com.solvd.army.service.IPlaneService;

import java.util.List;

public class PlaneServiceImpl implements IPlaneService {

    private final PlaneRepositoryImpl planeRepository;

    public PlaneServiceImpl() {
        planeRepository = new PlaneRepositoryImpl();
    }

    @Override
    public List<Plane> getByMilitaryUnitName(String militaryUnitName) {
        List<Plane> planes = planeRepository.getByMilitaryUnitName(militaryUnitName);
        if(planes == null) {
            throw new NoDataException("planeRepository.getByMilitaryUnitName() was returned null-value in PlaneServiceImpl.");
        }
        return planes;
    }

    @Override
    public List<Plane> update(List<Plane> plane, List<Long> planeIds, Long militaryUnitId) {
        planeRepository.update(plane, planeIds, militaryUnitId);
        return plane;
    }

    @Override
    public Plane create(Plane plane, Long militaryUnitId) {
        planeRepository.create(plane, militaryUnitId);
        return plane;
    }

    @Override
    public List<Long> getId(Long militaryUnitId) {
        List<Long> ammoIds = planeRepository.getId(militaryUnitId);
        if(ammoIds == null) {
            throw new NoDataException("planeRepository.getId() was returned null-value in PlaneServiceImpl.");
        }
        return ammoIds;
    }

}
