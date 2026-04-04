package vivek.pageobjectClasses;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vivek.abstractcomponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;

	@FindBy(css=".col-lg-4.col-md-6") // ***@FindBy is applicable to only the elements which use driver.findElement(s) not for any elements reference to other elements
	List<WebElement> productsList;

	@FindBy(css=".ng-animating")
	WebElement loadingSpinner;

	By productBy=By.cssSelector(".col-lg-4.col-md-6");

	By toastMessage=By.cssSelector("#toast-container");
	public ProductCatalogue(WebDriver driver) {
		super(driver);// to call the WaitFor method and pass the Driver - use super keyword
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void addToCart(String productName) {

		WebElement prod1=getProductName(productName);
		prod1.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisAppear(loadingSpinner);

	}

	public List<WebElement> getProductList(){

		waitForElementToAppear(productBy);
		return productsList;
	}

	public WebElement getProductName(String productName) {

		WebElement prod= getProductList().stream()
										 .filter(s-> s.findElement(By.cssSelector("b"))
										 .getText().equals(productName))
										 .findFirst().orElse(null);
		return prod;
	}
	



}
