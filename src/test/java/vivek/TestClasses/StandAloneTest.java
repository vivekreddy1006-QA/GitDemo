package vivek.TestClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
//		LandingPage lp=new LandingPage(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("dummyVivek123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Vivek988");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4.col-md-6")));
		List<WebElement> productsList=driver.findElements(By.cssSelector(".col-lg-4.col-md-6"));
		WebElement product1=productsList.stream().filter(s-> s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		product1.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		List<WebElement> cartProduct=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProduct.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions act=new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build().perform();
		driver.findElement(By.cssSelector(".ta-results button:last-of-type")).click();
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Boolean match2=confirmMessage.equalsIgnoreCase("Thankyou for the order.");
		Assert.assertTrue(match2);
	}

}
