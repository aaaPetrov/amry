package com.solvd.army.service;

import com.solvd.army.domain.resources.Plane;

import java.util.List;

public interface IPlaneService {

    Plane insert(Plane plane, Long militaryUnitId);

    List<Plane> update(List<Plane> plane, Long militaryUnitId);

    List<Plane> select(String militaryUnitName);

}
