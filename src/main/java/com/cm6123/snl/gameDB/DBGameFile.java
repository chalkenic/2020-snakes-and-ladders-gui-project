package com.cm6123.snl.gameDB;

import com.cm6123.snl.gameDB.GameDBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBGameFile {

    private String gameText;

    private Integer id;
    private Integer gamePlayerTurn;
    private Integer gameRound;
    private Integer boardSize;
    private Boolean gameOver;
    private Boolean boostSquareFeature;
    private Boolean winningSquareFeature;
    private Boolean recordGameFeature;
    private Integer diceChoice;
    private Integer diceFaces;
    private Integer diceCount;
    private Integer totalPlayers;
    private ArrayList<Integer> playerPositions;

    private ArrayList<Integer> gameSnakes;
    private ArrayList<Integer> gameLadders;
    private ArrayList<Integer> gameBoosts;



    public DBGameFile(final int newID, final String text) {

        this.id = newID;
        this.gameText = text;

        addDatabaseGameData();
        addDiceData();

        gameSnakes = new ArrayList<>();
        addSnakeData();
        gameLadders = new ArrayList<>();
        addLadderData();

        playerPositions = new ArrayList<>();
        addPlayers();
//
        if (boostSquareFeature) {
            gameBoosts = new ArrayList<>();
            addBoostData();
        }

    }

    private void addDatabaseGameData() {

        Connection connect = GameDBUtils.connectGuiToDatabase();
        ResultSet result = null;
        CallableStatement statement = null;
        String procedure = null;

        if (connect != null) {
            PreparedStatement query = null;
            try {
                query = connect.prepareStatement("SELECT * FROM Game WHERE gameID=?;");

                query.setInt(1, id);

                result = query.executeQuery();

                while (result.next()) {
                    id = result.getInt("gameID");
                    gamePlayerTurn = result.getInt("gamePlayerTurn");
                    gameRound = result.getInt("gameRound");
                    boardSize = result.getInt("boardGridSize");
                    gameOver = result.getBoolean("gameHasEnded");
                    boostSquareFeature = result.getBoolean("boostSquareFeature");
                    winningSquareFeature = result.getBoolean("winningSquareOnlyFeature");
                    recordGameFeature = result.getBoolean("recordGameFeature");
                    diceChoice = result.getInt("dice_diceID");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (result != null) {
                        result.close();
                    }
                    if (connect != null) {
                        connect.close();
                    }
                    if (result != null) {
                        result.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addDiceData() {
        Connection connect = GameDBUtils.connectGuiToDatabase();
        ResultSet result = null;
        CallableStatement statement = null;
        String procedure = null;
        if (connect != null) {
            try {
                procedure = "{CALL select_dice_choice_from_game(?)}";

                statement = connect.prepareCall(procedure);

                statement.setInt(1, id);
                result = statement.executeQuery();

                while (result.next()) {
                    diceFaces = result.getInt("diceFaces");
                    diceCount = result.getInt("diceCount");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void addSnakeData() {
        Connection connect = GameDBUtils.connectGuiToDatabase();
        ResultSet result = null;
        CallableStatement statement = null;
        String procedure = null;
        if (connect != null) {
            try {
                procedure = "{CALL select_game_snakes(?)}";

                statement = connect.prepareCall(procedure);

                statement.setInt(1, id);
                result = statement.executeQuery();

                while (result.next()) {
                    gameSnakes.add(result.getInt("snakehead"));
                    gameSnakes.add(result.getInt("snakeTail"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addLadderData() {
        Connection connect = GameDBUtils.connectGuiToDatabase();
        ResultSet result = null;
        CallableStatement statement = null;
        String procedure = null;
        if (connect != null) {
            try {
                procedure = "{CALL select_game_ladders(?)}";

                statement = connect.prepareCall(procedure);

                statement.setInt(1, id);
                result = statement.executeQuery();

                while (result.next()) {
                    gameLadders.add(result.getInt("ladderFoot"));
                    gameLadders.add(result.getInt("ladderTop"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addBoostData() {
        Connection connect = GameDBUtils.connectGuiToDatabase();
        ResultSet result = null;
        CallableStatement statement = null;
        String procedure = null;
        if (connect != null) {
            try {
                procedure = "{CALL select_game_boosts(?)}";

                statement = connect.prepareCall(procedure);

                statement.setInt(1, id);
                result = statement.executeQuery();

                while (result.next()) {
                    gameBoosts.add(result.getInt("boostLocation"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addPlayers() {
        Connection connect = GameDBUtils.connectGuiToDatabase();
        ResultSet result = null;
        CallableStatement statement = null;
        String procedure = null;
        totalPlayers = 0;
        if (connect != null) {
            try {
                procedure = "{CALL select_players_from_game(?)}";

                statement = connect.prepareCall(procedure);

                statement.setInt(1, id);
                result = statement.executeQuery();

                while (result.next()) {
                    totalPlayers++;
                    playerPositions.add(result.getInt("playerPosition"));
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String toString() {
        return gameText;
    }

    public int getId() {
        return id;
    }

    public Integer getGamePlayerTurn() {
        return gamePlayerTurn;
    }

    public Integer getBoardSize() {
        return boardSize;
    }

    public Boolean getBoostSquareFeature() {
        return boostSquareFeature;
    }

    public Boolean getWinningSquareFeature() {
        return winningSquareFeature;
    }

    public Boolean getRecordGameFeature() {
        return recordGameFeature;
    }

    public Integer getDiceFaces() {
        return diceFaces;
    }

    public Integer getDiceCount() {
        return diceCount;
    }

    public Integer getTotalPlayers() {
        return totalPlayers;
    }

    public ArrayList<Integer> getPlayerPositions() {
        return playerPositions;
    }

    public ArrayList<Integer> getGameSnakes() {
        return gameSnakes;
    }

    public ArrayList<Integer> getGameLadders() {
        return gameLadders;
    }

    public ArrayList<Integer> getGameBoosts() {
        return gameBoosts;
    }

    public Boolean getGameOver() {
        return gameOver;
    }
}
