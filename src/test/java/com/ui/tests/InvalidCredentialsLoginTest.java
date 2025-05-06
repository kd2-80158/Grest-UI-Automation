package com.ui.tests;

import static com.constants.Browser.*;

import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.LoginPage;
import com.ui.pojos.User;
import com.utility.LoggerUtility;

@Listeners({com.ui.listeners.TestListener.class})
public class InvalidCredentialsLoginTest extends TestBase {

	Logger logger = LoggerUtility.getLogger(this.getClass());
	private static final String INVALID_USERNAME ="grestadmin";
	private static final String INVALID_PASSWORD ="Test@123";

	//test ng is annotation based library
	@Test(description = "Verify if the proper message is shown for the user when they enter Invalid Credentials", groups = {"e2e","sanity", "smoke"})
	public void loginTest() {

		/*
		 * Test Method rules
		 * 1. test script should be small.
		 * 2. you cannot have conditional statement, loops, try-catch methods(don't bring any logic)
		 * 3. Reduce the use of local variables.
		 * 4. Have atleast one assertion.
		 */
//        String username=loginPage.doLoginWith("nikesh.agarwal@agbeindia.com", "Grest@124").getUserName();
        assertEquals(loginPage.doLoginWithInvalidCredentials(INVALID_USERNAME,INVALID_PASSWORD).getErrorMessage(),"Invalid credentials");
        
	}

}
