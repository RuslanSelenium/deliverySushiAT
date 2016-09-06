package deliverySushiAT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class CommonDefinitions {
	
	static WebDriver browser = null;
	public String homeUrl = "http://90.156.142.122:3000";
	
	@Before("@web")
	public void beforeScenario(){
		System.setProperty("webdriver.chrome.driver", "E:/deliverySushiAT/jars/chromedriver.exe");
		browser = new ChromeDriver();
	}
	
	@After("@web")
	public void afterScenario(){
		browser.quit();
	}
	
	@Given("^I am on \"([^\"]*)\" page$")
	public void shouldBeOnCurrentPage(String url) throws Throwable {
		browser.navigate().to(homeUrl+url);
		synchronized (browser)
		{
			browser.wait(3000);
		}
	}
	
	public static boolean waitTillElementDisplays(WebElement element) throws Throwable{
		int timeout = 10;
		int i = 0;
		while(!element.isEnabled() && i < timeout+1){
			synchronized (browser)
			{
				browser.wait(1000);
			}
			i++;
		}
		
		if(i < timeout) return true;
		else return false;
	}
}
