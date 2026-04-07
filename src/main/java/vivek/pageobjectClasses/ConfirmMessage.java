package vivek.pageobjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vivek.abstractcomponents.AbstractComponents;

public class ConfirmMessage extends AbstractComponents {

	WebDriver driver;

	@FindBy(css=".hero-primary")
	WebElement confirmMsg;

	public ConfirmMessage(WebDriver driver) {

		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	public String orderConfirmMessage() {
			System.out.println(confirmMsg.getText());
			return confirmMsg.getText();

	}


}
