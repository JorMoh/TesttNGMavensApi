package pagesPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class EcommCommonPOM {
	public EcommCommonPOM() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(linkText="Welcome!")
	public WebElement welcomeDropdown;
	
	@FindBy(xpath="//button[text()='Sign Up']")
	public WebElement signUpLink;
	
}
