Feature: Navigate through the APEX Menu items

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'kborowski@dataconsulting.pl' in 'Username' field
    And user entered '.-$J6jhjtyZPjfd' in 'Password' field
    And user clicked on the 'Sign In' button


  Scenario: Navigate to main menu element
    When user navigates to 'Activities' page
    Then verify, that menu option 'Activities' is selected

  Scenario: Navigate to sub menu element
    When user navigates to 'Customers->Categories' page
    Then verify, that menu option 'Customers->Categories' is selected

  Scenario: Navigate to sub (2nd) menu element
    When user navigates to 'Administration->Access Control->Settings' page
    Then verify, that menu option 'Administration->Access Control->Settings' is selected
