package craterTests;

import org.testng.annotations.Test;

import craterPagesPOM.CommonPOM;
import craterPagesPOM.ItemsPOM;
import craterPagesPOM.LoginPOM;
import utils.BrowserUtils;
import utils.Driver;
import utils.ReusableMethods;
import utils.dataReader;

import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Create_UpdateItemTests {
	  
  @Test(priority=2)
  public void verifyCreating() {
//	  user is able to create an item or service
//	  user logs in
//	  user navigates to Items tab //until here: in before method
	  BrowserUtils utils = new BrowserUtils();
	  ItemsPOM items = new ItemsPOM();
//	  click on the Add Item button
	  items.addItemBtn.click();
//	  Then user should be on item create page
	  Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("create"));
//	  provide item information “name” and “price” and “unit” and “description”
	  items.nameField.sendKeys("cleaning products");
	  utils.clearTextOfTheFieldWindows(items.priceField);
	  items.priceField.sendKeys("15000");
	  items.unitField.click();
	  items.unitFieldbox.click();
	  items.descriptionField.sendKeys("Heavy duty cleaning products");
//	  click Save Item button 
	  items.saveItemBtn.click();
//	  Then the Item is added to the Item list table
	  Assert.assertEquals(items.addedItem.getText(), "cleaning products");
  }
  
  
  
  @Test(priority=1)
  public void verifyUpdating() throws InterruptedException {
//	  user is able to update an item or service
//	  Given user is on the login page
//	  And user navigates to Items tab
//	  When selects the item “Books”
	  BrowserUtils utils = new BrowserUtils();
	  ItemsPOM items = new ItemsPOM();
	  Driver.getDriver().findElement(By.xpath("//a[text()='10']")).click();
	  items.bookItem.click();
//	  and user should be on item details page
	  Assert.assertTrue(items.updateItemBtn.isDisplayed());
//	  When user updates the item price to 30 dollars
	  utils.clearTextOfTheFieldWindows(items.priceField);
	  items.priceField.sendKeys("3000");
//	  And click Update Item button 
	  items.updateItemBtn.click();
	  items.nextButton.click();
//	  Then the Item price is updated to 30 dollars
	  utils.waitUntilElementVisible(items.bookPrice);
	  Assert.assertEquals(items.bookPrice.getText(), "$ 30.00");
  }
  
  
  
  
  @BeforeMethod
  public void setup() {
	  CommonPOM common = new CommonPOM();
	  LoginPOM login = new LoginPOM();
	  Driver.getDriver().manage().window().maximize();
	  Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  Driver.getDriver().get(dataReader.getData("craterUrl"));
	  ReusableMethods.craterLogin();
	  common.itemsTab.click();
  }
  
  
  @AfterMethod
  public void afterTest() {
	  Driver.quitDriver();
  }

}
