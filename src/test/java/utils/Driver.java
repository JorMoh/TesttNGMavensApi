package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.EdgeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;

public class Driver {

public static WebDriver driver;
	
	public static WebDriver getDriver() {
		String browser = System.getProperty("browser");
		if(browser==null) {
			browser = dataReader.getData("browser");
		}
		if(driver==null) {
			switch(dataReader.getData("browser")) {
			case "Chrome":
//				System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mohammad\\Downloads\\PRIME TECH\\Software"
//						+ "\\SeleniumWebDriver\\chromedriver_win32\\chromedriver.exe");
				ChromeDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
				
			case "Firefox":
//				System.setProperty("webdriver.gecko.driver", "C:\\Users\\Mohammad\\Downloads\\PRIME TECH\\Software"
//						+ "\\SeleniumWebDriver\\Firefox Driver\\geckodriver.exe");
				FirefoxDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
				
			case "Edge":
				EdgeDriverManager.edgedriver().setup();
//				System.setProperty("webdriver.edge.driver", "C:\\Users\\Mohammad\\Downloads\\PRIME TECH\\Software"
//						+ "\\SeleniumWebDriver\\edgedriver_win64\\msedgedriver.exe");
				driver = new EdgeDriver();
				break;
				
			default:
				ChromeDriverManager.chromedriver().setup();
//				System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mohammad\\Downloads\\PRIME TECH\\Software"
//						+ "\\SeleniumWebDriver\\Chrome Driver\\chromedriver.exe");
				driver = new ChromeDriver();
				
				
				
			}
		}
		return driver;
	}

	//to quit the driver instance we make in this class
	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}}
}
