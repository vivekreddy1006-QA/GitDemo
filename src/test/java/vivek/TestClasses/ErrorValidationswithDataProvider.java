package vivek.TestClasses;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vivek.TestComponents.BaseTest;

public class ErrorValidationswithDataProvider extends BaseTest{
	
	@Test(dataProvider="getDataFromDP")
	//When we have 15 data, we need mention all those in method as params, so to overcome this, use Hashmap
	public void loginWithInvalidCreds(String email, String passWord, String errorMsg) {
		
		lp.loginToApp(email, passWord);
		Assert.assertEquals(errorMsg,lp.getErrorMessage());
	}
	
	@DataProvider
	public Object[][] getDataFromDP() {
		Object[][] obj=new Object[][] {{"dummyVivek9809@gmail.com", "Vivek988","Incorrect email or password."},//email incorrect
										{"dummyVivek123@gmail.com", "Vivek98","Incorrect email or password."},//password incorrect
										{"dummyVivek9809@gmail.com", "Vivek88","Incorrect email or password."}};//both incorrect
												
		return obj;
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
