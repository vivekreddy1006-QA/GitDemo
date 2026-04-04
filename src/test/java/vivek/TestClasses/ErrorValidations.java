package vivek.TestClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import vivek.TestComponents.BaseTest;

public class ErrorValidations extends BaseTest{
	
	@Test
	public void loginWithInvalidEmail() {
		
		lp.loginToApp("dummyVivek9809@gmail.com", "Vivek988");
		Assert.assertEquals("Incorrect email or password.",lp.getErrorMessage());
	}
	
	@Test
	public void loginWithInvalidPassword() {
		
		lp.loginToApp("dummyVivek123@gmail.com", "Vivek98");
		Assert.assertEquals("Incorrect email or password.",lp.getErrorMessage());
	}
	@Test
	public void loginWithInvalidEmailPassword() {
		
		lp.loginToApp("dummyVivek9809@gmail.com", "Vivek88");
		Assert.assertEquals("Incorrect email or password.",lp.getErrorMessage());
	}
	@Test
	public void loginWithEmptyEmailPassword() {
		
		lp.loginToApp("", "");
		Assert.assertEquals("*Email is required",lp.getEmptyErrorMessage());
		
	}
}
