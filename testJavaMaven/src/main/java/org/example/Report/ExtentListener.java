package org.example.Report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.example.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.Logger;

public class ExtentListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(ExtentListener.class);
    private ExtentReports extent;
    private static ExtentTest classTest;
    private static ExtentTest currentTest;
    private static String currentClassName;

    public static ExtentTest getClassTest() {
        return classTest;
    }

    public static ExtentTest getCurrentTest() {
        return currentTest;
    }

    public void onStart(ITestContext context) {
        logger.info("ExtentListener is working");
        if (extent == null) {
            logger.info("extent == null, initializing");
            extent = ReportManager.getInstance();
            logger.info("ExtentListener: onStart - ExtentReports initialized");
        }
    }

    public void onTestStart(ITestResult result) {
        logger.info("onTestStart called for: " + result.getMethod().getMethodName());
        String testClassName = result.getTestClass().getName();

        if (currentClassName == null || !currentClassName.equals(testClassName)) {
            // Create ExtentTest new for each tcs
            classTest = extent.createTest(testClassName);
            classTest.log(Status.INFO, "Test class started: " + testClassName);
            currentClassName = testClassName;
        }

        // Create node child for each tcs
        currentTest = classTest.createNode(result.getMethod().getMethodName());
        currentTest.log(Status.INFO, "Test started: " + result.getMethod().getMethodName());
        logger.info("Test started: " + result.getMethod().getMethodName());
    }


    private String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            logger.info("Start function captureScreenshot");
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "test-output/screenshots/" + screenshotName + ".png";
            logger.info("captureScreenshot in onTestFailure: " + screenshotPath);
            FileUtils.copyFile(sourceFile, new File(System.getProperty("user.dir") + "/" + screenshotPath));
            return screenshotPath;
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }


    public void onTestSuccess(ITestResult result) {
        if (currentTest != null) {
            currentTest.pass("Test Passed");
            logger.info("Test passed: " + result.getMethod().getMethodName());
        } else {
            logger.error("ExtentTest is null in onTestSuccess for: " + result.getMethod().getMethodName());
        }
    }


    @Override
    public void onTestFailure(ITestResult result) {
        if (currentTest != null) {
            currentTest.log(Status.FAIL, "Test failed: " + result.getThrowable());
            logger.error("Test failed: " + result.getMethod().getMethodName() + " - " + result.getThrowable());
            Object testClass = result.getInstance();
            if (testClass instanceof BaseTest) {
                logger.info("Capturing screenshot for failed test");
                WebDriver driver = ((BaseTest) testClass).getDriver();
                String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());
                if (screenshotPath != null) {
                    currentTest.fail("Screenshot on failure", MediaEntityBuilder.createScreenCaptureFromPath("../" + screenshotPath).build());
                }
            }
        } else {
            logger.error("ExtentTest is null in onTestFailure for: " + result.getMethod().getMethodName());
        }
    }


    public void onTestSkipped(ITestResult result) {
        if (currentTest != null) {
            currentTest.skip("Test Skipped");
            logger.warn("Test skipped: " + result.getMethod().getMethodName());
        } else {
            logger.error("ExtentTest is null in onTestSkipped for: " + result.getMethod().getMethodName());
        }
    }


    public void onFinish(ITestContext context) {
        logger.info("Start onFinish(ITestContext context)");
        if (extent != null) {
            extent.flush();
            logger.info("function onFinish run successfully");
        }
        // set variant when finish
        currentTest = null;
        classTest = null;
        currentClassName = null;
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}




