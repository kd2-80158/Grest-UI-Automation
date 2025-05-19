package com.ui.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.Dashboard;
import com.ui.pojos.User;
import com.utility.LoggerUtility;

@Listeners({ com.ui.listeners.TestListener.class })
public class LoginTest extends TestBase {

	Logger logger = LoggerUtility.getLogger(this.getClass());
	private static final String VALID_USERNAME = "nikesh.agarwal@agbeindia.com";
	private static final String VALID_PASSWORD = "Grest@124";
	private static final String INVALID_USERNAME = "grestadmin";
	private static final String INVALID_PASSWORD = "Test@123";

	Dashboard dashboard;

	// test ng is annotation based library
	@Test(description = "Verify whether the valid user is able to login to CMS application", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
	public void loginTest(User user) {

		/*
		 * Test Method rules 1. test script should be small. 2. you cannot have
		 * conditional statement, loops, try-catch methods(don't bring any logic) 3.
		 * Reduce the use of local variables. 4. Have atleast one assertion.
		 */
//        String username=loginPage.doLoginWith("nikesh.agarwal@agbeindia.com", "Grest@124").getUserName();
		assertEquals(loginPage.doLoginWith(user.getUsername(), user.getPassword()).getUserName(), "Admin");

	}

	@Test(description = "Verify whether the valid user is able to login to CMS application", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
	public void loginCSVTest(User user) {

		assertEquals(loginPage.doLoginWith(user.getUsername(), user.getPassword()).getUserName(), "Admin");
	}

	@Test(description = "Verify whether the valid user is able to login to CMS application", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	public void loginExcelTest(User user) {

		assertEquals(loginPage.doLoginWith(user.getUsername(), user.getPassword()).getUserName(), "Admin");
	}

	@Test(description = "Verify if the proper message is shown for the user when they enter Invalid Credentials", groups = {
			"e2e", "sanity", "smoke" })
	public void loginTest() {
//        String username=loginPage.doLoginWith("nikesh.agarwal@agbeindia.com", "Grest@124").getUserName();
		assertEquals(loginPage.doLoginWithInvalidCredentials(INVALID_USERNAME, INVALID_PASSWORD).getErrorMessage(),
				"Invalid credentials");
	}

	@Test(description = "Verify login page UI elements", groups = { "e2e", "sanity" })
	public void verifyUIElementTest() {
		logger.info("Verifying UI elements on the login page.");

		assertEquals(loginPage.isLogoVisible(), true);
		assertEquals(loginPage.isLoginHeadingVisible(), true);
		assertEquals(loginPage.isGuideTextVisible(), true);
		assertEquals(loginPage.isUsernameFieldVisible(), true);
		assertEquals(loginPage.isPasswordFieldVisible(), true);
		assertEquals(loginPage.isSubmitButtonVisible(), true);
	}

	@Test(description = "Verify login failure with invalid username", groups = { "e2e", "sanity" })
	public void loginTestWithInvalidUsername() {
		logger.info("Verifying login failure with invalid username");
		assertEquals(loginPage.doLoginWithInvalidCredentials(INVALID_USERNAME, VALID_PASSWORD).getErrorMessage(),
				"Invalid credentials");

	}

	@Test(description = "Verify login failure with invalid password", groups = { "e2e", "sanity" })
	public void loginTestWithInvalidPassword() {
		logger.info("Verifying login failure with invalid password");
		assertEquals(loginPage.doLoginWithInvalidCredentials(VALID_USERNAME, INVALID_PASSWORD).getErrorMessage(),
				"Invalid credentials");
	}

	@Test(description = "Verify login failure with empty username field")
	public void loginTestWithEmptyUsername() {
		logger.info("Verifying login failure with empty username input field");
		assertEquals(loginPage.doLoginWithInvalidCredentials("", VALID_PASSWORD).getErrorMessage(),
				"Invalid credentials");
	}

	@Test(description = "Verify login failure with empty password field")
	public void loginTestWithEmptyPassword() {
		logger.info("Verifying login failure with empty password input field");
		assertEquals(loginPage.doLoginWithInvalidCredentials(VALID_USERNAME, "").getErrorMessage(),
				"Invalid credentials");
	}

	@Test(description = "Verify login failure with empty username and empty password field")
	public void loginTestWithEmptyUsernameAndPassword() {
		logger.info("Verifying login failure with empty username and empty password input field");
		assertEquals(loginPage.doLoginWithInvalidCredentials("", "").getErrorMessage(), "Invalid credentials");
	}

	@Test(description = "Verify password field masking")
	public void checkPasswordMasking() {
		logger.info("verifying if the password is masked");
		assertEquals(loginPage.isPasswordMasked(), true);
	}

	@Test(description = "Verify browser back button after logout")
	public void checkLogoutFunctionality() {
		logger.info(
				"verifying that by pressing the back button after doing logout it is not accessing protected pages");
		dashboard = loginPage.doLoginWith(VALID_USERNAME, VALID_PASSWORD);

//		loginPage.waitForToastToDisappear();
		dashboard.doLogout();
	}
	
	@Test
	public void checkSessionPersistence()
	{
		dashboard = loginPage.doLoginWith(VALID_USERNAME, VALID_PASSWORD);
        String session1 = dashboard.getSessionId();
        dashboard.quitDriver();
        
        //reopen browser
        dashboard = loginPage.doLoginWith(VALID_USERNAME, VALID_PASSWORD);
        dashboard.injectSession(session1);
        
        //do refresh
        dashboard.refresh();
        
	}

}
