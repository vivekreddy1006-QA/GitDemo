package vivek.CucumberStepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vivek.TestComponents.BaseTest;
import vivek.pageobjectClasses.CartPage;
import vivek.pageobjectClasses.CheckOutPage;
import vivek.pageobjectClasses.ConfirmMessage;
import vivek.pageobjectClasses.LandingPage;
import vivek.pageobjectClasses.ProductCatalogue;

public class CucumberStepDefinitionImpl extends BaseTest{
	
	public LandingPage lp;
	public ProductCatalogue pc;
	String confirmMsg;
	
	@Given("I landed on ECommerce page")
	public void I_landed_on_ECommerce_page() throws IOException 
	{
		lp=initializeApplication();
	}
	//Here email and password are dynamic in .feature file so below is the syntax for Regular Exp
	//@Given("^ Text to be placed (.+) $") ^ & $ at the end, (.+) for the dynamic fields
	
	@Given("^Logged in with email (.+) and password (.+)$")
	public void logged_In_email_and_password(String email, String password)
	{
		pc=lp.loginToApp(email, password);	
	}
	
	@When("^I add product (.+) to cart")
	public void add_Product_to_Cart(String productName) {
		pc.addToCart(productName);
	}
	
	@When("^checkout (.+) and Submit Order$")
	public void checkOut_And_SubmitOrder(String productName)
	{
		CartPage cp=pc.goToCartPage();

		Boolean match=cp.verifyProductIsDisplayed(productName);
		Assert.assertTrue(match);

		CheckOutPage cop=cp.goToCheckOutPage();
		cop.chooseCountry("India");

		ConfirmMessage cm=cop.placeOrder();
		confirmMsg=cm.orderConfirmMessage();
	}
	
	@Then("{string} message is displayed on Confirmation page")
	public void verify_Confirmation_Message(String expectedMsg) {
		Boolean match2=confirmMsg.equalsIgnoreCase("Thankyou for the order.");
		Assert.assertTrue(match2);
	}
}
