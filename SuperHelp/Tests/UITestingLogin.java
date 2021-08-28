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
        Register registerScreen = _theView.clickOnRegisterButton();
        Assert.assertTrue(registerScreen.getNameScreen().equals("Register"));
        registerScreen.clickGoBack();
        Contact contact = _theView.clickOnContactButton();
        Assert.assertTrue(contact.getNameScreen().equals("Contact"));
        contact.clickGoBack();
        Assert.assertTrue(_theView.getNameScreen().equals("Login"));
    }

    @Test
    void happyPathLoginForAllUsers() {
        _theView.setEmailField("amit@gmail.com");
        _theView.setPasswordField("Aa123456!");
        Manager managerScreen = _theView.clickOnLoginButton("Manager");
        Assert.assertTrue(managerScreen.getNameScreen().equals("Manager"));
        managerScreen.clickLogout();
        Assert.assertTrue(_theView.getNameScreen().equals("Login"));
        _theView.setEmailField("or@gmail.com");
        _theView.setPasswordField("Oo123456!");
        Admin adminScreen = _theView.clickOnLoginButton("Admin");
        Assert.assertTrue(adminScreen.getNameScreen().equals("Admin"));
        adminScreen.clickLogout();
        Assert.assertTrue(_theView.getNameScreen().equals("Login"));
        _theView.setEmailField("yossi@gmail.com");
        _theView.setPasswordField("Aa123456!");
        Volunteer volunteerScreen = _theView.clickOnLoginButton("Volunteer");
        Assert.assertTrue(volunteerScreen.getNameScreen().equals("Volunteer"));
        volunteerScreen.clickLogout();
        Assert.assertTrue(_theView.getNameScreen().equals("Login"));
    }
}
