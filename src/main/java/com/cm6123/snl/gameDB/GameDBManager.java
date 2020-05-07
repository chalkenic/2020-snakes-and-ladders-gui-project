package com.cm6123.snl.gameDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDBManager {
    /**
     * Method selects all snakes from the database.
     * @param connection
     */
    public void selectAllSnakes(final Connection connection) {
        String query = "select * from snakes ";
        Statement statement = null;
        ResultSet result = null;

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                String snakeHead = result.getString("snakeHead");
                System.out.println("snake ID: " + snakeHead);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
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

    /**
     *
     * @param connection
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

    public static void main(final String[] args) {
        GameDBManager test = new GameDBManager();
        Connection connect = GameDBUtils.connectGuiToDatabase();
        test.createDatabase(connect);
        test.selectAllSnakes(connect);

//        if (connect != null) {
//
//        }
    }
}
