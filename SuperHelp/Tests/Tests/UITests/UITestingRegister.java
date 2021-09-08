package Tests.UITests;

import Controller.RegisterController;
import DB.ClientRepository;
import Utilites.TestBase;
import View.Login;
import View.Register;
import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UITestingRegister extends TestBase {
    private RegisterController _theController = new RegisterController(_register, _clientRepository);

    @Test
    public void test1_invalidRegister() {
        _register.clickRegister();
        Assert.assertTrue(_register.emailErrorVisible());
        Assert.assertTrue(_register.passwordErrorVisible());
        Assert.assertTrue(_register.firstNameErrorVisible());
        Assert.assertTrue(_register.lastNameErrorVisible());
        Assert.assertTrue(_register.addressErrorVisible());
        Assert.assertTrue(_register.phoneNumberErrorVisible());
        Assert.assertTrue(_register.getNameScreen().equals("Register"));
        _testResult.setTestResult(true);
    }

    @Test
    public void test2_validRegister() {
        _register.setEmailField("zohar@gmail.com");
        _register.setPasswordField("Zz123456!");
        _register.setFirstNameField("Zohar");
        _register.setLastNameField("Hajaj");
        _register.setAddressField("Akko");
        _register.setPhoneNumber("0502223311");
        Login loginScreen = _register.clickRegister();
        _testResult.setTestResult(true);
    }
}
