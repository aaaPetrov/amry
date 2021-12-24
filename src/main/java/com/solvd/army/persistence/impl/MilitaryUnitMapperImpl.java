package com.solvd.army.persistence.impl;

import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.persistence.IMilitaryUnitRepository;
import com.solvd.army.persistence.ISoldierRepository;
import com.solvd.army.persistence.SessionHolder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MilitaryUnitMapperImpl implements IMilitaryUnitRepository {

    @Override
    public void create(@Param("militaryUnit") MilitaryUnit militaryUnit, @Param("armyId") Long armyId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IMilitaryUnitRepository militaryUnitRepository = session.getMapper(IMilitaryUnitRepository.class);
            militaryUnitRepository.create(militaryUnit, armyId);
        }
    }

    @Override
    public void update(@Param("militaryUnit") MilitaryUnit militaryUnit, @Param("armyId") Long armyId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IMilitaryUnitRepository militaryUnitRepository = session.getMapper(IMilitaryUnitRepository.class);
            militaryUnitRepository.update(militaryUnit, armyId);
        }
    }

    @Override
    public List<MilitaryUnit> get(String country) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IMilitaryUnitRepository militaryUnitRepository = session.getMapper(IMilitaryUnitRepository.class);
            return militaryUnitRepository.get(country);
        }
    }

    @Override
    public Integer getCount() {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IMilitaryUnitRepository militaryUnitRepository = session.getMapper(IMilitaryUnitRepository.class);
            return militaryUnitRepository.getCount();
        }
    }

    @Override
    public Long getFirstMilitaryUnitId() {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IMilitaryUnitRepository militaryUnitRepository = session.getMapper(IMilitaryUnitRepository.class);
            return militaryUnitRepository.getFirstMilitaryUnitId();
        }
    }

}
