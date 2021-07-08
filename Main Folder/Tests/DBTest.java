import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class DBTest {
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;

    @BeforeAll
    public static void setUp() throws SQLException {
        conn = ConnectDB.connectToMySql();
        stmt = conn.createStatement();
        rs = stmt.executeQuery("select * from volunteer");
        rsmd = rs.getMetaData();
    }

    @Test
    void checkNumberOfColumns() throws Exception {
        int numberOfColumns = rsmd.getColumnCount();
        assert numberOfColumns == 5 : "Number of coulums need to be 5";
    }

    @Test
    void checkNamesOfColumns() throws Exception {
        String col1Name = rsmd.getColumnLabel(1); String col2Name = rsmd.getColumnLabel(2); String col3Name = rsmd.getColumnLabel(3); String col4Name = rsmd.getColumnLabel(4); String col5Name = rsmd.getColumnLabel(5);
        assert col1Name != "first_name"; assert col2Name != "last_name"; assert col3Name != "age"; assert col4Name != "location"; assert col5Name != "admin";
    }
}
