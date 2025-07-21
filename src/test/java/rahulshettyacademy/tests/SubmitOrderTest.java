package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.OrderPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{

	String productName = "ZARA COAT 3";
	String countryName="india";
	
	@Test(dataProvider="getData",groups="Purchase")
	public void SubmitOrder(String email,String password,String productName) throws IOException, InterruptedException{
		
		ProductCatalogue productCatalogue=landingpage.loginApplication(email, password);
	
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartPage.goToCheckout();
		checkoutpage.selectCountry(countryName);
		ConfirmationPage confirmationPage=checkoutpage.submitOrder();
		String confirmMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println(confirmMessage);
	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest(){
		
		ProductCatalogue productCatalogue=landingpage.loginApplication("jsuhas822@gmail.com", "Rahulshetty@123#");
		OrderPage orderpage=productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"jsuhas822@gmail.com","Rahulshetty@123#","ZARA COAT 3"},{"jsuhas747@gmail.com","Rahul@123#","ADIDAS ORIGINAL"}};
	}

}
