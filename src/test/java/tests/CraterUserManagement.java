package tests;

import org.testng.annotations.Test;

import pagesPOM.CraterDashboardPOM;
import pagesPOM.CraterLoginPOM;
import utils.Driver;
import utils.dataReader;

import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class CraterUserManagement {
  @Test
  public void validLogin() {
	  /*
	   * Scenario: Successful log in
         Given user is on the login page
         When user enters valid username and password
         And click login button
         Then user should be on the dashboard page
	   */
	  
	  Driver.getDriver().get(dataReader.getData("craterUrl"));
	  CraterLoginPOM login = new CraterLoginPOM();
	 
	  login.userEmailField.sendKeys(dataReader.getData("craterValidUserEmail"));
	  login.passwordField.sendKeys(dataReader.getData("craterValidPassword"));
	  login.loginButton.click();
	  
	  // verify the amount due element display
	  CraterDashboardPOM dashboardPage = new CraterDashboardPOM();
	  Assert.assertTrue(dashboardPage.amountDueText.isDisplayed()); 
	  
	  // verify the url contains dashboard
	  String dashboardUrl = Driver.getDriver().getCurrentUrl();
	  Assert.assertTrue(dashboardUrl.contains("dashboard"));
  }
  
  
  @BeforeMethod
  public void setup() {
	  Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
  }

  @AfterMethod
  public void tearDown() {
	  Driver.quitDriver();
  }

}
