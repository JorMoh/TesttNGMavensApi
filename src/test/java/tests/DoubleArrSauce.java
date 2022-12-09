package tests;

import org.testng.annotations.Test;

import pagesPOM.SauceDemoPOM;
import utils.Driver;
import utils.dataReader;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class DoubleArrSauce {
  @Test(dataProvider="credentials")
  public void sauceCredentialsTest(String username, String password) {
	  SauceDemoPOM sauce = new SauceDemoPOM();
	  //go to sauceDemo.com
	  Driver.getDriver().get(dataReader.getData("sauceDemoUrl"));
	  //enter a valid username
	  sauce.usernameField.sendKeys(username);
	  //enter an invalid password
	  sauce.passwordField.sendKeys(password);
	  //click login
	  sauce.loginBtn.click();
	  //verify your error message contains "Epic sadface:"
	  Assert.assertTrue(sauce.credentialsDontMatchError.getText().contains("Epic sadface:"));

	  
  }
  
  
  @BeforeMethod
  public void stup() {
	  Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  Driver.getDriver().manage().window().maximize();

  }

  @AfterMethod
  public void quitDriver() {
		Driver.quitDriver();

  }
  
  @DataProvider
  public String[][] credentials(){
	  String[][] credentials = new String[2][2];
	  credentials[0][0]= "standard_user";
	  credentials[0][1]="ertert";
	  credentials[1][0]="standard_user";
	  credentials[1][1]="fdgbdfgdfg";
	  return credentials;
  }

}
