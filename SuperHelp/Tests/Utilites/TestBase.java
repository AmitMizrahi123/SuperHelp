package Utilites;

import DB.Utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.*;
import org.junit.rules.TestName;

import java.sql.Connection;
import java.sql.SQLException;

public class TestBase {
     public static Connection _db;
     public static SingletonTestResult testResult = SingletonTestResult.getInstance();
     public static ExtentHtmlReporter _htmlReporter;
     public static ExtentReports _extent;
     public static ExtentTest _logger;
     public static String _reportPath = "C:\\Users\\97250\\IdeaProjects\\SuperHelp\\SuperHelp\\Tests\\Reports\\AutomationReport.html";

     @Rule public TestName testName = new TestName();

     @BeforeClass
     public static void beforeClass() {
          _db = Utilities.connectToMySql();

          _htmlReporter = new ExtentHtmlReporter(_reportPath);
          _htmlReporter.config().setEncoding("utf-8");
          _htmlReporter.config().setDocumentTitle("Automation Report");
          _htmlReporter.config().setReportName("Automation Test Result");
          _htmlReporter.config().setTheme(Theme.DARK);
          _extent = new ExtentReports();
          _extent.attachReporter(_htmlReporter);
          _extent.setSystemInfo("Automation Tester", "Amit Mizrahi");
          _extent.setSystemInfo("Automation Tester", "Yossi Bengaev");
          _extent.setSystemInfo("Automation Tester", "Or Ezra");
          _extent.setSystemInfo("Automation Tester", "Itzik Israeli");
     }

     @Before
     public void before() {
          _logger = _extent.createTest(testName.getMethodName());
          testResult.setTestResult(false);
     }

     @After
     public void after() {
          if (testResult.getTestResult()) {
               String methodNamePass = testName.getMethodName();
               String logTextPass = "Test Case: " + methodNamePass + " Passed!!";
               Markup m = MarkupHelper.createLabel(logTextPass, ExtentColor.GREEN);
               _logger.log(Status.PASS, m);
          } else {
               String methodNameFailed = testName.getMethodName();
               String logTextFailed = "Test Case: " + methodNameFailed + " Failed!!";
               Markup m = MarkupHelper.createLabel(logTextFailed, ExtentColor.RED);
               _logger.log(Status.FAIL, m);
          }
     }

     @AfterClass
     public static void afterClass() {
          try {
               _db.close();
          } catch (SQLException throwables) {
               throwables.printStackTrace();
          }

          _extent.flush();
     }
}
