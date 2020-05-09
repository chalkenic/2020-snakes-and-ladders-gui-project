package com.cm6123.snl.gameDB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GameDBUtils {
    /**
     * Establishes database connection.
     * @param databaseName name of database
     * @return connection properties.
     */
    public static Connection connectGuiToDatabase() {

        String databaseName = "snakesAndLaddersDatabase";
        Connection connection = null;
        Properties properties = new Properties();

        String path = System.getProperty("user.dir");

        path += "/src/database.properties";

        try (FileInputStream file = new FileInputStream(path)) {
            properties.load(file);
            connection = DriverManager.getConnection(
                    properties.getProperty("DB_URL") + databaseName,
                    properties.getProperty("DB_USER"),
                    properties.getProperty("DB_PASS"));

        } catch (IOException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }
}
