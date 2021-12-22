package com.solvd.army.service;

import com.solvd.army.domain.Army;

import java.util.List;

public interface IArmyService {

    void update(Army army);

    void delete(Army army);

    Army create(Army army);

    Army get(String country);

    List<Army> getAll();

    Integer getCount();

    List<Army> getArmyCountries();

}
