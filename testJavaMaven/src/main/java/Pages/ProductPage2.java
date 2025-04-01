package Pages;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utility.WebUtilitys;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ProductPage2 {
    private static final Logger logger = LogManager.getLogger(ProductPage2.class);

    WebUtilitys utility = new WebUtilitys();
    WebDriver driver;
    WebDriverWait wait;

    By elementMenuProducts = By.xpath("//div[contains(@menu-key, '\"product\":\"product_product\"')]");
    //  All Products
    By elementAllProducts = By.xpath("//a[@menu-key='product_product']");
    By elementProductManagerment = By.xpath("//div[@class='gs-atm__flex-row--flex-start gs-atm__flex-align-items--center ']"); //Product management
    By titleCreateProduct = By.xpath("//a[text()= 'Create Collection, Product and Purchase Order']");
    By closeVideoIcon = By.xpath("//div[@class='v5Um9Ms0fy+rVulr6jyrVQ==']");
    By elementSubInventoryHistory = By.xpath("//a[@menu-key='product_inventory']");
    By titleInventory = By.xpath("//div [@class='gs-atm__flex-row--flex-start gs-atm__flex-align-items--center']");
    By createProduct = By.xpath("//button[@data-sherpherd='tour-product-step-3']");

    //  Input name product
    By productName = By.xpath("//input[@name='productName']");
    //  TODO: input description: icon, format, ....
    By description = By.xpath("//div[@class='fr-element fr-view']");
    //  TODO
    By imageDropZone = By.xpath("//div[@class='image-drop-zone']");
    By fileName = By.xpath("//input[@type='file']");
    //TODO do video

    /**                     -------- Input prices, stock when do not have variants -------- **/
    //    input giá: Giá niêm yết
    By listingPrice = By.xpath("//div[label[@for='productPrice']][1]//input[@type='text' and contains(@class,'cur-input--unit')]");
    //    input giá: Giá bán
    By sellingPrice = By.xpath("//div[label[@for='productDiscountPrice']]//input[@type='text' and contains(@class,'cur-input--unit')]");
    //    input giá: Giá gốc
    By costPrice1 = By.xpath("//div[label[@for='productPrice']][2]//input[@type='text' and contains(@class,'cur-input--unit')]");
    //    VAT btn
    By vat = By.xpath ("//div[@class = 'form-group' and label[@class = 'gs-frm-control__title']]//button[contains(@class, 'select__valueRendered')]");

    /**                     ---------- add variants -------- **/
    //    Button "Add variants" By variant = By.xpath("//span[text()='Thêm nhóm']");
    By variant = By.xpath("(//div[contains(@class, 'gs-widget__header')]/span[@class = 'gs-fake-link '])[1]");
    By iconDeleteVariant = By.xpath("//button[contains (@class , 'gs-button__white gs-button--undefined product-form-variation-selector__btn-delete')]");
    //    input name group variant
    By nameVariant = By.xpath("//input[contains (@name, 'variationName')]");
    By typeVariants = By.id("box-input-id");
    //    click ra ngoài 1 element khác để đóng dropdown khi thêm name variant: By nameVariantText = By.xpath("//div[@class='row d-none d-md-flex ']//label[@class='gs-frm-input__label'] [text()='Tên nhóm']");
    By nameVariantText = By.xpath("//div[@class='row d-none d-md-flex ']/div[@class = 'col-12 col-md-3 p-0']/label[@class='gs-frm-input__label']");
    By rowOfVariant = By.xpath("//table[contains(@class, 'product-form-variation-selector__table')]/tbody/tr");
    //  input Unit
    By nameUnit = By.name("input-search");
    //    btn Add Unit - If unit does not exist
    By addUnit = By.xpath("//span[@class='icon_add_unit']");
    //    có bao nhiu unit có sẵn
    By itemUnit = By.xpath("//div[@class='box-search-item']");
    //    button Save
    By buttonSave = By.xpath("//button[@data-testid='desktop-saveBtn']");
    //    Button Cancel
    By buttonCancel = By.xpath("//div[@class='gss-content-header--action-btn sticky']/div/button[2]");
     //   TODO Quản lý kho hàng theo loại nào
    By inputQuntity = By.xpath("//div[@class='col pl-0']//input[@class = 'form-control cur-input cur-input--non-unit']");
    // button Apply All By applyAll = By.xpath("//div[text()='Apply All']");
    By applyAll = By.xpath("//button[@class='gs-button  gs-button__blue gs-button--undefined ']");

    /****       Information with product has Variants      ***/
    //    Giá nêm yết
    By orgPrice = By.xpath(".//input[contains(@id,'orgPrice')]");
    //    GIá bán
    By discountPrice = By.id("-discountPrice");
    //    giá gốc
    By costPrice = By.id("-costPrice");
    //    Số lượng
    By stock =  By.xpath(".//input[contains(@id,'stock')]");
    //    Quantity row shows when adding variants
    //    Locator for row in table
    By  variantRows = By.xpath("//table[contains(@class, 'product-form-variation-selector__table')]/tbody/tr");
    //    Button 'Update' in Popup open By updateButton = By.xpath("//button/div[text() = 'Cập nhật']");
    By updateButton = By.xpath("//button[@class='gs-button  gs-button__green gs-button--undefined ']");
    By applyAllInput = By.xpath("//div[contains(@class, 'product-variation-price-editor-modal__apply-price')]//input[@precision='0']");
    By applyAllButton = By.xpath("//button/div[contains(text(), 'Áp dụng cho tất cả')]");
    By stockInput = By.xpath("//div[contains(@class, 'quantity-input-field')]//input[@precision='0']");

    //    TODO  SKU; Mã vach
    //    Select Option on popup update price
    //    Gia niem yet
    By orgPriceOption = By.xpath("(//div[@class='uik-select__optionContent']/div[@class='uik-select__label'])[1]");
    //    Gia bán
    By sellingPriceOption = By.xpath("(//div[@class='uik-select__optionContent']/div[@class='uik-select__label'])[2]");
    //    Gia gốc
    By costPriceOption = By.xpath("(//div[@class='uik-select__optionContent']/div[@class='uik-select__label'])[3]");
    //    Giá trị mặc định:
    By defaultValue = By.xpath("//div[contains(@class, 'modal-content')]//button[@class='uik-btn__base uik-select__valueRendered']");
    //   Add product successfully -> show popup: 'update product successfully'
    By popupCreateProduct = By.xpath("//div[@class='modal-body']");
    //    Click input: "Manage inventory by Lot/ IMEI/ normal product" click vô thanh input Quản lý kho hàng theo Lot/ IMEI/ bình thường
    By manageInventory = By.id("manageInventory");
    By imeiSerialOption = By.xpath("//option[@value='IMEI_SERIAL_NUMBER']");
    By verifyImeiOption = By.xpath("//div[@class='Notice-product-quantity']") ;
    //    Option manage follow product
    By productOption = By.xpath("//option[@value='PRODUCT']");
    By verifyProductOption = By.xpath("//div[text() = 'Quản lý kho hàng theo Lô hạn sử dụng']");
    //   Check LotDate: check or checked => value = "true" +  checked -> Clicked
    By lotDateAvailable = By.id("lotAvailable");
    //    Check "trừ số lượng hết hạn bởi tồn kho hay ko" -> value = "true", checked => cliked
    By expiredQuality = By.id("expiredQuality");
    //    Display if out of stock
    By displayOutOfStock = By.id("showOutOfStock");
    //    Hide remaining stock on online store
    By hideRemainingStock = By.id("isHideStock");

    /***      ---------------------                            ***/
    By inputQuantityBranch2 = By.xpath("//div[contains(@class, 'branch-list-stock')]/div[contains(@class, 'branch-list-stock__wrapper__row row')]//.//div[contains(@class, 'input-group cur-input--not-error')]/input[contains(@class, 'form-control cur-input cur-input--non-unit')]");
    By wrapperRowBranch = By.xpath("//div[contains(@class, 'branch-list-stock')]/div[contains(@class, 'branch-list-stock__wrapper__row row')]");
    By nameBranch = By.xpath(".//div[@class='font-weight-500 col-sm-7']");
    By activeBranch = By.xpath(".//div[contains (@class, 'crystrapinput__wrapper mb-0 ')]");
    By disableBranch = By.xpath(".//div[@class='crystrapinput__wrapper mb-0 crystrapinput__wrapper--disable']");
    By inputQuantityBranch = By.xpath(".//div//input[@class='form-control cur-input cur-input--non-unit']");

    /**       ------------------ UNIT --------------           ***/
    By inputSearch = By.xpath("//input[@id='input-search']");
    By noResultMessage = By.xpath("//div[@id='search-list']//p[@class='no-result p-2 text-center']");
    By searchItem = By.xpath("//div[@id='search-list']//div[@class='search-item gsa-hover--gray cursor--pointer']");
    By addButton = By.xpath("//span[@class='icon_add_unit']");
    By errorMessage = By.xpath("//div[@class='invalid-check']");

    /**       ------------------ Convert Unit --------------           ***/
    By conversionUnitCheckbox = By.xpath("//label[contains(@class, 'uik-checkbox__wrapper')]//input[@name='conversionUnitCheckbox']/../div[@class='uik-checkbox__label green']");
    By unitInput = By.xpath("//input[contains(@id, 'unit-')]"); // Many elements -> need index
    By quantityInput = By.xpath("//input[contains(@id, 'quantity-')]"); // Many elements -> need index
    By noResultMessageInConversationUnit = By.xpath("//div[@class='search-list']//p[@class='no-result p-2 text-center']");
    By searchItemInConversation = By.xpath("//div[@class='search-list']//div[@class='search-item gsa-hover--gray cursor--pointer']");
    By setupButton = By.xpath("//button[contains(@class, 'gs-button__green') and contains(@class, 'gs-button--undefined')]");
    By chooseUnitButton = By.xpath("//button[contains(@class, 'gs-button__green--outline') and contains(@class, 'gs-button--undefined')]");
    By saveButton = By.xpath("//button[contains(@class, 'gs-button__green') and contains(@class, 'gs-button--undefined') and not(contains(@class, 'gs-button__green--outline'))]");
    By successText = By.xpath("//div[@class = 'p-3 bg-light-white']//p[contains(@class, 'font-italic font-size-12px text-secondary')]");

    /**       ------------------ Delete products: Dashboard -> Products -> All Products --------------           ***/
    By chooseAction = By.xpath("//div[ contains (@class, 'selected-product')]/div[contains(@class, ' gs-dropdown-action')]/span[@class = 'gs-fake-link ']");
    By actionDeleteProduct = By.xpath("//div[@class = ' actions expand']/div[2]");
    By deleteButton = By.xpath("//button[@class='gs-button  gs-button__red gs-button--undefined ']");
    /**   ----- Show as listing product on store front    ---------   **/
    By enableListing = By.id("enabledListing"); //ticked: checked + value="true"
    By showListingCheckboxLocator = By.xpath("//div[contains(@class, 'form-group')]//input[@type='checkbox' and contains(@class, 'uik-checkbox__checkbox')]");
    By showListingLabelLocator = By.xpath("//div[contains(@class, 'form-group')]//label[contains(@class, 'uik-checkbox__wrapper')]");

    /**      ----------------  VAT  ---------- **/
    By vatDropdownButtonLocator = By.xpath("//div[contains(@class, 'uik-select__wrapper')]//button[contains(@class, 'uik-select__valueRendered')]");
    By vatCurrentValueLocator = By.xpath("//div[contains(@class, 'uik-select__wrapper')]//div[contains(@class, 'uik-select__valueWrapper')]");
    By dropdownVat =  By.xpath("//div[contains(@class, 'uik-select__optionList')]");
    //    String vatOptionLocator = "//button[contains(@class, 'uik-select__option')]//div[contains(@class, 'uik-select__label') and contains(text(), '%s')]/ancestor::button[contains(@class, 'uik-select__option')]";
    String vatOptionLocator = "//button[contains(@class, 'uik-select__option')]//div[contains(@class, 'uik-select__label') and contains(text(), '%s')"; //tax_10%

    /** ---------- INPUT IMEI FOR EACH BRANCH ----------- **/
    By branchDropdown = By.cssSelector("div.gs-dropdown-multiple-select__drop-header button.uik-btn__base.uik-select__valueRendered");
    By branchDropdownList = By.cssSelector("div.uik-menuDrop__list.uik-menuDrop__bottomLeft");
    By branchOptions = By.cssSelector("button.uik-btn__base.uik-dropdown-item__wrapper");
    By imeiInputs = By.cssSelector("input[name='serial']");
    By addedIMEIs = By.cssSelector("div.code div.content p");
    By duplicateIMEIError = By.cssSelector("div.errorExist");
    By saveButton2 = By.cssSelector("button.gs-button.gs-button__green");
    By cancelButton = By.cssSelector("button.gs-button.gs-button__white");
    By modelClose = By.cssSelector("div.modal-dialog.managed-inventory-modal");
    By popupInventoryModal = By.cssSelector("div.modal-dialog.managed-inventory-modal");
    By branchListStock = By.cssSelector("div.branch-list-stock__wrapper__row");
    By labelBranch = By.cssSelector("div.font-weight-500");
    By branchInput = By.cssSelector("input.form-control.cur-input.cur-input--non-unit");
    By branchLabel = By.cssSelector("span.gs-dropdown-multiple-select__opt-label");
    By checkboxBranch = By.cssSelector("input.uik-checkbox__checkbox");
    By branchCell = By.xpath("//table//th[text()='%s']/following-sibling::td");
    By divOfCode = By.xpath("./ancestor::form//div[@class='code']");

    /**  -----------Delete product by name -------- **/
    String productRow ="//td[contains(@class, 'product-table__name')]/b[text()='%s']/ancestor::tr";
    By checkboxLocator = By.xpath(".//input[@type='checkbox' and contains(@name, 'checkbox_')]");
    By labelLocator = By.xpath(".//input[@type='checkbox' and contains(@name, 'checkbox_')]/ancestor::label[contains(@class, 'uik-checkbox__wrapper')]");

    /** ------------- Set Flatform -----------**/
    String checkbox = "//input[@name='%s' and @type='checkbox']";
    String labelFlatform = "//input[@name='%s' and @type='checkbox']/ancestor::label[@class='uik-checkbox__wrapper']";

    /** ------------- Verify modal is closed after saving -----------**/
    By modalClose = By.cssSelector("div.modal-dialog.managed-inventory-modal");
    
    //--------------------****************-------------------------- Functions --------------------****************--------------------------

    public ProductPage2 (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void navigateToProducts (){
        logger.info("Starting navigateToProducts process...");
        try {
            System.out.println("Start function navigateToProducts");
            utility.clickElement(driver, elementMenuProducts);
            System.out.println("Click navigateToProducts successfully");

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
            System.out.println("Click navigateToAllProducts successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("navigateToProducts failed: " + e);
        }
    }


    public void verifyNavigateToAllProducts (){
        try {
            System.out.println("Start function verifyNavigateToAllProducts");
            WebElement menuProducts = utility.waitForVisibility(driver, elementProductManagerment, 10);

            if (menuProducts.getText().equals("Product management")){
                System.out.println("verifyNavigateToAllProducts successfully");
            }

        } catch (TimeoutException e) {
            throw new TimeoutException("navigateToProducts failed: " + e);
        }
    }


    public void navigateToInventory (){
        logger.info("Starting navigateToInventory process...");
        try {
            System.out.println("Start function navigateToInventory");
            utility.clickElement(driver, elementSubInventoryHistory);
            System.out.println("navigateToInventory successfully");

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


    public void clickCreateProduct (){
        logger.info("Starting clickCreateProduct process...");
        try {
            System.out.println("Start function clickCreateProduct");
            utility.clickElement(driver, createProduct);
            System.out.println("Click createProductButton successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("clickCreateProduct failed: " + e);
        }
    }


    public void inputProductName (String nameProduct){
        logger.info("Starting inputProductName process...");
        try {
            System.out.println("Start function inputProductName");
            utility.setInputValue(driver, productName, nameProduct);
            System.out.println("Input user product name successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("click inputProductName failed: " + e);
        }
    }


    public void inputDescription (String contentDescription) {
        logger.info("Starting inputDescription process...");
        try {
            System.out.println("Start function inputDescription");
            utility.setInputValue(driver, description, contentDescription);
            System.out.println("Input inputDescription successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("inputDescription failed: " + e);
        }
    }


    // TODO: C:\Users\admin\Downloads\20250215_123821.jpg | "C:\\Users\\hoait\\Desktop\\R.jfif"  | improve drag drop select image
    public void selectImage () {
        logger.info("Starting selectImage process...");
        try {
            System.out.println("Start function selectImage");
            utility.scrollToElement(driver, imageDropZone);
            logger.info("utility.scrollToElement(driver, clickSelectImage);");
            WebElement fileNameInput = utility.waitForPresence(driver, fileName, 10);
            fileNameInput.sendKeys("C:\\Users\\admin\\Downloads\\20250215_123821.jpg");
            System.out.println("selectImage successfully");

        } catch (TimeoutException e) {
            System.out.println("Check the image again; does it exist on your laptop");
            throw new TimeoutException("selectImage failed: " + e);
        }
    }


    public void inputListingPriceNoVariants (double price) {
        logger.info("Starting inputListingPriceNoVariants/ Giá niêm yết process...");
        try {
            System.out.println("Start function inputListingPriceNoVariants");
            utility.scrollToElement(driver, listingPrice);
            utility.setInputValue(driver, listingPrice, String.valueOf(price));
            System.out.println("inputListingPriceNoVariants successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("inputListingPriceNoVariants failed: " + e);
        }
    }


    public void inputSellingPriceNoVariants (double price) {
        logger.info("Starting inputSellingPriceNoVariants/ selling price process...");
        try {
            System.out.println("Start function inputSellingPriceNoVariants");
            utility.setInputValue(driver, sellingPrice, String.valueOf(price));
            System.out.println("inputSellingPriceNoVariants successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("inputSellingPriceNoVariants failed: " + e);
        }
    }


    public void inputCostPriceNoVariants (double price) {
        logger.info("Starting inputCostPriceNoVariants/ giá gốc process...");
        try {
            System.out.println("Start function inputCostPriceNoVariants");
            utility.setInputValue(driver, costPrice1, String.valueOf(price));
            System.out.println("inputCostPriceNoVariants successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("inputCostPriceNoVariants failed: " + e);
        }
    }


    public void inputVatNoVariants (String vatInput) {
        logger.info("Starting inputVatNoVariants/ VAT...");
        try {
            System.out.println("Start function inputVatNoVariants");
            utility.setInputValue(driver, vat, String.valueOf(vatInput));
            System.out.println("inputVat NoVariants successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("inputCostPriceNoVariants failed: " + e);
        }
    }


    public void addVariants () {
        logger.info("Starting click addVariants process...");
        try {
            System.out.println("Start function addVariants");
            utility.clickElement(driver, variant);
            System.out.println("inputVariantName successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("addVariants failed: " + e);
        }
    }


    //    TODO thêm - hiện tại viết cơ bản cần ktra lại
    public void inputVariantName (String variantName) {
        logger.info("Starting inputVariantName process...");
        try {
            System.out.println("Start function inputVariantName");
            List<WebElement> nameVariantElements = utility.visibilityOfAllElementsLocatedBy(driver, nameVariant, 10 );
            //   Get final element TODO: xem lai
            WebElement latestNameVariantElement = nameVariantElements.get(nameVariantElements.size() - 1);
            latestNameVariantElement.click();
            latestNameVariantElement.clear();
            latestNameVariantElement.sendKeys(variantName);
            utility.clickElement(driver, nameVariantText);
            System.out.println("inputVariantNames successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("inputVariantName failed: " + e);
        }
    }


    public void inputVariationValue (String[] variationValue) {
        logger.info("Starting inputVariationValue process...");
        try {
            System.out.println("Start function inputVariationValue");
            List<WebElement> variantValueElements = utility.visibilityOfAllElementsLocatedBy(driver, typeVariants, 10);
            WebElement latestVariantValueElement = variantValueElements.get(variantValueElements.size() - 1);
            latestVariantValueElement.clear();
            //TODO clickElement1 xem lai
            utility.clickElement(driver, latestVariantValueElement);

            for (int i = 0; i < variationValue.length; i++) {
                latestVariantValueElement.sendKeys(variationValue[i]);
                utility.pressEnter(driver);
                System.out.println("inputVariationValue successfully");
            }

        } catch (TimeoutException e) {
            throw new TimeoutException("inputVariationValue failed: " + e);
        }
    }


    public void deleteVariation () {
        logger.info("Starting deleteVariation process...");
        try {
            System.out.println("Start function deleteVariation");
            List<WebElement> elementDeleteVariants = utility.visibilityOfAllElementsLocatedBy(driver, iconDeleteVariant, 10);

            for (int i = 0; i < elementDeleteVariants.size(); i++) {
                utility.clickElement(driver, elementDeleteVariants.get(i));
            }
            System.out.println("Function deleteVariation run successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("deleteVariation failed: " + e);
        }
    }


    //TODO CẦN THÊM ĐIỀU KIỆN - xem lại: hình như có viết 1 function khác
    public void inputUnit (String nameUnitInput) {
        logger.info("Starting inputUnit process...");
        try {
            System.out.println("Start function inputUnit");
            utility.setInputValue(driver, nameUnit, nameUnitInput);
            utility.clickElement(driver, addUnit);

        } catch (TimeoutException e) {
            throw new TimeoutException("inputUnit failed: " + e);
        }
    }


    //    Click into manageInventoryByProduct -> to choose product mange by Lot/ IMEI
    public void manageInventoryByProduct (){
        logger.info("Starting manageInventoryByProduct process...");
        try {
            System.out.println("Start function manageInventoryByProduct");
            utility.clickElement(driver, manageInventory);
            System.out.println("Function manageInventoryByProduct run successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("manageInventoryByProduct failed: " + e);
        }
    }


    //  Manage normal product
    public void manageStockNormalProduct () {
        logger.info("Starting manageStockNormalProduct process...");
        try {
            System.out.println("Start function manageStockNormalProduct");
            utility.clickElement(driver, productOption);
            System.out.println("Function manageStockNormalProduct run successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("manageStockNormalProduct failed: " + e);
        }
    }


    public void verifyManageNormalStock () {
        logger.info("Starting verifyManageNormalStock process...");
        try {
            System.out.println("Start function verifyManageNormalStock");
            WebElement verifyNormalProductOption = utility.waitForPresence(driver, lotDateAvailable, 10);
            System.out.println( "Value: verifyNormalProductOption is: " + verifyNormalProductOption.getAttribute("value"));

            if  ("false".equals(verifyNormalProductOption.getAttribute("value"))){
                System.out.println("verifyManageNormalStock successfully");

            } else {
                System.out.println("verifyManageNormalStock failed, please check again");
            }

        } catch (TimeoutException e) {
            throw new TimeoutException("verifyManageNormalStock failed: " + e);
        }
    }


    //   Main function: Mange normal product
    public void manageNormalProduct (){
        logger.info("Starting manageNormalProduct process...");
        try {
            System.out.println("Start function manageNormalProduct");
            manageInventoryByProduct();
            manageStockNormalProduct();
            verifyManageNormalStock ();
            System.out.println("Function manageNormalProduct successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("manageNormalProduct failed: " + e);
        }
    }

    public void manageStockByLotDate () {
        logger.info("Starting manageStockByLotDate process...");
        try {
            System.out.println("Start function manageStockByLotDate");
            utility.clickElement(driver, productOption);
            System.out.println("Click lotManageOption Successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("manageStockByLotDate failed: " + e);
        }
    }


    public void verifyManageStockByLotDate () {
        logger.info("Starting clickManageStockByLotDate process...");
        try {
            System.out.println("Start function clickManageStockByLotDate");
            WebElement verifyLotManageOption = utility.waitForPresence(driver, lotDateAvailable, 10); // Return true/ false

            if ("false".equals(verifyLotManageOption.getAttribute("value"))  ){
                System.out.println(verifyLotManageOption.getText() + "is not checked");
                utility.clickElement(driver, verifyLotManageOption);

            } else {
                System.out.println(verifyLotManageOption.getText() + "is checked so do not click again");
            }

        } catch (TimeoutException e) {
            throw new TimeoutException("clickManageStockByLotDate failed: " + e);
        }
    }


    public void clickExcludeExpiredQuantity (boolean click) {
        //click is true: wanna to check button ExcludeExpiredQuantity
        //click is false: wanna to Uncheck button ExcludeExpiredQuantity
        logger.info("Starting clickExcludeExpiredQuantity process...");
        try {
            System.out.println("Start function clickExcludeExpiredQuantity");
            WebElement expiredQuantityElement = utility.waitForPresence(driver, expiredQuality, 10);
            //  If user want to click into 'ExcludeExpiredQuantity' -> click = true

            if (click){
                System.out.println("User want to check clickExcludeExpiredQuantity");

                if ("false".equals(expiredQuantityElement.getAttribute("value"))  ){
                    System.out.println("Exclude expired quantity from remaining stock is not checked");
                    utility.clickElement(driver, expiredQuantityElement);
                }

                System.out.println(" Exclude expired quantity from remaining stock is checked so do not click again");

            } else {
                System.out.println("User does NOT want to check clickExcludeExpiredQuantity");

                if ("true".equals(expiredQuantityElement.getAttribute("value"))  ){
                    System.out.println("Exclude expired quantity from remaining stock is checked");
                    utility.clickElement(driver, expiredQuantityElement);
                }

                System.out.println(" Exclude expired quantity from remaining stock is Unchecked so do not click again");
            }

        } catch (TimeoutException e) {
            throw new TimeoutException("clickExcludeExpiredQuantity failed: " + e);
        }
    }


    //  Main function: Mange product Lot-date
    public void manageLotProduct (boolean click){
        logger.info("Starting manageLotProduct process...");
        try {
            System.out.println("Start function manageLotProduct");
            manageInventoryByProduct();
            manageStockByLotDate();
            verifyManageStockByLotDate();
            clickExcludeExpiredQuantity(click);
            System.out.println("Run manageLotProduct Successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("manageLotProduct failed: " + e);
        }
    }

    public void manageInventoryByImei () {
        logger.info("Starting manageInventoryByImei process...");
        try {
            System.out.println("Start function manageInventoryByImei");
            utility.clickElement(driver, imeiSerialOption);
            System.out.println("manageInventoryByImei successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("manageInventoryByImei failed: " + e);
        }
    }


    public boolean verifyClickManageInventoryByImei () {
        logger.info("Starting verifyClickManageInventoryByImei process...");
        try {
            System.out.println("Start function verifyClickManageInventoryByImei");
            WebElement imeiOptionElement = utility.waitForVisibility(driver, verifyImeiOption, 10);
            System.out.println("Status imeiOptionElement.isDisplayed() is: " + imeiOptionElement.isDisplayed());
            return imeiOptionElement.isDisplayed(); // if found element -> display is true

        } catch (TimeoutException e) {
            throw new TimeoutException("verifyClickManageInventoryByImei failed: " + e);
        }
    }


    //  Mange product Lot-Date
    public void manageIMEIProduct (){
        logger.info("Starting manageIMEIProduct process...");
        try {
            System.out.println("Start function manageIMEIProduct");
            manageInventoryByProduct();
            manageInventoryByImei();
            verifyClickManageInventoryByImei();
            System.out.println("Function manageIMEIProduct successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("manageIMEIProduct failed: " + e);
        }
    }


    //  TODO need to be enhance verify on SSR
    public void displayIfOutOfStock(boolean click) {
        logger.info("Starting displayIfOutOfStock process...");
        try {
            System.out.println("Start function displayIfOutOfStock");
            WebElement displayIfOutOfStockElement = utility.waitForVisibility(driver, displayOutOfStock, 10);

            if (click){
                System.out.println("User wants to check displayIfOutOfStock");

                if ("false".equals(displayIfOutOfStockElement.getAttribute("value"))  ){
                    System.out.println("displayIfOutOfStock is not checked");
                    utility.clickElement(driver, displayIfOutOfStockElement);

                } else {System.out.println(" displayIfOutOfStock is checked");}

            } else {
                System.out.println("User does NOT want to check displayIfOutOfStock");

                if ("true".equals(displayIfOutOfStockElement.getAttribute("value"))  ){
                    System.out.println("displayIfOutOfStock is checked");
                    utility.clickElement(driver, displayIfOutOfStockElement);

                } else {System.out.println(" displayIfOutOfStock is Unchecked so do not click again");}
            }

        } catch (TimeoutException e) {
            throw new TimeoutException("displayIfOutOfStock failed: " + e);
        }
    }


    //    TODO need to be enhance verify on SSR
    public void hideRemainingStockOnOnlineStore (boolean click) {
        logger.info("Starting hideRemainingStockOnOnlineStore process...");
        try {
            System.out.println("Start function hideRemainingStockOnOnlineStore");
            WebElement hideRemainingStockElement = utility.waitForVisibility(driver, hideRemainingStock, 10);

            if (click){
                System.out.println("User wants to check hideRemainingStockOnOnlineStore");

                if ("false".equals(hideRemainingStockElement.getAttribute("value"))  ){
                    System.out.println("hideRemainingStockElement is not checked");
                    utility.clickElement(driver, hideRemainingStockElement);
                } else { System.out.println(" hideRemainingStockElement is checked"); }

            } else {
                System.out.println("User does NOT want to check hideRemainingStockOnOnlineStore");

                if ("true".equals(hideRemainingStockElement.getAttribute("value"))  ){
                    System.out.println("hideRemainingStockOnOnlineStore is checked");
                    utility.clickElement(driver, hideRemainingStockElement);

                } else {System.out.println(" hideRemainingStockOnOnlineStore is Unchecked so do not click again");}
            }

        } catch (TimeoutException e) {
            throw new TimeoutException("hideRemainingStockOnOnlineStore failed: " + e);
        }
    }


    public void clickSaveButton () {
        logger.info("Starting clickSaveButton process...");
        try {
            System.out.println("Start function clickSaveButton");
            utility.scrollToElement(driver, buttonSave);
            System.out.println("utility.scrollToElement(driver, saveButtonElement)");
            utility.clickElement(driver, buttonSave);
            System.out.println("function clickSaveButton successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("clickSaveButton failed: " + e);
        }
    }


    public void clickCancelButton () {
        logger.info("Starting clickCancelButton process...");
        try {
            System.out.println("Start function clickCancelButton");
            utility.clickElement(driver, buttonCancel);
            System.out.println("clickCancelButton successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("clickCancelButton failed: " + e);
        }
    }


    public boolean verifyTitleVideoShow (){
        try {
            System.out.println("Start function verifyTitleVideoShow");
            WebElement titleVideo = utility.waitForVisibility(driver, titleCreateProduct, 10);
            System.out.println("titleVideo.isDisplayed() is: " + titleVideo.isDisplayed());
            return titleVideo.isDisplayed();
        } catch (TimeoutException e){
            return false;
        }
    }


    public void closePopupVideo () {
        try {
            System.out.println("Start function closePopupVideo");
            utility.clickElement(driver, closeVideoIcon);
            System.out.println("closePopupVideo successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("closePopupVideo failed: " + e);
        }
    }

    public void verifyCreateProduct() {
        logger.info("Starting verifyCreateProduct process...");
        try {
            System.out.println("Start function verifyCreateProduct");
            WebElement popupCreateProductElement = utility.waitForClickable(driver, popupCreateProduct, 10);
            System.out.println("IInformation after create product is:" + popupCreateProductElement.getText());

            // TODO enhance do not use Text
            if (popupCreateProductElement.getText().equals("Sản phẩm được tạo thành công!")) {
                System.out.println("verifyManageNormalStock successfully");
            } else {
                System.out.println("verifyCreateProduct failed, please check again");
            }

        } catch (TimeoutException e) {
            throw new TimeoutException("verifyCreateProduct failed: " + e);
        }
    }


    //TODO
    public void inputQuantityApplyAll(String quantityProduct) {
        logger.info("Starting inputQuantityApplyAll/ giá gốc process...");
        try {
            System.out.println("Start function inputQuantityApplyAll");
            utility.setInputValue(driver, inputQuntity, quantityProduct);
            System.out.println("inputQuantityApplyAll successfully");

            if (Long.parseLong(quantityProduct) >= 500000000L){
                System.out.println("Error: Value " + quantityProduct + " Over 10,000,000,000!");
                // Verify issue after input quantity
                verifyInputErrorShowed(utility.waitForVisibility(driver, inputQuntity , 10));
            }
            clickApplyAll();
            System.out.println("function inputQuantityApplyAll run successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("inputCostPriceNoVariants failed: " + e);
        }
    }


    public void clickApplyAll() {
        logger.info("Starting clickApplyAll process...");
        try {
            System.out.println("Start function clickApplyAll");
            utility.clickElement(driver, applyAll);
            System.out.println("clickApplyAll successfully");

        } catch (TimeoutException e) {
            throw new TimeoutException("clickApplyAll failed: " + e);
        }
    }


    public void addVariantGroups(String[] variantNames, String[][] variationValues) {
        logger.info("Starting addVariantGroups process...");
        System.out.println("Start function addVariantGroups");
        System.out.println("Start check length of array variationValues");
        if (variantNames.length != variationValues.length) {
            throw new IllegalArgumentException("Quantity variation name is not same as quantity variantion values!");
        }
        for (int i = 0; i < variantNames.length; i++) {
            // Click button "Add variant"
            addVariants();
            //Input name variant
            inputVariantName(variantNames[i]);
            //Input variant values (one or more value)
            inputVariationValue(variationValues[i]);
        }
    }


    /***  ------------------ With Variants ---------------   ***/
    public void fillVariantTable (Map<String, VariantData> priceMap, VariantData defaultData) {
        List<WebElement> rows = driver.findElements(rowOfVariant);
        System.out.println("Sum rows of variant: " + rows.size());

        for (WebElement row : rows) {
            // Get text column "Color"
            String colorText = row.findElement(By.xpath(".//td[3]")).getText().trim();
            System.out.println("row: " + row + "color: " + colorText.toLowerCase());
            // Get text column "Size"
            String sizeText = row.findElement(By.xpath(".//td[4]")).getText().trim();
            System.out.println("row: " + row + "sizeText: " + sizeText.toLowerCase());
            // Combine into "color-size" key, for example "xanh-s"
            String key = colorText.toLowerCase() + "-" + sizeText.toLowerCase();
            System.out.println("key is: " + key);

            // Find VariantData From Map
            VariantData data = priceMap.get(key) != null ? priceMap.get(key) : defaultData;
            System.out.println("VariantData data: " + data.getOrgPrice() + ", " + data.getDiscountPrice() + ", " +
                    data.getCostPrice() + ", " + data.getStock());

            WebElement giaNiemYet = row.findElement(orgPrice);
            WebElement soLuong    = row.findElement(stock);
            utility.clickElement(driver, giaNiemYet);
            // Đợi popup hiển thị và tìm ô input trong popup
//            WebElement popupOrgPriceInput = utility.waitForVisibility(driver, By.xpath("//div[contains(@class, 'modal-content')]//input[contains(@id, 'orgPrice')]"), 10);
            WebElement testWay1 = utility.waitForVisibility(driver, 
                    By.xpath("//*[contains(@class, 'modal-dialog product-variation-price-editor-modal')]//*[*/*/*[contains(@id, 'orgPrice')]]/input"), 10);
//            TODO   ---- thêm to test cách 1

            String orgPriceValue = data.getOrgPrice();
            String discountPriceValue = data.getDiscountPrice();
            String costPriceValue = data.getCostPrice();

            System.out.println("Values to be set - orgPrice: " + orgPriceValue + ", discountPrice: " + discountPriceValue + ", costPrice: " + costPriceValue);
            utility.setInputValue(driver, applyAllInput, data.getOrgPrice());
            System.out.println("sendKeys(data.getOrgPrice()) successfully in popup");
            utility.clickElement(driver, applyAllButton);

            selectSellingPriceOption();

            utility.setInputValue(driver, applyAllInput, data.getDiscountPrice());
            utility.clickElement(driver, applyAllButton);

            selectCostPriceOption();

            utility.setInputValue(driver, applyAllInput, data.getCostPrice());
            utility.clickElement(driver, applyAllButton);

            String actualApplyAllValue = (utility.waitForVisibility(driver,applyAllInput, 10).getAttribute("value"));
            System.out.println("Actual applyAllValue after setting: " + actualApplyAllValue);

            utility.clickElement(driver, updateButton);

            System.out.println("updateButtonElement.click() successfully");
            System.out.println("Đã nhập giá cho " + colorText + " - " + sizeText);

            utility.clickElement(driver, soLuong);
            utility.setInputValue(driver, stockInput, data.getStock());
            utility.clickElement(driver, updateButton);

            System.out.println("stockInputElement.click() successfully");
            System.out.println("Đã nhập giá cho " + colorText + " - " + sizeText);
        }
    }


    public void selectSellingPriceOption () {
        try {
            System.out.println("Start function selectOptionInUpdatePrice");
            utility.clickElement(driver, defaultValue);
            System.out.println("selectSellingPriceOption successfully");
            utility.clickElement(driver, sellingPriceOption);
        } catch (TimeoutException e) {
            throw new TimeoutException("selectOptionInUpdatePrice failed: " + e);
        }
    }


    public void selectCostPriceOption (){
        try {
            System.out.println("Start function selectCostPriceOption");
            utility.clickElement(driver, defaultValue);
            System.out.println("selectCostPriceOption successfully");
            utility.clickElement(driver, costPriceOption);

        }catch (TimeoutException e){
            throw new TimeoutException("selectCostPriceOption failed: " + e);
        }
    }


    // Enter the same quantity for all branches -> just enter the quantity
    public void setStockForAllBranches(int quantity) {
        logger.info("Starting setStockForAllBranches process...");
        try {
            System.out.println("Start function setStockForAllBranches with quantity: " + quantity);
            List<WebElement> branchRows = utility.visibilityOfAllElementsLocatedBy(driver, inputQuantityBranch2, 10);

            for (WebElement branchRow : branchRows) {
                try {
                    System.out.println("Start click element " + branchRow + " using Selenium");
                    //  Ko biết ts ko cho nó click - clear  đc -))) :D
                    branchRow.sendKeys(String.valueOf(quantity));
                    System.out.println("Start click element" + branchRow + " using Selenium Successfully");

                }catch (Exception e){
                    System.out.println("Start click element using JavaScript: " + branchRow + " ");
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].value = arguments[1]; " +
                                    "arguments[0].dispatchEvent(new Event('change'));",
                            branchRow, String.valueOf(quantity)
                    );
                    throw new RuntimeException("setStockForAllBranches click JS: " + e.getMessage());
                }
            } System.out.println("Input quantity for branch successfuly");

        } catch (Exception e) {
            throw new RuntimeException("setStockForAllBranches failed: " + e.getMessage());
        }
    }


    //used for case: 1 array of values, input value first
    public void setStockForAllBranches3(int[] quantity) {
        logger.info("Starting setStockForAllBranches3 process...");
        try {
            for (int i =0; i < quantity.length; i++) {
                System.out.println("Start function setStockForAllBranches with Arg quantity: " + quantity[i]);
//                Thread.sleep(1500);
                List<WebElement> listBranchRows = utility.visibilityOfAllElementsLocatedBy(driver, wrapperRowBranch, 10);

                for (WebElement branchRow : listBranchRows) {
                    System.out.println("branchRow " + branchRow);
                    WebElement branchNameElement = branchRow.findElement(nameBranch);
                    String branchName = branchNameElement.getText().trim();
                    System.out.println("Chi nhánh: " + branchName);

                    WebElement activeBranchElement = branchRow.findElement(activeBranch);
                    System.out.println("activeBranchElement: " + activeBranchElement);

                    System.out.println("Check branch can input quantity or not : " + activeBranchElement.getAttribute("class").contains("crystrapinput__wrapper--disable"));
                    boolean isDisabled = activeBranchElement.getAttribute("class").contains("crystrapinput__wrapper--disable");
                    System.out.println("isDisabled: " + isDisabled);

                    if (isDisabled) {
                        System.out.println("Input of branch " + branchName + " disable, Can not input quantity");
                        continue;
                    }

                    WebElement inputQuantityBranchElement = activeBranchElement.findElement(inputQuantityBranch);
                    inputQuantityBranchElement.sendKeys(String.valueOf(quantity[i]));
                    System.out.println("Number for branch" + branchName + ": " + quantity[i]);
                    System.out.println("Input quantity for branch successfully");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("setStockForAllBranches failed: " + e.getMessage());
        }
    }


    public void setStockForAllBranches4 (Map<String, String> stockMap, String defaultQuantity)  {
        logger.info("Starting setStockForBranches process...");
        try {
            System.out.println("Start function setStockForBranches with default quantity: " + defaultQuantity);
            List<WebElement> listBranchRows = utility.visibilityOfAllElementsLocatedBy(driver, wrapperRowBranch, 10);
            System.out.println("Sum branch is: " + listBranchRows.size());

            for (int i = 0; i < listBranchRows.size(); i++) {
                List<WebElement> refreshedBranchRows = utility.visibilityOfAllElementsLocatedBy(driver, wrapperRowBranch, 10);
                WebElement branchRow = refreshedBranchRows.get(i);
                WebElement branchNameElement = branchRow.findElement(nameBranch);
                String branchName = branchNameElement.getText().trim();
                System.out.println("Branch: " + branchName);
                WebElement activeBranchElement = branchRow.findElement(activeBranch);

                // Check branch can input quantity or not
                boolean isDisabled = activeBranchElement.getAttribute("class").contains("crystrapinput__wrapper--disable");

                if (isDisabled) {
                    System.out.println("Input of branch " + branchName + " disable, Can not input quantity");
                    continue;
                }

                // input quantity
                WebElement stockInput = branchRow.findElement(By.xpath(".//input[contains(@class, 'form-control')]"));
                // Get quantity from stockMap, if not -> use default
                String quantity = stockMap.getOrDefault(branchName, defaultQuantity);
                System.out.println("Quantity of branch " + branchName + ": " + quantity);

                if (quantity == null || quantity.trim().isEmpty()) {
                    System.out.println("Invalid quantity for branch " + branchName + ", ignore");
                    continue;
                }
                stockInput.sendKeys(String.valueOf(quantity));
                System.out.println("Inputted quantity " + quantity + " for branch " + branchName);
            }

        } catch (Exception e) {
            throw new RuntimeException("setStockForBranches failed: " + e.getMessage());
        }
    }


    public boolean hasInputError (WebElement inputElement) {
        //  Use for input quantity, price over maximum
        logger.info("Starting hasInputError process...");
        try {
            System.out.println("Start function hasInputError");
            WebElement parentElement = inputElement.findElement(By.xpath(".."));
            String parentClass = parentElement.getAttribute("class");
            return parentClass.contains("cur-input--error"); // true or false
        } catch (TimeoutException e) {
            throw new TimeoutException("hasInputError failed: " + e);
        }
    }


    public void verifyInputErrorShowed (WebElement inputElement) {
        //  Use for input quantity, price over maximum
        logger.info("Starting verifyInputError process...");
        try {
            System.out.println("Start function verifyInputError");
            if (hasInputError(inputElement)){ //true
                System.out.println("Input quantity showed error");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException("Input error is not showed - verifyInputErrorShowed failed: " + e);
        }
    }


    public void verifyInputErrorNotShowed (WebElement inputElement) {
        //  Use for input quantity, price over maximum
        logger.info("Starting verifyInputErrorNotShowed process...");
        try {
            System.out.println("Start function verifyInputErrorNotShowed");
            if (!hasInputError(inputElement)){ //false
                System.out.println("Input quantity correctly, does not have error");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException("Input quantity showed error, please check again: " + e);
        }
    }


    /**   UNIT   **/
    public void addUnit(String unit) {
        logger.info("Starting addUnit with unit: " + unit);
        try {
            utility.setInputValue(driver, inputSearch, unit);
            System.out.println("Đã nhập đơn vị: " + unit);

            // Check status dropdown
            boolean isNoResultVisible = utility.isElementVisible(driver, noResultMessage, 5);
            boolean isUnitOptionVisible = utility.isElementVisible(driver,searchItem, 5);

            if (isNoResultVisible) {
                // Trường hợp 1: Đơn vị chưa tồn tại -> click "Thêm"
                System.out.println("Đơn vị '" + unit + "' chưa tồn tại, tiến hành thêm mới...");
                utility.clickElement(driver, addButton);
                System.out.println("Thêm đơn vị '" + unit + "' thành công!");

            } else if (isUnitOptionVisible) {
                // Trường hợp 2: Đơn vị đã tồn tại -> chọn option
                WebElement unitOption = wait.until(ExpectedConditions.elementToBeClickable(searchItem));
                String optionText = unitOption.getText().trim();

                if (optionText.equalsIgnoreCase(unit)) {
                    System.out.println("Đơn vị '" + unit + "' đã tồn tại, tiến hành chọn...");
                    utility.clickElement(driver, unitOption);
                    System.out.println("Chọn đơn vị '" + unit + "' thành công!");

                } else {
                    throw new RuntimeException("Đơn vị '" + unit + "' không khớp với option hiển thị: " + optionText);
                }

            } else {
                throw new RuntimeException("Không tìm thấy dropdown kết quả sau khi nhập đơn vị: " + unit);
            }

            // Kiểm tra lỗi "Đơn vị đã có" (nếu có)
            if (utility.isElementVisible(driver,errorMessage, 1)) {
                throw new RuntimeException("LỖI: Đơn vị '" + unit + "' đã tồn tại, không thể thêm mới!");
            }

        } catch (Exception e) {
            throw new RuntimeException("addUnit failed: " + e.getMessage());
        }
    }


    /***   ------------------   Conversation UNIT  --------------*/
    public void addConversionUnit(String unit, String quantity) {
        logger.info("Starting addConversionUnit with unit: " + unit + ", quantity: " + quantity);
        try {

            utility.clickElement(driver, conversionUnitCheckbox);
            System.out.println("Clicked 'Add Unit' ");
            utility.clickElement(driver, setupButton);
            System.out.println("Clicked 'Setup'");

            utility.waitForVisibility(driver, chooseUnitButton, 10);
            utility.clickElement(driver, chooseUnitButton);
            System.out.println("Clicked 'Choose Unit'");

            addUnitToRow(0, unit); // Add first row (index 0)
            setQuantityToRow(0, quantity);
            utility.clickElement(driver, saveButton);
            System.out.println("Clicked 'Save'");

            utility.waitForVisibility(driver, successText, 10);
            System.out.println("Setup Add unit successfully!");

        } catch (Exception e) {
            throw new RuntimeException("addConversionUnit failed: " + e.getMessage());
        }
    }


    private void addUnitToRow(int rowIndex, String unit) {
        logger.info("Start addUnitToRow");
        By dynamicUnitInput = By.xpath("//input[@id='unit-" + rowIndex + "']");
        utility.setInputValue(driver, dynamicUnitInput, unit);
        System.out.println("Inputted unit: " + unit);

        boolean isNoResultVisible = utility.isElementVisible(driver, noResultMessageInConversationUnit, 10);
        boolean isUnitOptionVisible = utility.isElementVisible(driver,searchItemInConversation, 10);
        System.out.println("isNoResultVisible: " + isNoResultVisible);
        System.out.println("isUnitOptionVisible: " + isUnitOptionVisible);

        if (isNoResultVisible) {
            utility.clickElement(driver, addButton);
            System.out.println("Added new unit: " + unit);

        } else if (isUnitOptionVisible) {
            WebElement unitOption = utility.waitForClickable(driver, searchItemInConversation, 10);
            System.out.println( "44444444444");
            String optionText = unitOption.getText().trim();

            if (optionText.equalsIgnoreCase(unit)) {
                System.out.println("Unit '" + unit + "' Exist, progress choose...");
                utility.clickElement(driver, unitOption);
                System.out.println("Chosen unit: " + unit);

            } else {
                throw new RuntimeException("Unit '" + unit + "' do not same with option: " + optionText);
            }

        } else {
            throw new RuntimeException("No dropdown results found after entering unit: " + unit);
        }
    }

    private void setQuantityToRow(int rowIndex, String quantity) {
        logger.info("Start setQuantityToRow");
        By dynamicQuantityInput = By.xpath("//input[@id='quantity-" + rowIndex + "']");
        utility.setInputValue(driver, dynamicQuantityInput, quantity);
        System.out.println("Inputted quantity: " + quantity);
    }

    public void verifyConversionUnitSetupSuccess() {
        logger.info("Start Verifying conversion unit setup success");
        try {
            WebElement successElement = utility.waitForVisibility(driver, successText, 10);
            String successTextContent = successElement.getText().trim();
            assertTrue(successTextContent.contains("Thêm 1 đơn vị quy đổi"),
                    "Thiết lập đơn vị quy đổi không thành công, text mong đợi: 'Thêm 1 đơn vị quy đổi', text thực tế: " + successTextContent);
            System.out.println("Verifying conversion unit setup success: " + successTextContent);

        } catch (Exception e) {
            throw new RuntimeException("verifyConversionUnitSetupSuccess failed: " + e.getMessage());
        }
    }


    private void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }


    public void deleteProductByName(String productName) {
        logger.info("Start function deleteProductByName");
        try {
            String formatProductRowXpath = String.format(productRow, productName);
            By productRowLocator = By.xpath(formatProductRowXpath);
            List<WebElement> times = driver.findElements(productRowLocator);

            if (times.isEmpty()) {
                System.out.println("Not find any products same as product name: " + productName + ". Do nothing");
                utility.reloadPage(driver);
                System.out.println("Reload page oki ");
                return; //out the functon if do not fint product name
            }

            System.out.println("Found product: " + productName + ", Size rows: " + times.size());
            for (int i = 0; i < times.size() ; i++) {
                // find check and click
                WebElement checkbox = times.get(i).findElement(checkboxLocator);
                System.out.println("Checkbox show: " + checkbox.isDisplayed());
                System.out.println("Checkbox enabled: " + checkbox.isEnabled());

                WebElement label = times.get(i).findElement(labelLocator);
                System.out.println("Label show: " + label.isDisplayed());
                System.out.println("Label enabled: " + label.isEnabled());

                utility.scrollToElement1(driver, label);
                wait.until(ExpectedConditions.elementToBeClickable(label)).click();
                System.out.println("Checked checkbox of product: " + productName + " at row " + (i + 1));

                // waiting status checkbox update
                wait.until(ExpectedConditions.elementSelectionStateToBe(checkbox, true));
                boolean isCheckedAfterClick = checkbox.isSelected();

                if (isCheckedAfterClick) {
                    System.out.println("Checkbox updated successfully at row: " + (i + 1));

                } else {
                    System.out.println("Checkbox did not choose at row: " + (i + 1) + ", check again UI or try click again.");
                    // Try click again if failed
                    utility.clickElement(driver, label);
                    isCheckedAfterClick = checkbox.isSelected();

                    if (isCheckedAfterClick) {
                        System.out.println(" Checkbox chosen successfully after second click at row: " + (i + 1));

                    } else {
                        System.out.println("The checkbox is still not selected in the row " + (i + 1) + ", try check again.");
                    }
                }
            }

            utility.clickElement(driver, chooseAction);

            utility.clickElement(driver, actionDeleteProduct);

            utility.clickElement(driver, deleteButton);

            utility.reloadPage(driver);

        } catch (Exception e) {
            System.out.println("Error details: " + e.getMessage());
            throw new RuntimeException("Delete product failed: " + e.getMessage());
        }
    }


    public void setPlatformSelection(List<String> platforms, boolean isSelected) {
        logger.info("Start function setPlatformSelection");
        try {
            if (platforms == null || platforms.isEmpty()) {
                System.out.println("List platform is empty, Nothing to do it.");
                return;
            }

            for (int platform = 0; platform < platforms.size(); platform++) {
                String currentPlatform = platforms.get(platform);
                String formatCheckboxXpath = String.format(checkbox, currentPlatform);

                By checkboxLocator = By.xpath(formatCheckboxXpath);
                WebElement checkbox = utility.waitForPresence(driver, checkboxLocator, 10);

                System.out.println("Checkbox show: " + checkbox.isDisplayed());
                System.out.println("Checkbox enabled: " + checkbox.isEnabled());
                boolean isCurrentlyChecked = checkbox.isSelected();
                System.out.println("Platform: " + currentPlatform + ", Actual status: " + (isCurrentlyChecked ? "Selected" : "UnSelected "));

                if (isCurrentlyChecked != isSelected) {
                    String formatLabelXpath = String.format(labelFlatform, currentPlatform);
                    By labelLocator = By.xpath(formatLabelXpath);
                    WebElement label = utility.waitForPresence(driver, labelLocator, 10);
                    System.out.println("Label show: " + label.isDisplayed());
                    System.out.println("Label enabled: " + label.isEnabled());

                    utility.scrollToElement1(driver, label);
                    utility.clickElement(driver, label);
                    System.out.println("Did " + (isSelected ? "select" : "deselect") + " platform: " + currentPlatform);

                    // wait status of checkbox change
                    wait.until(ExpectedConditions.elementSelectionStateToBe(checkbox, isSelected));
                    // check status after click
                    boolean newState = checkbox.isSelected();

                    if (newState == isSelected) {
                        System.out.println("Action successful for platform: " + currentPlatform + ", New status: " + (newState ? "Selected" : "Unselected"));

                    } else {
                        System.out.println("Action failed for platform: " + currentPlatform + ", Actual status: " + (isSelected ? "Selected" : "Unselected") + ", Actual: " + (newState ? "Selected" : "Unselected"));
                    }

                } else {
                    System.out.println("Status platform: " + currentPlatform + " do nothing");
                }
            }

        } catch (Exception e) {
            System.out.println("Error details: " + e.getMessage());
            throw new RuntimeException("Platform Select/Deselect Operation Failed: " + e.getMessage());
        }
    }


    //    On/ Off listing
    public void setShowListingProduct(boolean enable) {
        /**
         *  true is turn On Listing, false is turn Off Listing
         */
        logger.info("Start function setShowListingProduct");
        try {
            System.out.println("Start function setShowListingProduct");
            WebElement checkbox = utility.waitForPresence(driver, showListingCheckboxLocator, 10);
            boolean isCurrentlyChecked = checkbox.isSelected();
            System.out.println("Present status of 'Show as listing product on store front': " + (isCurrentlyChecked ? "On" : "Off"));

            if (isCurrentlyChecked != enable) {
                WebElement label = utility.waitForPresence(driver, showListingLabelLocator, 10);
                System.out.println("Label showed: " + label.isDisplayed());
                System.out.println("Label enabled: " + label.isEnabled());
                utility.clickElement(driver, label);
                System.out.println("Did " + (enable ? "On" : "Off") + " feature 'Show as listing product on store front'");
                // Wait checkbox update status
                wait.until(ExpectedConditions.elementSelectionStateToBe(checkbox, enable));
                // check status after click

                boolean newState = checkbox.isSelected();

                if (newState == enable) {
                    System.out.println("Action successfully, New status: " + (newState ? "On" : "Off"));

                } else {
                    throw new RuntimeException("Action failed, Expected status is: " + (enable ? "On" : "Off") + ", Actual Status: " + (newState ? "On" : "Off"));
                }

            } else {
                System.out.println("Function 'Show as listing product on store front' as status as expected : " + (enable ? "On" : "Off") + ", so do not change.");
            }

        } catch (Exception e) {
            System.out.println("Error is: " + e.getMessage());
            throw new RuntimeException("Action with 'Show as listing product on store front' Failed: " + e.getMessage());
        }
    }


        /*** ------------ VAT   --------- **/
    public void setVAT(String vatValue) {
        logger.info("Start function vatValue");
        try {
            // find button dropdown
            WebElement dropdownButton = utility.waitForPresence(driver, vatDropdownButtonLocator, 10);
            // Check cent value
            WebElement currentValueElement = utility.waitForPresence(driver, vatCurrentValueLocator, 10);
            String currentValue = currentValueElement.getText().trim();
            System.out.println("Actual VAT is: " + currentValue);

            // If value need choose -> chosen -> do not thing
            if (currentValue.equalsIgnoreCase(vatValue)) {
                System.out.println("Value VAT '" + vatValue + "' chosen, no need change.");
                return;
            }

            // Scroll into view
            utility.scrollToElement(driver, vatDropdownButtonLocator);

            // Click -> open dropdown
            utility.clickElement(driver, dropdownButton);
            System.out.println("Opened dropdown VAT");

            //   Wait dropdown showed
            utility.waitForVisibility(driver, dropdownVat, 10);

            // Find and click option want
            String formattedXPath = String.format(vatOptionLocator, vatValue);
            By optionLocator = By.xpath(formattedXPath);
            utility.clickElement(driver, optionLocator);
            System.out.println("Chosen VAT: " + vatValue);

            // Verify value after choose - TODO textToBe
            wait.until(ExpectedConditions.textToBe(vatCurrentValueLocator, vatValue));
            System.out.println("wait.until(ExpectedConditions.textToBe(vatCurrentValueLocator, vatValue)); =======");
            String newValue = driver.findElement(vatCurrentValueLocator).getText().trim();

            if (newValue.equalsIgnoreCase(vatValue)) {
                System.out.println("Verify successfully: Actual VAT is " + newValue);

            } else {
                throw new RuntimeException("Verify failed: Value expected is: '" + vatValue + "', But Actual is '" + newValue + "'");
            }

        } catch (Exception e) {
            System.out.println("Error when function setVAT run: " + e.getMessage());
            throw new RuntimeException("Choose VAT failed by: " + e.getMessage());
        }
    }


    public void setTaxNotApply() {
        /**
         * Choose "Tax does not apply" (không áp dụng thuế).
         */
        logger.info("Start function setTaxNotApply");
        setVAT("Tax does not apply");
    }


    public void setTaxRate(String taxRate) {
        /**
        taxRate: vat you want to choose: "Tax 15%", "Tax 10%", ...
         */
        logger.info("Start function setTaxRate");
        setVAT(taxRate);
    }


    /** INPUT IMEI FOR EACH BRANCH ----------- **/
    // Click input of branch -> open Popup
    public void clickBranchInputToOpenPopup(String branchName) {
        logger.info("Start function clickBranchInputToOpenPopup");
        WebElement branchInput = findBranchInput(branchName);

        if (branchInput == null) {
            throw new IllegalArgumentException("No input found for branch: " + branchName);
        }

        utility.clickElement(driver, branchInput);
    }


    // Verify popup "Add IMEI/Serial Number" opened
    public void verifyPopupOpened() {
        logger.info("Start function verifyPopupOpened");
        WebElement popup = utility.waitForVisibility(driver, popupInventoryModal, 10);
        Assert.assertTrue(popup.isDisplayed(), "Popup 'Add IMEI/Serial Number' should be opened");
    }


    // Find input of branch
    private WebElement findBranchInput(String branchName) {
        logger.info("Start function findBranchInput");
        List<WebElement> branchRows = driver.findElements(branchListStock);

        for (WebElement row : branchRows) {
            WebElement branchLabel = row.findElement(labelBranch);

            if (branchLabel.getText().equalsIgnoreCase(branchName)) {
                return row.findElement(branchInput);
            }
        }
        return null;
    }


    // Open branch dropdown
    public void openBranchDropdown() {
        logger.info("Start function openBranchDropdown");
        utility.clickElement(driver, branchDropdown);
    }


    // Verify branch dropdown is opened
    public void verifyBranchDropdownOpened() {
        logger.info("Start function verifyBranchDropdownOpened");
        WebElement elementBranchDropdownList = utility.waitForVisibility(driver, branchDropdownList, 10);
        Assert.assertTrue(elementBranchDropdownList.isDisplayed(), "Branch dropdown should be opened");
    }


    // Select a branch by name
    public void selectBranch(String branchName) {
        logger.info("Start function selectBranch");
        List<WebElement> elementBranchOptions = utility.visibilityOfAllElementsLocatedBy(driver, branchOptions, 10);

        for (WebElement option : elementBranchOptions) {
            WebElement label = option.findElement(branchLabel);

            if (label.getText().equalsIgnoreCase(branchName)) {
                WebElement checkbox = option.findElement(checkboxBranch);

                if (!checkbox.isSelected()) {
                    option.click();
                }
                break;
            }
        }
    }


    // Verify branch is selected
    public void verifyBranchSelected(String branchName) {
        logger.info("Start function verifyBranchSelected");
        List<WebElement> elementBranchOptions = utility.visibilityOfAllElementsLocatedBy(driver, branchOptions, 10);

        for (WebElement option : elementBranchOptions) {
            WebElement label = option.findElement(branchLabel);

            if (label.getText().equalsIgnoreCase(branchName)) {
                WebElement checkbox = option.findElement(checkboxBranch);
                Assert.assertTrue(checkbox.isSelected(), "Branch " + branchName + " should be selected");
                return;
            }

        }
        Assert.fail("Branch " + branchName + " not found in dropdown");
    }


    // Input IMEI/Serial for a branch
    public void inputIMEIForBranch(String branchName, String imei) {
        logger.info("Start function inputIMEIForBranch");
        WebElement input = findIMEIInputForBranch(branchName);

        if (input == null) {
            throw new IllegalArgumentException("No IMEI input found for branch: " + branchName);
        }

        if (input.getAttribute("disabled") != null) {
            throw new IllegalStateException("IMEI input for branch " + branchName + " is disabled");
        }

        utility.clickElement(driver, input);

        utility.sendKeys(driver, 10, imei);

        utility.pressEnter(driver);
    }


    // Find IMEI input for a specific branch in the popup
    private WebElement findIMEIInputForBranch(String branchName) {
        logger.info("Start function findIMEIInputForBranch");
        List<WebElement> branchCells = driver.findElements(branchCell);
        if (branchCells.isEmpty()) {
            return null;
        }
        return branchCells.get(0).findElement(By.cssSelector("input[name='serial']"));
    }


    // Verify IMEI/Serial is added for a branch
    public void verifyIMEIAdded(String branchName, String imei) {
        logger.info("Start function verifyIMEIAdded");
        WebElement input = findIMEIInputForBranch(branchName);

        if (input == null) {
            Assert.fail("No IMEI input found for branch: " + branchName);
        }

        WebElement codeDiv = input.findElement(divOfCode);
        List<WebElement> imeiElements = codeDiv.findElements(By.cssSelector("div.content p"));
        boolean found = false;

        for (WebElement element : imeiElements) {

            if (element.getText().equals(imei)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "IMEI/Serial " + imei + " should be added for branch " + branchName);
    }


    public void verifyDuplicateIMEIError(String imei) {
        logger.info("Start function verifyDuplicateIMEIError");
        WebElement elementDuplicateIMEIError =  utility.waitForVisibility(driver, duplicateIMEIError, 10);
        String errorMessage = elementDuplicateIMEIError.getText();
        Assert.assertTrue(errorMessage.contains(imei), "Duplicate IMEI error should contain: " + imei);
    }


    public void clickSave() {
        logger.info("Start function clickSave");
        WebElement elementSaveButton2 =  utility.waitForClickable(driver, saveButton2, 10);
        elementSaveButton2.click();
    }


    // Verify modal is closed after saving
    public void verifyModalClosed() {
        logger.info("Start function verifyModalClosed");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modalClose));
        Assert.assertFalse(driver.findElements(modelClose).size() > 0, "Modal should be closed after saving");
    }


    public void clickCancel() {
        logger.info("Start function clickCancel");
        try {
            System.out.println("Start function clickCancel");
            utility.clickElement(driver, cancelButton);
            System.out.println("Function clickCancel run successfully");

        } catch (Exception e) {
            throw new RuntimeException("clickCancel failed: " + e.getMessage());
            }
    }


    // Main function to add IMEI/Serial for branches
    public void addIMEIForBranches(Map<String, List<String>> branchIMEIs) {
        logger.info("Start function addIMEIForBranches");
        for (Map.Entry<String, List<String>> entry : branchIMEIs.entrySet()) {
            String branch = entry.getKey();
            List<String> imeis = entry.getValue();

            // Click input of branch -> open popup
            clickBranchInputToOpenPopup(branch);

            verifyPopupOpened();

            // Open dropdown and choose branch
            openBranchDropdown();

            verifyBranchDropdownOpened();

            selectBranch(branch);

            verifyBranchSelected(branch);

            // Input IMEI/Serial
            for (String imei : imeis) {
                try {
                    inputIMEIForBranch(branch, imei);
                    verifyIMEIAdded(branch, imei);

                } catch (IllegalStateException e) {
                    System.out.println("Skipping IMEI input for branch " + branch + ": " + e.getMessage());

                } catch (Exception e) {
                    WebElement elementDuplicateIMEIError = utility.waitForVisibility(driver, duplicateIMEIError, 10);

                    if (elementDuplicateIMEIError.isDisplayed()) {
                        verifyDuplicateIMEIError(imei);

                    } else {
                        throw e;
                    }
                }
            }

            clickSave();
            verifyModalClosed();
        }
    }
}
