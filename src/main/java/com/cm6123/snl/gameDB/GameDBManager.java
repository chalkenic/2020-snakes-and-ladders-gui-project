package com.cm6123.snl.gameDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDBManager {

    /**
     * static method creates the database.
     * @param connection - the connection established on GUIFrame class.
     */
    public static void createDatabase(final Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("CREATE DATABASE IF NOT EXISTS `snakesAndLaddersDatabase`");

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
