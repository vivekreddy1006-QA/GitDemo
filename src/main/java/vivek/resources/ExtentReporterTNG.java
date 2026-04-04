package vivek.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTNG {
	
	public static ExtentReports getReporterObject() {
	//ExtentSparkReporter -
	String timeStamp=String.valueOf(System.currentTimeMillis());
	String path = System.getProperty("user.dir")+"\\Reports\\index.html"+"_"+timeStamp;
	ExtentSparkReporter reporter=new ExtentSparkReporter(path);	
	reporter.config().setReportName("Login Inputs Test");
	reporter.config().setDocumentTitle("Error Validations");
	
	//ExtentReports
	ExtentReports extent=new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Vivek Reddy");
	return extent;
	
	}

}
