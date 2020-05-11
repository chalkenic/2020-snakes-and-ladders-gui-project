
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

//        Statement statement = null;
        try {
    //Code adapted from Benoit Duffez  - ScriptRunner.
    /* Available at:
    https://github.com/BenoitDuffez/ScriptRunner?fbclid=IwAR2BSaTAjhJQD8YvsFhf3GIepreDGU8SWtaqDNKhtmtcOmjRXyrNu12Ykks
     */
            ScriptRunner runner = new ScriptRunner(connection, false, false);
            System.out.println(runner);
            String sqlPath = (System.getProperty("user.dir"));
            sqlPath += "/src/c1936922_database.sql";
            System.out.println(sqlPath);
            Reader sqlReader = new BufferedReader(new FileReader(sqlPath));
            System.out.println(sqlReader);
            runner.runScript(new BufferedReader((sqlReader)));


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
