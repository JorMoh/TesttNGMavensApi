package tests;

import org.testng.annotations.Test;

import pagesPOM.AmazonHomePOM;
import utils.Driver;
import utils.ExcelUtils;
import utils.dataReader;

import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class DataProviderAmazSrch {
  
	@BeforeMethod
	public void setup() {
  		Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  		Driver.getDriver().manage().window().maximize();
	}
	
	@AfterMethod
	public void quitDriver() {
		Driver.quitDriver();
	}
	
//	@DataProvider
//	public String[] searchItems() {
//		String[] items = new String[6];
//		items[0]="coffe mug";
//		items[1]="kids mug";
//		items[2]="dads mug";
//		items[3]="funny mug";
//		items[4]="work mug";
//		items[5]="cool mug";
//
//		return items;
//	}
	
  
  //DataProvider using an Excel sheet:
	
  @DataProvider
  public String[] searchItems() {
	  
	 String[] items = 
	 ExcelUtils.getExcelDataInAColumn("./src/test/resources/amazonSearchItems.xlsx", "sheet1");
	 return items;
  }
	

	
	
  @Test(description="testing different search data sets", dataProvider="searchItems", groups="smoke-test")
  public void amazonSearchTest(String item) {
	  AmazonHomePOM home = new AmazonHomePOM();
	  //1. go to amazon.com
	  Driver.getDriver().get(dataReader.getData("amazonUrl"));
	  //2. search {TestData} and click search
	  home.homeSearchBox.sendKeys(item);
	  home.searchBtn.click();
	  //3. get text of the search criteria text element
	  String searchedItemText= home.searchedItemText.getText().substring(1, item.length()+1);
	  //4.verify searched item text matches searched item excluding quotations.
	  Assert.assertEquals(searchedItemText, item);
	  
	  
  }

  
 
 
  
  
  
  
//  @DataProvider
//  public Object[][] dp() {
//    return new Object[][] {
//      new Object[] { 1, "a" },
//      new Object[] { 2, "b" },
//    };
//  }
}
