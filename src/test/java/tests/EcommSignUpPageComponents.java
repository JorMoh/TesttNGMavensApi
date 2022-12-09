package tests;

import org.testng.annotations.Test;

import pagesPOM.EcommCommonPOM;
import pagesPOM.EcommSignUpPOM;
import utils.Driver;
import utils.dataReader;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class EcommSignUpPageComponents {
	EcommCommonPOM home = new EcommCommonPOM();
	EcommSignUpPOM signup = new EcommSignUpPOM();
	
  @Test(description="verify sign up page components",groups="smoke-test")
  public void verifyComponents() {
	  
	  //1.navigate to the “Prime Tech Ecommerce Store”, https://primetech-store-qa.herokuapp.com/
	  Driver.getDriver().get(dataReader.getData("primeTechUrl"));
	  //2.click on the Welcome Dropdown.
	  home.welcomeDropdown.click();
	  //3.click on the ‘Sign Up’ Link.
	  home.signUpLink.click();
	  //4.verify the system displays the following page with the UI components as follows:
	  
      //Page Title: “Sign Up”
	  Assert.assertTrue(signup.signUpHeader.isDisplayed()); 
      //Text Box -1: A Text box with the Label ‘Email Address’ with “Please Enter Your Email” in grey as the default value.
      Assert.assertEquals(signup.emailAddressLabel.getText(), dataReader.getData("emailAddressLabel"));
      Assert.assertEquals(signup.emailAddressField.getAttribute("placeholder"), dataReader.getData("emailAddressPlaceholder"));
      //Text Box -2: A Text box with the Label ‘First Name’ with “Please Enter Your First Name” in grey as the default value.
      Assert.assertEquals(signup.firstNameLabel.getText(), dataReader.getData("firstNameLabel"));
      Assert.assertEquals(signup.firstNameField.getAttribute("placeholder"), dataReader.getData("firstNamePlaceholder"));
      //Text Box -3: A Text box with the Label ‘Last Name’ with “Please Enter Your Last Name” in grey as the default value.
      Assert.assertEquals(signup.lastNameLabel.getText(), dataReader.getData("lastNameLabel"));
      Assert.assertEquals(signup.lastNameField.getAttribute("placeholder"), dataReader.getData("lastNamePlaceholder"));
      //Text Box -4: A Text box with the Label ‘Password’ with “Please Enter Your Password” in grey as the default value.
      Assert.assertEquals(signup.passwordLabel.getText(), dataReader.getData("passwordLabel"));
      Assert.assertEquals(signup.passwordField.getAttribute("placeholder"), dataReader.getData("passwordPlaceholder"));
      //Button -1: A button titled ‘Login with Google’.
      Assert.assertTrue(signup.loginWithGoogleBtn.isDisplayed());
      Assert.assertEquals(signup.loginWithGoogleBtn.getText(),dataReader.getData("googleLoginBtnText") );
      //Button -2: A button titled ‘Login with Facebook’.
      Assert.assertTrue(signup.loginWithFacebookBtn.isDisplayed());
      Assert.assertEquals(signup.loginWithFacebookBtn.getText(),dataReader.getData("facebookLoginBtnText") );
      //Grey #eceef3 underline beneath Text Box -4.
      Assert.assertTrue(signup.underlineBeneathPassword.isDisplayed());
      //Checkbox -1: A Checkbox titled ‘Subscribe To Our Newsletter’
      assertTrue(signup.newsletterSubscriptioncheck.isDisplayed());
      Assert.assertEquals(signup.newsletterSubscriptionlabel.getText(),dataReader.getData("newsletterSubscriptionlabel") );
      //Button - 3: A button titled ‘Sign Up’.
      assertTrue(signup.signUpBtn.isDisplayed());
      assertTrue(signup.signUpBtn.getText().equals("Sign Up"));
      //Link 1 - A Link titled ‘Back To Login’
      assertTrue(signup.backToLoginLink.isDisplayed());
      Assert.assertEquals(signup.backToLoginLink.getText(),dataReader.getData("backToLoginLinkText") );

	  
  }
  @BeforeMethod(groups="smoke-test")
  public void beforeMethod() {
	  Driver.getDriver().manage().window().maximize();
	  Driver.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
  }
  
//  @AfterMethod(groups="smoke-test")
//  public void afterMethod() {
//	  Driver.quitDriver();
//  }
  
  

}
