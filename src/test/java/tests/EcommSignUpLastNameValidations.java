package tests;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pagesPOM.EcommCommonPOM;
import pagesPOM.EcommSignUpPOM;
import utils.Driver;
import utils.ExcelUtils;
import utils.dataReader;

public class EcommSignUpLastNameValidations {
	
		EcommCommonPOM home = new EcommCommonPOM();
		EcommSignUpPOM signup = new EcommSignUpPOM();
		
		@DataProvider
		public String[] testNamesLength() {
			String[] names= ExcelUtils.getExcelDataInAColumn("./src/test/resources/testData/TestNames.xlsx", "Sheet1");
			return names;
		}
		
		@DataProvider
		public String[] testNamesFormat() {
			String[] names= ExcelUtils.getExcelDataInAColumn("./src/test/resources/testData/TestNames.xlsx", "Sheet2");
			return names;
		}
		
		
		
	// validate that the Last Name entered is less than or equal to 50 alphabetical characters in length. 
	// If the entered Last Name is greater than 50 characters in length then I should see the following inline error message:
	// ”The firstName may not be greater than 50 characters.”
	  @Test(description="validating the first name field accepts values that are <= 50 characters in length"
	  , dataProvider="testNamesLength")
	  public void enterValidLength(String name) {
		  
		  //1.Enter value that is 1 alphabetical characters in length, and verify you don't get an error message.
		  //2.Enter a value that is 2 alphabetical characters in length, and verify you don't get an error message.
		  //3.Enter a value that is 49 alphabetical characters in length, and verify you don't get an error message.
		  //4.Enter a value that is 50 alphabetical characters in length, and verify you don't get an error message.
		  signup.lastNameField.sendKeys(name);
		  signup.signUpBtn.click();
		  Assert.assertFalse(signup.lastNameErrorMsg.isDisplayed());
	  }
	  
	  @Test(description="validating the last name field DOESN'T accept values that are > 50 characters in length")
	  public void enterInvalidLength() {
		//5.Enter a value that is 51 alphabetical characters in length, and verify you DO get an error message. 
		  signup.lastNameField.sendKeys("aaaklsdajdkhsdjbJJkkiKJDHFKSDFJKGBJDFGBFGVNBDFkjhhu");
		  signup.signUpBtn.click();
		  Assert.assertTrue(signup.LastNameCharsLengthError.isDisplayed());
	  }


//   -------------------------------------------------------------------------------------
	  
	//validate that the Last Name entered does not contain non-alphabetical characters. 
	//If the entered Last Name entered contains non-alphabetical characters then I should see the following error message:
	//”The lastName format is invalid.”
	  @Test(description="validating the first name field DOESN'T accept values that contain non-alphabetical characters"
			  , dataProvider="testNamesFormat")
	  public void enterInvalidFormat(String name) {
		  
		  //Enter a value that contains a combination of letters, numbers and special characters,
		  //Enter a value that contains a combination of letters and numbers.
		  //Enter a value that contains a combination of letters and special characters.
		  //and verify getting the error message.
		  signup.lastNameField.sendKeys(name);
		  signup.signUpBtn.click();
		  Assert.assertTrue(signup.LastNameFormatError.isDisplayed());
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
