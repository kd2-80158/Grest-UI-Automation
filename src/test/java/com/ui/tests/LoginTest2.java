package com.ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ui.pages.LoginPage;

public class LoginTest2 {

	public static void main(String[] args) {
		// launch browser window - create browser session
		WebDriver wd = new FirefoxDriver(); // loose coupling[Parent class for ref and Child class for object]
//		wd.get("https://cms.grest.agbe.in/login");
		
//		LoginPage loginPage = new LoginPage(wd);
//		loginPage.maximizeWindow();
//		loginPage.doLoginWith("nikesh.agarwal@agbeindia.com", "Grest@124");
		

	}

}
