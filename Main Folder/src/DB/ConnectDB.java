import java.sql.*;

class ConnectDB {
    // Connect to MySql
    public static Connection connectToMySql() {
        String USER = "root";
        String PASS = "pass123";
        String DB_URL = "jdbc:mysql://localhost:3306/dbso";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver for connection
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create Table
    public static void createTable(Connection conn, String tableName, String values) throws SQLException {
        if (checkIfTableExists(conn, tableName)) {
            System.out.printf("table %s exists\n", tableName);
        } else {
            Statement statement = conn.createStatement();
            statement.executeUpdate("use dbso");
            statement.executeUpdate(values);
            System.out.printf("table %s created\n", tableName);
        }
    }

    // Store Data
    public static void storeData(Connection conn, String tableName, String data) throws SQLException {
        Statement statement = conn.createStatement();
        statement.executeUpdate(data);
        System.out.printf("Store Data Inside %s Success\n", tableName);
    }

    // Check if volunteer table exists
    public static boolean checkIfTableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet result = metaData.getTables(null, null, tableName, new String[]{"TABLE"});
        return result.next();
    }
}
