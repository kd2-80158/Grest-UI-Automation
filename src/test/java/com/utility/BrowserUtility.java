package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Browser;

public abstract class BrowserUtility {

	// inside the class and non-static - instance variable
	// private WebDriver driver;
	// making driver thread safe--for parallel execution
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

	// option for headless
	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching browser for " + browserName);
		if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("-headless");
				options.addArguments("disable-gpu");
				options.addArguments("-window-size=1920,1080");
				driver.set(new FirefoxDriver(options));
			} else {
				driver.set(new FirefoxDriver());
			}

		} else if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
			} else {
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
		logger.info("Element found now enter text: " + text);
		WebElement element = driver.get().findElement(locator); // find the element
		element.sendKeys(text);
	}

	public void enterSpecialKey(By locator, Keys keyToEnter) {

		WebElement element = driver.get().findElement(locator); // find the element
		logger.info("Element found now enter special key: " + keyToEnter);
		element.sendKeys(keyToEnter);
	}

	public String getVisibleText(By locator) {
		try {
			logger.info("Element finding with the locator: " + locator);

			// Wait for the element to be visible
			WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(5));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

			String text = element.getText();
			logger.info("Element found. Returning the visible text: " + text);
			return text;
		} catch (NoSuchElementException e) {
			logger.error("Error: Unable to find the element with locator: " + locator, e);
			return ""; // Return empty string or handle it as per your needs
		}
	}

	public String getVisibleText(WebElement element) {

		logger.info("Returning the visible text " + element.getText());

		return element.getText();
	}

	public List<String> getAllVisibleText(By locator) {
		logger.info("Finding all elements with locator: " + locator);

		List<String> texts = new ArrayList<>();
		int maxRetries = 2;
		int attempts = 0;

		while (attempts < maxRetries) {
			try {
				List<WebElement> elements = waitForElementsVisible(locator);
				logger.info("Elements found. Now printing the list of elements.");

				elements.forEach(element -> {
					try {
						String text = getVisibleText(element);
						System.out.println(text);
						texts.add(text);
					} catch (StaleElementReferenceException e) {
						logger.warn("Caught stale element, skipping this element.");
					}
				});

				return texts; // Return texts if no errors

			} catch (StaleElementReferenceException e) {
				logger.warn(
						"StaleElementReferenceException caught during attempt #" + (attempts + 1) + ", retrying...");
				attempts++;
				continue; // Skip to the next attempt
			}
		}

		logger.error("Failed to retrieve visible text after " + maxRetries + " attempts due to stale elements.");
		return texts; // Return empty or partial list
	}

	public static String takeScreenShot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		// timestamp--to differentiate btw screenshot
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		// removing absolute path and using relative path
		String path = "./screenshots/" + name + " - " + timeStamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}

	public WebElement waitForElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> waitForElementsVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void dropdownLocator(By dropdownLocator, String optionToSelect)
	{
		logger.info("Finding all elements with locator: " + dropdownLocator);
		WebElement element = driver.get().findElement(dropdownLocator);
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
		    logger.info("Dropdown option: '" + option.getText() + "'");
		}
		logger.info("Selecting the option: " +optionToSelect);
		select.selectByVisibleText(optionToSelect);
	}

	public static void quitDriver() {
		WebDriver webDriver = driver.get();
		if (webDriver != null) {
			webDriver.quit();
			driver.remove(); // This clears the thread-local value
			System.out.println("Driver quit and removed successfully.");
		} else {
			System.out.println("Driver is null. Nothing to quit or remove.");
		}
	}

}
