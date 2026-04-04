package vivek.pageobjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vivek.abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents{

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
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorPopup;
	
	@FindBy(css=".invalid-feedback")
	WebElement errorMsg;
	
//	@FindBy(xpath="(//div[contains(@class,'invalid-feedback')]/div)[2]")
//	WebElement pwdErrorMsg;
//When we use indexed LOCATORS, if we have only one Empty Error (either Email/password), the xpath/css doesn't work since only 1, locator is available
//it works only when both are empty, so for both empty messages, it will give us the first occurence of xpath
	
	

	public LandingPage(WebDriver driver) {
		super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);//
	}

	//Action Methods
	public void goToURL() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

	public ProductCatalogue loginToApp(String email, String password) {
		goToURL();
		emailInputBox.sendKeys(email);
		passwordInputBox.sendKeys(password);
		loginBtn.click();
		
		ProductCatalogue pc=new ProductCatalogue(driver);
		return pc;
	}
	
	public String getErrorMessage() {
		waitForWebElement(errorPopup);
		return errorPopup.getText();
	}
	
	public String getEmptyErrorMessage() {
		
		waitForWebElement(errorMsg);
		return errorMsg.getText();
		
	}
//	public String getEmptyPasswordErrorMessage() {
//		waitForWebElement(pwdErrorMsg);
//		System.out.println(pwdErrorMsg.getText());
//		return pwdErrorMsg.getText();
//	}

}
