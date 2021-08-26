import Controller.LoginController;
import DB.ClientRepository;
import DB.Utilities;
import View.*;
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

    @Test
    void invalidLoginNoArgs() {
        ErrorMessageUI errorMessageScreen = _theView.clickOnLoginButtonInvalidArgs();
        Assert.assertTrue(errorMessageScreen.getErrorText().equals("Invalid Email"));
    }

    @Test
    void invalidLoginErrorPasswoord() {
        _theView.setEmailField("amit@gmail.com");
        ErrorMessageUI errorMessageScreen = _theView.clickOnLoginButtonInvalidPassword();
        Assert.assertTrue(errorMessageScreen.getErrorText().equals("Invalid Password"));
    }

    @Test
    void happyPathLoginManager() {
        _theView.setEmailField("amit@gmail.com");
        _theView.setPasswordField("Aa123456!");
        Manager managerScreen = _theView.clickOnLoginButtonValidManager();
        Assert.assertTrue(managerScreen.getNameScreen().equals("Manager"));
    }

    @Test
    void happyPathLoginAdmin() {
        _theView.setEmailField("or@gmail.com");
        _theView.setPasswordField("Oo123456!");
        Admin adminScreen = _theView.clickOnLoginButtonValidAdmin();
        Assert.assertTrue(adminScreen.getNameScreen().equals("Admin"));
    }

    @Test
    void happyPathLoginVolunteer() {
        _theView.setEmailField("yossi@gmail.com");
        _theView.setPasswordField("Aa123456!");
        Volunteer adminScreen = _theView.clickOnLoginButtonValidVolunteer();
        Assert.assertTrue(adminScreen.getNameScreen().equals("Volunteer"));
    }
}
