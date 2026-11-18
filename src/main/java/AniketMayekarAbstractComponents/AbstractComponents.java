package AniketMayekarAbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AniketMayekar.pageobjects.OrderPage;
import AniketMayekar.pageobjects.cartPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
	
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[contains(@routerlink,'cart')]")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[contains(@routerlink,'myorders')]")
	WebElement orderHeader;
	
	public void waitForElementToAppear(By findBy) throws InterruptedException {
	
WebDriverWait Wait = new WebDriverWait (driver, Duration.ofSeconds(20));
	
	Wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

}
	public void waitForWebElementToAppear(WebElement element) throws InterruptedException {
		
		WebDriverWait Wait = new WebDriverWait (driver, Duration.ofSeconds(20));
			
		Wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public cartPage goToCartPage() {
		
		cartHeader.click();
		
		cartPage CartPage = new cartPage(driver);
		return CartPage;
	}
	
public OrderPage goToOrdersPage() {
		
	orderHeader.click();
		
		OrderPage  orderPage = new OrderPage(driver);
		return orderPage;
	}
	
	public void waitForElementToDissapear(WebElement ele) throws InterruptedException{
		
		Thread.sleep(2000);
		
		//WebDriverWait Wait = new WebDriverWait (driver, Duration.ofSeconds(20));

		//Wait.until(ExpectedConditions.invisibilityOf(ele));
	}
}
