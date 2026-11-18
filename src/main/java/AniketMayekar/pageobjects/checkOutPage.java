package AniketMayekar.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AniketMayekarAbstractComponents.AbstractComponents;

public class checkOutPage extends AbstractComponents{
	
	WebDriver driver;
	
	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	By results = By.cssSelector(".ta-results");
	
@FindBy(css="[placeholder='Select Country']")
WebElement country;

@FindBy(css=".action__submit")
WebElement submit;

@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
WebElement selectCountry;

public void selectCountry(String countryName) throws InterruptedException {
	
Actions a = new Actions(driver);
	
	a.sendKeys(country, countryName).build().perform();
	
	waitForElementToAppear(results);
	
	selectCountry.click();
}

public ConfirmationPage submitButton() {
	
	submit.click();
	
	return new ConfirmationPage(driver);
}
}

