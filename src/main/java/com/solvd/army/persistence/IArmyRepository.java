package com.solvd.army.persistence;

import com.solvd.army.domain.Army;

import java.util.List;

public interface IArmyRepository {

    void insert(Army army);

    void delete(Army army);

    void update(Army army);

    Army select(String country);

    List<Army> selectAll();

}
