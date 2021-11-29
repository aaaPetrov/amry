package com.solvd.army.service;

import com.solvd.army.domain.resources.Tank;

import java.util.List;

public interface ITankService {

    Tank create(Tank tank, Long militaryUnitId);

    List<Tank> update(List<Tank> tank, List<Long> tankIds, Long militaryUnitId);

    List<Tank> getByMilitaryUnitName(String militaryUnitName);

    List<Long> getId(Long militaryUnitId);


}
