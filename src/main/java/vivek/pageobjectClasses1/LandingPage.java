package vivek.pageobjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;

	@FindBy(id="userEmail")
	WebElement emailInputBox;

	//WebElement emailInputBox = driver.findElement(By.id("userEmail"));
	//      |					 		|
	//  	|				 			|
	//		V				 			V
	//WebElement emailInputBox   @FindBy(id="userEmail")


	@FindBy(id="userPassword")
	WebElement passwordInputBox;

	@FindBy(id="login")
	WebElement loginBtn;

	public LandingPage(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);//
	}

	//Action Methods
	public void goToURL() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

	public ProductCatalogue loginToApp(String email, String password) {

		emailInputBox.sendKeys(email);
		passwordInputBox.sendKeys(password);
		loginBtn.click();
		ProductCatalogue pc=new ProductCatalogue(driver);
		return pc;
	}

}
