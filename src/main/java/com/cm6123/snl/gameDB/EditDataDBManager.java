package com.cm6123.snl.gameDB;

import com.cm6123.snl.GUI.ConstantDatabaseName;
import com.cm6123.snl.GUI.Edit;
import com.cm6123.snl.GUI.LoadingFormEvent;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Utility class for editing data inside database.
 */
public final class EditDataDBManager {

    private EditDataDBManager() {
    }
    /**
     * Edits game dice.
     * @param editChoice - Object containing dice data.
     */
    public static void editDiceData(final LoadingFormEvent editChoice) {
        Connection connect = GameDBUtils.connectGuiToDatabase(ConstantDatabaseName.DATABASENAME); //Connect to database.
        CallableStatement saveStatement = null;
        String procedure = null;
        Integer id = null;
        Integer firstEdit = null;
        Integer secondEdit = null;
        //Procedure requires 3 values - all sourced from LoadingFormEvent object.
        procedure = "{CALL update_dice(?,?,?)}";
        id = editChoice.getDatabaseID();
        firstEdit = editChoice.getFirstEntry();
        secondEdit = editChoice.getSecondEntry();

        try  { //Attempts to update table row based upon passed parameters.
            saveStatement = connect.prepareCall(procedure);
            saveStatement.setInt(1, id);
            saveStatement.setInt(2, firstEdit);
            saveStatement.setInt(3, secondEdit);
            //attempts to add data.
            saveStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Edits Snake/Ladder data inside game.
     * @param editChoice - Object containing square data.
     */
    public static void editSnakeOrLadderData(final LoadingFormEvent editChoice) {
        Connection connect = GameDBUtils.connectGuiToDatabase(ConstantDatabaseName.DATABASENAME);
        CallableStatement saveStatement = null;
        String procedure = null;
        Integer id = null;
        Integer firstEdit = null;
        Integer secondEdit = null;
        //Edit enum decides which table to edit.
        if (editChoice.getEditChoice() == Edit.SNAKE) {
            procedure = "{CALL update_snake(?,?,?)}";
        } else {
            procedure = "{CALL update_ladder(?,?,?)}";
        }

        id = editChoice.getDatabaseID();
        firstEdit = editChoice.getFirstEntry();
        secondEdit = editChoice.getSecondEntry();

        try  {
            saveStatement = connect.prepareCall(procedure);
            saveStatement.setInt(1, id);
            saveStatement.setInt(2, firstEdit);
            saveStatement.setInt(3, secondEdit);

            saveStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Edits Boost data inside game.
     * @param editChoice - Object containing square data.
     */
    public static void editBoostData(final LoadingFormEvent editChoice) {
        Connection connect = GameDBUtils.connectGuiToDatabase(ConstantDatabaseName.DATABASENAME);
        CallableStatement saveStatement = null;
        String procedure = null;
        Integer id = null;
        Integer firstEdit = null;

        procedure = "{CALL update_boost(?,?)}";
        id = editChoice.getDatabaseID();
        firstEdit = editChoice.getFirstEntry();

        try {
            saveStatement = connect.prepareCall(procedure);
            saveStatement.setInt(1, id);
            saveStatement.setInt(2, firstEdit);

            saveStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Edits Player name inside database..
     * @param editChoice - Object containing amended player name.
     */
    public static void editPlayerData(final LoadingFormEvent editChoice) {
        Connection connect = GameDBUtils.connectGuiToDatabase(ConstantDatabaseName.DATABASENAME);
        CallableStatement saveStatement = null;
        String procedure = null;
        Integer id = null;
        String playerEdit = null;

        procedure = "{CALL update_player(?,?)}";
        id = editChoice.getDatabaseID();
        playerEdit = editChoice.getPlayerNameEntry();

        try  {
            saveStatement = connect.prepareCall(procedure);
            saveStatement.setInt(1, id);
            saveStatement.setString(2, playerEdit);

            saveStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
