
package com.cm6123.snl.gameDB;
import java.io.*;
import java.sql.*;
/**
 * Utility class handles database creation on main menu. Marked final to avoid subclass extension.
 */
public final class CreateDBManager {

    private CreateDBManager() {
    }
    /**
     * static method creates the database.
     * @param connection - the connection established on GUIFrame class.
     * @param databaseName - name of the database.
     */
    public static void createDatabase(final Connection connection, final String databaseName) {

        try {
    /*
    Code for accesing third party ScriptRunner class sourced from Benoit Duffez  - ScriptRunner.
    Available at:
    https://github.com/BenoitDuffez/ScriptRunner?fbclid=IwAR2BSaTAjhJQD8YvsFhf3GIepreDGU8SWtaqDNKhtmtcOmjRXyrNu12Ykks
     */
            //ScriptRunner object created ready for intake of sql file path.
            ScriptRunner runner = new ScriptRunner(connection, false, false);
            //Path defined.
            String sqlPath = (System.getProperty("user.dir"));
            sqlPath += "/src/c1936922_database.sql";
            //File reader created for scanning character stream of file path.
            Reader sqlReader = new BufferedReader(new FileReader(sqlPath));
            //Script is parsed through & database created at location of parameter.
            runner.runScript(new BufferedReader((sqlReader)));
            sqlReader.close();


        } catch (SQLException e) {
            System.out.println("Cannot connect to server.");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file.");
            e.printStackTrace();
        } catch (IOException e) {
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
    }

}
