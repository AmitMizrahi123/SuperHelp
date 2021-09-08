package SystemTests;

import DB.ClientRepository;
import Model.Client;
import Utilites.TestBase;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SystemTestsClient extends TestBase {
    private static ClientRepository _model;
    static {
        try {
            _model = new ClientRepository(_db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static int numberOfUsers = 1000;

    @Test
    public void test1_insertAdminData() throws Exception {
        Random random = new Random();
        boolean flag = false;
        int numberOfCurrentUsers = _model._clients.size(), rand = random.nextInt(numberOfUsers);
        String phoneNumber = "0591111111";
        String randomClient = String.format("admin%s@gmail.com", rand);

        for (int i = 1; i <= numberOfUsers; i++) {
            int phoneNumberValue = Integer.parseInt(phoneNumber) + i;
            _model.add(new Client(String.format("admin%s@gmail.com", i), "Aa123456!",
                    "Admin", "Admin", "Tel Aviv", "0" + String.valueOf(phoneNumberValue), "Admin"));
        }

        Assert.assertTrue("Not all clients entered to DB",numberOfCurrentUsers + numberOfUsers == _model._clients.size());

        for (Client client : _model._clients) {
            if (client.getEmail().equals(randomClient)) {
                flag = true;
                break;
            }
        }

        Assert.assertTrue("Random client are not entered to DB", flag);
        _testResult.setTestResult(true);
    }

    @Test
    public void test2_deleteAdminData() throws Exception {
        Random random = new Random();
        boolean flag = true;
        int numberOfCurrentUsers = _model._clients.size(), rand = random.nextInt(numberOfUsers);
        String randomClient = String.format("admin%s@gmail.com", rand);

        for (int i = 1; i <= numberOfUsers; i++) {
            _model.delete(String.format("admin%s@gmail.com", i));
        }

        Assert.assertTrue(numberOfCurrentUsers - numberOfUsers == _model._clients.size());

        for (Client client : _model._clients) {
            if (client.getEmail() == randomClient) {
                flag = false;
                break;
            }
        }

        Assert.assertTrue("Random client are not entered to DB", flag);
        _testResult.setTestResult(true);
    }

    @Test
    public void test3_insertVolunteeringData() throws Exception {
        Random random = new Random();
        boolean flag = false;
        int numberOfCurrentUsers = _model._clients.size(), rand = random.nextInt(numberOfUsers);
        String phoneNumber = "0581111111";
        String randomClient = String.format("volunteering%s@gmail.com", rand);

        for (int i = 1; i <= numberOfUsers; i++) {
            int phoneNumberValue = Integer.parseInt(phoneNumber) + i;
            _model.add(new Client(String.format("volunteering%s@gmail.com", i), "Vv123456!",
                    "Volunteering", "Volunteering", "Tel Aviv", "0" + String.valueOf(phoneNumberValue), "Volunteering"));
        }

        Assert.assertTrue(numberOfCurrentUsers + numberOfUsers == _model._clients.size());

        for (Client client : _model._clients) {
            if (client.getEmail().equals(randomClient)) {
                flag = true;
                break;
            }
        }

        Assert.assertTrue("Random volunteer are not entered to DB", flag);
        _testResult.setTestResult(true);
    }

    @Test
    public void test4_deleteVolunteeringData() throws Exception {
        Random random = new Random();
        boolean flag = true;
        int numberOfCurrentUsers = _model._clients.size(), rand = random.nextInt(numberOfUsers);
        String randomClient = String.format("volunteering%s@gmail.com", rand);

        for (int i = 1; i <= numberOfUsers; i++) {
            _model.delete(String.format("volunteering%s@gmail.com", i));
        }

        Assert.assertTrue(numberOfCurrentUsers - numberOfUsers == _model._clients.size());

        for (Client client : _model._clients) {
            if (client.getEmail() == randomClient) {
                flag = false;
                break;
            }
        }

        Assert.assertTrue("Random volunteer are not entered to DB", flag);
        _testResult.setTestResult(true);
    }
}
