package vivek.pageobjectClasses;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vivek.abstractcomponents.AbstractComponents;

public class OrdersHistoryPage extends AbstractComponents {
	
	WebDriver driver;
	public OrdersHistoryPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//table/tbody/tr/td[2]")
	List<WebElement> orderHistoryList;
	
	public Boolean getOrdersHistoryList(String productName) {
		Boolean odHL= orderHistoryList.stream()
				 .anyMatch(s-> s.getText().equals(productName));
		return odHL;
	}

}
