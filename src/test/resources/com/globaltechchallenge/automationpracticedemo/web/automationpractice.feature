@Regression
@endtoend

Feature: User should be able add product to cart
  and verify the total cost of the all product in the Cart


  @AddAndRemoveProduct
  Scenario: end to end journey till product checkout page.
    Given User is on my store product home page
    When user verify the page title "My Store"
    And User selects productBlock as "WOMEN" and
      | subcategory | ProductCategory | Size | Colour | ProductName                 | quantity  |  actionNow  | actionNext        |
      | TOPS        | T-shirts        | M    | Blue   | Faded Short Sleeve T-shirts | 1         | Add to Cart | Continue Shopping |
      | DRESSES     | Evening Dresses | S    | Beige  | Printed Dress               | 1         | Add to Cart | Continue Shopping |
      | DRESSES     | Summer Dresses  | M    | Orange | Printed Summer Dress        | 1         | Add to Cart | Continue Shopping |
