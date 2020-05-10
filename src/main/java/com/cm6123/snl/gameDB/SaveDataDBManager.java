package com.cm6123.snl.gameDB;

import com.cm6123.snl.Game;
import com.cm6123.snl.dice.DiceSet;

import java.sql.*;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Class for saving a current game into database.
 */
public class SaveDataDBManager {
    /**
     * Game object to add into database.
     */
    private Game newGameSave;
    /**
     * Dice to save into database.
     */
    private DiceSet dice;
    /**
     * Holds gameID - creates new one if a new save gave by pulling brand new gameID from database.
     */
    private Integer gameID;
    /**
     * grabs gridsize from board.
     */
    private Integer gridSize;
    /**
     * Holds all special square vales as named keys & array of integers.
     */
    private TreeMap<String, Integer[]> allTestSpecials;
    /**
     * Informs manager whether saving to new or existing game.
     */
    private Boolean loaded;
    /**
     * Constructor here used for marking a game as ended inside database.
     * @param id - current game ID for a loaded game.
     */
    public SaveDataDBManager(final Integer id) {
        this.gameID = id;
    }
    /**
     * Constructor used for storing values for incoming new save as file into database.
     * @param gameSave - the game.
     * @param gameDice - dice being used.
     * @param size - grid size of game.
     * @param specialList - all snakes/ladders/boosts used.
     * @param loadedGame - was this game loaded or is it a new file?
     * @param id - current game ID. A new save will be null, loaded game will be integer.
     */
    public SaveDataDBManager(final Game gameSave, final DiceSet gameDice, final Integer size,
                             final TreeMap specialList, final Boolean loadedGame, final Integer id) {
        this.newGameSave = gameSave;
        this.dice = gameDice;
        this.gridSize = size;
        this.allTestSpecials = specialList;
        this.loaded = loadedGame;
        this.gameID = id;
    }

