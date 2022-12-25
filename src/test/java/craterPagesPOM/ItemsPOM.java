package craterPagesPOM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class ItemsPOM {
	
   public ItemsPOM() {
	PageFactory.initElements(Driver.getDriver(), this);
   }
   
   @FindBy(xpath="//button[text()=' Add Item']")
   public WebElement addItemBtn;
   
   @FindBy(xpath="(//input[@type= 'text'])[2]")
   public WebElement nameField;
   
   @FindBy(xpath="(//input[@type= 'tel'])")
   public WebElement priceField;
   
   @FindBy(xpath="(//input[@type= 'text'])[3]")
   public WebElement unitField;
   
   @FindBy(xpath="(//span[text()='box'])")
   public WebElement unitFieldbox;
   
   @FindBy(xpath="//textarea[@name='description']")
   public WebElement descriptionField;
   
   @FindBy(xpath="//button[text()=' Save Item']")
   public WebElement saveItemBtn;
   
   @FindBy(xpath="//button[text()=' Update Item']")
   public WebElement updateItemBtn;
   
   @FindBy(xpath="(//a[@class='font-medium text-primary-500'])[1]")
   public WebElement addedItem;
		   
   @FindBy(xpath="//a[text()='Book']")
   public WebElement bookItem;
   
   @FindBy(xpath="(//td[@style = 'font-family: sans-serif;'])[1]")
   public WebElement bookPrice;
 
   @FindBy(xpath="(//a[@href='#'])[6]")
   public WebElement nextButton;
 
   
   
}
