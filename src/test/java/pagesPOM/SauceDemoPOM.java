package pagesPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class SauceDemoPOM {
	
	public SauceDemoPOM() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(id="user-name")
	public WebElement usernameField;
	
	@FindBy(id="password")
	public WebElement passwordField;
	
	@FindBy(id="login-button")
	public WebElement loginBtn;
	
	@FindBy(xpath="//span[text()='Products']")
	public WebElement productsText;
	
	@FindBy(xpath="//h3[text()='Epic sadface: Username and password do not match any user in this service']")
	public WebElement credentialsDontMatchError;
	
	@FindBy(xpath="//h3[text()='Epic sadface: Sorry, this user has been locked out.']")
	public WebElement lockedOutUserError;


}
