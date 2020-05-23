
package com.cm6123.snl.gameDB;

import java.sql.*;
import java.util.ArrayList;

/**
 * Object stores all data from database as a file object for choice & reading from CreateGame & GUIFrame respectively.
 * Built inside LoadGamePanel upon initialising panel.
 */
public class DBGameFile {
    /**
     * Holds gameText used for identifying games on LoadGamePanel JList.
     */
    private String gameText;
    /**
     * ID of game in file.
     */
    private Integer gameID;
    /**
     * Current player turn stored (TBC).
     */
    private Integer gamePlayerTurn;
    /**
     * current game round (TBC).
     */
    private Integer gameRound;
    /**
     * Size of of game board as grid (full size not parsed).
     */
    private Integer boardSize;
    /**
     * Confirms if game is over - determines whether LoadGamePanel will show in JList.
     */
    private Boolean gameOver;
    /**
     * Has the game enabled boost feature.
     */
    private Boolean boostSquareFeature;
    /**
     * Has the game enabled winning square feature.
     */
    private Boolean winningSquareFeature;
    /**
     * which dice is being used inside database.
     */
    private Integer diceChoice;
    /**
     * how many copies of the dice choice does the game use.
     */
    private Integer diceCount;
    /**
     * how many faces does the dice choice have.
     */
    private Integer diceFaces;
    /**
     * Total player count for game.
     */
    private Integer totalPlayers;
    /**
     * Where each player is currently located inside game.
     */
    private ArrayList<Integer> playerPositions;
    /**
     * Locations of all snakes inside game.
     */
    private ArrayList<Integer> gameSnakes;
    /**
     * Locations of all ladders inside game.
     */
    private ArrayList<Integer> gameLadders;
    /**
     * Locations of all boosts (if feature turned on) inside game.
     */
    private ArrayList<Integer> gameBoosts;

