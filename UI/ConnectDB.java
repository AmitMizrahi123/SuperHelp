import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    // Class Attribute For MySql DB
    private final String DB_URL = "jdbc:mysql://localhost:3306/dbso";
    private final String USER = "root";
    private final String PASS = "pass123";

    // Connect to MySql
    public void ConnectToMySql() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver for connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}