package DB;

import java.sql.*;

public class ConnectDB {
    // Class Attribute For MySql DB
    private final String DB_NAME = "dbso";
    private final String DB_URL = String.format("jdbc:mysql://localhost:3306/%s", DB_NAME);
    private final String USER = "root";
    private final String PASS = "pass123";
    private final String tableName = "volunteer";

    private Connection conn;
    private DatabaseMetaData metaData;
    private ResultSet result;
    private Statement statement;

    private final String USE_DB = "use dbso";
    private final String createTable = "create table volunteer(first_name varchar(20), last_name varchar(20), phone int(10), location varchar(20), admin BOOLEAN);";
    private final String createFakeData1 = "insert into dbso.volunteer values(\"Amit\", \"Mizrahi\", 25, \"Modiin\", \"1\");";
    private final String createFakeData2 = "insert into dbso.volunteer values(\"Yossi\", \"Bengaev\", 26, \"Kfar shmual\", \"1\");";
    private final String createFakeData3 = "insert into dbso.volunteer values(\"Or\", \"Ezra\", 26, \"Gan Yavne\", \"1\");";
    private final String createFakeData4 = "insert into dbso.volunteer values(\"Itzhik\", \"Israeli\", 25, \"Yavne\", \"1\");";


    // Connect to MySql
    public void connectToMySqlAndCreateTable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver for connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS); // Connect to DB
            System.out.println(String.format("Connect to %s", DB_NAME));

            if (checkIfTableExists(conn, tableName)) {
                System.out.println(String.format("table %s exists", tableName));
            } else {
                statement = conn.createStatement();
                statement.executeUpdate(USE_DB);
                statement.executeUpdate(createTable);
                statement.executeUpdate(createFakeData1);
                statement.executeUpdate(createFakeData2);
                statement.executeUpdate(createFakeData3);
                statement.executeUpdate(createFakeData4);
                System.out.println(String.format("table %s created", tableName));
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
