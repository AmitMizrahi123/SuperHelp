package Tests.UITests;

import Controller.LoginController;
import DB.ClientRepository;
import Utilites.TestBase;
import View.*;
import org.junit.Test;

public class UITestingLogin extends TestBase {
    private LoginController _theController = new LoginController(_login, _clientRepository);

    @Test
    public void verifyLoginToAnotherScreen() {
        Register registerScreen = _login.clickOnRegisterButton();
        registerScreen.clickGoBack();
        Contact contact = _login.clickOnContactButton();
        contact.clickGoBack();
        _testResult.setTestResult(true);
    }

    @Test
    public void happyPathLoginForAllUsers() {
        _login.setEmailField("amit@gmail.com");
        _login.setPasswordField("Aa123456!");
        Manager managerScreen = _login.clickOnLoginButton("Manager");
        managerScreen.clickLogout();
        _login.setEmailField("or@gmail.com");
        _login.setPasswordField("Oo123456!");
        Admin adminScreen = _login.clickOnLoginButton("Admin");
        adminScreen.clickLogout();
        _login.setEmailField("yossi@gmail.com");
        _login.setPasswordField("Aa123456!");
        Volunteer volunteerScreen = _login.clickOnLoginButton("Volunteer");
        volunteerScreen.clickLogout();
        _testResult.setTestResult(true);
    }
}
