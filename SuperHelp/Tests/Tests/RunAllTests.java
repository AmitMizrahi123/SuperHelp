package Tests;

import Tests.DBTests.TestingClient;
import Tests.DBTests.TestingVolunteering;
import Tests.SystemTests.SystemTestVolunteering;
import Tests.SystemTests.SystemTestsClient;
import Tests.UITests.UITestingLogin;
import Tests.UITests.UITestingRegister;
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
