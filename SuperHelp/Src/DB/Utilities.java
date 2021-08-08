package DB;

import java.sql.*;

public class Utilities {
    // Connect to MySql
    public static Connection connectToMySql() {
        String USER = "root";
        String PASS = "pass123";
        String DB_URL = "jdbc:mysql://localhost:3306/dbso?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver for connection
            String unicode= "useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
            return DriverManager.getConnection(DB_URL + unicode, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void createSchema(Connection conn, String databaseName) throws SQLException {
        String sql = "create database " + databaseName;

        Statement statement = conn.createStatement();
        statement.executeUpdate(sql);
        System.out.println("Create schema");
    }

    public static boolean checkIfSchemaExists(Connection conn, String databaseName) throws SQLException {
        ResultSet rs = conn.getMetaData().getCatalogs();
        int counter = 1;

        while (rs.next()) {
            String catalog = rs.getString(counter);

            if (databaseName.equals(catalog)) {
                return true;
            }

            counter++;
        }

        return false;
    }

    public static void createTable(Connection conn, String tableName) throws SQLException {
        String sql = "create table " + tableName + " (Email varchar(40) primary key not null unique, Password varchar(40) not null, FirstName varchar(40) not null, LastName varchar(40) not null, Address varchar(40) not null, PhoneNumber varchar(40) unique not null, Permission varchar(40) not null)";

        Statement statement = conn.createStatement();
        statement.executeUpdate(sql);
        System.out.println("Create tabled");
    }

    public static boolean checkIfTableExists(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet tables = dbm.getTables(null, null, tableName, null);
        return tables.next();
    }
}
