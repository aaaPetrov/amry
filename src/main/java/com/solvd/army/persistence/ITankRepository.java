package com.solvd.army.persistence;

import com.solvd.army.domain.resources.Tank;

import java.util.List;

public interface ITankRepository {

    void insert(Tank tank, Long militaryUnitId);

    void update(List<Tank> tank, Long militaryUnitId);

    List<Tank> select(String militaryUnitName);

}
