package tests;

import org.testng.annotations.Test;

import utils.ExcelUtils;

public class ExcelDataProvider {
  @Test
  public void test() {
	  ExcelUtils.openExcelFile("./src/test/resources/amazonSearchItems.xlsx", "sheet1");
		System.out.println("Total row count: " + ExcelUtils.getUsedRowsCount());
		System.out.println(ExcelUtils.getCellData(0, 0));
		
		
	  
  }
}
