package com.utility;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {
	
	//one extent report
	private static ExtentReports extentReports;
	
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public static void setupSparkReporter(String reportName) {
	    // Force the report to go into target/test-output
	    String reportDir = System.getProperty("user.dir") + "/target/test-output/";
	    new File(reportDir).mkdirs(); // Create the directory if it doesn't exist
	    String reportPath = reportDir + reportName;

	    ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
	    extentReports = new ExtentReports();
	    extentReports.attachReporter(extentSparkReporter);
	}
	
	public static void createExtentTest(String testName)
	{
		ExtentTest test = extentReports.createTest(testName);
		extentTest.set(test);
	}
	
	public static ExtentTest getTest()
	{
		return extentTest.get();
	}
	
	public static void flushReport()
	{
		extentReports.flush();
	}

}
