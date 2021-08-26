import Controller.LoginController;
import DB.ClientRepository;
import DB.Utilities;
import View.Contact;
import View.Login;
import View.Register;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class UITestingLogin {
    private static Connection _db;
    private static Login _theView;
    private static ClientRepository _theModel;
    private static LoginController _theController;

    @BeforeAll
    public static void setUp() throws Exception {
        _db = Utilities.connectToMySql();
        _theView = new Login();
        _theModel = new ClientRepository(_db);
        _theController = new LoginController(_theView, _theModel);
    }

    @Test
    void verifyLoginToRegisterScreen() {
        Register register = _theView.clickOnRegisterButton();
        Assert.assertTrue(register.getNameScreen().equals("Register"));
    }

    @Test
    void verifyLoginToContactScreen() {
        Contact contact = _theView.clickOnContactButton();
        Assert.assertTrue(contact.getNameScreen().equals("Contact"));
    }
}
