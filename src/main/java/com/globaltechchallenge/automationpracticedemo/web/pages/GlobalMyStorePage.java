package com.globaltechchallenge.automationpracticedemo.web.pages;

import com.globaltechchallenge.automationpracticedemo.web.base.BasePage;
import com.globaltechchallenge.automationpracticedemo.web.utilities.PropertiesProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalMyStorePage extends BasePage {

    @FindBy(how = How.XPATH, using = "//a[text()='Women']")
    WebElement productBlockElement;

    //Generic productCategoryXpath creator for productCategory selection
    String productCategoryXpathPrefix = "//a[@class='subcategory-name' and text()='";
    String xPathSuffix = "']";
    String productCategoryXpath = null;
    WebElement productCategoryElement;

    //Generic CheckboxXpath
    String checkboxXpathPrefix = "//a[text()='";
    WebElement checkboxSizeElement;
    WebElement checkboxColorElement;

    //Generic productNameXpath
    String productNameXpathPrefix = "//a[@title='";
    String productNameXpathSuffix = "' and @class='product-name']";
    WebElement productNameElement;

    //sizeWanted
    @FindBy(how = How.ID, using = "quantity_wanted")
    WebElement inputQuantityWantedElement;

    //sizeWanted
    @FindBy(how = How.NAME, using = "group_1")
    WebElement sizeWantedElement;

    //colorWanted
    String colorWantedXpathPrefix = "//ul[@id='color_to_pick_list']//li/a[@title='";
    WebElement colorWantedElement;

    //cartStatus
    @FindBy(how = How.XPATH, using = "//i[@class='icon-ok']//..")
    WebElement itemCartStatusElement;

    String itemCartStatusSuccess = "Product successfully added to your shopping cart";

    //Continue Shopping in the cart
    @FindBy(how = How.CSS, css = "span[title='Continue shopping']")
    WebElement continueShoppingElement;

    //titleXpath
    String aTitleXpathPrefix = "//a[@title='";
    WebElement aTitleElement;

    //cartTitle
    @FindBy(how = How.ID, using = "cart_title")
    WebElement cartTitleElement;

    //xpath for product Table in Cart
    String everyTableRowsXpath = "//div[@id='order-detail-content']//table[@id='cart_summary']//tbody//tr";
    String productColumnXpath = "./td[2]";
    String quantityColumnXpath = "./td[@class='cart_quantity text-center']//input[@type='hidden']";
    String totalAmountColumnXpath = "./td[6]";

    //summary products text
    String summaryProductsQuantityXpath = "//*[@id='summary_products_quantity']";

    @FindBy(how = How.ID, using = "summary_products_quantity")
    WebElement summaryProductQuantityElement;

    //total price amount text
    @FindBy(how = How.ID, using = "total_price")
    WebElement totalPriceElement;



    //actionInCartElement
    String actionInCartXpathPrefix = "//p[@class='product-name']//a[text()='";
    String actionInCartXpathMiddleFirst = "']//../..//..//a[@title='";
    String actionInCartXpathMiddleSecond = "']//..//i[@class='";
    WebElement actionInCartElement;

    //Constructor
    public GlobalMyStorePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Goto Home page
    public void openStartPage() {
        goToWeb(PropertiesProvider.getProperties().getProperty("starturl"));
        waitUntilUrlContains(PropertiesProvider.getProperties().getProperty("starturl"));
    }

    //Check Page Title
    public void selectPageTitle(String pageTitle) {
        waitUntilUrlContains(PropertiesProvider.getProperties().getProperty("starturl"));
        checkIfPageTitleContains(getPageTitle(), pageTitle);
    }

    //add Product to Cart
    //page url loads from productBlock to category and includes size and color
    public void addProductInCart(String productBlock, String subCategory, String productCategory, String size, String color, String productName, String quantity, String actionNow, String actionNext) {

        //Navigate to productBlock
        waitUntilElementClickable(productBlockElement);
        checkIfTextIsExpected(productBlockElement, productBlock);
        clickOn(productBlockElement);
        waitUntilElementVisible(paraSubCategoryElement);
        subCategoryElement = getSubCategoryElement(subCategory);
        checkIFElementIsNotNull(subCategoryElement);
        waitUntilElementClickable(subCategoryElement);
        clickOn(subCategoryElement);

        //Navigate to productCategory
        productCategoryXpath = productCategoryXpathPrefix + productCategory + xPathSuffix;
        productCategoryElement = find_element_by_xpath(productCategoryXpath);
        checkIFElementIsNotNull(productCategoryElement);
        waitUntilElementClickable(productCategoryElement);
        clickOn(productCategoryElement);
        waitUntilUrlContains("category");

        //Filter by size
        checkboxSizeElement = getElementByXpathPrefixAndSuffix(checkboxXpathPrefix, size, xPathSuffix);
        checkIFElementIsNotNull(checkboxSizeElement);
        waitUntilElementClickable(checkboxSizeElement);
        clickOn(checkboxSizeElement);
        waitUntilUrlContains("size-" + size.toLowerCase());

        //Filter by color
        checkboxColorElement = getElementByXpathPrefixAndSuffix(checkboxXpathPrefix, color, xPathSuffix);
        checkIFElementIsNotNull(checkboxSizeElement);
        waitUntilElementClickable(checkboxColorElement);
        clickOn(checkboxColorElement);
        waitUntilUrlContains("color-" + color.toLowerCase());

        //Check the product name visible
        productNameElement = getElementByXpathPrefixAndSuffix(productNameXpathPrefix, productName, productNameXpathSuffix);
        checkIFElementIsNotNull(productNameElement);
        checkIfTextContainsExpected(productNameElement, productName);
        clickOn(productNameElement);
        checkIfTextIsExpected(itemHeadingElement, productName);

        //Select the quantity
        inputTextBox(inputQuantityWantedElement, quantity);
        selectByVisibleTextInDropDown(sizeWantedElement, size);

        //Select the color
        colorWantedElement = getElementByXpathPrefixAndSuffix(colorWantedXpathPrefix, color,xPathSuffix);
        checkIFElementIsNotNull(colorWantedElement);
        clickOn(colorWantedElement);

        //submit and verify color and size included
        clickOn(submitButtonElement);
        waitUntilUrlContains("color-" + color.toLowerCase());
        waitUntilUrlContains("size-" + size.toLowerCase());

        //submit and verify color and size included
        waitUntilElementVisible(itemCartStatusElement);
        checkIfTextContainsExpected(itemCartStatusElement, itemCartStatusSuccess);

        //continue Shopping to browse more product
        waitUntilElementClickable(continueShoppingElement);
        clickOn(continueShoppingElement);

    }

    //Navigate to Order Page
    public void userClicksLinkTitle(String linkToClickFromTitle) {

        aTitleElement = getElementByXpathPrefixAndSuffix(aTitleXpathPrefix , linkToClickFromTitle , xPathSuffix);
        checkIFElementIsNotNull(aTitleElement);
        clickOn(aTitleElement);
        waitUntilUrlContains("order");

    }

    //Navigate to Cart
    public void viewCart(String cartTitle) {
        waitUntilElementVisible(cartTitleElement);
        checkIfTextContainsExpected(cartTitleElement, cartTitle);
    }

    //verifyEvery Product - get all product from basket and return to assert
    public List<Map<String, String>> verifyProductList() {

        waitUntilUrlContains("order");
        List<Map<String, String>> actualProductCarts = new ArrayList<Map<String, String>>();

        List<WebElement> tableRows = find_elements_by_xpath(everyTableRowsXpath);
        waitUntilElementsVisible(tableRows);
        for (WebElement eachRow : tableRows) {

            List<WebElement> productColumns = find_elements_by_xpath(eachRow, productColumnXpath);
            List<WebElement> quantityColumns = find_elements_by_xpath(eachRow, quantityColumnXpath);
            List<WebElement> totalColumns = find_elements_by_xpath(eachRow, totalAmountColumnXpath);

            //First HeaderOnly from td for product description
            String[] listCarts = productColumns.get(0).getText().split("\\n");

            String productName = listCarts[0];
            String quantity = quantityColumns.get(0).getAttribute("value");
            String totalCount = totalColumns.get(0).getText();

            Map<String, String> productInCart = new HashMap<String, String>();
            productInCart.put("productname", productName);
            productInCart.put("amount", totalCount);
            productInCart.put("quantity", quantity);
            //System.out.println(productInCart);

            actualProductCarts.add(productInCart);

        }

        return actualProductCarts;
    }

    //verify the change in the summary product text
    public void verifySummaryProduct(String summaryProduct) {
        checkIfTextIsExpected(summaryProductQuantityElement, summaryProduct);
        Assert.assertEquals(summaryProductQuantityElement.getText(), summaryProduct);
    }

    //verify the amount
    public void verifyAmount(String amountValue) {
        waitUntilElementVisible(totalPriceElement);
        checkIfTextIsExpected(totalPriceElement, amountValue);
    }



    //action an product in cart - add or delete
    public void actionTheProductInCart(String productName, String actionName, String iconName) {
        actionInCartElement = getElementByXpathPrefixMiddleSuffix(actionInCartXpathPrefix, productName, actionInCartXpathMiddleFirst, actionName, actionInCartXpathMiddleSecond, iconName, xPathSuffix);
        waitUntilElementClickable(actionInCartElement);
        moveElement(actionInCartElement);
        summaryProductQuantityElement = find_element_by_xpath(summaryProductsQuantityXpath);
        String summaryProductQuantityValue = summaryProductQuantityElement.getText();

        clickOn(actionInCartElement);
        waitUntilTextInElementInvisible(summaryProductsQuantityXpath, summaryProductQuantityValue);


        waitUntilUrlContains("order");
    }

    //delete the item in cart
    public void verifyTheProductInCartDeleted(String productName, String actionName, String iconName) {
        waitUntilElementDeletedByXpath(actionInCartXpathPrefix, productName, actionInCartXpathMiddleFirst, actionName, actionInCartXpathMiddleSecond, iconName, xPathSuffix);
    }
}
