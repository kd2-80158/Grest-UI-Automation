package com.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtility {
	
	private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";

	private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();
	private static ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<DesiredCapabilities>();
	
	public static WebDriver initializeLambdaTestSession(String browser,String testName)
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        System.out.println("browser name is: "+browser);
        capabilities.setCapability("browserVersion", "latest");
        Map<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("user", "samchhimwal28");
        ltOptions.put("accessKey", "CDFxtdbXKNmaiP3U1qy8wkbBkM2Hb2j25ZqsB5CGyGO25sELxn");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", testName);
        capabilities.setCapability("platformName", "macOS Sequoia");
        capabilities.setCapability("LT:Options", ltOptions);
        capabilitiesLocal.set(capabilities);
        WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesLocal.get());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driverLocal.set(driver);
        System.out.println(driver);
        
        return driverLocal.get();
	}
	
	public static void quitSession()
	{
		if(driverLocal.get()!=null)
			driverLocal.get().quit();
	}
	
	
}
