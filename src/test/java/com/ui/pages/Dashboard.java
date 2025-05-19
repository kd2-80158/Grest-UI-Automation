package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.BrowserUtility;
import com.utility.LoggerUtility;

import java.time.Duration;
import java.util.Map;
import java.util.Set;

public final class Dashboard extends BrowserUtility {
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private static final By USERNAME_LOCATOR = By.xpath("//div[text()=' Admin ']");
	private static final By PRODUCTS_LOCATOR = By.xpath("//span[text()='Products ']");
	private static final By CATEGORIES_LOCATOR = By.xpath("//span[normalize-space()='Categories']");
	private static final By LOGOUT_LOCATOR = By.xpath("//a[text()='Logout ']");

	// Constructor to initialize the WebDriver
	public Dashboard(WebDriver driver) {
		super(driver);
	}

	// Method to get the username from the dashboard
	public String getUserName() {
		try {
			WebElement usernameElement = waitForElementVisible(USERNAME_LOCATOR);
			return usernameElement.getText();
		} catch (Exception e) {
			// You can log the error or handle the exception as per your test requirements
			throw new RuntimeException("Username element is not visible", e);
		}
	}

	public void clickOnProducts() {
		try {
			WebElement productElement = waitForElementVisible(PRODUCTS_LOCATOR);
			productElement.click();
			logger.info("Clicked on the 'Products' icon in the drawer.");
		} catch (Exception e) {
			throw new RuntimeException("Unable to click on the 'Products' icon", e);
		}
	}
	
	//login-->click on Admin ---> click on logout
	public void doLogout()
	{
		WebElement usernameElement = waitForElementVisible(USERNAME_LOCATOR);
		usernameElement.click();
		clickOn(LOGOUT_LOCATOR);	
	}
	
	// Print session ID
	public String getSessionId() {
	    JavascriptExecutor js = (JavascriptExecutor) getDriver();
	    String sessionId = (String) js.executeScript("return window.localStorage.getItem('token');");
	    System.out.println("Session ID: " + sessionId);
	    return sessionId;
	}
	
	public void injectSession(String token) {
	    JavascriptExecutor js = (JavascriptExecutor) getDriver();
	    js.executeScript("window.localStorage.setItem('token', arguments[0]);", token);
	}

	public void refresh() {
	    getDriver().navigate().refresh();
	}
	
	public boolean isDashboardVisible()
	{
		return true;
	}


	// Generic wait method for visibility of elements
	public WebElement waitForElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public CategoryPage goToCategoryPage()
	{
		
		WebElement categoryElement = waitForElementVisible(CATEGORIES_LOCATOR);
		categoryElement.click();
		return new CategoryPage(getDriver());
	}
}
