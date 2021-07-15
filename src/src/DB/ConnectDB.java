import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    // Retrieve Data
    public static Map<String, ArrayList> retrieveData(Connection conn) throws SQLException {
        Map<String, ArrayList> map = new HashMap<>();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM dbso.volunteer");
        ResultSetMetaData rsmd = rs.getMetaData();

        var firstNameList = new ArrayList<>();
        var lastNameList = new ArrayList<>();
        var ageList = new ArrayList<>();
        var locationList = new ArrayList<>();
        var adminList = new ArrayList<>();

        while (rs.next()) {
            firstNameList.add(rs.getString("first_name"));
            lastNameList.add(rs.getString("last_name"));
            ageList.add(String.valueOf(rs.getInt("age")));
            locationList.add(rs.getString("location"));
            adminList.add(String.valueOf(rs.getBoolean("admin")));
        }

        map.put(rsmd.getColumnLabel(1), firstNameList);
        map.put(rsmd.getColumnLabel(2), lastNameList);
        map.put(rsmd.getColumnLabel(3), ageList);
        map.put(rsmd.getColumnLabel(4), locationList);
        map.put(rsmd.getColumnLabel(5), adminList);

        return map;
    }

    // Check if volunteer table exists
    public static boolean checkIfTableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet result = metaData.getTables(null, null, tableName, new String[]{"TABLE"});
        return result.next();
    }
}
