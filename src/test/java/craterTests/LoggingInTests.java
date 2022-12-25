package craterTests;

import org.testng.annotations.Test;

import craterPagesPOM.DashboardPOM;
import craterPagesPOM.LoginPOM;
import utils.BrowserUtils;
import utils.Driver;
import utils.dataReader;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class LoggingInTests {
	BrowserUtils utils = new BrowserUtils();
	LoginPOM login = new LoginPOM();
    DashboardPOM dash = new DashboardPOM();
	
  @DataProvider
  public String[][] userPassCombonation() {
	  String [][] credits = new String[4][2];
	  credits[0][0]="helil@primetechschool.com";
	  credits[0][1]="sfdsgdh";
	  
	  credits[1][0]="ghd@yahoo.com";
	  credits[1][1]="Testing123";
	  
	  credits[2][0]="";
	  credits[2][1]="Testing123";
	  
	  credits[3][0]="helil@primetechschool.com";
	  credits[3][1]="";
	  return credits;
	  
  }
  
	
  @Test
  public void validLogin() {
	  /*
	   * Scenario: Successful log in
         Given user is on the login page
         When user enters valid username and password
         And click login button
         Then user should be on the dashboard page
	   */
	  
	  //enter valid username and password And click login button
	  login.userEmailField.sendKeys(dataReader.getData("craterValidUserEmail"));
	  login.passwordField.sendKeys(dataReader.getData("craterValidPassword"));
	  login.loginButton.click();
	  //verify user is on the dashboard page.
	  Assert.assertTrue(dash.amountDueText.isDisplayed());
	  //OR:
	  Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("dashboard"));
	  
  }
  
  @Test(dataProvider = "userPassCombonation")
  public void invalidLogin(String username, String password) {
	  /*
	   * Scenario: Invalid login attempts
         Given user is on the login page
         When user enters invalid username and password
         And click login button
         Then an error message appears
         And user is not logged in
	   */
	 
	//Enter valid user and invalid password and click login, then verify the invalid user error msg is displayed
	  //an that user is still on the login page
	//Enter invalid user and valid password and click login, then verify the invalid user error msg is displayed
	//an that user is still on the login page
	//Leave user blank and enter valid password and click login, then verify the field required error msg is displayed
	//Enter valid user and leave password blank and click login, then verify the field required error msg is displayed
	  login.userEmailField.sendKeys(username);
	  login.passwordField.sendKeys(password);
	  login.loginButton.click();
	  
  if(username.isEmpty() || password.isEmpty()) {
		  utils.waitUntilElementVisible(login.fieldRequiredMsg);
		  Assert.assertTrue(login.fieldRequiredMsg.isDisplayed());
	  }
 
  else {
	  utils.waitUntilElementVisible(login.invalidUserErrorMessage); 
	  Assert.assertTrue(login.invalidUserErrorMessage.isDisplayed());
	  Assert.assertTrue(login.loginButton.isDisplayed());
	  }
  }
  
  
  @BeforeMethod
  public void setup() {
	  Driver.getDriver().manage().window().maximize();
	  Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  Driver.getDriver().get(dataReader.getData("craterUrl"));
	 

  }

  @AfterTest
  public void cleanUp() {
	  Driver.quitDriver();
  }

}
