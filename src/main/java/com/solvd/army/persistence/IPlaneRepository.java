package com.solvd.army.persistence;

import com.solvd.army.domain.resources.Plane;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPlaneRepository {

    void create(@Param("plane") Plane plane, @Param("militaryUnitId") Long militaryUnitId);

    void update(@Param("plane") Plane plane, @Param("planeId") Long ammoId, @Param("militaryUnitId") Long militaryUnitId);

    List<Plane> getByMilitaryUnitName(String militaryUnitName);

    List<Long> getId(Long militaryUnitId);

}
