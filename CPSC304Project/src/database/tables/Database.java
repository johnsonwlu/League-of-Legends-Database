package database.tables;

import java.sql.*;

// References: CPSC 304 JavaDemo - DatabaseHandler.java
public class Database {
    // Use this version of the ORACLE_URL if you are running the code off of the server
//    private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    public static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    public static final String EXCEPTION_TAG = "[EXCEPTION]";
    public static final String WARNING_TAG = "[WARNING]";

    public static Connection connection = null;

    // credentials
    private String username;
    private String password;

    public Database() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void setConnection(Connection connection) {
        Database.connection = connection;
    }

    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = DriverManager.getConnection(ORACLE_URL, this.username, this.password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return connection;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return null;
        }
    }

    public void close() {

    }

    //
    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }
            // System.out.println("line 141");
            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            //  System.out.println("line 143");
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    private void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    /*public void dropAccountTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("account")) {
                    stmt.execute("DROP TABLE account");
                    break;
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }*/
}
