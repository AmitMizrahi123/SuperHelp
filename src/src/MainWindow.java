import java.sql.Connection;
import java.sql.SQLException;

public class MainWindow {
    public static void main(String[] args) throws SQLException {
        // Connect to database in create table if don't exists
        Connection conn = ConnectDB.connectToMySql();
        ConnectDB.createTable(conn,"volunteer", "create table volunteer(first_name varchar(20), last_name varchar(20), age int(3), location varchar(20), admin BOOLEAN);");
        ConnectDB.storeData(conn,"volunteer", "insert into dbso.volunteer values(\"Amit\", \"Mizrahi\", 25, \"Modiin\", \"1\");");
        ConnectDB.storeData(conn,"volunteer","insert into dbso.volunteer values(\"Yossi\", \"Bengaev\", 26, \"Kfar shmual\", \"1\");");
        ConnectDB.storeData(conn,"volunteer","insert into dbso.volunteer values(\"Or\", \"Ezra\", 26, \"Gan Yavne\", \"1\");");
        ConnectDB.storeData(conn,"volunteer","insert into dbso.volunteer values(\"Itzik\", \"Israeli\", 26, \"Yavne\", \"1\");");
    }
}
