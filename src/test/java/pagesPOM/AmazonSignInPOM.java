package pagesPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class AmazonSignInPOM {
	public AmazonSignInPOM() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//h1[contains(text(),'Sign in')]")
	public WebElement signInHeader;
	
	@FindBy(name="email")
	public WebElement signInEmailOrPhone;
	
	@FindBy(id="createAccountSubmit")
	public WebElement createAccountBtn;

}
