import DBTests.TestingClient;
import DBTests.TestingVolunteering;
import SystemTests.SystemTestVolunteering;
import SystemTests.SystemTestsClient;
import UITests.UITestingLogin;
import UITests.UITestingRegister;
import Utilites.TestBase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestingClient.class,
        TestingVolunteering.class,
        SystemTestsClient.class,
        SystemTestVolunteering.class,
        UITestingLogin.class,
        UITestingRegister.class
})

public class RunAllTests { }
