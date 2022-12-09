package pagesPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class AmazonCreateAccountPOM {
	
	public AmazonCreateAccountPOM() {
		PageFactory.initElements(Driver.getDriver(), this);

	}
	
	@FindBy(css=".a-spacing-small")
	public WebElement createAccountHeader;
	
	@FindBy(xpath="//div[contains(text(),'Enter your name')]")
	public WebElement nameErrorMsg;
	
	@FindBy(xpath="//div[contains(text(),'Enter your email or mobile phone number')]")
	public WebElement numberEmailErrorMsg;

	@FindBy(xpath="//div[contains(text(),'Minimum 6 characters required')]")
	public WebElement passwordErrorMsg;

	@FindBy(id="continue")
	public WebElement createAccContinueBtn;

}
