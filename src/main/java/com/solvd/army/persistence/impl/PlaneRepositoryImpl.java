package com.solvd.army.persistence.impl;

import com.solvd.army.domain.MilitaryUnit;
import com.solvd.army.domain.exception.ProcessingException;
import com.solvd.army.domain.resources.Plane;
import com.solvd.army.persistence.ConnectionPool;
import com.solvd.army.persistence.IPlaneRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaneRepositoryImpl implements IPlaneRepository {

    private final static String SQL_COMMAND = SELECT_COMMAND();

    @Override
    public List<Plane> getByMilitaryUnitName(String militaryUnitName) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        List<Plane> planes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_COMMAND)) {
            preparedStatement.setString(1, militaryUnitName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Plane newPlane = new Plane();
                Plane.PlaneType planeType = Plane.PlaneType.valueOf(resultSet.getString("type"));
                newPlane.setPlaneType(planeType);
                newPlane.setAmount(resultSet.getInt("amount"));
                planes.add(newPlane);
            }
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
        return planes;
    }

    @Override
    public List<Long> getId(Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommandSelect = "select plane_id from military_unit_plane where military_unit_id = ?;";
        List<Long> ids = null;
        try (PreparedStatement preparedStatementSelect = connection.prepareStatement(sqlCommandSelect)) {
            preparedStatementSelect.setLong(1, militaryUnitId);
            ResultSet resultSet = preparedStatementSelect.executeQuery();
            if (resultSet != null) {
                ids = new ArrayList<>();
            }
            while (resultSet.next()) {
                ids.add(resultSet.getLong("plane_id"));
            }
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
        return ids;
    }

    @Override
    public void update(Plane plane, Long planeId, Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommandUpdate = "update military_unit_plane set plane_id = ?, amount = ? where military_unit_id = ? and plane_id = ?;";
        try (PreparedStatement preparedStatementUpdate = connection.prepareStatement(sqlCommandUpdate)) {
            preparedStatementUpdate.setLong(1, plane.getPlaneType().getPlaneId());
            preparedStatementUpdate.setInt(2, plane.getAmount());
            preparedStatementUpdate.setLong(3, militaryUnitId);
            preparedStatementUpdate.setLong(4, planeId);
            preparedStatementUpdate.executeUpdate();
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void create(Plane plane, Long militaryUnitId) {
        Connection connection = ConnectionPool.CONNECTION_POOL.getConnection();
        String sqlCommand = "insert into military_unit_plane(plane_id, military_unit_id, amount) value(?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setLong(1, plane.getPlaneType().getPlaneId());
            preparedStatement.setLong(2, militaryUnitId);
            preparedStatement.setLong(3, plane.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        } finally {
            ConnectionPool.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public static List<Plane> fillPlanes(ResultSet resultSet, List<MilitaryUnit> militaryUnits) {
        List<Plane> planes = null;
        try {
            Long militaryUnitPlaneId = resultSet.getLong("military_unit_plane_id");
            Long militaryUnitPlaneMilitaryUnitId = resultSet.getLong("military_unit_plane_military_unit_id");
            for (MilitaryUnit militaryUnit : militaryUnits) {
                if (militaryUnit.getId() == militaryUnitPlaneMilitaryUnitId) {
                    planes = militaryUnit.getPlanes();
                    Plane plane = createIfNotExist(militaryUnitPlaneId, planes);
                    Plane.PlaneType planeType = Plane.PlaneType.valueOf(resultSet.getString("plane_type"));
                    plane.setPlaneType(planeType);
                    plane.setAmount(resultSet.getInt("amount"));
                }
            }
        } catch (SQLException exception) {
            throw new ProcessingException(exception.getMessage());
        }
        return planes;
    }

    private static Plane createIfNotExist(Long militaryUnitPlaneId, List<Plane> planes) {
        Plane result = null;
        if (!planes.isEmpty()) {
            for (Plane plane : planes) {
                if (militaryUnitPlaneId.equals(plane.getPlaneType().getPlaneId())) {
                    result = plane;
                }
            }
            if (result == null) {
                Plane newPLane = new Plane();
                planes.add(newPLane);
                result = newPLane;
            }
        } else {
            Plane newPLane = new Plane();
            planes.add(newPLane);
            result = newPLane;
        }
        return result;
    }

    private static String SELECT_COMMAND() {
        return "select MUP.plane_id, MUP.military_unit_id, MUP.amount, P.id, P.type from military_unit_plane as MUP "
                + "inner join planes as P on MUP.plane_id = P.id "
                + "where military_unit_id = (select id from military_units where name = ?);";
    }

}
