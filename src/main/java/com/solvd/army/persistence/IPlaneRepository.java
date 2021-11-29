package com.solvd.army.persistence;

import com.solvd.army.domain.resources.Plane;

import java.util.List;

public interface IPlaneRepository {

    void create(Plane plane, Long militaryUnitId);

    void update(List<Plane> plane, List<Long> ammoIds, Long militaryUnitId);

    List<Plane> getByMilitaryUnitName(String militaryUnitName);

    List<Long> getId(Long militaryUnitId);

}
