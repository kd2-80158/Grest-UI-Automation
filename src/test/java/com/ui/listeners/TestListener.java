package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener {
    Logger logger = LoggerUtility.getLogger(this.getClass()); // Logger for logging test details
    ExtentReports extentReports; // ExtentReports object to generate the HTML report

    // This method runs before each test starts
    public void onTestStart(ITestResult result) {
        // Log the method name, description, and groups associated with the test
        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());
        logger.info(Arrays.toString(result.getMethod().getGroups()));

        // Create a new ExtentTest instance to track this test's result
        ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
    }

    // This method runs when a test passes
    public void onTestSuccess(ITestResult result) {
        // Log the success message and update the Extent report as PASS
        logger.info(result.getMethod().getMethodName() + " " + "PASSED");
        ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
    }

    // This method runs when a test fails
    public void onTestFailure(ITestResult result) {
        // Log the failure details including the error message
        logger.error(result.getMethod().getMethodName() + " " + "FAILED");
        logger.error(result.getThrowable().getMessage());

        // Log the failure status in the Extent report
        ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");
        ExtentReporterUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());

        // Extract the test instance and use it to get an instance of BrowserUtility for taking a screenshot
        Object testClass = result.getInstance();
        BrowserUtility browserUtility = ((TestBase) testClass).getInstance();
        logger.info("Taking screenshots for the failed test");

        // Take a screenshot and store the path to the screenshot
        String screenshotPath = browserUtility.takeScreenShot("loginTest");
        logger.info("Attaching the screenshot to the HTML file");

        // Attach the screenshot to the Extent report
        try {
            ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            // Log any issues that arise while attaching the screenshot to the report
            logger.error("Error attaching screenshot: " + e.getMessage());
        }
    }

    // This method runs when a test is skipped
    public void onTestSkipped(ITestResult result) {
        // Log the skipped test status and update the Extent report as SKIPPED
        logger.warn(result.getMethod().getMethodName() + " " + "SKIPPED");
        ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");
    }

    // This method runs before the test suite starts
    public void onStart(ITestContext context) {
        // Log the start of the test suite and set up the Extent Spark Reporter for the report
        logger.info("Test Suite Started..");
        ExtentReporterUtility.setupSparkReporter("report.html");
    }

    // This method runs after the test suite finishes
    public void onFinish(ITestContext context) {
        // Log the completion of the test suite and flush the report to disk
        logger.info("Test Suite Completed!!");
        ExtentReporterUtility.flushReport(); // Ensure the report is finalized and saved
    }
}
