package com.globaltechchallenge.automationpracticedemo.web.base;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class BaseAction {
    WebDriver driver;

    //Constructor
    public BaseAction(WebDriver driver) {
        this.driver = driver;
    }



    //goToWeb Method:
    @Step
    protected void goToWeb(String url) {
        driver.get(url);
        String message = "Landing to Web Page: " + url;
        System.out.println(message);
    }

    //waitUntilUrlContain
    @Step
    protected void waitUntilUrlContains(String url) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains(url));
        String message = "wait until URL: " + url + " is visible";
        System.out.println(message);
    }

    //getPageTitle
    @Step
    protected String getPageTitle() {
        String  title = driver.getTitle();
        String message = "Page title is : " + title;
        System.out.println(message);
        return title;
    }

    // checkIfPageTitleContains
    @Step
    protected void checkIfPageTitleContains(String pageTitle, String expected) {
        String message = "CHECK_IF" + pageTitle + "CONTAINS" + expected;
        System.out.println(message);
        Assert.assertTrue(pageTitle.contains(expected));
    }

    //waitUntilElementClickable Method:
    @Step
    protected void waitUntilElementClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        String message = "wait until " + element + " is clickable";
        System.out.println(message);
    }

    //checkIfTextIsExpected Method:
    @Step
    protected void checkIfTextIsExpected(WebElement element, String expected) {
        Assert.assertEquals(element.getText(), expected);
        String message = "Text is expected: " + expected;
        System.out.println(message);
    }

    //checkIfTextContainsExpected Method:
    @Step
    protected void checkIfTextContainsExpected(WebElement element, String expected) {
        String message;
        message = "Text is actual: " + element.getText();
        message = message + "\\nText is expected: " + expected;
        System.out.println(message);
        Assert.assertTrue(element.getText().contains( expected));
    }

    //checkIfTextContainsExpected Method:
    @Step
    protected void checkIFElementIsNotNull(WebElement element) {
        String message;
        Assert.assertNotNull(element);
        message = "element checked for not null?";
        message = message + "\\nPassed";
        System.out.println(message);
    }

    //ClickOn Method:
    protected void clickOn(WebElement element) {
        try {
            element.click();
            String message = "User clicks On Element: " + element;
            System.out.println(message);
        } catch (Exception e) {
            System.out.println("Exception on clicking element" + element);
            System.out.println(e.getMessage());
        }
    }

    //InputTextBox Method:
    protected void inputTextBox(WebElement element, String value) {
        try {
            element.clear();
            element.sendKeys(value);
            String message = "INPUT_TEXT_FIELD" + element + "AND_VALUE" + value;
            System.out.println(message);
        } catch (InvalidElementStateException e) {
            String message = "unable to clear text box, InvalidElementStateException found";
            System.out.println(message);
        }

    }

    //selectByVisibleTextInDropDown Method:
    protected void selectByVisibleTextInDropDown(WebElement element, String value) {
        try {
            //clickOn(element);
            Select objSelect = new Select(element);
            objSelect.selectByVisibleText(value);
            //clickOn(element);
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException on selecting value " + value + " for element " + element);
            System.out.println(e.getMessage());
        }
    }

    //waitUntilElementVisible Method:
    @Step
    protected void waitUntilElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element));
        String message = "wait until " + element + " is visible";
        System.out.println(message);
    }

    @Step
    protected void waitUntilElementsVisible(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        String message = "wait until all elements are visible";
        System.out.println(message);
    }

    //find_elements_by_xpath Method:
    protected List<WebElement> find_elements_by_xpath(WebElement inElement, String xPath) {
        List<WebElement> elements = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By) inElement.findElements(By.xpath(xPath))));
            elements = inElement.findElements(By.xpath(xPath));
        } catch (Exception e) {
            System.out.println("Exception on xPath element" + xPath);
            System.out.println(e.getMessage());
        }
        return elements;
    }

    //find_elements_by_xpath Method:
    protected List<WebElement> find_elements_by_xpath(String xPath) {
        List<WebElement> elements = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xPath)));
            elements =driver.findElements(By.xpath(xPath));
        } catch (Exception e) {
            System.out.println("Exception on xPath element" + xPath);
            System.out.println(e.getMessage());
        }
        return elements;
    }

    //find_element_by_xpath Method:
    protected WebElement find_element_by_xpath(String xPath) {
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
            element =driver.findElement(By.xpath(xPath));
        } catch (Exception e) {
            System.out.println("Exception on xPath element" + xPath);
            System.out.println(e.getMessage());
        }
        return element;
    }


    protected WebElement getElementByXpathPrefixAndSuffix(String xpathPrefix,String elementName,String xpathSuffix) {
        String xPath = xpathPrefix + elementName + xpathSuffix;
        WebElement element = find_element_by_xpath(xPath);
        return element;
    }

    protected WebElement getElementByXpathPrefixMiddleSuffix(String xpathPrefix,String productName,String xPathMiddleFirst,String actionName,String xPathMiddleSecond,String iconName,String xpathSuffix) {
        String xPath = xpathPrefix + productName + xPathMiddleFirst + actionName + xPathMiddleSecond + iconName + xpathSuffix;
        WebElement element = find_element_by_xpath(xPath);
        return element;
    }

    protected void waitUntilElementDeletedByXpath(String xpathPrefix,String productName,String xPathMiddleFirst,String actionName,String xPathMiddleSecond,String iconName,String xpathSuffix) {
        String xPath = xpathPrefix + productName + xPathMiddleFirst + actionName + xPathMiddleSecond + iconName + xpathSuffix;
        waitUntilElementInvisible(xPath);
    }


    @Step
    protected void waitUntilTextInElementInvisible(String xPath, String textValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(xPath),textValue));
        String message = "wait until " + xPath + " textValue " + textValue + " is visible";
        System.out.println(message);
    }

    @Step
    protected void waitUntilElementInvisible(String elementXpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(elementXpath)));
        String message = "wait until " + elementXpath + " is invisible";
        System.out.println(message);
    }


    protected void moveElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }






}
