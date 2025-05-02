package com.ui.tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.constants.Env;
import com.ui.pages.LoginPage;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {

	protected LoginPage loginPage;
	protected Logger logger = LoggerUtility.getLogger(this.getClass());
//    private Browser browserType = Browser.FIREFOX_REMOTE; // Change to FIREFOX for local testing
//    private boolean isHeadless = true;
	private boolean isLambdaTest;

	@Parameters({ "browser", "isLambdaTest", "isHeadless" })
	@BeforeMethod(description = "Load the login page of the website")
	public void setup(@Optional("FIREFOX") String browser, @Optional("false") boolean isLambdaTest,
			@Optional("true") boolean isHeadless, ITestResult result) {
		try {
			this.isLambdaTest = isLambdaTest;
			WebDriver lambdaDriver;
			if (isLambdaTest) {
				logger.info("Running test on LambdaTest using Firefox");
				lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser,
						result.getMethod().getMethodName());
				loginPage = new LoginPage(lambdaDriver);
			} else {
				logger.info("Running test locally on Firefox browser");
				loginPage = new LoginPage(Browser.valueOf(browser), isHeadless);
				loginPage.maximizeWindow();
			}
			loginPage.goToWebsite(JSONUtility.readJSONProperty(Env.QA).getUrl());
			loginPage.isLoginPageLoaded();
		} catch (Exception e) {
			logger.error("Error while initializing the test setup", e);
			throw new RuntimeException("Test setup failed", e);
		}
	}

	public BrowserUtility getInstance() {
		return loginPage;
	}

	@AfterMethod(description = "Tear down the browser session")
	public void tearDown(ITestResult result) {
	    try {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            logger.error("Test failed: Taking screenshot and attaching it to the report");
	            // Code to capture a screenshot or any other failure actions can be added here
	        }

	        if (isLambdaTest) {
	            logger.info("Quitting LambdaTest session");
	            LambdaTestUtility.quitSession();
	        } else {
	            BrowserUtility.quitDriver(); // Use the static method here
	        }
	    } catch (Exception e) {
	        logger.error("Error during tearDown", e);
	    }
	}
}
