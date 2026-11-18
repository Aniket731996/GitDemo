package AniketMayekar.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AniketMayekarAbstractComponents.AbstractComponents;

public class productCatalogue extends AbstractComponents {
	
	WebDriver driver;
	
	public productCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
				
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> Products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By ProductsBy = By.cssSelector(".mb-3");
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	By toastMessage = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList() throws InterruptedException {
		
		waitForElementToAppear(ProductsBy);
		
		return Products;
	}
	
	public WebElement getProductsByName(String productName) throws InterruptedException {
		
		WebElement Prod = getProductList().stream().filter(Product->Product.findElement(By.cssSelector("b")).getText()
				.equals(productName)).findFirst().orElse(null);
		return Prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		
		WebElement Prod = getProductsByName(productName);
		
		Prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDissapear(spinner);
	}
	
	}

