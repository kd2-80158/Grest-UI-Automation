package com.ui.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.utility.BrowserUtility;

public class AddNewCategoryDialog extends BrowserUtility {
	
	public static final By CATEGORY_INPUT_LOCATOR = By.xpath("//input[@type='text' and @formcontrolname='name']");
	private static final By DESCRIPTION_INPUT_TEXTAREA = By.xpath("//textarea[@formcontrolname='description']");
	private static final By PARENT_CATEGORY_DROPDOWN = By.xpath("//div[2]/div[3]/div[2]/div/select");
    private static final By IMAGE_UPLOAD = By.xpath("//div/input[@type='file']");
    private static final By SAVE_BUTTON = By.xpath("//button[@class='btn btn-primary' and text()='Save']");
    private static final By CLOSE_BUTTON = By.xpath("//button[@class='btn btn-secondary' and text()='Close']");
	
	public AddNewCategoryDialog(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void saveCategory(String imagePath)
	{
		clickClose();
		
	    // Enter text for category and description
	    enterText(CATEGORY_INPUT_LOCATOR, "Test");
	    enterText(DESCRIPTION_INPUT_TEXTAREA, "this is test");
	    dropdownLocator(PARENT_CATEGORY_DROPDOWN, "Mobile Phones");

	    // Upload image file
	    WebElement imageUploadField = getDriver().findElement(IMAGE_UPLOAD);
	    imageUploadField.sendKeys(imagePath);

	    // Click on save button
	    clickOn(SAVE_BUTTON);
	}



    public void uploadImage() {
        
    }

    public void clickSave() {
        
    }

    public void clickClose() {
        clickOn(CLOSE_BUTTON);
    }
	

}
