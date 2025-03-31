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
public class CreateProductWithTwoVariantsTest extends BaseTest {

    String name;
    String pass;
    String productName;
    String descriptionProduct;
    // Mảng tên nhóm
    String[] variantNames = {"Color" , "Size"};
    String[][] variationValues = {
            {"xanh", "do", "vang"}, // Giá trị cho Color
            {"S", "M", "L"}        // Giá trị cho Size
    };
    String orgPrice = "100000";
    String discountPrice = "10000";
    String costPrice = "10000";
    String stock = "100";

    Map<String, VariantData > priceMap;
    int[] quantityBranch = {999};
    String defaultQuantity;
    Map<String, String> stockMap;
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
        orgPrice = "1000000";
        discountPrice = "10000";
        costPrice = "100";
        priceMap = new HashMap<>();
        priceMap.put("xanh-s", new VariantData("2000","1800","1500","10"));
        variantData = new VariantData(orgPrice, discountPrice, costPrice, stock);
        stockMap = new HashMap<>();
        stockMap.put("Conan Store6", "789987");
        stockMap.put("tthtu stg cn2", "200");
        defaultQuantity = "3333";
        arr = new ArrayList<>();
        arr.add("onApp");
        arr.add("inStore"); //onWeb; inGosocial
    }

    @Test
    public void testCreateProductNoVariant(){
        if (ExtentListener.getCurrentTest() != null) {
            ExtentListener.getCurrentTest().assignCategory("testCreateProductNoVariant");
            ExtentListener.getCurrentTest().getModel().setDescription("testCreateProductNoVariant description");
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
        productPage.addVariantGroups(variantNames, variationValues);
        productPage.fillVariantTable(priceMap, variantData);
        productPage.clickSaveButton();
        productPage.verifyCreateProduct();
    }

    @AfterClass
    public void closeDriver() {
        super.tearDown();
    }
}



