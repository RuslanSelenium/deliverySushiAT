package deliverySushiAT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CategoryDefinitions{

	protected CommonDefinitions definitions;
	
	WebElement categoryNameField = CommonDefinitions.browser.findElement(By.xpath("//input[@placeholder = 'категория']"));
	WebElement categoryAddButton = CommonDefinitions.browser.findElement(By.xpath("//div[@class='btn-group']/button"));
	
	
	public CategoryDefinitions(){
		this.definitions = new CommonDefinitions();
	}

	@Given("^I add category \"([^\"]*)\"$")
	public void shouldAddCategory(String categoryName) throws Throwable {
		categoryNameField.sendKeys(categoryName);
		categoryAddButton.click();
		synchronized (CommonDefinitions.browser)
		{
			CommonDefinitions.browser.wait(500);
		}
		System.out.println("clicking");
	}
	
	@Then("^I verify \"([^\"]*)\" category is displaying$")
	public void shouldVerifyCategoryDisplays(String categoryName) throws Throwable {
	    WebElement categoryElement = CommonDefinitions.browser.findElement(By.xpath("//div[contains(text(),'" + categoryName + "')]"));
	    if(!CommonDefinitions.waitTillElementDisplays(categoryElement)) throw new Exception("Element is no displaying");
	}
		
}
