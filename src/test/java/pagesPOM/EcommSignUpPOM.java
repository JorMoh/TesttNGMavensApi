package pagesPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class EcommSignUpPOM {
	
	public EcommSignUpPOM() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(xpath="//h2[text()='Sign Up']")
    public WebElement signUpHeader;
	
	@FindBy(xpath="//label[text()='Email Address']")
    public WebElement emailAddressLabel;
	
	@FindBy(name="email")
	public WebElement emailAddressField;
	
	@FindBy(xpath="//label[text()='First Name']")
	public WebElement firstNameLabel;
	
	@FindBy(name="firstName")
	public WebElement firstNameField;
	
	@FindBy(xpath="//label[text()='Last Name']")
	public WebElement lastNameLabel;
	
	@FindBy(name="lastName")
	public WebElement lastNameField;
	
	@FindBy(xpath="//label[text()='Password']")
	public WebElement passwordLabel;
	
	@FindBy(name="password")
	public WebElement passwordField;
	
	@FindBy(xpath="//span[text()='Login with Google']")
	public WebElement loginWithGoogleBtn;
	
	@FindBy(xpath="//span[text()='Login with Facebook']")
	public WebElement loginWithFacebookBtn;
	
	@FindBy(xpath="//hr[1]")
	public WebElement underlineBeneathPassword;
	
	@FindBy(css="#subscribe")
	public WebElement newsletterSubscriptioncheck;
	
	@FindBy(xpath="//label[text()='Subscribe To Our Newsletter']")
	public WebElement newsletterSubscriptionlabel;
	
	@FindBy(xpath="//a[text()='Back to login']")
	public WebElement backToLoginLink;
	
	@FindBy(xpath="//button[@type= 'submit']")
	public WebElement signUpBtn;
	//*******************************
	
	//Required Fields Error Messages:
	@FindBy(xpath="//span[text()='Email is required.']")
	public WebElement EmailRequiredMsg;
	
	@FindBy(xpath="//span[text()='First Name is required.']")
	public WebElement firstNameRequiredMsg;

	@FindBy(xpath="//span[text()='Last Name is required.']")
	public WebElement lastNameRequiredMsg;
	
	@FindBy(xpath="//span[text()='Password is required.']")
	public WebElement passwordRequiredMsg;
	
	
	
	//Length and format Error Messages:
	@FindBy(xpath="//span[text()='The email may not be greater than 125 characters.']")
	public WebElement EmailCharsLengthError;
	
	@FindBy(xpath="//span[text()='Please enter a valid email address.']")
	public WebElement EmailFormatError;
	
	@FindBy(xpath="//span[text()='The firstName may not be greater than 50 characters.']")
	public WebElement FirstNameCharsLengthError;
	
	@FindBy(xpath="//span[text()='The firstName format is invalid.']")
	public WebElement FirstNameFormatError;

	@FindBy(xpath="//span[text()='The lastName may not be greater than 50 characters.']")
	public WebElement LastNameCharsLengthError;
	
	@FindBy(xpath="//span[text()='The lastName format is invalid.']")
	public WebElement LastNameFormatError;
	

	//No Error Messages Displayed:
	@FindBy(xpath="(//span[@class='invalid-message'])[1]")
	public WebElement emailErrorMsg;
	
	@FindBy(xpath="(//span[@class='invalid-message'])[2]")
	public WebElement firstNameErrorMsg;
	
	@FindBy(xpath="(//span[@class='invalid-message'])[3]")
	public WebElement lastNameErrorMsg;
	
	@FindBy(xpath="(//span[@class='invalid-message'])[4]")
	public WebElement passwordErrorMsg;
	
	
	
	



	
	
}
