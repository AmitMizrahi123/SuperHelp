import Controller.LoginController;
import DB.ClientRepository;
import DB.UtilitiesClient;
import View.Login;

import java.sql.Connection;

public class MainWindow {
    public static void main(String[] args) throws Exception {
        Connection conn = UtilitiesClient.connectToMySql();
        if (!UtilitiesClient.checkIfTableExists(conn, "client")) {
            UtilitiesClient.createTable(conn);
        }

        Login theView = new Login();
        ClientRepository theModel = new ClientRepository(conn);
        LoginController theController = new LoginController(theView, theModel);
        theView.setVisible(true);
    }
}
