package Tests.UITests;

import Controller.ManagerController;
import Model.Client;
import Utilites.TestBase;
import View.Login;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class UITestingManager extends TestBase {
    private ManagerController _theController = new ManagerController(_managerScreen, _clientRepository);
    private int clientSize = _clientRepository._clients.size();
    private Random random = new Random();

    @Test
    public void verifyDeleteClient() throws Exception {
        int clientSizeBefore = clientSize - 1;
        int randomClientIndex = random.nextInt(clientSizeBefore);
        Client removeClient = _managerScreen.getSeletedItemByIndex(randomClientIndex);
        _clientRepository.delete(removeClient.getEmail());
        int clientSizeAfter = clientSize - 1;
        Assert.assertTrue("Remove client failed!", clientSizeBefore != clientSizeAfter + 1);
        _clientRepository.add(removeClient);
        _testResult.setTestResult(true);
    }

    @Test
    public void verifyGoBackToLogin()
    {
        Login login = _managerScreen.clickLogout();
        Assert.assertTrue(login.getNameScreen().equals("Login"));
        _testResult.setTestResult(true);
    }
}
