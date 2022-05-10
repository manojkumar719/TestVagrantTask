package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppIndependent {
	WebDriver driver;
	public WebDriver launchBrowser(String url) {
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
	public String getProperty(String key) throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("./Config/config.properties"));
		return prop.getProperty(key);
	}
	
	public void setTextValue(String locator,String locatorValue, String text) {
//		We can use Swithc case incase we are using other locators
		if(locator.equalsIgnoreCase("id"))
			driver.findElement(By.id(locatorValue)).sendKeys(text);
		else
			if(locator.equalsIgnoreCase("name"))
				driver.findElement(By.name(locatorValue)).sendKeys(text);
			else
				if(locator.equalsIgnoreCase("xpath"))
					driver.findElement(By.xpath(locatorValue)).sendKeys(text);
	}
	
	public void click(String locator, String locatorValue) {
		if(locator.equalsIgnoreCase("id"))
			driver.findElement(By.id(locatorValue)).click();
		else
			if(locator.equalsIgnoreCase("name"))
				driver.findElement(By.name(locatorValue)).click();
			else
				if(locator.equalsIgnoreCase("xpath"))
					driver.findElement(By.xpath(locatorValue)).click();
	}
	
	public String getText(String locator, String locatorValue) {
		String val="";
		if(locator.equalsIgnoreCase("id"))
			val = driver.findElement(By.id(locatorValue)).getText();
		else
			if(locator.equalsIgnoreCase("name"))
				val = driver.findElement(By.name(locatorValue)).getText();
			else
				if(locator.equalsIgnoreCase("xpath"))
					val = driver.findElement(By.xpath(locatorValue)).getText();
		return val;
	}
	
	public void closePage() {
		driver.close();
	}
	
	public void navigateTo(String url) {
		driver.get(url);
	}

}
