package ProductCreationTests;

import Pages.LoginPage;
import Pages.ProductPage2;
import Pages.VariantData;
import com.aventstack.extentreports.Status;
import org.example.BaseTest;
import org.example.Report.ExtentListener;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utility.WebUtilitys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Listeners(org.example.Report.ExtentListener.class)
public class CreateProductWithOneVariantTest extends BaseTest {

    String name;
    String pass;
    String productName;
    String descriptionProduct;
    String orgPrice;
    String discountPrice;
    String costPrice;
    String[] variations = {"Color1", "Color2", "Color3"}; // array
    // Mảng tên nhóm
    String variantNames = "Color";
    String stock;
    Map<String, VariantData > priceMap;
    String defaultQuantity;

    @BeforeClass
    public void setupTest() {
//        //        super.setup();
        loginPage = new LoginPage(driver);
        productPage = new ProductPage2(driver);
        driver.get("https://admin.gosell.vn/login.html#/");
        name = "lam.vo.tung@gosell.vn";
        pass = "Tunglam@95";
        productName= "ht test at";
        descriptionProduct = "inputDescription 123450";
        orgPrice = "1000000";
        discountPrice = "10000";
        costPrice = "100";
        stock = "100";
        priceMap = new HashMap<>();
        priceMap.put("xanh-s", new VariantData("2000","1800","1500","10"));
        variantData = new VariantData(orgPrice, discountPrice, costPrice, stock);
        defaultQuantity = "3333";

    }

    @Test
    public void testCreateProductWithOneVariantTest(){
        if (ExtentListener.getCurrentTest() != null) {
            ExtentListener.getCurrentTest().assignCategory("testCreateProductWithOneVariantTest");
            ExtentListener.getCurrentTest().getModel().setDescription("testCreateProductWithOneVariantTest description");
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
        productPage.addVariants();
        productPage.inputVariantName(variantNames);
        productPage.inputVariationValue(variations);
        productPage.fillVariantTable(priceMap, variantData);
        productPage.clickSaveButton();
        productPage.verifyCreateProduct();
    }

    @AfterClass
    public void closeDriver() {
        super.tearDown();
    }
}



