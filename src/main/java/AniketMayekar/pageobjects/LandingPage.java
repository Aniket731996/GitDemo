package AniketMayekar.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AniketMayekarAbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement UserEmail = driver.findElement(By.id("userEmail"));
	
	//Page Factory
	
	@FindBy(id="userEmail")
	WebElement UserEmail;
	
	@FindBy(id="userPassword")
	WebElement UserPassword;

	@FindBy(name="login")
	WebElement Login;
	
	@FindBy(css="[class*='toast-message']")
	WebElement errorMessage;
	
	public productCatalogue loginApplication(String email, String password) {
		
		UserEmail.sendKeys(email);
		UserPassword.sendKeys(password);
		Login.click();
		
		productCatalogue ProductCatalogue = new productCatalogue(driver);
		return ProductCatalogue;
	}
	
	public String getErrorMessage() throws InterruptedException{
		
		waitForWebElementToAppear(errorMessage);
		
		return errorMessage.getText();
	}
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
}
