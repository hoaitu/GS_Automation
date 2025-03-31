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

public class ProductPageTest extends BaseTest {
    LoginPage loginPage;
    ProductPage2 productPage;
    VariantData variantData;

    WebUtilitys utilitys;
    //    String name; // Biến toàn cục cho username
//    String pass; // Biến toàn cục cho password
//    String nameStore;
    String name = "lam.vo.tung@gosell.vn";
    String pass = "Tunglam@95";
    String nameStore = "Connan";
    String[] variations = {"Variant1", "Variant2", "Variant3"}; // array

    // Mảng tên nhóm
    String[] variantNames = {"Color" , "Size"};

    // Mảng 2 chiều cho giá trị
    // [0] -> giá trị cho "Color"
    // [1] -> giá trị cho "Size"
    String[][] variationValues = {
            {"xanh", "do", "vang"}, // Giá trị cho Color
            {"S", "M", "L"}        // Giá trị cho Size
    };

    //----------4
    String orgPrice = "100000";
    String discountPrice = "10000";
    String costPrice = "10000";
    String stock = "100";

    Map<String, VariantData > priceMap;
//        priceMap.put("xanh-s", new VariantData("2000","1800","1500","10"));

    //        Nhập gia từng chi nhánh:
    int[] quantityBranch = {999};

    //        ========
    String defaultQuantity;
    Map<String, String> stockMap;
    List<String> arr;

//    VAT: tax_10%


//    WebDriver driver;
//    WebDriverWait wait;
//
//    private ExtentTest test;
//    private ExtentReports extent;

    @BeforeClass
    public void setupTest() {
        super.setup();
        loginPage = new LoginPage(driver);
        productPage = new ProductPage2(driver);
        driver.get("https://admin.gosell.vn/login.html#/");
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
    //        Tcs tạo sản phẩm ko có variant
    @Test
    public void testCreateProductNoVariant(){
//        test = extent.createTest("testCreateProductNoVariant", "testCreateProductNoVariant description");
        loginPage.inputUserName(name)
                .inputPassword(pass)
                .clickSubmit();

        productPage.navigateToProducts();
        productPage.closePopupVideo();
        productPage.navigateToAllProducts();

//        productPage.deleteProductByName("ht test at");

//                ====================


        productPage.clickCreateProduct();
        productPage.inputProductName("ht test at");
        productPage.inputDescription("inputDescription 123450");
        productPage.selectImage();
////
        productPage.inputListingPriceNoVariants(50000);
        productPage.inputSellingPriceNoVariants(40000);
        productPage.inputCostPriceNoVariants(30000);
        productPage.setTaxRate("tax_10%");

//        Bật tắt listing
        // Tắt tính năng "Show as listing product on store front"
        productPage.setShowListingProduct(false);
////        UNIT
//        productPage.addUnit("Combo3999");
////        Conversation Unit
//        productPage.addConversionUnit("123456", "3");
//
//        //            Nhập số lượng
//        productPage.inputQuantityApplyAll("100000000000");
//
////            Chọn sản phẩm quản lý theo normal date
//        productPage.manageNormalProduct();

//        ================


//            Chọn sản phẩm quản lý theo Lot-date
//            productPage.manageLotProduct(true); //false
        //           Chọn sản phẩm quản lý theo IMEI
//            productPage.manageIMEIProduct();

//            nhập ố lượng cho từng chi nhánh
//            productPage.setStockForAllBranches2(123456789);
//            productPage.setStockForAllBranches(999999);
//            productPage.setStockForAllBranches3(quantityBranch);
//            productPage.setStockForAllBranches4(stockMap, defaultQuantity);



//VAT để sau

//            Input variant: 1 variant name - 1/ nhiều variants input
//            productPage.addVariants();
//            productPage.inputVariantName("nameVariant 1");
//            productPage.inputVariationValue(variations);

//            Input: 2 variant name - mỗi variant name -> 1/ nhiều variants input
//            productPage.addVariantGroups(variantNames, variationValues);
        //TODO : nhìu element -> thêm vị trí nào nữa

//            nhập giá tiền, số lượng
//            productPage.fillVariantTable(priceMap, variantData);

        productPage.setPlatformSelection(arr, false);
        productPage.clickSaveButton();
        productPage.verifyCreateProduct();
//            productPage
//            productPage
//            productPage



    }
//        tcs tạo sản phẩm có 1 variant
//        2 variant
//        có set outofstock
//        có set ẩn .....


//        @AfterClass
//        public void closeDriver() {
//            try {
//                System.out.println("Start function close browser");
//                if (driver != null) {
//                    driver.quit();
//                    System.out.println("Browser closed successfully");
//                } else {
//                    System.out.println("Driver is null, no browser to close");
//                }
//            } catch (WebDriverException e) {
//                test.log(Status.FAIL, "Browser close failed: " + e.getMessage());
////        } finally {
////            extent.flush(); // Ghi báo cáo
////        }
//            }
//
//        }
}
