Feature: Acceptance Test Cases - Customers

  Background: User access Customers page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    When user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Customers' page
    Then verify, that menu option 'Customers' is selected

  Scenario: Add new Customer
    Given user clicked on the 'Add Customer' button
    When user enters 'Tom Dash' in 'Customer Name' field
    And user selects 'Customer' in 'Status' field
    And user selects 'Corporate' in 'Category' field
    And user selects 'Insurance' in 'Industry' field
    And user selects 'Japan' in 'Geography' field
    And user selects 'Yes' in 'Marquee Customer' field
    And user selects 'Partner' in 'Sales Channel' field
    And user enters '5' in 'ARR' field
    And user enters '10' in 'Discount' field
    And user selects 'Yes' in 'Publicly Referenceable' field
    And user sets checkboxes 'Logo, Quote' for 'Reference Types' checkbox item
    And user unsets checkboxes 'Logo' for 'Reference Types' checkbox item
    And user selects 'Closed' in 'Type' field
    And user enters '10' in 'Number of Users' field
    And user enters 'CLOUD' in 'Tags' field
    And user enters 'Project' in 'Project Summary' field
    And user enters 'AGD' in 'Stock Symbol' field
    And user enters '5145862552' in 'Account Number' field
    And user clicked on the 'Add Customer' button
    And user clicked on the 'Edit Customer' button
    Then verify, that value of text item 'Customer Name' is 'Tom Dash'

  Scenario: Add existing Customer
    Given user clicked on the 'Add Customer' button
    When user enters 'Tom Dash' in 'Customer Name' field
    And user clicked on the 'Add Customer' button
    Then validation message 'unique constraint (DATACONSULTING.EBA_CUST_CUSTOMERS_UK) violated' for 'Customer Name' field is displayed




























