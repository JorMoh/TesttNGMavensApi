package tests;

import org.testng.annotations.Test;

import pagesPOM.EcommCommonPOM;
import pagesPOM.EcommSignUpPOM;
import utils.Driver;
import utils.ExcelUtils;
import utils.dataReader;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class EcommSignUpPasswordValidations {
	EcommCommonPOM home = new EcommCommonPOM();
	EcommSignUpPOM signup = new EcommSignUpPOM();
	
//	Validate that The system encrypts the “password” value by the user.
//	The system should validate that the “password” value meets the following criteria:
//	A. Password length between 8-12 characters.
//	B. Password must contain at least 1 special character. 
//	C. Password must have at least 1 uppercase letter.
//	D. Password must have have at least 1 number. 
//	If the password entered does not meet the criteria mentioned in 2A - 2D, then the system should provide the following inline error message: 
//	”Please enter a valid password.”
	
	@DataProvider
	public String[] validTestPasswords() {
		String[] passwords = ExcelUtils.getExcelDataInAColumn("./src/test/resources/testData/SignUpFieldsTestData.xlsx", "Sheet3");
		return passwords;
	}
	
	@DataProvider
    public String[] invalidTestPasswords() {
    	String[] passwords = ExcelUtils.getExcelDataInAColumn("./src/test/resources/testData/SignUpFieldsTestData.xlsx", "Sheet4");
		return passwords;
	}
	
	
  @Test(description="validataing the password fields encrypts the value entered by user.")
  public void validatePasswordEncryption() {
	  Assert.assertTrue(signup.passwordField.getAttribute("type").equals("password"));
  }
  
  
  @Test(description="validating the password field accepts values that meet the criteria"
		  , dataProvider="validTestPasswords")
  public void enterValidPassword(String password) {
	  //Enter a password that is 8 characters and meets the other criteria 
	  //Enter a password that is 9 characters and meets the other criteria
	  //Enter a password that is 11 characters and meets the other criteria 
	  //Enter a password that is 12 characters and meets the other criteria 
	  signup.passwordField.sendKeys(password);
	  signup.signUpBtn.click();
	  Assert.assertFalse(signup.passwordErrorMsg.isDisplayed());
  }
  
  
  
  @Test(description="validatin the password field DOESN'T accept values that DOESN'T meet the criteria"
		  , dataProvider="invalidTestPasswords")
  public void enterInvalidPassword(String password) {
	  //Enter a password that is 7 characters but meets the other criteria.
	  //Enter a password that is 8 characters letters only.
	  //Enter a password that is 9 characters and meets the other criteria except numbers.
	  //Enter a password that is 11 characters and meets the other criteria except uppercase letter.
	  //Enter a password that is 12 characters and meets the other criteria except special character.
	  //Enter a password that is 13 characters and meets the other criteria.
	  signup.passwordField.sendKeys(password);
	  signup.signUpBtn.click();
	  Assert.assertTrue(signup.passwordErrorMsg.getText().equals("Please enter a vlaid password"));

  }
  
  
  @BeforeMethod
  public void setup() {
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
  public void quitDriver() {
	  Driver.quitDriver();
  }

}
