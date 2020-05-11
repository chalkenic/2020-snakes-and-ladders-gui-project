package com.cm6123.snl.gameDB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Static utility class used to call database with default database name.
 */
public final class GameDBUtils {

    private GameDBUtils() {
    }
    /**
     * Establishes database connection.
     * @param databaseName - name of the database.
     * @return database connection link.
     */
    public static Connection connectGuiToDatabase(final String databaseName) {
        Connection connection = null;
        Properties properties = new Properties();
        //Defines path for user to access database.
        String path = System.getProperty("user.dir");
        //Specfic location of database data.
        path += "/src/database.properties";

        try (FileInputStream file = new FileInputStream(path)) {
            System.out.println("test1");
            properties.load(file);
            //First connection will source the location for database to be sent.
            //additional following connections will source the actual database name.
            connection = DriverManager.getConnection(
                    properties.getProperty("DB_URL") + databaseName,
                    properties.getProperty("DB_USER"),
                    properties.getProperty("DB_PASS"));
            System.out.println("test2");

        } catch (IOException e) {
            System.out.println("test3");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("test4");
            e.printStackTrace();
        }

        return connection;

    }
}
