import Pages.LoginPage;
import  Pages.ProductPage;
import org.example.Report.ReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
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

@Listeners(org.example.Report.ExtentListener.class)
public class LoginPageTest {
    LoginPage loginPage;
    ProductPage productPage;
    WebUtilitys utilitys;
    String name; // Biến toàn cục cho username
    String pass; // Biến toàn cục cho password
    String nameStore;
    WebDriver driver;
    WebDriverWait wait;

    private ExtentTest test;
    private ExtentReports extent;

    @BeforeClass
    public void setup() {
        // Initialize ExtentReports
//        extent = ReportManager.getExtentReports();
        // Khởi tạo báo cáo
//        extent =  new ExtentReports();
        extent = ReportManager.getInstance();
        test = extent.createTest("UI Test - Login Page");

        // Khởi tạo đối tượng LoginPage và gọi initDriver() để khởi tạo driver
//        utilitys = new WebUtilitys();

//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        driver.get("https://admin.gosell.vn/login.html#/");
        name = "lam.vo.tung@gosell.vn";
        pass = "Tunglam@95";
        nameStore = "Connan";

        test = extent.createTest("Setup Test", "Initializing WebDriver and Page Objects");
        test.log(Status.INFO, "Driver initialized");
//        extent.flush();
        test.log(Status.PASS, "Setup completed successfully");
//        extent.flush();
    }

    @Test
    public void test001selenium() {
        test = extent.createTest("test001selenium", "test001selenium description");
        loginPage.inputUserName(name)
                .inputPassword(pass)
                .clickSubmit()
                .verifyLogin(nameStore);
//        extent.flush();
//        test.log(Status.PASS, "test001selenium test passed");
//        test.log(Status.FAIL, "test001selenium test Failed");
//        extent.flush();
    }



//    @Test
//    public void test002(){
//        test = extent.createTest("test002selenium", "test002selenium description");
//
//        try {
//            productPageTest.navigateToProducts();
//            productPageTest.navigateToAllProducts();
//            productPageTest.verifyNavigateToAllProducts();
//            productPageTest.closePopupVideo();
//
//            test.log(Status.PASS, "test002 test passed");
//
//        } catch (Exception e) {
//            test.log(Status.FAIL, "test002 test failed: " + e.getMessage());
//
//            String screenshotPath = utilitys.captureScreenshot(driver, "test002_error");
//            try {
//                if (screenshotPath != null) {
//                    test.addScreenCaptureFromPath(screenshotPath, "Screenshot on failure");
//                }
//            } catch (Exception ex) {
//                test.log(Status.WARNING, "Failed to attach screenshot: " + ex.getMessage());
//            }
//        } finally {
//            extent.flush();
//        }
//    }


//    @Test
//    public  void test002(){
//        test = extent.createTest("test002selenium", "test002selenium description");
//        productPageTest.navigateToProducts();
//        productPageTest.navigateToAllProducts();
//        productPageTest.verifyNavigateToAllProducts();
//        productPageTest.closePopupVideo();
//        extent.flush();
//        test.log(Status.PASS, "test002 test passed");
//        test.log(Status.FAIL, "test002selenium test Failed");
////        extent.flush();
//
//    }

    @AfterClass
    public void closeDriver() {
        try {
            System.out.println("Start function close browser");
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed successfully");
            } else {
                System.out.println("Driver is null, no browser to close");
            }
        } catch (WebDriverException e) {
            test.log(Status.FAIL, "Browser close failed: " + e.getMessage());
//        } finally {
//            extent.flush(); // Ghi báo cáo
//        }
        }

    }
}


