package Tests.UITests;

import Controller.RegisterController;
import Utilites.TestBase;
import View.Login;
import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UITestingRegister extends TestBase {
    private RegisterController _theController = new RegisterController(_registerScreen, _clientRepository);

    @Test
    public void test1_invalidRegister() {
        _registerScreen.clickRegister();
        Assert.assertTrue(_registerScreen.emailErrorVisible());
        Assert.assertTrue(_registerScreen.passwordErrorVisible());
        Assert.assertTrue(_registerScreen.firstNameErrorVisible());
        Assert.assertTrue(_registerScreen.lastNameErrorVisible());
        Assert.assertTrue(_registerScreen.addressErrorVisible());
        Assert.assertTrue(_registerScreen.phoneNumberErrorVisible());
        Assert.assertTrue(_registerScreen.getNameScreen().equals("Register"));
        _testResult.setTestResult(true);
    }

    @Test
    public void test2_validRegister() {
        _registerScreen.setEmailField("zohar@gmail.com");
        _registerScreen.setPasswordField("Zz123456!");
        _registerScreen.setFirstNameField("Zohar");
        _registerScreen.setLastNameField("Hajaj");
        _registerScreen.setAddressField("Akko");
        _registerScreen.setPhoneNumber("0502223311");
        Login loginScreen = _registerScreen.clickRegister();
        _testResult.setTestResult(true);
    }
}
