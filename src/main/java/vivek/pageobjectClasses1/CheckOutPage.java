package vivek.pageobjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vivek.abstractcomponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{

	WebDriver driver;

	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryDropdown;

	@FindBy(css=".ta-results button:last-of-type")
	WebElement lastValue;

	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrderBtn;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void chooseCountry(String countryName) {

		Actions act=new Actions(driver);
		act.sendKeys(countryDropdown, countryName).build().perform();
		//waitForElementToAppear(By.cssSelector(".ta-results"));
		lastValue.click();

	}

	public ConfirmMessage placeOrder() {

		placeOrderBtn.click();
		return new ConfirmMessage(driver);
	}




}
