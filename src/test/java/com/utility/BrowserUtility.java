package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {

	// inside the class and non-static - instance variable
	//private WebDriver driver;
	//making driver thread safe--for parallel execution
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		BrowserUtility.driver.set(driver);
	}

	public BrowserUtility(String browserName) {
		logger.info("Launching browser for " + browserName);
		if (browserName.equalsIgnoreCase("firefox"))
			driver.set(new FirefoxDriver());
		else if (browserName.equalsIgnoreCase("chrome"))
			driver.set(new ChromeDriver());
		else {
			logger.error("Invalid browser name....Please select chrome or firefox only");
			System.err.println("Invalid browser name....Please select chrome or firefox only");
	}
	}
	public BrowserUtility(Browser browserName) {
		logger.info("Launching browser for " + browserName);
		if (browserName == Browser.FIREFOX) {

			driver.set(new FirefoxDriver());
		} else if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
		}

		else {
			logger.error("Invalid browser name....Please select chrome or firefox only");
			System.err.println("Invalid browser name....Please select chrome or firefox only");
	}
	}
	//option for headless
	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching browser for " + browserName);
		if (browserName == Browser.FIREFOX) {
			if(isHeadless) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-headless");
			options.addArguments("disable-gpu");
			options.addArguments("-window-size=1920,1080");
			driver.set(new FirefoxDriver(options));
			}
			else
			{
				driver.set(new FirefoxDriver());
			}
			
		} else if (browserName == Browser.CHROME) {
			if(isHeadless) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=old");
			options.addArguments("--window-size=1920,1080");
			driver.set(new ChromeDriver(options));
		}
			else
			{
				driver.set(new ChromeDriver());
			}
		}

		else {
			logger.error("Invalid browser name....Please select chrome or firefox only");
			System.err.println("Invalid browser name....Please select chrome or firefox only");
	}
	}


	public void goToWebsite(String url) {
		logger.info("Visiting the website: " + url);
		
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing the browser window..");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding element with the locator: " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element is found and now performing the click operation");
		element.click();
	}

	public void enterText(By locator, String text) {
		logger.info("Element found now enter text" + text);
		WebElement element = driver.get().findElement(locator); // find the element
		element.sendKeys(text);
	}

	public String getVisibleText(By locator) {
		logger.info("Element finding with the locator: " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found now returning the visible text: " + element.getText());
		return element.getText();
	}
	
	public static String takeScreenShot(String name)
	{
		TakesScreenshot screenshot = (TakesScreenshot)driver.get();
		File screenshotData=screenshot.getScreenshotAs(OutputType.FILE);
		//timestamp--to differentiate btw screenshot
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp=format.format(date);
		String path = System.getProperty("user.dir")+"//screenshots//"+name+" - "+timeStamp+".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}

}
