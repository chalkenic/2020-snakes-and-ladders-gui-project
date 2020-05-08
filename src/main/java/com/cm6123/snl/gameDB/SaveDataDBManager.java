package com.cm6123.snl.gameDB;

import com.cm6123.snl.Game;
import com.cm6123.snl.dice.DiceSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SaveDataDBManager {

    private Game newSave;
    private DiceSet dice;

    public SaveDataDBManager(final Game gameSave, final DiceSet gameDice) {
        this.newSave = gameSave;
        this.dice = gameDice;
    }

    public void saveCurrentGame(final Connection connection) {

        try {
            String addDice;


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String addGame = "SELECT COUNT(*) AS totalCount FROM Game ";
        String addDice;
        String addSnake;
        String addLadder;
        String addBoost;
        String addPlayer;

        Integer gameCount = null;
        Statement statement = null;
        ResultSet result = null;

    }
}
