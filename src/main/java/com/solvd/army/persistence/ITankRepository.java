package com.solvd.army.persistence;

import com.solvd.army.domain.resources.Tank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITankRepository {

    void create(@Param("tank") Tank tank, @Param("militaryUnitId") Long militaryUnitId);

    void update(@Param("tank") Tank tank, @Param("tankId") Long tankId, @Param("militaryUnitId") Long militaryUnitId);

    List<Tank> getByMilitaryUnitName(String militaryUnitName);

    List<Long> getId(Long militaryUnitId);

}
