package Tests.UITests;

import Controller.LoginController;
import Utilites.TestBase;
import View.*;
import org.junit.Test;

public class UITestingLogin extends TestBase {
    private LoginController _theController = new LoginController(_loginScreen, _clientRepository);

    @Test
    public void verifyLoginToAnotherScreen() {
        Register registerScreen = _loginScreen.clickOnRegisterButton();
        registerScreen.clickGoBack();
        Contact contact = _loginScreen.clickOnContactButton();
        contact.clickGoBack();
        _testResult.setTestResult(true);
    }

    @Test
    public void happyPathLoginForAllUsers() {
        _loginScreen.setEmailField("amit@gmail.com");
        _loginScreen.setPasswordField("Aa123456!");
        Manager managerScreen = _loginScreen.clickOnLoginButton("Manager");
        managerScreen.clickLogout();
        _loginScreen.setEmailField("or@gmail.com");
        _loginScreen.setPasswordField("Oo123456!");
        Admin adminScreen = _loginScreen.clickOnLoginButton("Admin");
        adminScreen.clickLogout();
        _loginScreen.setEmailField("yossi@gmail.com");
        _loginScreen.setPasswordField("Aa123456!");
        Volunteer volunteerScreen = _loginScreen.clickOnLoginButton("Volunteer");
        volunteerScreen.clickLogout();
        _testResult.setTestResult(true);
    }
}
