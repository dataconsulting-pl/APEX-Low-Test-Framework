Feature: Navigate through the APEX Menu items

  Background: User access Activities page
    Given the user has accessed the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button

  @unit
  Scenario: Navigate to main menu element
    When user navigates to 'Activities' page
    Then verify, that menu option 'Activities' is selected

  @unit
  Scenario: Navigate to sub menu element
    When user navigates to 'Customers->Categories' page
    Then verify, that menu option 'Customers->Categories' is selected