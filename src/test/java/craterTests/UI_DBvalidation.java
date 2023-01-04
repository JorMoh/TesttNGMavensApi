package craterTests;

import org.testng.annotations.Test;

import craterPagesPOM.CommonPOM;
import craterPagesPOM.ItemsPOM;
import craterPagesPOM.LoginPOM;
import utils.BrowserUtils;
import utils.DButils;
import utils.Driver;
import utils.ReusableMethods;
import utils.dataReader;

import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class UI_DBvalidation {
	
	String addedItem = "cleaning products";
	
  @Test
  public void validate() {
//	  Create an item on UI.
	  BrowserUtils utils = new BrowserUtils();
	  ItemsPOM items = new ItemsPOM();
	  DButils db = new DButils();
	  items.addItemBtn.click();
	  addedItem = addedItem + utils.randomNumber();
	  items.nameField.sendKeys(addedItem);
	  utils.clearTextOfTheFieldWindows(items.priceField);
	  items.priceField.sendKeys("15000");
	  items.unitField.click();
	  items.unitFieldbox.click();
	  items.descriptionField.sendKeys("Heavy duty cleaning products");
	  items.saveItemBtn.click();
//	  Then go to database, and query from the items table using select * to get the information
	  
	  List<String> recordData = db.selectArecord("SELECT * FROM items WHERE name='"+addedItem+"'");
	  for(int i=0; i<recordData.size(); i++) {
		  System.out.println(recordData.get(i));
	  }
	  
//	  Then verify the information that you have provided on UI is correct.
	  Assert.assertEquals(recordData.get(1), addedItem);
//	  Then update your item on the UI, 
	  WebElement newItem = Driver.getDriver().findElement(By.xpath("//a[text()='"+addedItem+"']"));
	  newItem.click();
	  Assert.assertTrue(items.editItemHeader.isDisplayed());
	  //edit item's description
	  utils.clearTextOfTheFieldWindows(items.descriptionField);
	  String updateDescription = "Heavy Duty Cleaning Products";
	  items.descriptionField.sendKeys(updateDescription);
	  items.updateItemBtn.click();
	  utils.waitUntilElementVisible(items.itemUpdatedMsg);
	  Assert.assertTrue(items.itemUpdatedMsg.isDisplayed());
//	  come back to database and verify the update is in effect.
	  List<String> updatedData = db.selectArecord("SELECT * FROM items WHERE name='"+addedItem+"'");
	  System.out.println(updatedData.get(2));
	  Assert.assertEquals(updatedData.get(2), updateDescription);
//	  Then delete the item on the UI, come back to database and verify the estimate no longer
//	  exist.
	  items.threeDotsBtn.click();
	  items.ItemDeleteBtn.click();
	  items.deleteOkButton.click();
	  Assert.assertTrue(items.itemDeletedMsg.isDisplayed());
	  
	  List<String> deletedData = db.selectArecord("SELECT * FROM items WHERE name='"+addedItem+"'");

	  Assert.assertTrue(deletedData.isEmpty());
	  
  }
   
  @Test
  public void validate2() {
//	  Create an item on UI.
	  BrowserUtils utils = new BrowserUtils();
	  ItemsPOM items = new ItemsPOM();
	  DButils db = new DButils();
	  items.addItemBtn.click();
	  addedItem = addedItem + utils.randomNumber();
	  items.nameField.sendKeys(addedItem);
	  items.priceField.sendKeys("12000");
	  items.unitField.click();
	  items.unitFieldbox.click();
	  items.descriptionField.sendKeys("heavy duty cleaning products");
	  items.saveItemBtn.click();
	  
//	  Then go to database, and query from the items table using select * to get the information
	  List<String> recordData = db.selectArecord("select * from items where name = '"+addedItem+"'");
	  System.out.println(recordData.get(1));
//	  Then verify the information that you have provided on UI is correct.
	  Assert.assertTrue(recordData.get(1).equals(addedItem));
//	  Then update your Item using DB, and come back to UI and verify the update is in effect.
	  db.updateRecord("update items set price=22000 where name='"+addedItem+"'");
	  Driver.getDriver().navigate().refresh();
	  Assert.assertEquals(items.addedItemPrice.getText(), "$ 220.00");
//	  WebElement newItem = Driver.getDriver().findElement(By.xpath("//a[text()='"+addedItem+"']"));
//	  newItem.click();
	  
//	  Then delete the Item using DB, and come back to UI and verify the item no longer exists.
	  db.deleteRecord("delete from items where name ='"+addedItem+"'");
	  Driver.getDriver().navigate().refresh();
//	  WebElement newItem = Driver.getDriver().findElement(By.xpath("//a[text()='"+addedItem+"']"));
//	  Assert.assertFalse(newItem.isDisplayed());

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
