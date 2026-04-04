package vivek.TestClasses;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vivek.TestComponents.BaseTest;

public class ErrorValidationswithDPHashMap extends BaseTest{
	
	@Test(dataProvider="getDataFromDP")
	//When we have 15 data, we need mention all those in method as params, so to overcome this, use Hashmap
	public void loginWithInvalidCreds(HashMap<String,String> input) {
		
		lp.loginToApp(input.get("email"), input.get("pass"));
		Assert.assertEquals(input.get("errorMsg"),lp.getErrorMessage());
	}
	
	@DataProvider
	public Object[][] getDataFromDP() {
		HashMap<String,String> incorrectEmail=new HashMap<String,String>();
		incorrectEmail.put("email","dummyVivek9809@gmail.com");
		incorrectEmail.put("pass","Vivek988");
		incorrectEmail.put("errorMsg", "Incorrect email or password.");
		
		HashMap<String,String> incorrectPass=new HashMap<String,String>();
		incorrectPass.put("email","dummyVivek123@gmail.com");
		incorrectPass.put("pass","Vivek98");
		incorrectPass.put("errorMsg", "Incorrect email or password.");
		
		HashMap<String,String> incorrectEmailPass=new HashMap<String,String>();
		incorrectEmailPass.put("email","dummyVivek9809@gmail.com");
		incorrectEmailPass.put("pass","Vivek88");
		incorrectEmailPass.put("errorMsg", "Incorrect email or password.");
												
		return new Object[][] {{incorrectEmail},{incorrectPass},{incorrectEmailPass}};
	}
	
	@Test(dataProvider = "getEmptyDataFromDP")
	public void loginWithEmptyEmailPassword(String email, String passWord, String errorMsg) {

		lp.loginToApp(email, passWord);
		Assert.assertEquals(errorMsg, lp.getEmptyErrorMessage());
	}

	@DataProvider
	public Object[][] getEmptyDataFromDP() {

		Object[][] obj = new Object[][] { { "dummyVivek123@gmail.com", "", "*Password is required" }, // password empty
											{ "", "Vivek988", "*Email is required" }, // email empty
											{ "", "", "*Email is required" } };// both empty

		return obj;
	}
}
