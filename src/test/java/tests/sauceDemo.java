package tests;

import org.testng.annotations.Test;

import pagesPOM.SauceDemoPOM;
import utils.Driver;
import utils.dataReader;

import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;

public class sauceDemo {
	SauceDemoPOM sauce = new SauceDemoPOM();
	
  @Test(priority=1, description="logging in with a valid username")
  public void validUser() {
//	  log in with valid username = standard_user   password = secret_sauce
	  sauce.usernameField.sendKeys(dataReader.getData("validSauceUsername"));
	  sauce.loginBtn.click();
//	  Verify that you are logged in successfully.
	  Assert.assertTrue(sauce.productsText.isDisplayed());
	  Assert.fail();
  }
  
  
  
  @Test(priority=2, description="loggin in with an invalid username", dependsOnMethods="validUser")
  public void invalidUser() {
	  
//	  log in with invalid username = invalid_user  password = secret_sauce
	  sauce.usernameField.sendKeys(dataReader.getData("invalidSauceUsername"));
	  sauce.loginBtn.click();
//	  Verify that you are not logged in, and you get the error message of:
//	  “Epic sadface: Username and password do not match any user in this service”
	  Assert.assertTrue(sauce.loginBtn.isDisplayed());
	  Assert.assertEquals(sauce.credentialsDontMatchError.getText(), dataReader.getData("invalidSauceUsernameError"));
  }
  
  
  
  @Test(priority=3, description="logging in with a locked out username")
  public void lockedoutUser() {
//	  log in with locked  username = locked_out_user   password = secret_sauce
	  sauce.usernameField.sendKeys(dataReader.getData("lockedoutSauceUsername"));
	  sauce.loginBtn.click();
//	  Verify that you are not logged in, and you get the error message of:
//	  “Epic sadface: Sorry, this user has been locked out.”
	  Assert.assertTrue(sauce.loginBtn.isDisplayed());
	  Assert.assertEquals(sauce.lockedOutUserError.getText(), dataReader.getData("lockedoutSauceError"));
	  Driver.quitDriver();

  }
  
  
  @BeforeMethod
  public void beforeMethod() {
	  Driver.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  Driver.getDriver().manage().window().maximize();
	  //go to https://saucedemo.com
	  Driver.getDriver().get(dataReader.getData("sauceDemoUrl"));
	  sauce.passwordField.sendKeys(dataReader.getData("validSaucePassword"));
	  

	  
  }

  

}
