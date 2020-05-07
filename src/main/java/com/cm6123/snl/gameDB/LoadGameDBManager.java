package com.cm6123.snl.gameDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Manager for the LoadGamePanel database links.
 */
public class LoadGameDBManager {

    /**
     * Counts all games recorded in database.
     * @param connection utility connection.
     * @return total number of games in database.
     */
    public static Integer countGamesInDatabase(final Connection connection) {
        String query = "SELECT COUNT(*) AS totalCount FROM Game ";
        Integer gameCount = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                gameCount = result.getInt("totalCount");
                System.out.println(gameCount);
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
        return gameCount;
    }
}
