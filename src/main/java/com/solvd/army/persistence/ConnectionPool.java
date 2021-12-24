package com.solvd.army.persistence;


import com.solvd.army.domain.exception.ProcessingException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {

    public volatile static ConnectionPool CONNECTION_POOL;

    private final List<Connection> connections;

    static {
        CONNECTION_POOL = getInstance(5);
    }

    private ConnectionPool(int sizeOfPool) {
        this.connections = new ArrayList<>();
        IntStream.range(0, sizeOfPool).boxed()
                .forEach(index -> {
                    Connection connection = getConnection(Config.URL, Config.USER, Config.PASSWORD);
                    this.connections.add(connection);
                });
    }

    public Connection getConnection() {
        synchronized (this.connections) {
            while (this.connections.isEmpty()) {
                try {
                    connections.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return this.connections.remove(connections.size() - 1);
        }
    }

    public void releaseConnection(Connection connection) {
        synchronized (this.connections) {
            this.connections.add(connection);
            this.connections.notify();
        }
    }

    public static ConnectionPool getInstance(int sizeOfPool) {
        if (CONNECTION_POOL == null) {
            synchronized (ConnectionPool.class) {
                if (CONNECTION_POOL == null) {
                    CONNECTION_POOL = new ConnectionPool(sizeOfPool);
                }
            }
        }
        return CONNECTION_POOL;
    }

    private static Class<?> classForName(String className) {
        Class<?> classResult = null;
        try {
            classResult = Class.forName(className);
        } catch (ClassNotFoundException exception) {
            throw new ProcessingException("Cant load JDBC driver. " + exception.getMessage());
        }
        return classResult;
    }

    private static Connection getConnection(String url, String user, String password) {
        classForName(Config.JDBC_DRIVER);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            throw new ProcessingException("Cant get JDBC connection. " + exception.getMessage());
        }
        return connection;
    }

}
