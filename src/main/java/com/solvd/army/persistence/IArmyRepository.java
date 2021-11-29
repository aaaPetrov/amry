package com.solvd.army.persistence;

import com.solvd.army.domain.Army;

import java.util.List;

public interface IArmyRepository {

    void create(Army army);

    void delete(Army army);

    void update(Army army);

    Army get(String country);

    List<Army> getAll();

}
