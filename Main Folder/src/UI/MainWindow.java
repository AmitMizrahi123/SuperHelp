import DB.ConnectDB;

public class MainWindow {
    public static void main(String[] args) {
        // Connect to database in create table if don't exists
        ConnectDB connect = new ConnectDB();
        connect.connectToMySqlAndCreateTable();
    }
}
