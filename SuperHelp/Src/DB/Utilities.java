package DB;

import Logger.SingletonLogger;

import java.lang.module.FindException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilities {
    static private Logger _logger = SingletonLogger.getInstance().configLogger();

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
        conn.prepareStatement(sql).executeUpdate();
        _logger.log(Level.INFO, "schema {0} created", schemaName);
    }

    public static boolean checkIfSchemaExists(Connection conn, String schemaName) throws SQLException {
        _logger.log(Level.INFO, "Check if {0} schema exists", schemaName);
        ResultSet rs = conn.getMetaData().getCatalogs();
        int searchShemaIndex = 1;

        while (rs.next()) {
            String catalog = rs.getString(searchShemaIndex);

            if (schemaName.equals(catalog)) {
                return true;
            }
        }

        return false;
    }

    public static void createTable(Connection conn, String tableName, String createClientSql) throws SQLException {
        String sql = "create table " + tableName + createClientSql;

        _logger.log(Level.INFO, "Trying to create {0} table", tableName);
        conn.prepareStatement(sql).executeUpdate();
        _logger.log(Level.INFO, "table {0} created", tableName);
    }

    public static boolean checkIfTableExists(Connection conn, String tableName) throws SQLException {
        _logger.log(Level.INFO, "Check if {0} table exists", tableName);
        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet tables = dbm.getTables(null, null, tableName, null);
        return tables.next();
    }
}
