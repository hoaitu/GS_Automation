package ProductCreationTests;

import Pages.LoginPage;
import Pages.ProductPage2;
import org.example.BaseTest;
import org.example.Report.ExtentListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//public class DeleteProductTest {
//Kế thừa

@Listeners(org.example.Report.ExtentListener.class)
public class DeleteProductTest extends BaseTest {

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
        productPage.deleteProductByName(productName);
    }

    @AfterClass
    public void closeDriver() {
        productPage.deleteProductByName(productName);
        super.tearDown();
    }
}
