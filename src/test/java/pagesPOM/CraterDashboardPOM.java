package pagesPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class CraterDashboardPOM {
	
	
		
		public CraterDashboardPOM() {
			PageFactory.initElements(Driver.getDriver(), this);
		}
		
		
		@FindBy (xpath = "//span[text()='Amount Due']")
		public WebElement amountDueText;

	}

