package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.WebUtilitys;

import java.time.Duration;

public class ProductPage {
    private static final Logger logger = LogManager.getLogger(ProductPage.class);

    WebUtilitys utility = new WebUtilitys();
    WebDriver driver;
    WebDriverWait wait;

    By elementMenuProducts = By.xpath("//span[text()= 'Sản phẩm2']");   //  Products
    By elementAllProducts = By.xpath("//div[text()= 'Tất cả sản phẩm']"); //All Products
    By elementProductManagerment = By.xpath("//div[text()= 'Quản lý sản phẩm']"); //Product management
    By titleCreateProduct = By.xpath("//a[text()= 'Create Collection, Product and Purchase Order']");
    By closeVideoIcon = By.xpath("//div[@class='v5Um9Ms0fy+rVulr6jyrVQ==']");
    By elementSubInventoryHistory = By.xpath("//a/div[text()= 'Kho Sản Phẩm']");
    By titleInventory = By.xpath("//div [@class='gs-atm__flex-row--flex-start gs-atm__flex-align-items--center']");


    public ProductPage (WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void navigateToProducts (){
        logger.info("Starting navigateToProducts process...");
        logger.debug("Starting navigateToProducts process debug12333...");
        try {
            System.out.println("Start function navigateToProducts");
            utility.clickElement(driver, elementMenuProducts);

        } catch (TimeoutException e) {
            logger.error("navigateToProducts failed: " + e.getMessage());
            throw new TimeoutException("navigateToProducts failed: " + e);
        }
    }


    public void navigateToAllProducts (){
        logger.info("Starting navigateToAllProducts process...");
        try {
            System.out.println("Start function navigateToAllProducts");
            utility.clickElement(driver, elementAllProducts);

        } catch (TimeoutException e) {
            throw new TimeoutException("navigateToProducts failed: " + e);
        }
    }


    public void verifyNavigateToAllProducts (){
        try {
            System.out.println("Start function verifyNavigateToAllProducts");
            WebElement menuProducts = wait.until(ExpectedConditions.visibilityOfElementLocated(elementProductManagerment));

            if (menuProducts.getText().equals("Product management")){
                System.out.println("verifyNavigateToAllProducts successfully");
            }

        } catch (TimeoutException e) {
            throw new TimeoutException("navigateToProducts failed: " + e);
        }
    }


    public boolean verifyTitleVideoShow (){
        try {
            System.out.println("Start function verifyTitleVideoShow");
            WebElement titleVideo = wait.until(ExpectedConditions.visibilityOfElementLocated(titleCreateProduct));
            System.out.println("titleVideo.isDisplayed() is: " + titleVideo.isDisplayed());
            return titleVideo.isDisplayed();

        } catch (TimeoutException e){
            return false;
        }
    }


    public void closePopupVideo (){
        try {
            System.out.println("Start function closePopupVideo");
            if (verifyTitleVideoShow()){
                utility.clickElement(driver, closeVideoIcon);
                System.out.println("closePopupVideo successfully");
            }

        }catch (TimeoutException e){
            throw new TimeoutException("closePopupVideo failed: " + e);

        }
    }


    public void navigateToInventory (){
        logger.info("Starting navigateToInventory process...");
        try {
            System.out.println("Start function navigateToInventory");
            utility.clickElement(driver, elementSubInventoryHistory);

        } catch (TimeoutException e) {
            throw new TimeoutException("navigateToInventory failed: " + e);
        }

    }


    public void verifyNavigateToInventory (){
        try {
            System.out.println("Start function verifyNavigateToInventory");
            WebElement menuProducts = utility.waitForVisibility(driver, titleInventory, 10);
            if (menuProducts.getText().equals("Kho Sản Phẩm")){
                System.out.println("verifyNavigateToInventory successfully");
            }

        } catch (TimeoutException e) {
            throw new TimeoutException("verifyNavigateToInventory failed: " + e);
        }
    }
}


