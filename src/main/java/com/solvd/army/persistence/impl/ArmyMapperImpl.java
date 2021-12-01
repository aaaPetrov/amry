package com.solvd.army.persistence.impl;

import com.solvd.army.domain.Army;
import com.solvd.army.persistence.IArmyRepository;
import com.solvd.army.persistence.SessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ArmyMapperImpl implements IArmyRepository {

    @Override
    public void create(Army army) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IArmyRepository armyRepository = session.getMapper(IArmyRepository.class);
            armyRepository.create(army);
        }
    }

    @Override
    public void delete(Army army) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IArmyRepository armyRepository = session.getMapper(IArmyRepository.class);
            armyRepository.delete(army);
        }
    }

    @Override
    public void update(Army army) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IArmyRepository armyRepository = session.getMapper(IArmyRepository.class);
            armyRepository.update(army);
        }
    }

    @Override
    public Army get(String country) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IArmyRepository armyRepository = session.getMapper(IArmyRepository.class);
            return armyRepository.get(country);
        }
    }

    @Override
    public List<Army> getAll() {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IArmyRepository armyRepository = session.getMapper(IArmyRepository.class);
            return armyRepository.getAll();
        }
    }

}
