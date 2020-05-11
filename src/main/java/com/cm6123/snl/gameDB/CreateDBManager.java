package com.cm6123.snl.gameDB;

import java.sql.*;

/**
 * Utility class handles database creation on main menu. Marked final to avoid subclass extension.
 */
public final class CreateDBManager {

    private CreateDBManager() {
    }
    /**
     * static method creates the database.
     * @param connection - the connection established on GUIFrame class.
     * @param databaseName - name of the database.
     */
    public static void createDatabase(final Connection connection, final String databaseName) {

        Statement statement = null;
        try {
            statement = connection.createStatement();
            DatabaseMetaData dmbd = connection.getMetaData();
            System.out.println(dmbd);
            String[] types = {"table"};
            ResultSet rs = dmbd.getTables(null, null, "%", types);
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_NAME"));
            }
            statement.execute("CREATE DATABASE IF NOT EXISTS " + databaseName);
            System.out.println("test: " + connection.getSchema());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
