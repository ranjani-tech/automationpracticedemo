package com.globaltechchallenge.automationpracticedemo.web;

import com.globaltechchallenge.automationpracticedemo.web.pages.GlobalMyStorePage;
import com.globaltechchallenge.automationpracticedemo.web.utilities.DriverProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @And("User selects productBlock as {string} and")
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


    @And("user clicks Link Title {string}")
    public void user_clicks_link_title(String linkToClickFromTitle) {
        myStoreProductPage.userClicksLinkTitle(linkToClickFromTitle);

    }
    @Then("user should be able to view the item in cart {string} page")
    public void user_should_be_able_to_view_the_item_in_cart_page(String cartTitle) {
        myStoreProductPage.viewCart(cartTitle);

    }

    @And("user verify the products")
    public void user_verify_the_products(io.cucumber.datatable.DataTable productInCarts) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<Map<String,String>> expectedProductlist = productInCarts.asMaps(String.class,String.class);
        List<Map<String,String>> actualProductList = myStoreProductPage.verifyProductList();


        System.out.println("expectedProductlist "+ expectedProductlist);
        System.out.println("actualProductList "+ actualProductList);

        assertTrue(new HashSet<Map<String, String>>(actualProductList).equals(new HashSet<Map<String, String>>(expectedProductlist)));

        /*
            Additional validation
            actualProductList as List<Map{k:v}> --> iterator every list and match for every key to every  <-- expectedProductList as List<Map{k:v}>
         */
        List<Map<String,String>> unMatchedRecords = actualProductList.parallelStream().filter(actualProductSearchData ->
                expectedProductlist.parallelStream().noneMatch( expectedProductMap ->
                        actualProductSearchData.entrySet().stream().noneMatch(actualProductSearchValue ->
                                expectedProductMap.entrySet().stream().noneMatch(expectedProductValue ->
                                        (expectedProductValue.getKey().equals(actualProductSearchValue.getKey()) &&
                                                expectedProductValue.getValue().equals(actualProductSearchValue.getValue()))))
                )).collect(Collectors.toList());
        System.out.println(unMatchedRecords.size());
        for(Map<String,String> unMatchedRecord: unMatchedRecords){
            System.out.println(unMatchedRecord);
        }
        assertEquals(unMatchedRecords.size(),0);

    }
    @And("user verify summary product is {string}")
    public void user_verify_summary_product_is(String summaryProduct) {
        myStoreProductPage.verifySummaryProduct(summaryProduct);

    }
    @And("user verify the total amount is {string}")
    public void user_verify_the_total_amount_is(String amountValue) {
        myStoreProductPage.verifyAmount(amountValue);
    }
    @When("user {string} the {string} in the cart")
    public void user_action_item_in_the_cart(String actionName, String productName) {
        switch(actionName) {
            case "Add":
                myStoreProductPage.actionTheProductInCart(productName,"Add","icon-plus");
                break;
            case "Delete":
            case "Remove":
            case "Subtract":
                myStoreProductPage.actionTheProductInCart(productName,"Delete","icon-trash");
                myStoreProductPage.verifyTheProductInCartDeleted(productName,"Delete","icon-trash");
                break;
            default:
                //fail for any other case
                assertTrue(Boolean.FALSE);
                return;
        }
    }


}

