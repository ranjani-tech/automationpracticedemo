#GlobalTechChall

AutomationpracticeChallenge:

** Project Scope ** Create a simple E2E test journey for the given scenario

  ```        Add to the cart a Faded Short Sleeve T Shirt, size medium, colour blue
          Add to the cart an Evening Dress, size small, colour beige
          Add to the cart a Printed Summer Dress, size medium, colour orange
          Checkout
          Remove the Evening Dress from the cart
          Add a second Faded Short Sleeve T Shirt of the same size and colour
          Assert the total for each line in the cart
          Assert the cart total is $65.53
```
```
         GIVEN Navigate to automationpractice homepage
         WHEN  Select the product
         THEN  Checkout the product
```
About the project

This project is am implementation of Selenium web automation framework in a page-object oriented architecture.
The code structure captured in different layers and their scope are as follows:

Layer I: Feature file (Path: [src/test/resources/com/globaltechchallenge/automationpracticedemo/web/automationpractice])
```
This layer contains feature files where a QA engineer writes simple scenarios in BDD format using Gherkin language.
Feature files increases the speed of writing new automated scenarios by reusing pre-existing scenario steps also allows
non-engineering team members to review test cases without getting into too much technical details.
```
Feature file can be custom changed to different product by updating
```And user selects the product as "$product" from "$page"```

Layer II: Step definitions (Path: [src/test/java/com/globaltechchallenge/automationpracticedemo/web/StepDefinitions.java])
```
This layer acts as a glue between scenario steps written in feature-file and their corresponding code execution setup.
The main objective of this layer is to process any/all test scenario inputs and call the relevant functions in page object classes to cause test execution.
```
Layer III: Page layer(Path: [src/main/java/com/globaltechchallenge/automationpracticedemo/web/pages])
```
This layer converts the action cues received from step definition layer to absolute commands for the base layer. 
This layer has two main responsibilities,
Locate business/page specific web elements.
Execute generic commands for the base action layer using locators and input data from step-definition.
```
Layer IV: Base Action layer(Path: [src/main/java/com/globaltechchallenge/automationpracticedemo/web/base])
```
This layer is free from business/page logic and deals with the technical mechanisms to execute framework specific commands as required by the page layer. Usually this is the only place where platform specific actions are performed.
```
Helper/Utility modules
```
Driver Provider: This class keeps a singleton driver instance to be used at framework specific operations in various test setup, initializers and page object command phases.
Properties Provider: This class helps load project/test-run specific properties at the start of test execution so that we have a more centralized way of configuring our test environment.
```
How to run:

Option A:

Open the project in IntelliJ Idea
Right click on project > Maven > Sync project
Open file automationpractice.feature
Click on "Run test" green play button on the left of the scenario
Incase error modify the build configuration glue = "com/globaltechchallenge/automationpracticedemo/web"
Option B:

Ensure that you have maven cli installed (brew install maven)
Go to root of project directory
Run mvn clean Install test
This project is tested on Chrome browsers only

screenshot Screenshot taken during the after each steps.

Report: Reports have auto generated and added into the Cucumber-reports folder. To see the report click on the report open in browser.

Refer to the below path for checking cucumber report automationpracticedemo/cucumber-reports/cucumber-reports.html
