package com.solvd.army.persistence.impl;

import com.solvd.army.domain.soldier.Soldier;
import com.solvd.army.persistence.ISoldierRepository;
import com.solvd.army.persistence.SessionHolder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SolderMapperImpl implements ISoldierRepository {

    @Override
    public void createSoldier(@Param("soldier") Soldier soldier, @Param("militaryUnitId") Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            ISoldierRepository soldierRepository = session.getMapper(ISoldierRepository.class);
            soldierRepository.createSoldier(soldier, militaryUnitId);
        }
    }

    @Override
    public void createRecruit(@Param("soldier") Soldier soldier, @Param("militaryUnitId") Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            ISoldierRepository soldierRepository = session.getMapper(ISoldierRepository.class);
            soldierRepository.createRecruit(soldier, militaryUnitId);
        }
    }

    @Override
    public void updateSoldier(@Param("soldier") Soldier soldier, @Param("militaryUnitId") Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            ISoldierRepository soldierRepository = session.getMapper(ISoldierRepository.class);
            soldierRepository.updateSoldier(soldier, militaryUnitId);
        }
    }

    @Override
    public void updateRecruit(@Param("soldier") Soldier soldier) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            ISoldierRepository soldierRepository = session.getMapper(ISoldierRepository.class);
            soldierRepository.updateRecruit(soldier);
        }
    }

    @Override
    public void delete(Soldier soldier) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            ISoldierRepository soldierRepository = session.getMapper(ISoldierRepository.class);
            soldierRepository.delete(soldier);
        }
    }

    @Override
    public List<Soldier> getByMilitaryUnitName(String militaryUnitName) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            ISoldierRepository soldierRepository = session.getMapper(ISoldierRepository.class);
            return soldierRepository.getByMilitaryUnitName(militaryUnitName);
        }
    }

    @Override
    public Soldier getById(Long id) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            ISoldierRepository soldierRepository = session.getMapper(ISoldierRepository.class);
            return soldierRepository.getById(id);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            ISoldierRepository soldierRepository = session.getMapper(ISoldierRepository.class);
            soldierRepository.deleteById(id);
        }
    }

    @Override
    public Integer getCount() {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            ISoldierRepository soldierRepository = session.getMapper(ISoldierRepository.class);
            return soldierRepository.getCount();
        }
    }

}
