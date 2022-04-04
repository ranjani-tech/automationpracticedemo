package com.globaltechchallenge.automationpracticedemo.web;

import com.globaltechchallenge.automationpracticedemo.web.pages.GlobalMyStorePage;
import com.globaltechchallenge.automationpracticedemo.web.utilities.DriverProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class StepDefinitions {
    private final GlobalMyStorePage myStoreProductPage;


    //Constructor
    public StepDefinitions(){
        myStoreProductPage = new GlobalMyStorePage(DriverProvider.getDriver());
    }

    @Given("User is on my store product home page")
    public void user_is_on_my_store_product_home_page() {
        myStoreProductPage.openStartPage();

    }
    @When("user verify the page title {string}")
    public void user_verify_the_page_title(String pageTitle) {
        myStoreProductPage.selectPageTitle(pageTitle);

    }

    @When("User selects productBlock as {string} and")
    public void user_selects_product_block_as_and(String productBlock, io.cucumber.datatable.DataTable addItemToCartTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        String subCategory = null;
        String productCategory = null;
        String size=null;
        String color=null;
        String productName=null;
        String quantity=null;
        String actionNow=null;
        String actionNext=null;
        List<Map<String,String>> productlist = addItemToCartTable.asMaps(String.class, String.class);
        for(Map<String,String> eachColumn :productlist){
            // eachColumn.get("Product"),("")
            subCategory=eachColumn.get("subcategory");
            productCategory=eachColumn.get("ProductCategory");
            size=eachColumn.get("Size");
            color=eachColumn.get("Color");
            productName=eachColumn.get("ProductName");
            quantity=eachColumn.get("quantity");
            actionNow=eachColumn.get("actionNow");
            actionNext=eachColumn.get("actionNext");

            myStoreProductPage.addProductInCart(productBlock, subCategory, productCategory,  size,  color,  productName,  quantity, actionNow,  actionNext);

        }
    }

}

