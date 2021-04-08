package com.etf.zadatak2.dao;

import com.etf.zadatak2.exception.AgencyException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dusan
 */
public class ResourcesManager {

    private static final String JDBC_CONNECTION_STRING = "jdbc:mysql://localhost:3308/zadatak2tema8?user=root&password=";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    static {
        try {
            Class.forName(DRIVER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(JDBC_CONNECTION_STRING);
        return con;
    }

    public static void closeResources(ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    public static void closeConnection(Connection con) throws AgencyException {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                throw new AgencyException("Failed to close database connection.", ex);
            }
        }
    }

    public static void rollbackTransactions(Connection con) throws AgencyException {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new AgencyException("Failed to rollback database transactions.", ex);
            }
        }
    }
}
