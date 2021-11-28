package com.solvd.army.persistence;

import com.solvd.army.domain.resources.Plane;

import java.util.List;

public interface IPlaneRepository {

    void insert(Plane plane, Long militaryUnitId);

    void update(List<Plane> plane, Long militaryUnitId);

    List<Plane> select(String militaryUnitName);

}
