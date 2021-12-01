package com.solvd.army.persistence.impl;

import com.solvd.army.domain.resources.Plane;
import com.solvd.army.persistence.IPlaneRepository;
import com.solvd.army.persistence.SessionHolder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PlaneMapperImpl implements IPlaneRepository {

    @Override
    public void create(@Param("plane") Plane plane, @Param("militaryUnitId") Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IPlaneRepository planeRepository = session.getMapper(IPlaneRepository.class);
            planeRepository.create(plane, militaryUnitId);
        }
    }

    @Override
    public void update(@Param("plane") Plane plane, @Param("planeId") Long ammoId, @Param("militaryUnitId") Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IPlaneRepository planeRepository = session.getMapper(IPlaneRepository.class);
            planeRepository.update(plane, ammoId, militaryUnitId);
        }
    }

    @Override
    public List<Plane> getByMilitaryUnitName(String militaryUnitName) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IPlaneRepository planeRepository = session.getMapper(IPlaneRepository.class);
            return planeRepository.getByMilitaryUnitName(militaryUnitName);
        }
    }

    @Override
    public List<Long> getId(Long militaryUnitId) {
        try (SqlSession session = SessionHolder.getSqlSessionFactory().openSession(true)) {
            IPlaneRepository planeRepository = session.getMapper(IPlaneRepository.class);
            return planeRepository.getId(militaryUnitId);
        }
    }

}
