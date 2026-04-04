package vivek.TestClasses;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vivek.TestComponents.BaseTest;

public class ErrorValidationswithDataFromJSON extends BaseTest{
	
	
	@Test(dataProvider="getDataFromDP")
	
	public void loginWithInvalidCreds(HashMap<String,String> input) {
		
		lp.loginToApp(input.get("email"), input.get("pass"));
		Assert.assertEquals(input.get("errorMsg"),lp.getErrorMessage());
	}
	
	@DataProvider
	public Object[][] getDataFromDP() throws IOException {

		List<HashMap<String, String>> data=getDataFromJSON(System.getProperty("user.dir")+"\\src\\test\\java\\vivek\\testData\\loginCreds.json" );
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};
	}
	
	
}
