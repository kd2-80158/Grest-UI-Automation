package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public class CategoryPage extends BrowserUtility {
	
	private static final By ADD_NEW_BUTTON_LOCATOR = By.xpath("//button[@class='btn btn-success clr-btn' and text()='Add New']");

	//passing session
	public CategoryPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void openAddNewCategoryDialog()
	{
		clickOn(ADD_NEW_BUTTON_LOCATOR);
	}

}
