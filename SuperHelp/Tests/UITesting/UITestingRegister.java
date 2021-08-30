package UITesting;

import Controller.RegisterController;
import DB.ClientRepository;
import DB.Utilities;
import View.Login;
import View.Register;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class UITestingRegister {
    private static Connection _db;
    private static Register _theView;
    private static ClientRepository _theModel;
    private static RegisterController _theController;

    @BeforeAll
    public static void setUp() throws Exception {
        _db = Utilities.connectToMySql();
        _theView = new Register();
        _theModel = new ClientRepository(_db);
        _theController = new RegisterController(_theView, _theModel);
    }

    @AfterClass
    public static void end() throws SQLException {
        _db.close();
    }
}
