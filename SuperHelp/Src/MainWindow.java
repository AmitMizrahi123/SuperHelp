import Controller.LoginController;
import DB.ClientRepository;
import DB.Utilities;
import View.Login;
import Logger.SingletonLogger;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainWindow {
    static private Logger _logger = SingletonLogger.getInstance().getLogger();

    public static void main(String[] args) throws Exception {
        final String databaseName = "dbso", tableNameClient = "client", tableNameVolunteer = "volunteering",
                createClientSql = " (Email varchar(40) primary key not null unique, Password varchar(40) not null, FirstName varchar(40) not null, LastName varchar(40) not null, Address varchar(40) not null, PhoneNumber varchar(40) unique not null, Permission varchar(40) not null)",
                createVoluneeringSql = " (volunteerId int primary key not null unique, FirstName varchar(40) not null, LastName varchar(40) not null, Address varchar(40) not null, PhoneNumber varchar(40) not null, Problem varchar(255) not null, Time DateTime not null)";

        Connection conn = Utilities.connectToMySql();

        _logger.log(Level.INFO, "{0} connected", databaseName);

        if (!Utilities.checkIfSchemaExists(conn, databaseName)) { // Validation dbso schema
            Utilities.createSchema(conn, databaseName);
        }

        if (!Utilities.checkIfTableExists(conn, tableNameClient)) { // Validation client table
            Utilities.createTable(conn, tableNameClient, createClientSql);
        }

        if (!Utilities.checkIfTableExists(conn, tableNameVolunteer)) { // Validation volunteer table
            Utilities.createTable(conn, tableNameVolunteer, createVoluneeringSql);
        }

        Login theView = new Login();
        ClientRepository theModel = new ClientRepository(conn);
        LoginController theController = new LoginController(theView, theModel);
        theView.setVisible(true);
    }
}
