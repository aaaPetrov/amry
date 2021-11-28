package com.solvd.army.service;

import com.solvd.army.domain.Army;
import java.util.List;

public interface IArmyService {

    Army update(Army army);

    void delete(Army army);

    Army insert(Army army);

    Army select(String country);

    List<Army> selectAll();

}
