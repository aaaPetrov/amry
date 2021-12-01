package com.solvd.army.persistence.impl;

import com.solvd.army.domain.resources.Weapon;
import com.solvd.army.persistence.IWeaponRepository;
import com.solvd.army.persistence.SessionHolder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class WeaponMapperImpl implements IWeaponRepository {

    @Override
    public void create(@Param("weapon") Weapon weapon, @Param("militaryUnitId") Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IWeaponRepository weaponRepository = session.getMapper(IWeaponRepository.class);
            weaponRepository.create(weapon, militaryUnitId);
        }
    }

    @Override
    public void update(@Param("weapon") Weapon weapon, @Param("weaponId") Long weaponId, @Param("militaryUnitId") Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IWeaponRepository weaponRepository = session.getMapper(IWeaponRepository.class);
            weaponRepository.update(weapon, weaponId, militaryUnitId);
        }
    }

    @Override
    public List<Weapon> getByMilitaryUnitName(String militaryUnitName) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IWeaponRepository weaponRepository = session.getMapper(IWeaponRepository.class);
            return weaponRepository.getByMilitaryUnitName(militaryUnitName);
        }
    }

    @Override
    public List<Long> getId(Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IWeaponRepository weaponRepository = session.getMapper(IWeaponRepository.class);
            return weaponRepository.getId(militaryUnitId);
        }
    }

}
