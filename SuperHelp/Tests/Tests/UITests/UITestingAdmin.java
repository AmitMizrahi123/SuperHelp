package Tests.UITests;

import Controller.AdminController;
import Utilites.TestBase;
import View.Login;
import org.junit.Assert;
import org.junit.Test;

public class UITestingAdmin extends TestBase {
    AdminController _theController = new AdminController(_adminScreen, _volunteeringRepository);
    int currentVolunteering = _volunteeringRepository._volunteerings.size();

    @Test
    public void verifyAddingVolunteering() {
        _adminScreen.setName("Need Help");
        _adminScreen.setAge(25);
        _adminScreen.setGenderMale();
        _adminScreen.setAddress("Akko");
        _adminScreen.setPhoneNumber("0501231233");
        _adminScreen.setProblem("Need Help...");
        _adminScreen.clickOnAddVolunteering();

        Assert.assertTrue("Add new volunteering fail!", _volunteeringRepository._volunteerings.size() == currentVolunteering + 1);
        Assert.assertTrue("Name field not removed", _adminScreen.getName().isEmpty());
        Assert.assertTrue("Phone number field not removed", _adminScreen.getPhoneNumber().isEmpty());
        Assert.assertTrue("Problem field not removed", _adminScreen.getProblem().isEmpty());
        Assert.assertTrue("Delete button not enabled", _adminScreen.deleteButtonEnable());
        Assert.assertTrue("Update button not enabled", _adminScreen.updateButtonEnable());
        _testResult.setTestResult(true);
    }

    @Test
    public void verifyReturnToLogin() {
        Login login = _adminScreen.clickLogout();
        Assert.assertTrue("Return to login failed!", login.getNameScreen().equals("Login"));
    }
}
