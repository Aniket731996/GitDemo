package AniketMayekar.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AniketMayekarAbstractComponents.AbstractComponents;

public class cartPage extends AbstractComponents{
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public cartPage(WebDriver driver) {
		
		super(driver);
		
		this.driver=driver;
		
	PageFactory.initElements(driver, this);
		
	}
	
	public Boolean verifyProductsDisplay(String productName) {
		
		Boolean Match =  cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		
		return Match;
	}
	
	public checkOutPage goToCheckout() {
	
		checkoutEle.click();
		
		return new checkOutPage(driver);
	}

}
