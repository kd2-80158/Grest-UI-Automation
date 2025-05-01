//package com.ui.tests;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//import com.utility.BrowserUtility;
//
//public class LoginTest {
//
//	public static void main(String[] args) {
//		// launch browser window - create browser session
//		WebDriver wd = new FirefoxDriver(); // loose coupling[Parent class for ref and Child class for object]
////		wd.get("https://cms.grest.agbe.in/login");
//		
//		//BrowserUtility -->abstraction
//		//BrowserUtility browserUtility = new BrowserUtility(wd);
//		browserUtility.goToWebsite("https://cms.grest.agbe.in/login");
//		// window maximize
//		browserUtility.maximizeWindow();
//		// By is abstract class and all its methods are static
////      By usernameInputLocator= By.xpath("//div[1]/input");
//		By usernameInputLocator = By.cssSelector(".ng-valid");
//		//avoid using findElement() because it is not synchronized
////		WebElement usernameWebElement = wd.findElement(usernameInputLocator); // find the element
////		usernameWebElement.sendKeys("nikesh.agarwal@agbeindia.com");
//		browserUtility.enterText(usernameInputLocator, "nikesh.agarwal@agbeindia.com");
//		
////		usernameWebElement.click();
//		By passwordInputLocator=By.xpath("//div[2]/input");
////		WebElement passwordWebElement=wd.findElement(passwordInputLocator);
////		passwordWebElement.sendKeys("Grest@1245");
//		browserUtility.enterText(passwordInputLocator, "Grest@1245");
//		
//		By submitButtonLocator=By.cssSelector(".btn-block");
////		WebElement submitButtonWebElement=wd.findElement(submitButtonLocator);
////		submitButtonWebElement.click();
//		browserUtility.clickOn(submitButtonLocator);
//
//	}
//
//}
