package com.ui.pages;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	private static final By PASSWORD_INPUT_LOCATOR = By
			.xpath("//form/div[@class='form-group']/input[@type='password']");

	private static final By SUBMIT_BUTTON_LOCATOR = By
			.xpath("//form/div[@class='form-group form-row mt-3 mb-0']/button[@type='submit']");

	private static final By ERROR_MESSAGE_LOCATOR = By
			.xpath("//*[@id='toast-container']//div[contains(@class, 'toast-message')]");

	private static final By GREST_LOGO_LOCATOR = By.xpath("//div/img");

	private static final By LOGIN_HEADING_TEXT_LOCATOR = By.xpath("//h4[text()='LOGIN']");

	private static final By GUIDE_TEXT_LOCATOR = By.xpath("//h6[text()='Enter your Username and Password ']");

	private static final By USERNAME_FIELD_LOCATOR = By.xpath("//label[text()='Username']");
	
	private static final By PASSWORD_FIELD_LOCATOR = By.xpath("//label[text()='Password']");
	
	private static final By TOAST_MESSAGE_LOCATOR = By.xpath("//div[@id='toast-container']");

	Logger logger = LoggerUtility.getLogger(this.getClass());

	// super - job is to call the parent class ctor from child class ctor
	public LoginPage(Browser browserName, boolean isHeadless) {
		super(browserName, isHeadless);
		goToWebsite(JSONUtility.readJSONProperty(Env.DEV).getUrl());

	}

	// void return type is not use in page object model
	public LoginPage(WebDriver driver) {
		super(driver); // Calls BrowserUtility(WebDriver driver)
	}

	public boolean isLogoVisible() {
		return getDriver().findElement(GREST_LOGO_LOCATOR).isDisplayed();
	}

	public boolean isLoginHeadingVisible() {
		return getDriver().findElement(LOGIN_HEADING_TEXT_LOCATOR).isDisplayed();
	}

	public boolean isGuideTextVisible() {
		return getDriver().findElement(GUIDE_TEXT_LOCATOR).isDisplayed();
	}

	public boolean isUsernameFieldVisible() {
		return getDriver().findElement(USERNAME_FIELD_LOCATOR).isDisplayed();
	}
	
	public boolean isPasswordFieldVisible()
	{
		return getDriver().findElement(PASSWORD_FIELD_LOCATOR).isDisplayed();
	}
	
	public boolean isSubmitButtonVisible()
	{
		return getDriver().findElement(SUBMIT_BUTTON_LOCATOR).isDisplayed();
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
		String expectedTitle = "AGBE CMS"; // Replace with actual title if different
		String actualTitle = getDriver().getTitle();
		logger.info("Page title: " + actualTitle);
		return actualTitle.contains(expectedTitle);
	}
	
	public boolean isPasswordMasked()
	{
		String fieldType = getDriver().findElement(PASSWORD_INPUT_LOCATOR).getAttribute("type");
		return fieldType.equals("password");
	}

	public LoginPage doLoginWithInvalidCredentials(String username, String password) {
		logger.info("Trying to perform login by entering invalid credentials..");
		enterText(USERNAME_INPUT_LOCATOR, username);
		enterText(PASSWORD_INPUT_LOCATOR, password);
		clickOn(SUBMIT_BUTTON_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
	}
	
//	public void waitForToastToDisappear(){
//	    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(25));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(
//				TOAST_MESSAGE_LOCATOR));
//	}

	public String getErrorMessage() {
		return getVisibleText(ERROR_MESSAGE_LOCATOR);
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
