package vivek.TestClasses;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vivek.TestComponents.BaseTest;
import vivek.pageobjectClasses.CartPage;
import vivek.pageobjectClasses.CheckOutPage;
import vivek.pageobjectClasses.ConfirmMessage;
import vivek.pageobjectClasses.OrdersHistoryPage;
import vivek.pageobjectClasses.ProductCatalogue;


public class FrameWorkTest extends BaseTest{
	
	ProductCatalogue pc;
	String productName="ZARA COAT 3";
	@Test
	public void SubmitOrder() throws IOException
	{
		
		
//		LandingPage	lp = initializeApplication();
		//Using BeforeMethod in BaseTest since its applicable for all Testcases

		pc=lp.loginToApp("dummyVivek123@gmail.com", "Vivek988");
		pc.addToCart(productName);

		CartPage cp=pc.goToCartPage();

		Boolean match=cp.verifyProductIsDisplayed(productName);
		Assert.assertTrue(match);

		CheckOutPage cop=cp.goToCheckOutPage();
		cop.chooseCountry("India");

		ConfirmMessage cm=cop.placeOrder();
		String confirmMessage=cm.orderConfirmMessage();

		Boolean match2=confirmMessage.equalsIgnoreCase("Thankyou for the order.");
		Assert.assertTrue(match2);

		System.out.println(confirmMessage);

	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void orderHistory() {
		lp.loginToApp("dummyVivek123@gmail.com", "Vivek988");
		OrdersHistoryPage ohp=lp.goToOrdersHistoryPage();
		Assert.assertTrue(ohp.getOrdersHistoryList(productName));
	}

	@DataProvider
	public void getDataFromDProviders() {
		
	}
}
