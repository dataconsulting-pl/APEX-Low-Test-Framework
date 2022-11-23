Feature: Test Cases - Customers

  Background: User access Customers page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    When user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Customers' page
    Then verify, that menu option 'Customers' is selected

  Scenario: Add new Customer
    Given user clicked on the 'Add Customer' button
    When user enters 'Eliza Mann' in 'Customer Name' field
    And user clicked on the 'Add Customer' button


  Scenario: Add existing Customer
    Given user clicked on the 'Add Customer' button
    When user enters 'Eliza Mann' in 'Customer Name' field
    And user clicked on the 'Add Customer' button
    Then validation message 'unique constraint (DATACONSULTING.EBA_CUST_CUSTOMERS_UK) violated' for 'Customer Name' field is displayed




























