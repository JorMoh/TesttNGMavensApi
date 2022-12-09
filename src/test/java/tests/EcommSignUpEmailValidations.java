package tests;

import org.testng.annotations.Test;
import pagesPOM.EcommCommonPOM;
import pagesPOM.EcommSignUpPOM;
import utils.Driver;
import utils.dataReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class EcommSignUpEmailValidations {
	EcommCommonPOM home = new EcommCommonPOM();
	EcommSignUpPOM signup = new EcommSignUpPOM();
	
	
  @DataProvider
  public String[] testEmailsLength() {
	  String[] emails = new String[4];
	  emails[0] = "a@gmail.com"; //1 character
	  emails[1] = "ab@gmail.com"; //2 characters
	  emails[2] = "fsdfjnskdhsdkjgbgkjdfgjnlkdfjhdkjlbndflkgjs"
	  		+ "dflkgjhsdkjgbfkjh3465hht7uijkhgdfgdfge223r5wfsrfgyryrhdfgdffskghjfjhgrt@gmail.com"; //124 characters
	  
	  emails[3] = "fsdfjnskdhsdkjgbgkjdfgjnlkdfjhdkjlbndflkgjsdflkgjhsdkjgbfkjh3465hht7uijkhgdfgdfg"
	  		+ "e223r5wfsrfgyryrhdfgdffskghjfjhgrtd@gmail.com"; //125 characters
	  return emails;
  }
  
  @DataProvider
  public String[] testEmailsFormat() {
	  String[] emails = new String[3];
	  emails[0]="@gmail.com";
	  emails[1]="jordanmo.com";
	  emails[2]="b@gmail.om";

	  return emails;
  }
	
//  validate that the Email Address entered is less than or equal to 125 characters in length. 
//  If the entered Email Address is greater than 125 characters in length then I should see the
//  following inline error message: ”The email may not be greater than 125 characters.”
  
  @Test(description="validating the email field accepts emails that are less or equal to 125 characters in length."
		  , dataProvider="testEmailsLength")
  public void enterValidLength(String email){

	  //Enter a value that is 1 characters in length, and verify you don't get an error message. 
	  //Enter a value that is 2 characters in length, and verify you don't get an error message.
	  //Enter a value that is 124 characters in length, and verify you don't get an error message.
	  //Enter a value that is 125 characters in length, and verify you don't get an error message.
	      signup.emailAddressField.sendKeys(email);
		  signup.signUpBtn.click();
		  Assert.assertFalse(signup.emailErrorMsg.isDisplayed());
		  
	  
  }
	  
 @Test(description="validating the email field doesn't accept emails that are greater than 125 characters in length.")
  public void enterInvalidLength() {
	  
	  //Enter a value that is 126 characters in length, and verify you DO get the error message
	  //after you click the sign up button
	  signup.emailAddressField.sendKeys("fsdfjnskdjfhsdkjgbgkjdfgjnlkdfjhdkjlbndflkgjsdflkgjhsdkjg"
	  		+ "bfkjh3465hht7uijkhgdfgdfge223r5wfsrfgyryrhdfgdffskghjfjhgrt@gmail.com");
	  signup.signUpBtn.click();
	  Assert.assertTrue(signup.EmailCharsLengthError.isDisplayed());
 }
	
//-------------------------------------------------------------------------------------

	  
// Validate that Email Address entered follows the following format [string@domain.com].
// If the entered email is not in [string@domain.com] format then I should see the following inline error message:
// ”Please enter a valid email address.”
 
  @Test(description="validating the email field accepts emails that are in [string@domain.com] format ")
  public void enterValidFormat() {
	 
	  //1.Enter a valid email address "jordanmo@gmail.com" and click the sign up button,
	  //then verify the error message isn't displayed.
	  signup.emailAddressField.sendKeys("jordanmo@gmail.com");
	  signup.signUpBtn.click();
	  Assert.assertTrue(signup.emailErrorMsg.getText().isEmpty()); 
	  
  }
  
  @Test(description="validating the email field doesn't accept emails that are not in [string@domain.com] format"
		  , dataProvider="testEmailsFormat")
  public void enterInvalidFormat(String email) {
	  
	  signup.emailAddressField.sendKeys(email);
	  signup.signUpBtn.click();
	  Assert.assertTrue(signup.EmailFormatError.isDisplayed());
  }
  
  
  
  
  @BeforeMethod
  public void beforeMethod() {
	  Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  Driver.getDriver().manage().window().maximize();
	  
	  //1.navigate to the “Prime Tech Ecommerce Store”, https://primetech-store-qa.herokuapp.com/
	  Driver.getDriver().get(dataReader.getData("primeTechUrl"));
	  //2.click on the Welcome Dropdown.
	  home.welcomeDropdown.click();
	  //3.click on the ‘Sign Up’ Link.
	  home.signUpLink.click();

  }

  @AfterTest
  public void afterMethod() {
	  Driver.quitDriver();
  }

}
