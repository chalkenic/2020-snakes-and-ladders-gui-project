package com.cm6123.snl.gameDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Manager for loading database game information into edit choice & counting games for loading game.
 */
public class LoadDataDBManager {
    /**
     * Records games that special squares appear in.
     */
    private ArrayList<Integer> gameInclusionID;
    /**
     * Records location of edit choice.
     */
    private ArrayList<Integer> tableID;
    /**
     * Records first entry (snakehead/ladderfoot/boost).
     */
    private ArrayList<Integer> totalFirstEntries;
    /**
     * Records second entry (snakeTail/ladderTop/diceFaces).
     */
    private ArrayList<Integer> totalSecondEntries;
    /**
     * Records all player names/colours currently in database.
     */
    private ArrayList<String> players;
    /**
     * Initialises Load manager for usage where required.
     */
    public LoadDataDBManager() {
    }
    /**
     * Counts all games recorded in database.
     * @param connection utility connection.
     * @return total number of games in database.
     */
    public static Integer countGamesInDatabase(final Connection connection) {
        String query = "SELECT COUNT(*) AS totalCount FROM Game";
        Integer gameCount = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                gameCount = result.getInt("totalCount");
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
    /**
     * Grabs total count of snakes & appends each row data into related arraylist.
     * @param connection - database connection link.
     */
    public void countSnakesInDatabase(final Connection connection) {
        String query = "SELECT * FROM Snakes ";
        Statement statement = null;
        ResultSet result = null;

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            tableID = new ArrayList<>();
            gameInclusionID =  new ArrayList<>();
            totalFirstEntries = new ArrayList<>();
            totalSecondEntries = new ArrayList<>();

            while (result.next()) {
                tableID.add(result.getInt("snakeID"));
                gameInclusionID.add(result.getInt("game_gameID"));
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
    /**
     * Grabs total count of ladders & appends each row data into related arraylist.
     * @param connection - database connection link.
     */
    public void countLaddersInDatabase(final Connection connection) {
        String query = "SELECT * FROM Ladders ";
        Statement statement = null;
        ResultSet result = null;

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            tableID = new ArrayList<>();
            gameInclusionID =  new ArrayList<>();
            totalFirstEntries = new ArrayList<>();
            totalSecondEntries = new ArrayList<>();

            while (result.next()) {
                tableID.add(result.getInt("ladderID"));
                gameInclusionID.add(result.getInt("game_gameID"));
                totalFirstEntries.add(result.getInt("ladderFoot"));
                totalSecondEntries.add(result.getInt("ladderTop"));
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
     * Grabs total count of boosts & appends each row data into related arraylist.
     * @param connection - database connection link.
     */
    public void countBoostsInDatabase(final Connection connection) {
        String query = "SELECT * FROM Boosts ";
        Statement statement = null;
        ResultSet result = null;

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            tableID = new ArrayList<>();
            gameInclusionID =  new ArrayList<>();
            totalFirstEntries = new ArrayList<>();

            while (result.next()) {
                tableID.add(result.getInt("boostID"));
                gameInclusionID.add(result.getInt("game_gameID"));
                totalFirstEntries.add(result.getInt("boostLocation"));
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
     * Grabs total count of players & appends each name & id into respective arraylists.
     * @param connection - database connection link.
     */
    public void countPlayersInDatabase(final Connection connection) {
        String query = "SELECT * FROM PlayerList ";
        Statement statement = null;
        ResultSet result = null;

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            tableID = new ArrayList<>();
            players = new ArrayList<>();

            while (result.next()) {
                tableID.add(result.getInt("playerListID"));
                players.add(result.getString("playerColour"));
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
     * Grabs total count of dice & appends each row data into related arraylist.
     * @param connection - database connection link.
     */
    public void countDiceInDatabase(final Connection connection) {
        String query = "SELECT * FROM Dice ";
        Statement statement = null;
        ResultSet result = null;

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            tableID = new ArrayList<>();
            totalFirstEntries = new ArrayList<>();
            totalSecondEntries = new ArrayList<>();

            while (result.next()) {
                tableID.add(result.getInt("diceID"));
                totalFirstEntries.add(result.getInt("diceCount"));
                totalSecondEntries.add(result.getInt("diceFaces"));

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
     * Getter for confirming total table row data. Used for creating JList size in respective edit choices.
     * @return size of the table.
     */
    public Integer getSelectionSize() {
        return tableID.size();
    }

    /**
     * Confirms which game the row belongs to (used for snakes/ladders/boosts).
     * @param choice - choice of row.
     * @return gameInclusionID - game of occurence.
     */
    public Integer getGameInclusionID(final Integer choice) {
        return gameInclusionID.get(choice);
    }

    /**
     * Confirms the table specific ID of given choice.
     * @param choice - choice of row.
     * @return tableID - ID of row.
     */
    public Integer getTableID(final Integer choice) {
        return tableID.get(choice);
    }
    /**
     * Confirms the first entry source from database row.
     * @param choice which row to source data from.
     * @return integer at specific data row.
     */
    public Integer getTotalFirstEntries(final Integer choice) {
        return totalFirstEntries.get(choice);
    }
    /**
     * Confirms the second entry (if exists) source from database row.
     * @param choice which row to source data from.
     * @return totalSecondEntries - integer at specific row.
     */
    public Integer getTotalSecondEntries(final Integer choice) {
        return totalSecondEntries.get(choice);
    }
    /**
     * Confirms the player name/colour  from database row.
     * @param choice which row to source data from.
     * @return string at specific data row.
     */
    public String getPlayers(final Integer choice) {
        return players.get(choice);
    }
}
