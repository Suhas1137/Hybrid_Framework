package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	
	@Test(groups={"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException{
		
		String productName = "ZARA COAT 3";
		String countryName="india";
		landingpage.loginApplication("jsuhas82@gmail.com", "Rahulshetty@123#");
		Assert.assertEquals("Incorrect email  password.", landingpage.getErrorMessage());
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException{
		
		String productName = "ZARA COAT 3";
		String countryName="india";
		ProductCatalogue productCatalogue=landingpage.loginApplication("jsuhas822@gmail.com", "Rahulshetty@123#");
	
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
	}
}
