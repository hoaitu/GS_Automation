package utility;

import Pages.LoginPage;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import java.time.Duration;
import java.util.List;

public class WebUtilitys {
    private WebDriver driver;
    private WebDriverWait wait;
    ExtentTest logger;
    int defaultTimeoutInSeconds = 10;
    int retryAttempts = 3;
    private final ExtentReports extent = new ExtentReports();

    public WebDriver getDriver(){
        return driver;
    }

    public WebDriverWait getWait(){
        return wait;
    }

    public void clickElement(WebDriver driver, By locator) {
        WebElement element = waitForClickable(driver, locator, 10);
        clickElement(driver, element);
    }

    public void clickElement(WebDriver driver, WebElement element) {
        if (trySeleniumClick(element)) {
            System.out.println("Clicked using Selenium.");
            return;
        }

        if (tryJavaScriptClick(driver, element)) {
            System.out.println("Clicked using JavaScript.");
            return;
        }

        System.out.println("Attempting to click using Actions...");
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        System.out.println("Clicked using Actions.");
    }

    private boolean trySeleniumClick(WebElement element) {
        try {
            System.out.println("Attempting to click element using Selenium...");
            element.click();
            return true;
        } catch (WebDriverException e) {
            System.out.println("Selenium click failed: " + e.getMessage());
            return false;
        }
    }

    private boolean tryJavaScriptClick(WebDriver driver, WebElement element) {
        try {
            System.out.println("Attempting to click using JavaScript...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            return true;
        } catch (WebDriverException e) {
            System.out.println("JavaScript click failed: " + e.getMessage());
            return false;
        }
    }


    public void scrollToElement1(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void scrollToElement(WebDriver driver, By locator) {
        try {
            WebElement element = waitForVisibility(driver, locator, 1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }catch (TimeoutException e){
            throw new TimeoutException(" scrollToElement failed: " + e);
        }

    }


    public WebElement waitForVisibility(WebDriver driver, By locator, int timeout) {
        /**
         * Chờ cho phần tử được nhìn thấy (visible) trong DOM trong khoảng thời gian chỉ định.
         */
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e){
            throw new TimeoutException(" waitForVisibility " + e);
        }
    }

    public List<WebElement> visibilityOfAllElementsLocatedBy (WebDriver driver, By locator, int timeout) {
        /**
         * Chờ cho phần tử được nhìn thấy (visible) trong DOM trong khoảng thời gian chỉ định.
         */
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
            );
        } catch (TimeoutException e){
            throw new TimeoutException(" visibilityOfAllElementsLocatedBy failed by " + e);
        }
    }


    public WebElement waitForClickable(WebDriver driver, By locator, int timeout) {
        /**
         * Chờ cho phần tử có thể click được (clickable).
         */
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e){
            throw new TimeoutException(" Element located but not clickable: " + locator, e);
        }
    }


    public WebElement waitForPresence(WebDriver driver, By locator, int timeout) {
        /**
         * Chờ cho phần tử xuất hiện (present) trong DOM, không cần hiển thị.
         */
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new TimeoutException("waitForPresence failed: " + e);
        }
    }


    public void sendKeys(WebDriver driver, int waitTimeInMillis, CharSequence... keysToSend) {
        /**
         * Gửi một hoặc nhiều phím (Keys) tới trình duyệt.
         * keysToSend Actions.sendKeys(CharSequence... keysToSend) trong Selenium hỗ trợ cả String lẫn Keys.
         */
        try {
            // Chờ một khoảng thời gian trước khi gửi phím
            Thread.sleep(waitTimeInMillis);
        } catch (InterruptedException e) {
            throw new TimeoutException("sendKeys failed: " + e);
        }
        Actions actions = new Actions(driver);
        actions.sendKeys(keysToSend).perform();
    }


    public boolean isElementVisible(WebDriver driver, By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void sendKeysWithModifier(WebDriver driver, Keys modifier, String text) {
        /**
         * Giữ (keyDown) một phím modifier như SHIFT, CONTROL, ALT
         * rồi nhả (keyUp) sau khi perform.
         * modifier SHIFT, CONTROL, ALT
         * text Chuỗi cần gõ trong lúc modifier đang giữ
         */
        Actions actions = new Actions(driver);
        actions.keyDown(modifier)
                .sendKeys(text)
                .keyUp(modifier)
                .perform();
    }


    public void reloadPage(WebDriver driver) {
        try {
            driver.navigate().refresh();
            Thread.sleep(2000); // Chờ 2 giây để trang tải lại
            System.out.println("Đã reload trang thành công.");
        } catch (Exception e) {
            System.out.println("Lỗi khi reload trang: " + e.getMessage());
            throw new RuntimeException("Reload trang thất bại: " + e.getMessage());
        }
    }

    /** Nhấn Enter*/
    public void pressEnter(WebDriver driver) {
        sendKeys(driver, 1500, Keys.ENTER);
    }

    /** Nhấn Tab */
    public void pressTab (WebDriver driver) {
        sendKeys(driver, 1000, Keys.TAB);
    }

    /** Nhấn Escape */
    public void pressEscape(WebDriver driver) {
        sendKeys(driver, 1000, Keys.ESCAPE);
    }


    public void setInputValue(WebDriver driver, By locator, String value) {
        try {
            WebElement element = waitForClickable(driver, locator, 100);
            element.click();
            element.clear();
            element.sendKeys(value);
            System.out.println("Successfully set value: " + value);
        } catch (Exception e) {
            System.err.println("Failed to set value: " + value + " - Error: " + e.getMessage());
            throw e;
        }
    }
}

