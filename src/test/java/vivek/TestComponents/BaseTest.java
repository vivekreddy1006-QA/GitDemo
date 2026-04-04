package vivek.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import vivek.pageobjectClasses.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage lp;
	
	public WebDriver initializeBrowser() throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\vivek\\resources\\GlobalData.properties");
		prop.load(fis);
		//Here it will take the browser name from maven and checks and if it is null then it takes from GlobalData properties file other takes from Maven command
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");

		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	@BeforeMethod
	public LandingPage initializeApplication() throws IOException {
		
		driver=initializeBrowser();
		lp=new LandingPage(driver);
		lp.goToURL();
		return lp;
	}
	
	public List<HashMap<String, String>> getDataFromJSON(String filepath) throws IOException {
	    // Read file content into a string
	    String jsonReader = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

	    // Parse JSON into List of HashMaps
	    ObjectMapper mapper = new ObjectMapper();
	    List<HashMap<String, String>> data=mapper.readValue(jsonReader, new TypeReference<List<HashMap<String, String>>>() {});
	    return data;
	}
	
	public String getScreenShot(String testcaseName, WebDriver driver) throws IOException {
		//get the life of driver from Listeners ( onTestFailure() method)
		TakesScreenshot ts= (TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String timestamp = String.valueOf(System.currentTimeMillis());
		File dest = new File("D:\\Vivek-QEA\\Screenshots\\"+testcaseName+ timestamp +".png");
		FileUtils.copyFile(src, dest);
		return ("D:\\Vivek-QEA\\Screenshots\\"+testcaseName+ timestamp +".png");
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
