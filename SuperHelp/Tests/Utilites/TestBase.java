package Utilites;

import DB.Utilities;
import Logger.SingletonLogger;
import org.junit.*;
import org.junit.rules.TestName;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestBase {
     public static Connection _db;
     public static Logger _logger;
     public static SingletonTestResult testResult = SingletonTestResult.getInstance();

     @Rule public TestName testName = new TestName();

     @BeforeClass
     public static void beforeClass() {
          _db = Utilities.connectToMySql();
          _logger = SingletonLogger.getInstance().configLogger();
     }

     @Before
     public void before() {
          _logger.log(Level.INFO, "Test Case: " + testName.getMethodName() + " Execute");
          testResult.setTestResult(false);
     }

     @After
     public void after() {
          if (testResult.getTestResult()) {
               String methodNamePass = testName.getMethodName();
               String logTextPass = "Test Case: " + methodNamePass + " Passed!!";
               _logger.log(Level.INFO, logTextPass);
          } else {
               String methodNameFailed = testName.getMethodName();
               String logTextFailed = "Test Case: " + methodNameFailed + " Failed!!";
               _logger.log(Level.INFO, logTextFailed);
          }
     }

     @AfterClass
     public static void afterClass() {
          try {
               _db.close();
          } catch (SQLException throwables) {
               throwables.printStackTrace();
          }
     }
}
