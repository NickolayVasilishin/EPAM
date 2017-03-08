package com.epam.jmp.softwareprinciples;

import org.apache.commons.dbcp2.BasicDataSource;

import java.net.URI;
import java.sql.*;

public class QueryManager implements AutoCloseable {
    /**
     * This class encapsulates all execution flow of query to Students table case.
     * In takes connection from connectionPool (I assumed that URL will be unchanged, but it can be specified as ENV variable),
     * then prepares statement with specified SQL query (it also can provide this statement to set some fields within)
     * and provides resultSet with results of this query.
     * Also this class allows to close all resources at one time.
     */
    private static volatile BasicDataSource connectionPool;
    public static final String LOCALDB_ADDRESS = "localhost:4000/localdb";
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private static BasicDataSource getConnectionPool() {
        if (connectionPool == null) {
            synchronized (QueryManager.class) {
                if (connectionPool == null) {
                    connectionPool = new BasicDataSource();
                    String url = System.getenv("DATABASE_URL");
                    connectionPool.setUrl(url == null ? LOCALDB_ADDRESS : url);
                }
            }
        }
        return connectionPool;
    }

    public QueryManager setQuery(String sql) throws SQLException {
        connection = QueryManager.getConnectionPool().getConnection();
        statement = connection.prepareStatement(sql);
        return this;
    }

    public PreparedStatement getPreparedStatement() {
        checkConnection();
        return statement;
    }

    public ResultSet getResultSet() throws SQLException {
        checkConnection();
        resultSet = statement.executeQuery();
        return resultSet;
    }

    @Override
    public void close() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    private void checkConnection() {
        if (connection == null) {
            throw new IllegalStateException("Query should be specified first.");
        }
    }
}
