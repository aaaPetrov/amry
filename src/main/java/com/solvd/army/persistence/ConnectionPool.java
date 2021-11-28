package com.solvd.army.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {

    public volatile static ConnectionPool CONNECTION_POOL;

    private final List<Connection> connections;
    private final int size;

    static {
        CONNECTION_POOL = getInstance(5);
    }

    private ConnectionPool(int sizeOfPool) {
        this.size = sizeOfPool;
        this.connections = new ArrayList<>();
        classForName(Config.JDBC_DRIVER);
        IntStream.range(0, this.size).boxed()
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
            return this.connections.remove(connections.size()-1);
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classResult;
    }

    private static Connection getConnection(String url, String user, String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
