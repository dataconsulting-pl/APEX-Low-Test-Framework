Feature: Acceptance Test Cases - Customers

  Background: User access Customer page and resets sample data
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    When user entered 'taf_admin@dataconsulting.pl' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    And user navigates to 'Administration->Configuration->Sample Data' page
    And user presses the 'Reset Data' button on 'Manage Sample Data' pop-up
    Then user navigates to 'Customer' page

  Scenario: Add new Customer
    Given user navigates to 'Customer' page
    When user clicked on the 'Add Customer' button
    And user enters 'Daniel Bosch' in 'Customer Name' field
    And user select 'Cyphria' in 'Parent' popup lov element
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
    And user selects 'Closed' in 'Type' field
    And user enters '10' in 'Number of Users' field
    And user enters 'CLOUD' in 'Tags' field
    And user enters 'Project' in 'Project Summary' field
    And user enters 'AGD' in 'Stock Symbol' field
    And user enters '5145862552' in 'Account Number' field
    And user clicked on the 'Add Customer' button
    And user clicked on the 'Edit Customer' button
    Then verify, that value of text item 'Customer Name' is 'Daniel Bosch'
    Then value 'Cyphria' is selected in 'Parent' popup lov field
    #Then verify, that value of popup lov field 'Parent' is 'Cyphria'
    Then value 'Customer' is selected in 'Status' field
    Then value 'Corporate' is selected in 'Category' field
    Then value 'Insurance' is selected in 'Industry' field
    Then value 'Japan' is selected in 'Geography' field
    Then value 'Yes' is selected in 'Marquee Customer' field
    Then value 'Partner' is selected in 'Sales Channel' field
    Then verify, that value of text item 'ARR' is '5'
    Then verify, that value of text item 'Discount' is '10'
    #Then value 'Yes' is selected in 'Publicly Referenceable' field
    #Then checkboxes 'Logo, Quote' in 'Reference Types' checkbox are selected
    Then value 'Closed' is selected in 'Type' field
    Then verify, that value of text item 'Number of Users' is '10'
    #Then verify, that value of text item 'Tags' is 'CLOUD'
    Then verify, that value of text item 'Project Summary' is 'Project'
    Then verify, that value of text item 'Stock Symbol' is 'AGD'
    Then verify, that value of text item 'Account Number' is '5145862552'

  Scenario: Add existing Customer
    Given user clicked on the 'Add Customer' button
    When user enters 'Mogul Mashups' in 'Customer Name' field
    And user clicked on the 'Add Customer' button
    Then validation message 'unique constraint (DATACONSULTING.EBA_CUST_CUSTOMERS_UK) violated' for 'Customer Name' field is displayed




























