package com.solvd.army.persistence.impl;

import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.persistence.IAmmoRepository;
import com.solvd.army.persistence.SessionHolder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AmmoMapperImpl implements IAmmoRepository {

    @Override
    public void create(@Param("ammo") Ammo ammo, @Param("militaryUnitId") Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IAmmoRepository ammoRepository = session.getMapper(IAmmoRepository.class);
            ammoRepository.create(ammo, militaryUnitId);
        }
    }

    @Override
    public void update(@Param("ammo") Ammo ammo, @Param("ammoId") Long ammoId, @Param("militaryUnitId") Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IAmmoRepository ammoRepository = session.getMapper(IAmmoRepository.class);
            ammoRepository.update(ammo, ammoId, militaryUnitId);
        }
    }

    @Override
    public List<Ammo> getByMilitaryUnitName(String militaryUnitName) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IAmmoRepository ammoRepository = session.getMapper(IAmmoRepository.class);
            return ammoRepository.getByMilitaryUnitName(militaryUnitName);
        }
    }

    @Override
    public List<Long> getId(Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IAmmoRepository ammoRepository = session.getMapper(IAmmoRepository.class);
            return ammoRepository.getId(militaryUnitId);
        }
    }

}
