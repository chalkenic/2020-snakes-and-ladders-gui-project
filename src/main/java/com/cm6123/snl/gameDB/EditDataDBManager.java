package com.cm6123.snl.gameDB;

import com.cm6123.snl.GUI.Edit;
import com.cm6123.snl.GUI.LoadingFormEvent;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class EditDataDBManager {



    public static void editDiceData(final LoadingFormEvent editChoice) {
        Connection connect = GameDBUtils.connectGuiToDatabase();
        CallableStatement saveStatement = null;
        String procedure = null;
        Integer id = null;
        Integer firstEdit = null;
        Integer secondEdit = null;

        procedure = "{CALL update_dice(?,?,?)}";
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

    public static void editSnakeOrLadderData(final LoadingFormEvent editChoice) {
        System.out.println("test1");
        Connection connect = GameDBUtils.connectGuiToDatabase();
        CallableStatement saveStatement = null;
        String procedure = null;
        Integer id = null;
        Integer firstEdit = null;
        Integer secondEdit = null;

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

    public static void editBoostData(final LoadingFormEvent editChoice) {
        System.out.println("test1");
        Connection connect = GameDBUtils.connectGuiToDatabase();
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

    public static void editPlayerData(final LoadingFormEvent editChoice) {
        Connection connect = GameDBUtils.connectGuiToDatabase();
        CallableStatement saveStatement = null;
        String procedure = null;
        Integer id = null;
        String playerEdit = null;

        procedure = "{CALL update_player(?,?,?)}";
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
