package com.solvd.army.service;

import com.solvd.army.domain.resources.Tank;

import java.util.List;

public interface ITankService {

    Tank insert(Tank tank, Long militaryUnitId);

    List<Tank> update(List<Tank> tank, Long militaryUnitId);

    List<Tank> select(String militaryUnitName);

}
