package vivek.pageobjectClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vivek.abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents{

	WebDriver driver;

	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;

	@FindBy(css=".totalRow button")
	WebElement checkOutBtn;

	public CartPage(WebDriver driver) {
		super(driver);// to call the WaitFor method and pass the Driver - use super keyword
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public CheckOutPage goToCheckOutPage() {
		checkOutBtn.click();
		CheckOutPage cop=new CheckOutPage(driver);
		return cop;
	}

	public Boolean verifyProductIsDisplayed(String productName) {
		cartBtn.click();
		System.out.println(productName);
		Boolean match=cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;

	}

}
