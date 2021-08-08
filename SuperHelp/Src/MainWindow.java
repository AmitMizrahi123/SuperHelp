import Controller.LoginController;
import DB.ClientRepository;
import DB.Utilities;
import View.Login;

import java.sql.Connection;

public class MainWindow {
    public static void main(String[] args) throws Exception {
        String databaseName = "dbso", tableName = "client";

        Connection conn = Utilities.connectToMySql();

        if (!Utilities.checkIfSchemaExists(conn, databaseName)) {
            Utilities.createSchema(conn, databaseName);
        }

        if (!Utilities.checkIfTableExists(conn, tableName)) {
            Utilities.createTable(conn, tableName);
        }

        Login theView = new Login();
        ClientRepository theModel = new ClientRepository(conn);
        LoginController theController = new LoginController(theView, theModel);
        theView.setVisible(true);
    }
}
