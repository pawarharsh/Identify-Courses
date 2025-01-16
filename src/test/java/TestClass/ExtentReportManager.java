package TestClass;

import java.net.InetAddress;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; // Populate common info on the report
	public ExtentTest test; // Creating test case entries in the report and update status of the test methods

	// Method to configure the ExtentReports and set system info
	public void onStart(ITestContext context) {
		sparkReporter = new ExtentSparkReporter("test-output\\myReport.html"); // Specify location of the report

		sparkReporter.config().setDocumentTitle("Automation Report"); // Title of report
		sparkReporter.config().setReportName("Functional Testing"); // Name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// Set dynamic system information
		try {
			extent.setSystemInfo("Computer Name", InetAddress.getLocalHost().getHostName());
		} catch (Exception e) {
			extent.setSystemInfo("Computer Name", "Unknown");
		}
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));

		// Get browser name dynamically
		String browserName = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser Name", browserName);

	}

	// Method to log the test success status
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); // Create a new entry in the report
		test.log(Status.PASS, "Test case PASSED is: " + result.getName()); // Update status to PASS
	}

	// Method to log the test failure status
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED is: " + result.getName());
		test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable());
	}

	// Method to log the test skipped status
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case SKIPPED is: " + result.getName());
	}

	// Method to flush the ExtentReports
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}