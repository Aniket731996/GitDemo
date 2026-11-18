package AniketMayekar.tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AniketMayekar.TestComponents.BaseTest;
import AniketMayekar.pageobjects.ConfirmationPage;
import AniketMayekar.pageobjects.LandingPage;
import AniketMayekar.pageobjects.OrderPage;
import AniketMayekar.pageobjects.cartPage;
import AniketMayekar.pageobjects.checkOutPage;
import AniketMayekar.pageobjects.productCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	
	//String productName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String>input) throws IOException, InterruptedException {
	
	productCatalogue ProductCatalogue= landingPage.loginApplication(input.get("email"),input.get("password"));
	
	List <WebElement> Products = ProductCatalogue.getProductList();
	
	ProductCatalogue.addProductToCart(input.get("productName"));
	
	cartPage CartPage= ProductCatalogue.goToCartPage();
	
	Boolean Match = CartPage.verifyProductsDisplay(input.get("productName"));
	
	Assert.assertTrue(Match);
	
	checkOutPage CheckoutPage = CartPage.goToCheckout();
	
	CheckoutPage.selectCountry("India");
	
	ConfirmationPage confirmationPage = CheckoutPage.submitButton();
	
	String confirmMessage = confirmationPage.getConfirmationMessage();
	
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	
	}
	
	@Test(dataProvider="getData",dependsOnMethods={"submitOrder"})
	public void oderHistoryTest(HashMap<String,String>input) {
		//ZARA COAT 3
		productCatalogue ProductCatalogue= landingPage.loginApplication(input.get("email"),input.get("password"));
		OrderPage orderPage= ProductCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(input.get("productName")));
	}
	
	@DataProvider
	public Object[][]getData() throws IOException {
		
		//return new Object[] [] {{"Aniket12345678@gmail.com","Aniket@12345","ZARA COAT 3"},{"AndyMurray@gmail.com","Aniket@123456","IPHONE 13 PRO"}};
		//HashMap<String,String> map = new HashMap<String,String>();
		//map.put("email","Aniket12345678@gmail.com");
		//map.put("password", "Aniket@12345");
		//map.put("productName", "ZARA COAT 3");
		
		//HashMap<String,String> map1 = new HashMap<String,String>();
		//map1.put("email","AndyMurray@gmail.com");
		//map1.put("password", "Aniket@123456");
		//map1.put("productName", "IPHONE 13 PRO");
		
		List<HashMap<String,String>>data=getJasonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AniketMayekar\\data\\PurchaseOrder.json");
		return new Object[][]{{data.get(0)},
		{data.get(1)}
		};
	}

}
