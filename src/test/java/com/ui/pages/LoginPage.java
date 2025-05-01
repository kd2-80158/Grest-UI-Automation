package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.constants.Env;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

//follow page object design pattern
public final class LoginPage extends BrowserUtility {
	// first type of variable is locators
	private static final By USERNAME_INPUT_LOCATOR = By.xpath("//form//input[@type='email']");

//	private static final By PASSWORD_INPUT_LOCATOR = By.xpath("//div[2]/input");
	private static final By PASSWORD_INPUT_LOCATOR = By.xpath("//form/div[@class='form-group']/input[@type='password']");

	private static final By SUBMIT_BUTTON_LOCATOR = By.xpath("//form/div[@class='form-group form-row mt-3 mb-0']/button[@type='submit']");

	Logger logger = LoggerUtility.getLogger(this.getClass());
	// super - job is to call the parent class ctor from child class ctor
	public LoginPage(Browser browserName, boolean isHeadless) {
		super(browserName,isHeadless);
		goToWebsite(JSONUtility.readJSONProperty(Env.DEV).getUrl());
		
	}

	// void return type is not use in page object model
	public LoginPage(WebDriver driver) {
		super(driver); // Calls BrowserUtility(WebDriver driver)
	}

	public Dashboard doLoginWith(String username, String password) {

		logger.info("Trying to perform login by entering credentials..");
		enterText(USERNAME_INPUT_LOCATOR, username);
		enterText(PASSWORD_INPUT_LOCATOR, password);
		clickOn(SUBMIT_BUTTON_LOCATOR);
		Dashboard dashboardPage = new Dashboard(getDriver());
		return dashboardPage;
	}
	public boolean isLoginPageLoaded() {
	    String expectedTitle = "AGBE CMS";  // Replace with actual title if different
	    String actualTitle = getDriver().getTitle();
	    logger.info("Page title: " + actualTitle);
	    return actualTitle.contains(expectedTitle);
	}

	public void quit() {
	    if (getDriver() != null) {
	        getDriver().quit();
	        System.out.println("Browser closed successfully.");
	    } else {
	        System.out.println("Driver is null. Browser not closed.");
	    }
	}
}
