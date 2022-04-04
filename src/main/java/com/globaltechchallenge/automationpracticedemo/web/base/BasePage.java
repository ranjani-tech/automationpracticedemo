package com.globaltechchallenge.automationpracticedemo.web.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class BasePage extends BaseAction {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//a[@class='subcategory-name']")
    protected List<WebElement> subCategoriesElements;

    @FindBy(how = How.XPATH, using = "//p[@class='subcategory-heading']")
    protected WebElement paraSubCategoryElement;

    protected WebElement subCategoryElement;

    //ProductNameHeading
    @FindBy(how = How.XPATH, using = "//h1[@itemprop='name']")
    protected WebElement itemHeadingElement;

    //submit button
    @FindBy(how = How.NAME, using = "Submit")
    protected WebElement submitButtonElement;

    //Constructor
    public BasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    protected WebElement getSubCategoryElement(String subCategory) {

        WebElement subCategoryElement = null;
        for (WebElement eachElement : subCategoriesElements) {
            System.out.println(eachElement.getText() + " vs " + subCategory);
            if (eachElement.getText().contains(subCategory)) {
                subCategoryElement = eachElement;
                System.out.println(subCategoryElement.getLocation() + "Found");
                return subCategoryElement;
            }
        }
        return subCategoryElement;

    }




}
