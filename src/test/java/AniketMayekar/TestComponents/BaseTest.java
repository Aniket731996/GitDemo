package AniketMayekar.TestComponents;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import AniketMayekar.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties Prop = new Properties();
		
		FileInputStream FIS = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\AniketMayekar\\resources\\GlobalData.properties");
		
		Prop.load(FIS);
		
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : Prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
		 driver = new ChromeDriver(options);
		 driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			//System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
		 driver = new FirefoxDriver();
			
		}
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		return driver;	
}
	
		
		public List<HashMap<String, String>> getJasonDataToMap(String FilePath) throws IOException {
			//Convert JSON to String
			
			String jsonContent= FileUtils.readFileToString(new File(FilePath),StandardCharsets.UTF_8);
		
		//String to HashMap Jackson Databind
			
			ObjectMapper mapper = new ObjectMapper();
			
			List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
				
				return data;
			}
		
		public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
			
			TakesScreenshot ts = (TakesScreenshot) driver;
			File Source= ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir")+"\\report\\"+testCaseName+".png");
			FileUtils.copyFile(Source, file);
			return System.getProperty("user.dir")+"\\report\\"+testCaseName+".png";
		}
		
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		
		driver =  initializeDriver();
		
		landingPage = new LandingPage(driver);
		
		landingPage.goTo();
		
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	}
