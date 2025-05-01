package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.BrowserUtility;

import java.time.Duration;

public final class Dashboard extends BrowserUtility {

    private static final By USERNAME_LOCATOR = By.xpath("//a/div/div");

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

    // Generic wait method for visibility of elements
    public WebElement waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Optionally, you can add other actions or checks related to the dashboard here.
}
