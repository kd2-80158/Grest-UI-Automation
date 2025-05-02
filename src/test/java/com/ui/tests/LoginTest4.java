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
public class LoginTest4 extends TestBase {

	Logger logger = LoggerUtility.getLogger(this.getClass());

	//test ng is annotation based library
	@Test(description = "Verify whether the valid user is able to login to CMS application", groups = {"e2e","sanity"}, 
		  dataProviderClass=com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginDataProvider")
	public void loginTest(User user) {

		/*
		 * Test Method rules
		 * 1. test script should be small.
		 * 2. you cannot have conditional statement, loops, try-catch methods(don't bring any logic)
		 * 3. Reduce the use of local variables.
		 * 4. Have atleast one assertion.
		 */
//        String username=loginPage.doLoginWith("nikesh.agarwal@agbeindia.com", "Grest@124").getUserName();
        assertEquals(loginPage.doLoginWith(user.getUsername(), user.getPassword()).getUserName(), "Admin");
        
	}
	@Test(description = "Verify whether the valid user is able to login to CMS application", groups = {"e2e","sanity"}, 
			  dataProviderClass=com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
		public void loginCSVTest(User user) {

	        assertEquals(loginPage.doLoginWith(user.getUsername(), user.getPassword()).getUserName(), "Admin");
		}
	@Test(description = "Verify whether the valid user is able to login to CMS application", groups = {"e2e","sanity"}, 
			  dataProviderClass=com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider",
			  retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
		public void loginExcelTest(User user) {
            
	        assertEquals(loginPage.doLoginWith(user.getUsername(), user.getPassword()).getUserName(), "Admin");	
	}

}
