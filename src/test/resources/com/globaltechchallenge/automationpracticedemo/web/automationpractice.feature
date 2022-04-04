@Regression
@endtoend

Feature: User should be able add product to cart
  and verify the total cost of the all product in the Cart


  @AddAndRemoveProduct
  Scenario: end to end journey till product checkout page.
    Given User is on my store product home page
    When user verify the page title "My Store"