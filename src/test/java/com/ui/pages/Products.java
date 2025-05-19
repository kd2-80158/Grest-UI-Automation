package com.ui.pages;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.utility.BrowserUtility;
import com.utility.LoggerUtility;

public final class Products extends BrowserUtility {

    Logger logger = LoggerUtility.getLogger(this.getClass());

    private static final By SEARCH_TEXTBOX_LOCATOR = By.xpath("//input[@placeholder='Search']");
    private static final By PAGINATION_NO = By.xpath("//div/div[2]/div[4]/span[2]");
    
    private static final By ALL_PRODUCTS_LIST_NAME = By.xpath("//div/table/tbody/tr/td[2]");
	private static final By CREATE_PRODUCTS_LOCATOR = By.xpath("//button[text()='Create Products']");


    public Products(WebDriver driver) {
        super(driver);
    }

    public String searchForProduct(String productName) {
        enterText(SEARCH_TEXTBOX_LOCATOR, productName);
        enterSpecialKey(SEARCH_TEXTBOX_LOCATOR, Keys.ENTER);
        
        
        
        logger.info("Search performed for product: " + productName);
        return productName;
        
    }
    
    public String getPaginationNo() {
        WebElement element = waitForElementVisible(PAGINATION_NO);
        String text = element.getText().trim();
        logger.info("Pagination/Status element text: " + text);

        // Extract number from "Products in Page : 10"
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(); // First number found
        } else {
            return "0"; // Default if no number found
        }
    }

    public boolean checkSearchTermPresentInProductList(String searchTerm)
    {
    	logger.info("Checking the term: "+searchTerm);
    	List<String> keywords = Arrays.asList(searchTerm.toLowerCase().split(" "));
    	List<String> productNamesList = getAllVisibleText(ALL_PRODUCTS_LIST_NAME);
    	boolean result = productNamesList.stream().anyMatch(name -> (keywords.stream().anyMatch(name.toLowerCase()::contains)));
        logger.info("returning result: "+result);
    	return result;
    }
    
    public CreateNewProductPage clickOnCreateProductsButton()
    {
    	WebElement productElement = waitForElementVisible(CREATE_PRODUCTS_LOCATOR);
    	productElement.click();
    	return new CreateNewProductPage(getDriver());
    }
}
