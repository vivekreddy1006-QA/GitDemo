package vivek.TestClasses;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sun.net.httpserver.Authenticator.Retry;

import vivek.TestComponents.BaseTest;
import vivek.TestComponents.RetryFailedTC;

public class ExtentReportsTest extends BaseTest{
	
	ExtentReports extent;
	
	@BeforeTest
	public void getExtentReport() {
		
		//ExtentSparkReporter -
		String path = System.getProperty("user.dir")+"\\Reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);	
		reporter.config().setReportName("Login Inputs Test");
		reporter.config().setDocumentTitle("Error Validations");
		
		//ExtentReports
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Vivek Reddy");
	}
	
	@Test(retryAnalyzer=RetryFailedTC.class)
	
	public void loginWithInvalidCreds() {
		extent.createTest("loginWithInvalidCreds");
		lp.loginToApp("dummyVivek12@gmail.com","Vivek100");
		Assert.assertEquals("Incorrect  or password.",lp.getErrorMessage());
		extent.flush();
	}

}
