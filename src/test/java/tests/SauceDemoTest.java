package tests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pagesPOM.SauceDemoPOM;
import utils.Driver;
import utils.dataReader;

public class SauceDemoTest {
	
	SauceDemoPOM sauce = new SauceDemoPOM();
	
	@BeforeMethod
	  public void commonSteps() {
//		  go to https://saucedemo.com
		  Driver.getDriver().get(dataReader.getData("sauceDemoUrl"));
		  Driver.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		  Driver.getDriver().manage().window().maximize();
		  sauce.passwordField.sendKeys(dataReader.getData("validSaucePassword"));
}
	
	@Test
	  public void suaceDemoLogin1() {
		
//		  log in with valid username = standard_user password = secret_sauce
		  sauce.usernameField.sendKeys(dataReader.getData("validSauceUsername"));
		  sauce.loginBtn.click();
//		  Verify that you are logged in successfully.
		  SoftAssert assrt = new SoftAssert();
		  assrt.assertTrue(sauce.productsText.isDisplayed());
		  assrt.assertAll();
		 
	  }
	
	 @Test
	  public void suaceDemoLogin2() {
		  
//		  log in with invalid username = invalid_user password = secret_sauce
		  sauce.usernameField.sendKeys(dataReader.getData("invalidSauceUsername"));
		  sauce.loginBtn.click();
//	    Verify that you are not logged in, and you get the error message of:		 
//		  Epic sadface: Username and password do not match any user in this service
		  SoftAssert assrt = new SoftAssert();
		  assrt.assertTrue(sauce.loginBtn.isDisplayed());
		  assrt.assertEquals(sauce.credentialsDontMatchError.getText(), dataReader.getData("invalidSauceUsernameError"));
		  assrt.assertAll();

	  }
	  @Test
	  public void suaceDemoLogin3() {
		  
//		  log in with locked username = locked_out_user password = secret_sauce
		  sauce.usernameField.sendKeys(dataReader.getData("lockedoutSauceUsername"));
		  sauce.loginBtn.click();
//		  Verify that you are not logged in, and you get the error message of:
//		 “Epic sadface: Sorry, this user has been locked out.”
		  SoftAssert assrt = new SoftAssert();
		  assrt.assertTrue(sauce.loginBtn.isDisplayed());
		  assrt.assertEquals(sauce.lockedOutUserError.getText(), dataReader.getData("lockedoutSauceError"));
		  Driver.quitDriver();
		  assrt.assertAll();
		  
	  }
	
}
