package vivek.TestComponents;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import vivek.resources.ExtentReporterTNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentReports extent=ExtentReporterTNG.getReporterObject();
	ExtentTest test;
	
    @Override		
    public void onTestStart(ITestResult result) {					
        // TODO Auto-generated method stub				
    	test=extent.createTest(result.getMethod().getMethodName());	
    }		

    @Override		
    public void onTestSuccess(ITestResult result) {					
        // TODO Auto-generated method stub				
        test.log(Status.PASS,"Test Case has been Passed successfully");
    }
	
	@Override		
    public void onFinish(ITestContext result) {					
        // TODO Auto-generated method stub				
        extent.flush();
    }		

    @Override		
    public void onStart(ITestContext result) {					
        // TODO Auto-generated method stub				
        
    }		

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onTestFailure(ITestResult result) {					
        // TODO Auto-generated method stub		
    	String filePath = null;
        test.fail(result.getThrowable());
        try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			filePath=getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		test.addScreenCaptureFromPath(filePath, "📌 Failed at this Step, click on me to see the Error");
        
    }		

    @Override		
    public void onTestSkipped(ITestResult result) {					
        // TODO Auto-generated method stub				
        		
    }				
}
