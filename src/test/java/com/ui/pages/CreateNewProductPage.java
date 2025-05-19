package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.utility.BrowserUtility;

public class CreateNewProductPage extends BrowserUtility {

	private static final By NAME_LOCATOR = By.xpath("//input[@placeholder=' Enter Product Name']");

	private static final By GSN_LOCATOR = By.xpath("//div/input[@placeholder='product_code']");
	private static final By SIMPLE_DESC_LOCATOR = By.xpath("//div/textarea[@placeholder='Simple Description']");
	private static final By MRP_LOCATOR = By.xpath("//div/input[@placeholder='MRP']");
	private static final By PAYPRICE_LOCATOR = By.xpath("//div/input[@placeholder='PayPrice']");
	private static final By SGST_LOCATOR = By.xpath("//div/input[@name='SGST_percent']");
	private static final By CGST_LOCATOR = By.xpath("//div/input[@name='CGST_percent']");
	private static final By IGST_LOCATOR = By.xpath("//div/input[@name='IGST_percent']");
	private static final By STOCK_LOCATOR = By.xpath("//div/input[@name='stock']");
	private static final By CATEGORY_LEVEL1_LOCATOR = By.xpath("//select[contains(@class,'form-control')][1]");
	private static final By CATEGORY_LEVEL2_LOCATOR = By.xpath("//select[contains(@class,'form-control')][2]");
	private static final By CATEGORY_LEVEL3_LOCATOR = By.xpath("//select[contains(@class,'form-control')][3]");

	private static final By SAVE_BUTTON_LOCATOR = By.xpath("//button[text()='Save']");

	public CreateNewProductPage(WebDriver driver) {
		super(driver);
	}

	public void selectCategoryLevel1(String visibleText) {
		WebElement dropdown = getDriver().findElement(CATEGORY_LEVEL1_LOCATOR);
		Select select = new Select(dropdown);
		select.selectByVisibleText(visibleText);
	}

	public void selectCategoryLevel2(String visibleText) {
		WebElement dropdown = getDriver().findElement(CATEGORY_LEVEL2_LOCATOR);
		Select select = new Select(dropdown);
		select.selectByVisibleText(visibleText);
	}

	public void selectCategoryLevel3(String visibleText) {
		WebElement dropdown = getDriver().findElement(CATEGORY_LEVEL3_LOCATOR);
		Select select = new Select(dropdown);
		select.selectByVisibleText(visibleText);
	}

	public void saveProduct() {
		getDriver().findElement(NAME_LOCATOR).sendKeys("MacBook Pro");
		getDriver().findElement(GSN_LOCATOR).sendKeys("MBP2025");
		getDriver().findElement(SIMPLE_DESC_LOCATOR).sendKeys("Apple MacBook Pro 2025 Model");

		getDriver().findElement(MRP_LOCATOR).sendKeys("250000");
		getDriver().findElement(PAYPRICE_LOCATOR).sendKeys("230000");
		getDriver().findElement(SGST_LOCATOR).sendKeys("9");
		getDriver().findElement(CGST_LOCATOR).sendKeys("9");
		getDriver().findElement(IGST_LOCATOR).sendKeys("0");
		getDriver().findElement(STOCK_LOCATOR).sendKeys("100");

		// Select categories
		selectCategoryLevel1("Mobile Phones");
		selectCategoryLevel2("QC PHONES");
		selectCategoryLevel3("GRADED QC PHONES");

		// Click Save button
		getDriver().findElement(SAVE_BUTTON_LOCATOR).click();

	}

}
