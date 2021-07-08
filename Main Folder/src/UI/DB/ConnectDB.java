package DB;

import java.sql.*;

public class ConnectDB {
    // Class Attribute For MySql DB
    private final String DB_URL = "jdbc:mysql://localhost:3306/dbso";
    private final String USER = "root";
    private final String PASS = "pass123";
    private final String tableName = "volunteer";

    private Connection conn;
    private DatabaseMetaData metaData;
    private ResultSet result;
    private Statement statement;

    // Connect to MySql
    public void connectToMySqlAndCreateTable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver for connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS); // Connect to DB
            System.out.println("Connect to dbso");

            if (checkIfTableExists(conn, tableName)) {
                System.out.println("Table volunteer exists");
            } else {
                statement = conn.createStatement();
                statement.executeUpdate("use dbso");
                statement.executeUpdate("create table volunteer(first name varchar(20), last name varchar(20), phone int(10), location varchar(20))");
                System.out.println("Create table volunteer");
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    // Check if volunteer table exists
    public boolean checkIfTableExists(Connection connection, String tableName) throws SQLException {
        metaData = connection.getMetaData();
        result = metaData.getTables(null, null, tableName, new String[] {"TABLE"});
        return result.next();
    }
}
