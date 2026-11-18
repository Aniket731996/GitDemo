package AniketMayekar.tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AniketMayekar.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class standAloneTest {

	public static void main(String[] args) {
		
	WebDriverManager.chromedriver().setup();
	
	WebDriver driver = new ChromeDriver();
	
	driver.manage().window().maximize();
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	
	driver.get("https://rahulshettyacademy.com/client");
	
	LandingPage landingPage = new LandingPage(driver);
	
	//driver.findElement(By.linkText("Register here")).click();
	
	//driver.findElement(By.id("firstName")).sendKeys("Aniket");
	
	//driver.findElement(By.cssSelector("input[placeholder='Last Name']")).sendKeys("Mayekar");
	
	//driver.findElement(By.xpath("//input[@formcontrolname='userEmail']")).sendKeys("Aniket12345678@gmail.com");
	
	//driver.findElement(By.xpath("//input[contains(@placeholder,'number')]")).sendKeys("9326688926");
	
	//WebElement StaticDropdown = driver.findElement(By.cssSelector("select[formcontrolname='occupation']"));
	
	//Select dropdown = new Select(StaticDropdown);
	
	//dropdown.selectByContainsVisibleText("Engineer");
	
	//driver.findElement(By.xpath("//input[@value='Male']")).click();
	
	//driver.findElement(By.id("userPassword")).sendKeys("Aniket@12345");
	
	//driver.findElement(By.cssSelector("input[formcontrolname='confirmPassword']")).sendKeys("Aniket@12345");
	
	//driver.findElement(By.xpath("//input[@type='checkbox']")).click();
	
	//driver.findElement(By.name("login")).click();
	
	//driver.findElement(By.cssSelector("button[class='btn btn-primary']")).click();
	
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	
	String ProductName = "ZARA COAT 3";
	
	driver.findElement(By.id("userEmail")).sendKeys("Aniket12345678@gmail.com");
	
	driver.findElement(By.id("userPassword")).sendKeys("Aniket@12345");
	
	driver.findElement(By.name("login")).click();
	
	WebDriverWait Wait = new WebDriverWait (driver, Duration.ofSeconds(5));
	
	Wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("products")));
	
	List<WebElement> Products = driver.findElements(By.cssSelector(".mb-3"));
	
	WebElement Prod = Products.stream().filter(Product->Product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
	
	Prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	
	Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	
	driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();
	
	List <WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
	
	Boolean Match =  cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(ProductName));
	
	Assert.assertTrue(Match);
	
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".container-fluid")));
	
	Actions a = new Actions(driver);
	
	a.sendKeys(driver.findElement(By.xpath("//input[contains(@placeholder,'Country')]")), "Ind").build().perform();
	
	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	
	driver.findElement(By.cssSelector(".action__submit")).click();
	
	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	
	driver.close();
	
	}

}
