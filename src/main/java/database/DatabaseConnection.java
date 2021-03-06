package database;

import java.sql.*;

public class DatabaseConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/newdb";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";

    Connection dbCon;
    Statement theStatement;
    PreparedStatement thePreparedStatement;

    DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbCon = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            theStatement = dbCon.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("Can't load the driver : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Can't connect to the database : " + e.getMessage());
        }
    }
}
