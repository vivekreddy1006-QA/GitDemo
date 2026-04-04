package vivek.abstractcomponents;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import vivek.pageobjectClasses.CartPage;
import vivek.pageobjectClasses.OrdersHistoryPage;

public class AbstractComponents {


	WebDriver driver;

	@FindBy(xpath="//button[contains(text(),'Cart')]")
	WebElement addToCartBtn;
	
	@FindBy(xpath="//button[contains(text(),'ORDERS')]")
	WebElement ordersBtn;

	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public CartPage goToCartPage() {
		addToCartBtn.click();
		CartPage cp=new CartPage(driver);
		return cp;
	}
	
	public OrdersHistoryPage goToOrdersHistoryPage() {
		ordersBtn.click();
		OrdersHistoryPage ohp=new OrdersHistoryPage(driver);
		return ohp;
		
	}
	
	public void waitForElementToAppear(By elementBy) {

		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
	}

	public void waitForElementToDisAppear(WebElement element) {

		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForWebElement(WebElement element) {

		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(element));
	}



}
