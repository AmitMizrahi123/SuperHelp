import DB.UtilitiesClient;
import Model.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.*;
import java.util.stream.Stream;

public class DBTest {
    private static String selectAllDb = "select * from dbso.client";
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;

    @BeforeAll
    public static void setUp() throws SQLException {
        conn = UtilitiesClient.connectToMySql();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(selectAllDb);
        rsmd = rs.getMetaData();
    }

    @Test
    void checkNumberOfColumns() throws Exception {
        int numberOfColumns = rsmd.getColumnCount();
        assert numberOfColumns == 7 : "Number of coulums need to be 7";
    }

    @Test
    void checkNamesOfColumns() throws Exception {
        String col1Name = rsmd.getColumnLabel(1); String col2Name = rsmd.getColumnLabel(2);
        String col3Name = rsmd.getColumnLabel(3); String col4Name = rsmd.getColumnLabel(4);
        String col5Name = rsmd.getColumnLabel(5); String col6Name = rsmd.getColumnLabel(6);
        String col7Name = rsmd.getColumnLabel(7);
        assert col1Name != "Email"; assert col2Name != "Password"; assert col3Name != "FirstName";
        assert col4Name != "LastName"; assert col5Name != "Address"; assert col6Name != "PhoneNumber";
        assert col7Name != "Permission";
    }

    @ParameterizedTest
    @MethodSource("createClients")
    void checkInsertAndDelteData(Client client) throws Exception {
        int flag = 0;

        UtilitiesClient.insertData(conn, client);

        ResultSet newRs = stmt.executeQuery(selectAllDb);

        while (newRs.next()) {
            String firstName = newRs.getString("FirstName");
            if (firstName.equals("admin") || firstName.equals("volunteer")) {
                flag = 1;
                break;
            }
        }

        UtilitiesClient.deleteData(conn, client);

        newRs = stmt.executeQuery(selectAllDb);

        while (newRs.next()) {
            String firstName = newRs.getString("FirstName");
            if (firstName.equals("admin") || firstName.equals("volunteer")) {
                flag = 0;
                break;
            }
        }

        assert flag != 0;
    }

    static Stream<Client> createClients() {
        return Stream.of(new Client("admin@gmail.com", "Aa123456!", "admin",
                "admin", "admin", "0501111111", "Admin"), new Client("volunteer@gmail.com",
                "Vv123456!", "volunteer", "volunteer", "volunteer",
                "0501111112", "Volunteer"));
    }
}
