package AniketMayekar.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AniketMayekarAbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	List <WebElement> productNames;
	
	
	public OrderPage(WebDriver driver) {
		
		super(driver);
		
		this.driver=driver;
		
	PageFactory.initElements(driver, this);
		
	}
	
	public Boolean verifyOrderDisplay(String productName) {
		
		Boolean Match =  productNames.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
		
		return Match;
	}
	
}

