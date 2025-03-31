package ProductCreationTests;

import Pages.LoginPage;
import Pages.ProductPage2;
import com.aventstack.extentreports.Status;
import org.example.BaseTest;
import org.example.Report.ExtentListener; // Import ExtentListener
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.example.Report.ExtentListener.class)
public class CreateProductSetupVAT extends BaseTest {
    String name;
    String pass;
    String productName;
    String descriptionProduct;
    double orgPrice;
    double discountPrice;
    double costPrice;
    String vat;

    @BeforeClass
    public void setupTest() {
        super.setup();
        System.out.println("Parent class: " + this.getClass().getSuperclass().getName());
        if (ExtentListener.getClassTest() != null) {
            ExtentListener.getClassTest().log(Status.INFO, "Parent class: " + this.getClass().getSuperclass().getName());
        }
        loginPage = new LoginPage(driver);
        productPage = new ProductPage2(driver);
        driver.get("https://admin.gosell.vn/login.html#/");
        name = "lam.vo.tung@gosell.vn";
        pass = "Tunglam@95";
        productName = "ht test at";
        descriptionProduct = "inputDescription 123450";
        orgPrice = 50000;
        discountPrice = 40000;
        costPrice = 30000;
        vat = "tax_10%";
    }

    @Test
    public void TestCreateProductSetupVAT() {
        if (ExtentListener.getCurrentTest() != null) {
            ExtentListener.getCurrentTest().assignCategory("TestCreateProductSetupVAT 123");
            ExtentListener.getCurrentTest().getModel().setDescription("TestCreateProductSetupVAT description");
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
        productPage.setTaxRate(vat);
        productPage.clickSaveButton();
        productPage.verifyCreateProduct();
    }

    @AfterClass
    public void closeDriver() {
        if (ExtentListener.getClassTest() != null) {
            ExtentListener.getClassTest().log(Status.INFO, "Driver closed in CreateProductSetupVAT");
        }
        super.tearDown();
    }
}



