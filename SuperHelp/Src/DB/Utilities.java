package DB;

import Logger.SingletonLogger;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilities {
    static private Logger _logger = SingletonLogger.getInstance().getLogger();

    // Connect to MySql
    public static Connection connectToMySql() {
        String USER = "root";
        String PASS = "pass123";
        String DB_URL = "jdbc:mysql://localhost:3306/dbso?";
        String unicode= "useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";

        try {
            _logger.log(Level.INFO, "Trying to connect database");
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver for connection
            return DriverManager.getConnection(DB_URL + unicode, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void createSchema(Connection conn, String schemaName) throws SQLException {
        String sql = "create database " + schemaName;

        _logger.log(Level.INFO, "Trying to create {0} schema", schemaName);
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.executeUpdate();
        _logger.log(Level.INFO, "schema {0} created", schemaName);
    }

    public static boolean checkIfSchemaExists(Connection conn, String schemaName) throws SQLException {
        _logger.log(Level.INFO, "Check if {0} schema exists", schemaName);
        ResultSet rs = conn.getMetaData().getCatalogs();
        int counter = 1;

        while (rs.next()) {
            String catalog = rs.getString(counter);

            if (schemaName.equals(catalog)) {
                return true;
            }

            counter++;
        }

        return false;
    }

    public static void createTable(Connection conn, String tableName) throws SQLException {
        String sql = "create table " + tableName + " (Email varchar(40) primary key not null unique, Password varchar(40) not null, FirstName varchar(40) not null, LastName varchar(40) not null, Address varchar(40) not null, PhoneNumber varchar(40) unique not null, Permission varchar(40) not null)";

        _logger.log(Level.INFO, "Trying to create {0} table", tableName);
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.executeUpdate();
        _logger.log(Level.INFO, "table {0} created", tableName);
    }

    public static boolean checkIfTableExists(Connection conn, String tableName) throws SQLException {
        _logger.log(Level.INFO, "Check if {0} table exists", tableName);
        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet tables = dbm.getTables(null, null, tableName, null);
        return tables.next();
    }
}
