package UITesting;

import Controller.LoginController;
import DB.ClientRepository;
import Utilites.TestBase;
import Utilites.SingletonTestResult;
import View.*;
import org.junit.Assert;
import org.junit.Test;

public class UITestingLogin extends TestBase {
    private Login _theView = new Login();
    private ClientRepository _theModel;
    {
        try {
            _theModel = new ClientRepository(_db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private LoginController _theController = new LoginController(_theView, _theModel);
    public static SingletonTestResult testResult = SingletonTestResult.getInstance();

    @Test
    public void verifyLoginToRegisterScreen() {
        Register registerScreen = _theView.clickOnRegisterButton();
        registerScreen.clickGoBack();
        Contact contact = _theView.clickOnContactButton();
        contact.clickGoBack();
        testResult.setTestResult(true);
    }

    @Test
    public void happyPathLoginForAllUsers() {
        _theView.setEmailField("amit@gmail.com");
        _theView.setPasswordField("Aa123456!");
        Manager managerScreen = _theView.clickOnLoginButton("Manager");
        managerScreen.clickLogout();
        _theView.setEmailField("or@gmail.com");
        _theView.setPasswordField("Oo123456!");
        Admin adminScreen = _theView.clickOnLoginButton("Admin");
        adminScreen.clickLogout();
        _theView.setEmailField("yossi@gmail.com");
        _theView.setPasswordField("Aa123456!");
        Volunteer volunteerScreen = _theView.clickOnLoginButton("Volunteer");
        volunteerScreen.clickLogout();
        testResult.setTestResult(true);
    }
}