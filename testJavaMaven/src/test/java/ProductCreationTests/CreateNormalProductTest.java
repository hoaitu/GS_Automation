package ProductCreationTests;

import Pages.LoginPage;
import Pages.ProductPage2;
import Pages.VariantData;
import org.example.BaseTest;
import org.example.Report.ExtentListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.WebUtilitys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Pages.LoginPage;
import  Pages.ProductPage;
import Pages.ProductPage2;
import Pages.VariantData;
import org.example.Report.ReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.BaseTest;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utility.WebUtilitys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//Kế thừa

@Listeners(org.example.Report.ExtentListener.class)
public class CreateNormalProductTest extends BaseTest {

    String name;
    String pass;
    String productName;
    String descriptionProduct;
    double orgPrice;
    double discountPrice;
    double costPrice;

@BeforeClass
public void setupTest() {
    super.setup();
    loginPage = new LoginPage(driver);
    productPage = new ProductPage2(driver);
    driver.get("https://admin.gosell.vn/login.html#/");
    name = "lam.vo.tung@gosell.vn";
    pass = "Tunglam@95";
    productName= "ht test at";
    descriptionProduct = "inputDescription 123450";
    orgPrice = 50000;
    discountPrice = 40000;
    costPrice = 30000;
}

@Test
public void testCreateNormalProductTest(){
    if (ExtentListener.getCurrentTest() != null) {
        ExtentListener.getCurrentTest().assignCategory("testCreateNormalProductTest");
        ExtentListener.getCurrentTest().getModel().setDescription("testCreateNormalProductTest description");
    }

    loginPage.inputUserName(name)
            .inputPassword(pass)
            .clickSubmit();

    productPage.navigateToProducts();
    productPage.closePopupVideo();
    productPage.navigateToAllProducts();
    productPage.clickCreateProduct();
    productPage.inputProductName(productName);
    productPage.inputDescription(descriptionProduct);
    productPage.selectImage();
    productPage.inputListingPriceNoVariants(orgPrice);
    productPage.inputSellingPriceNoVariants(discountPrice);
    productPage.inputCostPriceNoVariants(costPrice);
    productPage.manageNormalProduct();
    productPage.clickSaveButton();
    productPage.verifyCreateProduct();
}

@AfterClass
public void closeDriver() {
    super.tearDown();
    }
}


