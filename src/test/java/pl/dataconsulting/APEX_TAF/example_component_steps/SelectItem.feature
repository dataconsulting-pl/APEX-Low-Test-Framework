Feature: Set and verify APEX Select Item elements

  Background: User access Activities page
    Given the user has accessed the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_admin@dataconsulting.pl' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button


  @unit
  Scenario: Enter text into simple text field
    Given user navigates to 'Activities' page
    And user clicked on the 'Create Activity' button
    And frame 'Activity Details' is visible
    When user selects 'Meeting' in 'Type' field
    Then value 'Meeting' is selected in 'Type' field

  @unit
  Scenario: Select options in select item fields
    Given user navigates to 'Customer' page
    And user clicked on the 'Add Customer' button
    When user selects option in fields according to data in table:
      | Status   | Category  | Industry  |
      | Internal | Corporate | Insurance |
    Then value 'Internal' is selected in 'Status' field
    And value 'Corporate' is selected in 'Category' field
    And value 'Insurance' is selected in 'Industry' field

