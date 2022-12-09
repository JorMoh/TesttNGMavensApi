package tests;

import org.testng.annotations.Test;

import pagesPOM.EcommAccountPOM;
import pagesPOM.EcommCommonPOM;
import pagesPOM.EcommSignUpPOM;
import utils.Driver;
import utils.dataReader;

import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class EcommValidateRequiredFields {
	EcommCommonPOM common = new EcommCommonPOM();
	EcommSignUpPOM signup = new EcommSignUpPOM();
	EcommAccountPOM account = new EcommAccountPOM();
	
  @Test(groups="smoke-test")
  public void VerifyRequiredFieldMsg() throws InterruptedException {
	  
	  //navigate to the “Prime Tech Ecommerce Store”, https://primetech-store-qa.herokuapp.com/
	  Driver.getDriver().get(dataReader.getData("primeTechUrl"));
	  //.click on the Welcome Dropdown.
	  Thread.sleep(1000);
	  common.welcomeDropdown.click();
	  //click on the ‘Sign Up’ Link.
	  common.signUpLink.click();
	  
	  //leave all fields blank and click sign up. verify all fields display the error messages
	  //and there is no account created.
	  signup.signUpBtn.click();
	  Assert.assertTrue(signup.EmailRequiredMsg.isDisplayed());
	  Assert.assertTrue(signup.firstNameRequiredMsg.isDisplayed());
	  Assert.assertTrue(signup.lastNameRequiredMsg.isDisplayed());
	  Assert.assertTrue(signup.passwordRequiredMsg.isDisplayed());
	  Assert.assertTrue(signup.signUpBtn.isDisplayed());
	  

	  
	  //leave all fields blank but enter a value in password and click sign up.
	  //verify only the other fields display the error.
	  //and there is no account created. 
	  Driver.getDriver().navigate().refresh();
	  
	  signup.passwordField.sendKeys("wrhJ3@eF");
	  signup.signUpBtn.click();
	  
	  Assert.assertTrue(signup.EmailRequiredMsg.isDisplayed());
	  Assert.assertTrue(signup.firstNameRequiredMsg.isDisplayed());
	  Assert.assertTrue(signup.lastNameRequiredMsg.isDisplayed());
	 // Assert.assertFalse(signup.passwordRequiredMsg.isDisplayed());
	  Assert.assertTrue(signup.signUpBtn.isDisplayed());
	  
	  
	  //leave email and firstName blank. Enter values in lastName and password fields, and click sign up.
	  //verify lastName and password fields display the error messages.
	  //and there is no account created.
	  
      Driver.getDriver().navigate().refresh();
	  
	  signup.passwordField.sendKeys("wrhJ3@eF");
	  signup.lastNameField.sendKeys("Ab");
	  signup.signUpBtn.click();
	  
	  Assert.assertTrue(signup.EmailRequiredMsg.isDisplayed());
	  Assert.assertTrue(signup.firstNameRequiredMsg.isDisplayed());
	  Assert.assertTrue(signup.signUpBtn.isDisplayed());
	  
	  //leave all fields blank but enter a value in lastName and click sign up.
	  //verify all fields except last name display the error messages
	  //and there is no account created. 
      Driver.getDriver().navigate().refresh();
	  
	  signup.lastNameField.sendKeys("ab");
	  signup.signUpBtn.click();
	  
	  Assert.assertTrue(signup.EmailRequiredMsg.isDisplayed());
	  Assert.assertTrue(signup.firstNameRequiredMsg.isDisplayed());
	  Assert.assertTrue(signup.passwordRequiredMsg.isDisplayed());
	  Assert.assertTrue(signup.signUpBtn.isDisplayed());
	  
	  //only leave the email field blank. Enter values in the other fields and click sign up.
	  //verify only email field displays it's error message.
      Driver.getDriver().navigate().refresh();
	  
	  signup.passwordField.sendKeys("wrhJ3@eF");
	  signup.lastNameField.sendKeys("Ab");
	  signup.firstNameField.sendKeys("abc");
	  signup.signUpBtn.click();
	  
	  Assert.assertTrue(signup.EmailRequiredMsg.isDisplayed());
	  Assert.assertTrue(signup.signUpBtn.isDisplayed());
	  
	  //Enter valid values in all fields and click sign up. verify a new acccount is created.
      Driver.getDriver().navigate().refresh();
	  
	  signup.passwordField.sendKeys("wrhJ3@eF");
	  signup.lastNameField.sendKeys("Ab");
	  signup.firstNameField.sendKeys("abc");
	  signup.emailAddressField.sendKeys("abc@gmail.com");
	  signup.signUpBtn.click();
	  Assert.assertTrue(account.accDetailsText.isDisplayed());
	  
	  
  
  }
  
  
  @BeforeMethod(groups="smoke-test")
  public void beforeMethod() {
	  Driver.getDriver().manage().window().maximize();
	  Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @AfterMethod(groups="smoke-test")
  public void afterMethod() {
	  Driver.quitDriver();
  }

}
