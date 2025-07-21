package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
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
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException{
		
		ProductCatalogue productCatalogue=landingpage.loginApplication(input.get("email"), input.get("password"));
	
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match=cartPage.verifyProductDisplay(input.get("product"));
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
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "jsuhas822@gmail.com");
		map.put("password", "Rahulshetty@123#");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "jsuhas747@gmail.com");
		map1.put("password", "Rahul@123#");
		map1.put("product", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}};
	}

}