    /**
     * Instancing object pulls all data from database depending on the gameID given.
     * @param newID - id of game.
     * @param text - dynamic file name depending on ID parsed in.
     */
    public DBGameFile(final int newID, final String text) {
        this.gameID = newID;
        this.gameText = text;
        //Calls databse connection for usage by all following methods.
        Connection connect = GameDBUtils.connectGuiToDatabase(ConstantDatabaseName.DATABASENAME);
        //Methods run depending on initial DatabaseGameDatam method.
        addDatabaseGameData(connect);
        addDiceData(connect);

        gameSnakes = new ArrayList<>();
        addSnakeData(connect);
        gameLadders = new ArrayList<>();
        addLadderData(connect);

        playerPositions = new ArrayList<>();
        addPlayers(connect);
        //method called only if game informs file feature is on.
        if (boostSquareFeature) {
            gameBoosts = new ArrayList<>();
            addBoostData(connect);
        }
        try {
            if (connect != null) {
                connect.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Sources all generic game data by DBGameFile's id entry.
     * @param connect - database connection link.
     */
    private void addDatabaseGameData(final Connection connect) {
        //Holds values of database game upon completion of query.
        ResultSet result = null;

        if (connect != null) {
            PreparedStatement query = null;
            try { //Attempts database connection with provided string query. statement provided integer as parameter.
                query = connect.prepareStatement("SELECT * FROM Game WHERE gameID=?;");
                query.setInt(1, gameID);
                result = query.executeQuery();
                //Collects  data from columns specified depending on gameID.
                while (result.next()) {
                    gameID = result.getInt("gameID");
                    gamePlayerTurn = result.getInt("gamePlayerTurn");
                    gameRound = result.getInt("gameRound");
                    boardSize = result.getInt("boardGridSize");
                    gameOver = result.getBoolean("gameHasEnded");
                    boostSquareFeature = result.getBoolean("boostSquareFeature");
                    winningSquareFeature = result.getBoolean("winningSquareOnlyFeature");
                    diceChoice = result.getInt("dice_diceID");
                }
                //Error thrown if database connection fails.
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try { //Closes database connections if still open.
                    if (result != null) {
                        result.close();
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Grabs dice depending on gameID sourced from prior method.
     * @param connect - database connection link.
     */
    private void addDiceData(final Connection connect) {
        ResultSet result = null;
        //Statement type holds a DB procedure that will be called upon execution.
        CallableStatement statement = null;
        String procedure = null;
        if (connect != null) {
            try { //attempts to call a specfic procedure inside game using gameID for parameter.
                procedure = "{CALL select_dice_choice_from_game(?)}";
                statement = connect.prepareCall(procedure);
                statement.setInt(1, gameID);
                result = statement.executeQuery();

                while (result.next()) { //Stores specific dice into game file object.
                    diceFaces = result.getInt("diceFaces");
                    diceCount = result.getInt("diceCount");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Grabs all snakes depending on gameID sourced from prior method.
     * @param connect - database connection link.
     */
    private void addSnakeData(final Connection connect) {
        ResultSet result = null;
        CallableStatement statement = null;
        String procedure = null;
        if (connect != null) {
            try {
                procedure = "{CALL select_game_snakes(?)}";
                statement = connect.prepareCall(procedure);
                statement.setInt(1, gameID);
                result = statement.executeQuery();

                while (result.next()) { //Stores specific snakes into game file ArrayList.
                    gameSnakes.add(result.getInt("snakehead"));
                    gameSnakes.add(result.getInt("snakeTail"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Grabs all ladders depending on gameID sourced from prior method.
     * @param connect  - database connection link.
     */
    private void addLadderData(final Connection connect) {
        ResultSet result = null;
        CallableStatement statement = null;
        String procedure = null;
        if (connect != null) {
            try {
                procedure = "{CALL select_game_ladders(?)}";

                statement = connect.prepareCall(procedure);

                statement.setInt(1, gameID);
                result = statement.executeQuery();

                while (result.next()) { //Stores specific ladders into game file ArrayList.
                    gameLadders.add(result.getInt("ladderFoot"));
                    gameLadders.add(result.getInt("ladderTop"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Grabs all boosts (if using) depending on gameID sourced from prior method.
     * @param connect - database connection link.
     */
    private void addBoostData(final Connection connect) {
        ResultSet result = null;
        CallableStatement statement = null;
        String procedure = null;
        if (connect != null) {
            try {
                procedure = "{CALL select_game_boosts(?)}";

                statement = connect.prepareCall(procedure);

                statement.setInt(1, gameID);
                result = statement.executeQuery();

                while (result.next()) { //Stores specific boosts into game file ArrayList.
                    gameBoosts.add(result.getInt("boostLocation"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Counts how many players are linked to gameID & appends counter. Positions added into arraylist.
     * @param connect - database connection link.
     */
    private void addPlayers(final Connection connect) {
        ResultSet result = null;
        CallableStatement statement = null;
        String procedure = null;
        totalPlayers = 0;
        if (connect != null) {
            try {
                procedure = "{CALL select_players_from_game(?)}";

                statement = connect.prepareCall(procedure);

                statement.setInt(1, gameID);
                result = statement.executeQuery();

                while (result.next()) { //appends total player count & adds positions into ArrayList.
                    totalPlayers++;
                    playerPositions.add(result.getInt("playerPosition"));
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * prints gameFile onto JList as a string "file + (gameID)".
     * @return gameText - dynamic string text.
     */
    public String toString() {
        return gameText;
    }

    /**
     * Get the game's ID for loading usage.
     * @return gameID - file's game id.
     */
    public int getGameID() {
        return gameID;
    }
    /**
     * get player's turn (TBC).
     * @return gamePlayerTurn - current player turn in game.
     */
    public Integer getGamePlayerTurn() {
        return gamePlayerTurn;
    }
    /**
     * get board size for game loading use.
     * @return boardSize - board grid size
     */
    public Integer getBoardSize() {
        return boardSize;
    }
    /**
     * get confirmation of boost feature.
     * @return boostSquareFeature - feature on or off.
     */
    public Boolean getBoostSquareFeature() {
        return boostSquareFeature;
    }
    /**
     * get confirmation of winning square feature.
     * @return winningSquareFeature - feature on or off.
     */
    public Boolean getWinningSquareFeature() {
        return winningSquareFeature;
    }
    /**
     * get size of each die.
     * @return diceFaces - die size.
     */
    public Integer getDiceFaces() {
        return diceFaces;
    }

    /**
     * get amount of dice used.
     * @return diceCount - total amount.
     */
    public Integer getDiceCount() {
        return diceCount;
    }
    /**
     * get player count.
     * @return totalPlayers - amount of players in game.
     */
    public Integer getTotalPlayers() {
        return totalPlayers;
    }

    /**
     * get list of player positions for player assignment.
     * @return playerPositions - list of integer positions.
     */
    public ArrayList<Integer> getPlayerPositions() {
        return playerPositions;
    }

    /**
     * get list of all snakes currently in use by game.
     * @return gameSnakes - list of game snakes.
     */
    public ArrayList<Integer> getGameSnakes() {
        return gameSnakes;
    }
    /**
     * get list of all ladders currently in use by game.
     * @return gameLadders - list of game ladders.
     */
    public ArrayList<Integer> getGameLadders() {
        return gameLadders;
    }
    /**
     * get list of all boosts currently in use by game.
     * @return gameBoosts - list of game ladders.
     */
    public ArrayList<Integer> getGameBoosts() {
        return gameBoosts;
    }

    /**
     * confirm whether game file is holding a finished game.
     * @return gameover - confirmation of whether game is over.
     */
    public Boolean getGameOver() {
        return gameOver;
    }
}
