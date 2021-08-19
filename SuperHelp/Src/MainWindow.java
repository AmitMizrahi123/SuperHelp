import Controller.LoginController;
import DB.ClientRepository;
import DB.Utilities;
import View.Login;
import Logger.SingletonLogger;
import View.ProgressBarPage;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainWindow {
    static private Logger _logger = SingletonLogger.getInstance().configLogger();

    public static void main(String[] args) throws Exception {
        final String databaseName = "dbso", tableNameClient = "client", tableNameVolunteer = "volunteering",
                createClientSql = " (Email varchar(40) primary key not null unique, Password varchar(40) not null, FirstName varchar(40) not null, LastName varchar(40) not null, Address varchar(40) not null, PhoneNumber varchar(40) unique not null, Permission varchar(40) not null)",
                createVoluneeringSql = " (volunteerId int primary key not null unique, Name varchar(40) not null, Age int not null, Gender varchar(6) not null, Address varchar(40) not null, PhoneNumber varchar(40) not null, Problem varchar(255) not null, Time DateTime not null)";

        Connection db = Utilities.connectToMySql();

        _logger.log(Level.INFO, "{0} connected", databaseName);

        if (!Utilities.checkIfSchemaExists(db, databaseName)) { // Validation dbso schema
            Utilities.createSchema(db, databaseName);
        }

        if (!Utilities.checkIfTableExists(db, tableNameClient)) { // Validation client table
            Utilities.createTable(db, tableNameClient, createClientSql);
        }

        if (!Utilities.checkIfTableExists(db, tableNameVolunteer)) { // Validation volunteer table
            Utilities.createTable(db, tableNameVolunteer, createVoluneeringSql);
        }

        new ProgressBarPage();

        Login theView = new Login();
        ClientRepository theModel = new ClientRepository(db);
        LoginController theController = new LoginController(theView, theModel);
        theView.setVisible(true);
    }
}
