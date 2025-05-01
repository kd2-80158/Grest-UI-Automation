package com.ui.tests;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pojos.User;
import com.utility.LoggerUtility;

@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest4 extends TestBase {

	Logger logger = LoggerUtility.getLogger(this.getClass());

	//test ng is annotation based library
	@Test(description = "Verify whether the valid user is able to login to CMS application", groups = {"e2e","sanity"}, 
		  dataProviderClass=com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginDataProvider")
	public void loginTest(User user) {
		try {
			// Log the start of the login test
			logger.info("Starting login test with user: " + user.getUsername());
			
			// Performing the login action
			String actualUserName = loginPage.doLoginWith(user.getUsername(), user.getPassword()).getUserName();
			
			// Logging the expected and actual results
			logger.info("Expected username: Admin");
			logger.info("Actual username: " + actualUserName);
			
			// Assertion to check if the login was successful
			assertEquals(actualUserName, "Admin", "Login failed, username does not match expected value.");
			
			// Log success
			logger.info("Login test passed for user: " + user.getUsername());
		} catch (Exception e) {
			// Log failure if there was an exception
			logger.error("Login test failed for user: " + user.getUsername(), e);
			throw e;  // Re-throw the exception for TestNG to handle it
		}
	}
	@Test(description = "Verify whether the valid user is able to login to CMS application", groups = {"e2e","sanity"}, 
			  dataProviderClass=com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
		public void loginCSVTest(User user) {

	        assertEquals(loginPage.doLoginWith(user.getUsername(), user.getPassword()).getUserName(), "Admin");
		}
	@Test(description = "Verify whether the valid user is able to login to CMS application", groups = {"e2e","sanity"}, 
			  dataProviderClass=com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
//			  retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
		public void loginExcelTest(User user) {
            
	        assertEquals(loginPage.doLoginWith(user.getUsername(), user.getPassword()).getUserName(), "Admin");	
	}

}
