package com.solvd.army.persistence;

import com.solvd.army.domain.resources.Tank;

import java.util.List;

public interface ITankRepository {

    void create(Tank tank, Long militaryUnitId);

    void update(List<Tank> tank, List<Long> tankIds, Long militaryUnitId);

    List<Tank> getByMilitaryUnitName(String militaryUnitName);

    List<Long> getId(Long militaryUnitId);

}
