package com.globaltechchallenge.automationpracticedemo.web.pages;

import com.globaltechchallenge.automationpracticedemo.web.base.BasePage;
import com.globaltechchallenge.automationpracticedemo.web.utilities.PropertiesProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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
}
