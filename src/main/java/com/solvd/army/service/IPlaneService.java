package com.solvd.army.service;

import com.solvd.army.domain.resources.Plane;

import java.util.List;

public interface IPlaneService {

    Plane create(Plane plane, Long militaryUnitId);

    Plane update(Plane plane, Long planeId, Long militaryUnitId);

    List<Plane> getByMilitaryUnitName(String militaryUnitName);

    List<Long> getId(Long militaryUnitId);

}
