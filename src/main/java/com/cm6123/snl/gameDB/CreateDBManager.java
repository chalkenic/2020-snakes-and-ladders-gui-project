package com.cm6123.snl.gameDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
            statement.execute("CREATE DATABASE IF NOT EXISTS `databaseName`");

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
