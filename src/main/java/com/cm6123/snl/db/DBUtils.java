package com.cm6123.snl.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    public static Connection connectioToDatabase(String databaseName){
        Connection connection = null;
        Properties properties = new Properties();
        String path = System.getProperty("user.dir");
        path += "/src/database.properties";
        try (FileInputStream file = new FileInputStream(path)){
            properties.load(file);
            connection = DriverManager.getConnection(properties.getProperty("DB_URL")+databaseName,
                    properties.getProperty("DB_USER"), properties.getProperty("DB_PASS"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
