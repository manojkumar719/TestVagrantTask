package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import utility.AppIndependent;

public class MovieInfoValidation extends AppIndependent {
	
	WebDriver driver;
	@BeforeTest
	public void propSetting() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	}
	
	@Test
	public void validateMovie() throws Exception {
		String imdbOrigin="", imdbRelDate="";
		String wikiOrigin="", wikiRelDate="";
		String url = getProperty("imdbUrl");
		launchBrowser(url);
		setTextValue("id", "suggestion-search", "Pushpa: The Rise - Part 1");				//We can use POM classes here as well or excel to store objects
		click("id", "suggestion-search-button");
		if(getText("xpath", "//h1[@class='findHeader']").contains("Results for"))
		{
			click("xpath", "//a[contains(text(),'Pushpa: The Rise - Part 1')]");
			imdbOrigin=getText("xpath", "//a[contains(@href,'country_of_origin')]");
			System.out.println(imdbOrigin);
			click("xpath","//section[@data-testid='Details']//li/a[text()='Release date']");
			imdbRelDate=getText("xpath", "//a[contains(text(),'India')]/parent::td/following-sibling::td[contains(@class,'date')]");
			System.out.println(imdbRelDate);
		}
		
		url = getProperty("wikiUrl");
		navigateTo(url);
		setTextValue("id", "searchInput", "Pushpa: The Rise");
		click("id", "searchButton");
		wikiRelDate = getText("xpath", "//div[text()='Release date']/parent::th/following-sibling::td//li");
		wikiOrigin = getText("xpath", "//th[text()='Country']/following-sibling::td");
		
		if(imdbOrigin.equalsIgnoreCase(wikiOrigin) && imdbRelDate.equalsIgnoreCase(wikiRelDate))
			System.out.println("Movie Validaion Passed");
		else {
			System.out.println("Movie Validation Failed");
			Assert.fail();
		}
		closePage();
	}

}
