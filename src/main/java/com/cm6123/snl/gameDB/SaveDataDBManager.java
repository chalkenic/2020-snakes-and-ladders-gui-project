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
    private ArrayList allSpecials;
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
        saveSpecials(connection);
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
//        }

//        String addGame = "SELECT COUNT(*) AS totalCount FROM Game ";
//        String addDice;
//        String addSnake;
//        String addLadder;
//        String addBoost;
//        String addPlayer;
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveSpecials(final Connection connection) {
        CallableStatement saveStatement = null;
        Statement gameStatement = null;
        String procedure = null;
        String query = null;
        ResultSet result = null;
//        Integer counter;

        for (Map.Entry<String, Integer[]> entry : allTestSpecials.entrySet()) {
            String key = entry.getKey();
            Integer[] value = entry.getValue();

//        for (int i = 0; i < allTestSpecials.size(); i++) {

            if (key == "snakes") {
//                counter = 0;
                procedure = "{CALL add_new_snake(?,?,?)}";

                for (int i = 0; i < value.length; i += 2) {
                    try {
                        saveStatement = connection.prepareCall(procedure);
                        saveStatement.setInt(1, value[i]);
                        saveStatement.setInt(2, value[i + 1]);
                        saveStatement.setInt(3, gameID);

                        saveStatement.executeQuery();
//                        counter += 2;
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Snakes saved!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else if (key == "ladders") {
//                counter = 0;
                procedure = "{CALL add_new_ladder(?,?,?)}";

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
            } else if (key == "boosts") {
                procedure = "{CALL add_new_boost(?,?)}";
//                Integer[] special = (Integer[]) allSpecials.get(i);
//                counter = 0;

                for (int i = 0; i < value.length; i++) {
                    try {
                        saveStatement = connection.prepareCall(procedure);
                        saveStatement.setInt(1, value[i]);
                        saveStatement.setInt(2, gameID);

                        saveStatement.executeQuery();
//                        counter += 1;
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("Boosts saved!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }


            }
        }

//    private void saveBoostFeature() {
//
//    }
//
//    private void saveWinningSquareFeature() {
//
//    }
//
//    private void saveRecordGameFeature() {
//
//
//    }
}
