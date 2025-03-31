package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.WebUtilitys;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    WebUtilitys utility = new WebUtilitys();
    WebDriver driver;
    WebDriverWait wait;

    By userName = By.xpath("//input[@name= 'username']"); //By.id("username");
    By password = By.xpath("//div[contains(@class, 'form-group')]/input[@name='password']");
    By nameStore = By.xpath("//span[@class='store-name x7ZrYnn97VOmY9beLz+kkw==']");
    By btnSubmit = By.xpath("(//button[@type='submit'])[1]");
    By titleStore = By.xpath("//div[@class='title d-none d-lg-block d-md-block']/div");
    By errorMessageUserName = By.xpath("//input[@name= 'username']/..//div[@class='invalid-feedback']']"); //.text = "This field should not be empty"
    By errorMessagePassword = By.xpath("//div[contains(@class, 'form-group')]/input[@name='password']/..//div[@class='invalid-feedback']");
    By languageENG = By.xpath("//div[text() = 'ENG']");
    By languageVIE = By.xpath("//div[text() = 'VIE']");


    public LoginPage (WebDriver driver ){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public LoginPage inputUserName(String name) {
        try {
            System.out.println("Start function inputUserName");
            WebElement inputFiled = wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
            inputFiled.sendKeys(name);
            System.out.println("Input user name successfully");
            return this; // Trả về đối tượng hiện tại

        } catch (TimeoutException e) {
            logger.error("inputUserName failed: " + e.getMessage());
            throw new TimeoutException("Input user name failed: " + e);
        }
    }


    public LoginPage inputPassword(String pass) {
        logger.info("Starting login process...");
        logger.debug("pass: " + pass);
        try {
            WebElement inputPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(password));
            inputPassword.sendKeys(pass);
            System.out.println("Input password successfully");
            logger.info("Input password ...........Successfully");
            return this;

        } catch (TimeoutException e) {
            logger.error("inputUserName failed: " + e.getMessage());
            throw new TimeoutException("Input password failed: " + e);
        }
    }


    public LoginPage clickSubmit() {
        try {
            utility.clickElement(driver, btnSubmit);
            System.out.println("Click button successfully");
            return this;

        } catch (TimeoutException e) {
            logger.error("clickSubmit failed: " + e.getMessage());
            throw new TimeoutException("Input password failed: " + e);
        }
    }


    public String getExpectedErrorLanguageWhenBlankField (){
        try {
            System.out.println("Start function getExpectedErrorLanguageWhenBlankField");
            boolean isEnglishLanguage = wait.until(ExpectedConditions.visibilityOfElementLocated(languageENG)).isDisplayed();
            boolean isVietnameseLanguage = wait.until(ExpectedConditions.visibilityOfElementLocated(languageVIE)).isDisplayed();

            if (isEnglishLanguage) {
                return  "This field should not be empty";
            } else if (isVietnameseLanguage) {
                return "Mục này không được để trống";
            } else {
                return "Does not get the language";
            }

        } catch (TimeoutException e) {
            logger.error("getExpectedErrorLanguageWhenBlankField failed: " + e.getMessage());
            throw new TimeoutException("Get language failed: " + e);
        }
    }


    public String getExpectedErrorLanguageWhenLess8Number (){
        try {
            System.out.println("Start function get ExpectedErrorLanguageWhenLess8Number");
            boolean isEnglishLanguage = wait.until(ExpectedConditions.visibilityOfElementLocated(languageENG)).isDisplayed();
            boolean isVietnameseLanguage = wait.until(ExpectedConditions.visibilityOfElementLocated(languageVIE)).isDisplayed();

            if (isEnglishLanguage) {
                return  "Invalid phone number.";
            } else if (isVietnameseLanguage) {
                return "Số điện thoại không hợp lệ.";
            } else {
                return "Does not get the language for case less than 8 number";
            }
        } catch (TimeoutException e) {
            logger.error("getExpectedErrorLanguageWhenLess8Number failed: " + e.getMessage());
            throw new TimeoutException("getExpectedErrorLanguageWhenLess8Number failed: " + e);
        }
    }


    public String getExpectedErrorLanguageWhenMore15Number (){
        try {
            System.out.println("Start function get ExpectedErrorLanguageWhenLess8Number");
            boolean isEnglishLanguage = wait.until(ExpectedConditions.visibilityOfElementLocated(languageENG)).isDisplayed();
            boolean isVietnameseLanguage = wait.until(ExpectedConditions.visibilityOfElementLocated(languageVIE)).isDisplayed();

            if (isEnglishLanguage) {
                return  "Invalid phone number.";
            } else if (isVietnameseLanguage) {
                return "Số điện thoại không hợp lệ.";
            } else {
                return "Does not get the language for case less than 8 number";
            }

        } catch (TimeoutException e) {
            throw new TimeoutException("getExpectedErrorLanguageWhenLess8Number failed: " + e);
        }
    }


    public boolean validateBlankNameFieldError(){
        try {
        System.out.println("Start function: verify name blank");
        WebElement nameFieldErrorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageUserName));
        String textError = nameFieldErrorElement.getText();
        String expectedError = this.getExpectedErrorLanguageWhenBlankField();
        System.out.println("expectedError: "+ expectedError);
        if (textError.equals(expectedError)){
            System.out.println("Error msg actual "+  textError + " = with expected." );
            return true;
        } else
            System.out.println("Error msg actual "+  textError + " is not same with expected " +  expectedError);
            return false;
        }catch (TimeoutException e ){
            throw new TimeoutException("Verify Name Blank Filed failed: " + e);
        }
    }


    public boolean validateBlankPassFieldError(){
        try {
            System.out.println("Start function: verify name blank");
            WebElement passwordFieldErrorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessagePassword));
            String textError = passwordFieldErrorElement.getText();
            String expectedError = this.getExpectedErrorLanguageWhenLess8Number();
            System.out.println("expectedError: "+ expectedError);
            if (textError.equals(expectedError)){
                System.out.println("Error msg actual "+  textError + " = with expected." );
                return true;
            } else
                System.out.println("Error msg actual "+  textError + " is not same with expected " +  expectedError);
            return false;

        }catch (TimeoutException e ){
            throw new TimeoutException("Verify Name Blank Filed failed: " + e);
        }
    }


    public boolean validateNameFieldLessThan8Number(){
        try {
            System.out.println("Start function: validateNameFieldLessThan8Number");
            WebElement nameFieldErrorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageUserName));
            String textError = nameFieldErrorElement.getText();
            String expectedError = this.getExpectedErrorLanguageWhenBlankField();
            System.out.println("expectedError: "+ expectedError);

            if (textError.equals(expectedError)){
                System.out.println("Error msg actual "+  textError + " = with expected." );
                return true;
            } else
                System.out.println("Error msg actual "+  textError + " is not same with expected " +  expectedError);
            return false;

        }catch (TimeoutException e ){
            throw new TimeoutException("Verify Name Blank Filed failed: " + e);
        }
    }


    public boolean validateNameFieldMoreThan15Number(){
        try {
            System.out.println("Start function: validateNameFieldMoreThan16Number");
            WebElement nameFieldErrorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageUserName));
            String textError = nameFieldErrorElement.getText();
            String expectedError = this.getExpectedErrorLanguageWhenMore15Number();
            System.out.println("expectedError: "+ expectedError);

            if (textError.equals(expectedError)){
                System.out.println("Error msg actual "+  textError + " = with expected." );
                return true;
            } else
                System.out.println("Error msg actual "+  textError + " is not same with expected " +  expectedError);
            return false;

        }catch (TimeoutException e ){
            throw new TimeoutException("Verify Name Blank Filed failed: " + e);
        }
    }


    // Phương thức xác minh đăng nhập thành công
    public void verifyLogin(String name) {
        WebElement nameStoreElement = wait.until(ExpectedConditions.visibilityOfElementLocated(nameStore));
        String nameStore = nameStoreElement.getText();
        if (nameStore.equals(name)) {
            System.out.println("Tên cửa hàng: " + nameStore);
        } else {
            System.out.println("Tên cửa hàng không khớp: " + nameStore);
        }
        // Kiểm tra phần tử tiêu đề
        WebElement titleStoreElement = wait.until(ExpectedConditions.visibilityOfElementLocated(titleStore));
        wait.until(ExpectedConditions.textToBePresentInElement(titleStoreElement, "Chào mừng đến GoSELL, " + nameStore));
        System.out.println("Đăng nhập thành công: " + titleStoreElement.getText());
    }
}

