package AniketMayekar.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import AniketMayekar.TestComponents.BaseTest;
import AniketMayekar.pageobjects.ConfirmationPage;
import AniketMayekar.pageobjects.LandingPage;
import AniketMayekar.pageobjects.cartPage;
import AniketMayekar.pageobjects.checkOutPage;
import AniketMayekar.pageobjects.productCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest{
	
	public LandingPage landingpage;
	public productCatalogue ProductCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("Landed on Ecommerce page")
	public void Landed_on_Ecommerce_page() throws IOException {
		
		landingpage= launchApplication();
		
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password){
		
	ProductCatalogue = landingPage.loginApplication(username,password);
		
	}

	@When("^Add prpduct (.+) to cart$")
	public void Add_prpduct_to_cart(String productName) throws InterruptedException {
		
		List <WebElement> Products = ProductCatalogue.getProductList();
		
		ProductCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and Submit the order$")
	public void Checkout_Submit_order(String productName) throws InterruptedException {
		
		cartPage CartPage= ProductCatalogue.goToCartPage();
		
		Boolean Match = CartPage.verifyProductsDisplay(productName);
		
		Assert.assertTrue(Match);
		
		checkOutPage CheckoutPage = CartPage.goToCheckout();
		
		CheckoutPage.selectCountry("India");
		
		confirmationPage = CheckoutPage.submitButton();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String String) {
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(String));
		driver.quit();
	}
	
	@Then("{string} message is displayed on Login page")
	public void message_is_displayed_on_Login_page(String String) throws InterruptedException {
		
		Assert.assertEquals(String, landingPage.getErrorMessage());
		driver.quit();
	}
}
