package deliverySushiAT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SushiDefinitions {

	public WebElement productAddButton = CommonDefinitions.browser.findElement(By.xpath("//div[@class='btn-group']/button"));
	public WebElement productNameInput = CommonDefinitions.browser.findElement(By.xpath("//input[@name='sushiName']"));
	public WebElement productDescriptionInput = CommonDefinitions.browser.findElement(By.xpath("//textarea[@name='sushiDescription']"));
	public WebElement productPriceInput = CommonDefinitions.browser.findElement(By.xpath("//input[@name='sushiPrice']"));
	public WebElement productWeightInput = CommonDefinitions.browser.findElement(By.xpath("//input[@name='sushiWeight']"));
	public WebElement productPictureInput = CommonDefinitions.browser.findElement(By.xpath("//input[@name='picFile']"));
	public WebElement productSaveButton = CommonDefinitions.browser.findElement(By.xpath("//button[@ng-click='saveChanges(picFile)']"));
	
	@When("^I add product \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void shouldAddProduct(String productName, String productDescription, String productPrice, String productWeight, String productPicture, String categoryName) throws Throwable {
		WebElement category = CommonDefinitions.browser.findElement(By.xpath("//li[contains(text(),'" + categoryName + "')]"));
		
		category.click();
		
		productNameInput.sendKeys(productName);
		productDescriptionInput.sendKeys(productDescription);
		productPriceInput.sendKeys(productPrice);
		productWeightInput.sendKeys(productWeight);
		productPictureInput.sendKeys(productPicture);
		
		productAddButton.click();
		Thread.sleep(500);
	}
	
	@When("^I change product \"([^\"]*)\" with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" in the \"([^\"]*)\"$")
	public void shouldChangeProductWith(String productName1, String productName2, String productDescription2, String productPrice2, String productWeight2, String productPicture2, String categoryName) throws Throwable {
		WebElement category = CommonDefinitions.browser.findElement(By.xpath("//li[contains(text(),'" + categoryName + "')]"));
		category.click();
		
		WebElement changeProductButton = CommonDefinitions.browser.findElement(By.xpath("//div[contains(text(),'" + productName1 + "')]/../..//i[@ng-click='edit(sushiItem)']"));
		changeProductButton.click();
		Thread.sleep(1000);
		
		productNameInput.clear();
		productDescriptionInput.clear();
		productPriceInput.clear();
		productWeightInput.clear();
		
		productNameInput.sendKeys(productName2);
		productDescriptionInput.sendKeys(productDescription2);
		productPriceInput.sendKeys(productPrice2);
		productWeightInput.sendKeys(productWeight2);
		productPictureInput.sendKeys(productPicture2);
		
		productSaveButton.click();
		Thread.sleep(500);
	}
	
	@When("^I remove product \"([^\"]*)\" from the \"([^\"]*)\"$")
	public void shouldRemoveProductFromCategory(String productName, String categoryName) throws Throwable {
		WebElement category = CommonDefinitions.browser.findElement(By.xpath("//li[contains(text(),'" + categoryName + "')]"));
		category.click();
		
		WebElement removeCategoryButton = CommonDefinitions.browser.findElement(By.xpath("//div[contains(text(),'" + productName + "')]/../..//i[@ng-click='delete(sushiItem)']")); 
	    removeCategoryButton.click();
	    Thread.sleep(500);
	    
	    WebElement approveRemovingButton = CommonDefinitions.browser.findElement(By.xpath("//div[@class='modal-footer']/button[.='Да']"));
	    approveRemovingButton.click();
	    Thread.sleep(500);
	}

	@Then("^I verify \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" product is displaying$")
	public void shouldVerifyProductDisplays(String productName, String productDescription, String productPrice, String productWeight, String categoryName) throws Throwable {
		WebElement category = CommonDefinitions.browser.findElement(By.xpath("//li[contains(text(),'" + categoryName + "')]"));
		category.click();

		String productNameText = CommonDefinitions.browser.findElement(By.xpath("//div[@id='sushi-wrap']/div[1]/div[2]/div[last()]//div[@class='sushi-name ng-binding']")).getText();
		String productDescriptionText = CommonDefinitions.browser.findElement(By.xpath("//div[@id='sushi-wrap']/div[1]/div[2]/div[last()]//div[@class='sushi-desc ng-binding']")).getText();
		String productPriceText = CommonDefinitions.browser.findElement(By.xpath("//div[@id='sushi-wrap']/div[1]/div[2]/div[last()]//span[@class='sushi-price ng-binding']")).getText();
		String productWeightText = CommonDefinitions.browser.findElement(By.xpath("//div[@id='sushi-wrap']/div[1]/div[2]/div[last()]//span[@class='sushi-weight ng-binding']")).getText();
		
		if (!productNameText.contains(productName)) throw new Exception("Название продукта отображается некорректно!");
		if (!productDescriptionText.contains(productDescription)) throw new Exception("Описание продукта отображается некорректно!");
		if (!productPriceText.contains(productPrice)) throw new Exception("Цена продукта отображается некорректно!");
		if (!productWeightText.contains(productWeight)) throw new Exception("Вес продукта отображается некорректно!");
		
	}
	
	@Then("^I verify product \"([^\"]*)\" in the \"([^\"]*)\" is not displaying$")
	public void shouldVerifyProductNotDisplay(String productName, String categoryName) throws Throwable {
		WebElement category = CommonDefinitions.browser.findElement(By.xpath("//li[contains(text(),'" + categoryName + "')]"));
		category.click();
		
		boolean flag = false;
		try{
		   	WebElement categoryElement = CommonDefinitions.browser.findElement(By.xpath("//div[contains(text(),'" + productName + "')]"));
		}
		catch(Exception e){
			flag = true;
		}
			
		if(!flag) throw new Exception("Element is displaying!");
	}

}
