package com.cm6123.snl.gameDB;

import com.cm6123.snl.Game;
import com.cm6123.snl.dice.DiceSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SaveDataDBManager {

    private Game newGameSave;
    private DiceSet dice;
    private Integer diceID;
    private Integer gameID;
    private Integer gridSize;
    private TreeMap<String, Integer[]> allTestSpecials;

    public SaveDataDBManager(final Game gameSave, final DiceSet gameDice, final Integer size,
                             final TreeMap specialList) {
        this.newGameSave = gameSave;
        this.dice = gameDice;
        this.gridSize = size;
        this.allTestSpecials = specialList;
    }

    public void saveCurrentGame(final Connection connection) {

        saveDie(connection);
        saveGame(connection);
        if (allTestSpecials != null) {
            saveSpecials(connection);
        }

        savePlayers(connection);

        if (newGameSave.getBoostSquareOn()) {
            saveboostFeatureOn(connection);
        }

        if (newGameSave.isWinningSquareOn()) {
            saveWinningSquareFeatureOn(connection);
        }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


    }

    private void saveDie(final Connection connection) {

        CallableStatement saveStatement = null;
        Statement diceStatement = null;
        String procedure = null;
        String query = null;
        ResultSet result = null;

        procedure = "{CALL add_new_dice(?,?)}";

        try {
            saveStatement = connection.prepareCall(procedure);
            saveStatement.setInt(1, dice.getCount());
            saveStatement.setInt(2, dice.getFaces());

            saveStatement.executeQuery();

            query = "SELECT diceID FROM Dice ORDER BY diceID DESC LIMIT 1;";

            diceStatement = connection.createStatement();
            result = diceStatement.executeQuery(query);

            while (result.next()) {
                diceID = result.getInt("diceID");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveGame(final Connection connection) {
        CallableStatement saveStatement = null;
        Statement gameStatement = null;
        String procedure = null;
        String query = null;
        ResultSet result = null;

        procedure = "{CALL add_new_game(?,?)}";
        query = "SELECT gameID FROM Game ORDER BY gameID DESC LIMIT 1;";

        try {
            saveStatement = connection.prepareCall(procedure);
            saveStatement.setInt(1, gridSize);
            saveStatement.setInt(2, diceID);

            saveStatement.executeQuery();

            gameStatement = connection.createStatement();
            result = gameStatement.executeQuery(query);

            while (result.next()) {
                gameID = result.getInt("gameID");
                System.out.println(gameID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveSpecials(final Connection connection) {
        CallableStatement saveStatement = null;
        String procedure = null;

        for (Map.Entry<String, Integer[]> entry : allTestSpecials.entrySet()) {
            String key = entry.getKey();
            Integer[] value = entry.getValue();

            if (key == "snakes" && value.length > 0) {
                procedure = "{CALL add_new_snake(?,?,?)}";

                for (int i = 0; i < value.length; i += 2) {
                    try {
                        saveStatement = connection.prepareCall(procedure);
                        saveStatement.setInt(1, value[i]);
                        saveStatement.setInt(2, value[i + 1]);
                        saveStatement.setInt(3, gameID);

                        saveStatement.executeQuery();
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Snakes saved!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else if (key == "ladders" && value.length > 0) {
                System.out.println("test1");
                System.out.println(value);


                for (int i = 0; i < value.length; i += 2) {
                    try {
                        saveStatement = connection.prepareCall(procedure);
                        saveStatement.setInt(1, value[i]);
                        saveStatement.setInt(2, value[i + 1]);
                        saveStatement.setInt(3, gameID);

                        saveStatement.executeQuery();
//                        counter += 2;
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("ladders saved!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else if (key == "boosts" && value.length > 0) {
                procedure = "{CALL add_new_boost(?,?)}";

                for (int i = 0; i < value.length; i++) {
                    try {
                        saveStatement = connection.prepareCall(procedure);
                        saveStatement.setInt(1, value[i]);
                        saveStatement.setInt(2, gameID);

                        saveStatement.executeQuery();
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Boosts saved!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void savePlayers(final Connection connection) {
        CallableStatement saveStatement = null;
        String procedure = null;
        Integer playerPos;
        String playerCol;
        Boolean saving = false;



        for (int i = 0; i < newGameSave.numberOfPlayers(); i++) {
            procedure = "{CALL add_new_player_to_game(?,?,?)}";
            playerCol = newGameSave.getPlayer(i).getColour().toString();

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

        updatePlayerPosition(connection, saving);
    }

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

    private void updatePlayerPosition(final Connection connection, final Boolean gameSave) {

        Boolean isSaving = gameSave;
        CallableStatement saveStatement = null;
        String procedure = null;
        Integer playerPos;
        String playerCol;
        System.out.println("test");

        if (isSaving) {
            System.out.println("test2");
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
    }




//
//    private void saveRecordGameFeature() {
//
//
//    }
}
