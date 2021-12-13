package com.solvd.army.persistence.impl;

import com.solvd.army.domain.resources.Tank;
import com.solvd.army.persistence.ITankRepository;
import com.solvd.army.persistence.SessionHolder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TankMapperImpl implements ITankRepository {

    @Override
    public void create(@Param("tank") Tank tank, @Param("militaryUnitId") Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            ITankRepository tankRepository = session.getMapper(ITankRepository.class);
            tankRepository.create(tank, militaryUnitId);
        }
    }

    @Override
    public void update(@Param("tank") Tank tank, @Param("tankId") Long tankId, @Param("militaryUnitId") Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            ITankRepository tankRepository = session.getMapper(ITankRepository.class);
            tankRepository.update(tank, tankId, militaryUnitId);
        }
    }

    @Override
    public List<Tank> getByMilitaryUnitName(String militaryUnitName) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            ITankRepository tankRepository = session.getMapper(ITankRepository.class);
            return tankRepository.getByMilitaryUnitName(militaryUnitName);
        }
    }

    @Override
    public List<Long> getId(Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            ITankRepository tankRepository = session.getMapper(ITankRepository.class);
            return tankRepository.getId(militaryUnitId);
        }
    }

}
