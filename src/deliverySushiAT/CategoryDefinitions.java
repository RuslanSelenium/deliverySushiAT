package deliverySushiAT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CategoryDefinitions{

	protected CommonDefinitions definitions;
	
	WebElement categoryNameField = CommonDefinitions.browser.findElement(By.xpath("//input[@placeholder = 'категория']"));
	WebElement categoryAddButton = CommonDefinitions.browser.findElement(By.xpath("//button[@ng-click='addCategory()']"));
	WebElement categorySaveButton = CommonDefinitions.browser.findElement(By.xpath("//button[@ng-click='saveChanges()']"));
	
	
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
	
	@When("^I change \"([^\"]*)\" with \"([^\"]*)\"$")
	public void shouldChangeCategoryWith(String categoryName1, String categoryName2) throws Throwable {
		WebElement changedCategoryButton = CommonDefinitions.browser.findElement(By.xpath("//div[contains(text(),'" + categoryName1 + "')]/..//i[@ng-click='editCategory(category)']"));
		changedCategoryButton.click();
		Thread.sleep(1000);
		categoryNameField.clear();
		categoryNameField.sendKeys(categoryName2);
		categorySaveButton.click();
		Thread.sleep(1000);		
	}
	
	@When("^I remove category \"([^\"]*)\"$")
	public void shouldRemoveCategory(String categoryName) throws Throwable {
	    WebElement removeCategoryButton = CommonDefinitions.browser.findElement(By.xpath("//div[contains(text(),'" + categoryName + "')]/..//i[@ng-click='removeCategory(category)']")); 
	    removeCategoryButton.click();
	    Thread.sleep(500);
	    
	    WebElement approveRemovingButton = CommonDefinitions.browser.findElement(By.xpath("//div[@class='modal-footer']/button[.='Да']"));
	    approveRemovingButton.click();
	    Thread.sleep(500);
	}
	
	@Then("^I verify \"([^\"]*)\" category is displaying$")
	public void shouldVerifyCategoryDisplays(String categoryName) throws Throwable {
	    WebElement categoryElement = CommonDefinitions.browser.findElement(By.xpath("//div[contains(text(),'" + categoryName + "')]"));
	    if(!CommonDefinitions.waitTillElementDisplays(categoryElement)) throw new Exception("Element is not displaying!");
	}
	
	@Then("^I verify \"([^\"]*)\" category is not displaying$")
	public void shouldVerifyCategoryNotDisplays(String categoryName) throws Throwable {
	    boolean flag = false;
		try{
	    	WebElement categoryElement = CommonDefinitions.browser.findElement(By.xpath("//div[contains(text(),'" + categoryName + "')]"));
	    }
	    catch(Exception e){
	    	flag = true;
	    }
		
	    if(!flag) throw new Exception("Element is displaying!");
	}
		
}
