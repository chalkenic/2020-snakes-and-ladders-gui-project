package com.cm6123.snl.gameDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Manager for the LoadGamePanel database links.
 */
public class LoadDataDBManager {

    private ArrayList<Integer> gameID;
    private ArrayList<Integer> totalFirstEntries;
    private ArrayList<Integer> totalSecondEntries;
    private ArrayList<String> players;

    /**
     * Initialises manager for usage where required.
     */
    public LoadDataDBManager() {
    }

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

    public void countSnakesInDatabase(final Connection connection) {
        String query = "SELECT * FROM Snakes ";
        Integer gameCount = null;
        Statement statement = null;
        ResultSet result = null;



        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            gameID = new ArrayList<>();
            totalFirstEntries = new ArrayList<>();
            totalSecondEntries = new ArrayList<>();

            while (result.next()) {
                gameID.add(result.getInt("game_gameID"));
                totalFirstEntries.add(result.getInt("snakeHead"));
                totalSecondEntries.add(result.getInt("snakeTail"));

            }
            System.out.println(gameID.get(0));
            System.out.println(totalFirstEntries.get(0));
            System.out.println(totalSecondEntries.get(0));
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

    public void countLaddersInDatabase(final Connection connection) {
        String query = "SELECT COUNT(*) AS totalCount FROM Ladders ";
        Integer gameCount = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            gameID = new ArrayList<>();
            totalFirstEntries = new ArrayList<>();
            totalSecondEntries = new ArrayList<>();

            while (result.next()) {
                gameID.add(result.getInt("game_gameID"));
                totalFirstEntries.add(result.getInt("snakeHead"));
                totalSecondEntries.add(result.getInt("snakeTail"));
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

    public static Integer countBoostsInDatabase(final Connection connection) {
        String query = "SELECT COUNT(*) AS totalCount FROM Boosts ";
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

    public static Integer countPlayersInDatabase(final Connection connection) {
        String query = "SELECT COUNT(*) AS totalCount FROM PlayerList ";
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
    public static Integer countDiceInDatabase(final Connection connection) {
        String query = "SELECT COUNT(*) AS totalCount FROM Dice ";
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

    public ArrayList<Integer> getTotalFirstEntries() {
        return totalFirstEntries;
    }

    public ArrayList<Integer> getTotalSecondEntries() {
        return totalSecondEntries;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }
}
