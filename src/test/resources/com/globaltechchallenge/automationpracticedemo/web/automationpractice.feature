@Regression
@endtoend

Feature: User should be able add product to cart
  and verify the total cost of the all product in the Cart
  @AddAndRemoveProduct
  Scenario: end to end journey till product checkout page.
    Given User is on my store product home page
    When user verify the page title "My Store"
    And User selects productBlock as "WOMEN" and
      | subcategory | ProductCategory | Size | Color  | ProductName                 | quantity  |  actionNow  | actionNext        |
      | TOPS        | T-shirts        | M    | Blue   | Faded Short Sleeve T-shirts | 1         | Add to Cart | Continue Shopping |
      | DRESSES     | Evening Dresses | S    | Beige  | Printed Dress               | 1         | Add to Cart | Continue Shopping |
      | DRESSES     | Summer Dresses  | M    | Orange | Printed Summer Dress        | 1         | Add to Cart | Continue Shopping |
    And user clicks Link Title "View my shopping cart"
    Then user should be able to view the item in cart "SHOPPING-CART SUMMARY" page
    And user verify the products
      | productname                 | amount | quantity |
      | Printed Dress               | $50.99 | 1 |
      | Faded Short Sleeve T-shirts | $16.51 | 1 |
      | Printed Summer Dress        | $28.98 | 1 |
    And user verify summary product is "3 Products"
    And user verify the total amount is "$98.48"
    When user "Add" the "Faded Short Sleeve T-shirts" in the cart
    Then user verify the products
      | productname                 | amount | quantity |
      | Printed Dress               | $50.99 | 1 |
      | Faded Short Sleeve T-shirts | $33.02 | 2 |
      | Printed Summer Dress        | $28.98 | 1 |
    And user verify summary product is "4 Products"
    And user verify the total amount is "$114.99"
    When user "Remove" the "Printed Dress" in the cart
    Then user verify the products
      | productname                 | amount | quantity |
      | Faded Short Sleeve T-shirts | $33.02 | 2 |
      | Printed Summer Dress        | $28.98 | 1 |
    And user verify summary product is "3 Products"
    And user verify the total amount is "$64.00"


