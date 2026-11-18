package AniketMayekar.tests;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
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
import org.testng.annotations.Test;
import AniketMayekar.TestComponents.BaseTest;
import AniketMayekar.TestComponents.Retry;
import AniketMayekar.pageobjects.ConfirmationPage;
import AniketMayekar.pageobjects.LandingPage;
import AniketMayekar.pageobjects.cartPage;
import AniketMayekar.pageobjects.checkOutPage;
import AniketMayekar.pageobjects.productCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorHandling"},retryAnalyzer= Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException{
	
	String productName = "ZARA COAT 3";
	
	 landingPage.loginApplication("AndyMurray@gmail.com", "Aniket@123");
	
	Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	
	}
	
	@Test
	public void productCatalogueValidation() throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		
		productCatalogue ProductCatalogue= landingPage.loginApplication("aniketmayekar731996@gmail.com", "Aniket@731996");
		
		List <WebElement> Products = ProductCatalogue.getProductList();
		
		ProductCatalogue.addProductToCart(productName);
		
		cartPage CartPage= ProductCatalogue.goToCartPage();
		
		Boolean Match = CartPage.verifyProductsDisplay("ZARA COAT 3");
		
		Assert.assertTrue(Match);
	}
}
