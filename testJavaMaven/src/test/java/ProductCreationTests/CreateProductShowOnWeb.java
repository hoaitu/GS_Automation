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
public class CreateProductShowOnWeb extends BaseTest {
    String name;
    String pass;
    String productName;
    String descriptionProduct;
    double orgPrice;
    double discountPrice;
    double costPrice;
    List<String> arr;

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
        arr = new ArrayList<>();
        arr.add("onApp");
        arr.add("inStore");
        arr.add("inGosocial");
    }

    @Test
    public void testCreateProductShowOnWeb(){
        if (ExtentListener.getCurrentTest() != null) {
            ExtentListener.getCurrentTest().assignCategory("testCreateProductShowOnWeb");
            ExtentListener.getCurrentTest().getModel().setDescription("testCreateProductShowOnWeb description");
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
        productPage.setPlatformSelection(arr, false);
        productPage.clickSaveButton();
        productPage.verifyCreateProduct();
    }

    @AfterClass
    public void closeDriver() {
        super.tearDown();
    }
}



