package pagesPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class EcommAccountPOM {
	public EcommAccountPOM() {
		PageFactory.initElements(Driver.getDriver(), this);
	}


	@FindBy(xpath="//h2[text()='Account Details']")
	public WebElement accDetailsText;
}
