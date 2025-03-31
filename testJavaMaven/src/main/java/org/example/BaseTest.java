package org.example;

import Pages.LoginPage;
import Pages.ProductPage2;
import Pages.VariantData;
import org.apache.logging.log4j.LogManager;
import org.example.Report.ExtentListener;
import org.example.Report.ReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utility.WebUtilitys;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class BaseTest {
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;
    protected ExtentReports extent;
    protected LoginPage loginPage;
    protected ProductPage2 productPage;
    protected VariantData variantData;
    protected WebUtilitys utility = new WebUtilitys();

    @BeforeClass
    public void setup() {
        createLogDirectory();
        extent = ReportManager.getInstance();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("WebDriver initialized successfully");
        // Write log into ExtentTest of test class
        if (ExtentListener.getClassTest() != null) {
            ExtentListener.getClassTest().log(Status.INFO, "WebDriver initialized successfully");
        } else {
            logger.warn("ExtentTest (class) is null in setup");
        }
    }


    private void createLogDirectory() {
        String logDirPath = System.getProperty("user.dir") + "/logs";
        File logDir = new File(logDirPath);
        if (!logDir.exists()) {
            boolean created = logDir.mkdirs();
            if (created) {
                logger.info("Log directory created: " + logDirPath);
            } else {
                logger.error("Failed to create log directory: " + logDirPath);
            }
        } else {
            logger.info("Log directory already exists: " + logDirPath);
        }
    }


    @AfterClass
    public void tearDown() {
        try {
            logger.info("Start function close browser");
            if (ExtentListener.getClassTest() != null) {
                ExtentListener.getClassTest().log(Status.INFO, "Start function close browser");
            }
            if (driver != null) {
                driver.quit();
                logger.info("Browser closed successfully");
                if (ExtentListener.getClassTest() != null) {
                    ExtentListener.getClassTest().log(Status.INFO, "Browser closed successfully");
                }
            } else {
                logger.warn("Driver is null, no browser to close");
                if (ExtentListener.getClassTest() != null) {
                    ExtentListener.getClassTest().log(Status.WARNING, "Driver is null, no browser to close");
                }
            }
        } catch (WebDriverException e) {
            logger.error("Browser close failed: " + e.getMessage());
            if (ExtentListener.getClassTest() != null) {
                ExtentListener.getClassTest().log(Status.FAIL, "Browser close failed: " + e.getMessage());
            }
        }
    }


    public WebDriver getDriver() {
        return driver;
    }
}