    /**
     * Method used to mark gameover in database.
     * @param connection - database connection link.
     */
    public void markGameAsEnded(final Connection connection) {
        CallableStatement saveStatement = null;
        String procedure = null;
        try {
            procedure = "{CALL manually_update_gameover(?)}";
            //Attempts to mark game in database as gameover depending on ID in object.
            saveStatement = connection.prepareCall(procedure);
            saveStatement.setInt(1, gameID);
            saveStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
            if (saveStatement != null) {
                saveStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves game to database. Condition at beginning determines whether game is new or not.
     * @param connection - databse connection link
     */
    public void saveCurrentGame(final Connection connection) {
        //Game will be updated in database if game was loaded originally.
        if (!loaded) {
            //methods save data to their respective positions. dice & game added together.
            saveGame(connection);
            savePlayers(connection);

            if (newGameSave.getBoostSquareOn()) {
                saveboostFeatureOn(connection);
            }

            if (newGameSave.isWinningSquareOn()) {
                saveWinningSquareFeatureOn(connection);
            }

            if (allTestSpecials != null) {
                saveSpecials(connection);
            }
        } else {
                updatePlayerPosition(connection);
        }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    /**
     * Saves generic game data into Game table in database.
     * @param connection -database connection link.
     */
    private void saveGame(final Connection connection) {
        CallableStatement saveStatement = null;
        Statement gameStatement = null;
        String procedure = null;
        String query = null;
        ResultSet result = null;

        procedure = "{CALL add_new_game(?,?,?)}";
        //Query used after procedure to grab the new gameID created.
        query = "SELECT gameID FROM Game ORDER BY gameID DESC LIMIT 1;";


        try {
            saveStatement = connection.prepareCall(procedure);
            saveStatement.setInt(1, gridSize);
            saveStatement.setInt(2, dice.getCount());
            saveStatement.setInt(3, dice.getFaces());

            saveStatement.executeQuery();

            gameStatement = connection.createStatement();
            result = gameStatement.executeQuery(query);

            while (result.next()) {
                //new gameID storted into object for later additions.
                gameID = result.getInt("gameID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *Save all special squares to the respective table (snakes/ladders/boosts).
     * @param connection - database connection link.
     */
    private void saveSpecials(final Connection connection) {
        CallableStatement saveStatement = null;
        String procedure = null;
        //Loops through TreeMap keys & adds data to specific table depending on key String.
        for (Map.Entry<String, Integer[]> entry : allTestSpecials.entrySet()) {
            String key = entry.getKey(); //stores String.
            Integer[] value = entry.getValue(); //stores array of all squares related to string.

            if (key == "snakes" && value.length > 0) {
                //Adds any snakes that are inside array linked to "snakes" key.
                // Validation to ensure more than 1 value.
                procedure = "{CALL add_new_snake(?,?,?)}";
                //Adds snake head into field 1, snake tail into field + 1. For loop increments by 2.
                for (int i = 0; i < value.length; i += 2) {
                    try {
                        saveStatement = connection.prepareCall(procedure);
                        saveStatement.setInt(1, value[i]);
                        saveStatement.setInt(2, value[i + 1]);
                        saveStatement.setInt(3, gameID);

                        saveStatement.executeQuery();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else if (key == "ladders" && value.length > 0) {
                //Adds any ladders that are inside array linked to "ladders" key.
                // Validation to ensure more than 1 value.
                procedure = "{CALL add_new_ladder(?,?,?)}";
                //Adds ladder foot into field 1, ladder top into field + 1. For loop increments by 2.
                for (int i = 0; i < value.length; i += 2) {
                    try {
                        saveStatement = connection.prepareCall(procedure);
                        saveStatement.setInt(1, value[i]);
                        saveStatement.setInt(2, value[i + 1]);
                        saveStatement.setInt(3, gameID);

                        saveStatement.executeQuery();
//                        counter += 2;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else if (key == "boosts" && value.length > 0) {
                procedure = "{CALL add_new_boost(?,?)}";
                //Adds any boosts that are inside array linked to "boosts" key.
                for (int i = 0; i < value.length; i++) {
                    try {
                        saveStatement = connection.prepareCall(procedure);
                        saveStatement.setInt(1, value[i]);
                        saveStatement.setInt(2, gameID);

                        saveStatement.executeQuery();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Save players to Players table, assigns them gameID from constructor to link them to saved game.
     * @param connection - database connection link.
     */
    private void savePlayers(final Connection connection) {
        CallableStatement saveStatement = null;
        String procedure = null;
        Integer playerPos;
        String playerCol;
        Boolean saving = false;


        for (int i = 0; i < newGameSave.numberOfPlayers(); i++) {
            procedure = "{CALL add_new_player_to_game(?,?,?)}";
            playerCol = newGameSave.getPlayer(i).getColour().toString();
            //Assigns Colour & player number to game, along with their associated game via gameID.
            try {
                saveStatement = connection.prepareCall(procedure);
                saveStatement.setString(1, playerCol);
                saveStatement.setInt(2, gameID);
                saveStatement.setInt(3, i);
                saving = true;
                saveStatement.executeQuery();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        updatePlayerPosition(connection);
    }

    /**
     * mark boost feature as on for game.
     * @param connection - database connection link.
     */
    private void saveboostFeatureOn(final Connection connection) {
        CallableStatement saveStatement = null;
        String procedure = null;

        procedure = "{CALL switch_on_boost_feature(?)}";
        try {

            saveStatement = connection.prepareCall(procedure);
            saveStatement.setInt(1, gameID);
            saveStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * mark winning square feature as on for game.
     * @param connection - database connection link.
     */
    private void saveWinningSquareFeatureOn(final Connection connection) {

        CallableStatement saveStatement = null;
        String procedure = null;

        procedure = "{CALL switch_on_winning_feature(?)}";
        try {
            saveStatement = connection.prepareCall(procedure);
            saveStatement.setInt(1, gameID);
            saveStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Moves each player from game to their new respective position.
     * @param connection - database connection link.
     */
    private void updatePlayerPosition(final Connection connection) {
        CallableStatement saveStatement = null;
        String procedure = null;
        Integer playerPos;
        String playerCol;
        //Players are added to game in order - for loop adds their respective new positions.
        for (int i = 0; i < newGameSave.numberOfPlayers(); i++) {
            procedure = "{CALL update_player_position(?,?,?)}";
            playerPos = newGameSave.getPlayer(i).getPosition().get();
            playerCol = newGameSave.getPlayer(i).getColour().toString();

            try {
                saveStatement = connection.prepareCall(procedure);
                saveStatement.setString(1, playerCol);
                saveStatement.setInt(2, gameID);
                saveStatement.setInt(3, playerPos);

                saveStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Grabs game ID for a RunGamePanel to hold onto incase a brand new game is resaved in same session.
     * @return game id in use by current game.
     */
    public Integer getGameID() {
        return gameID;
    }
}
