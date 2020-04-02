package com.cm6123.snl.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SnakeAndLadderDBManager {
    public void selectAllSnake(Connection connect){
        String query = "select * from Snake";
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connect.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                String snakeName = rs.getString("name");
                System.out.println("The name of the snake is "+snakeName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try{
                if(rs != null)rs.close();
                if(statement != null)statement.close();
                if(connect != null)connect.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

    }
    public void createDatabase (Connection connect){
        Statement stmt = null;
        try {
            stmt =connect.createStatement();
            stmt.execute("Create Database if NOT EXISTS SnakeAndLadder");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(stmt != null){stmt.close();}
                if(connect != null) connect.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

    }

    public static void main (String[] args) {
        SnakeAndLadderDBManager snake = new SnakeAndLadderDBManager();
        Connection connect = DBUtils.connectioToDatabase("");
        snake.createDatabase(connect);
        Connection connect2 = DBUtils.connectioToDatabase("SnakeAndLadder");
        snake.selectAllSnake(connect2);
    }

}
