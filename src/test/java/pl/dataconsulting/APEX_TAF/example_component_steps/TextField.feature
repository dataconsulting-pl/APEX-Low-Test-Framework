Feature: set and verify APEX Text field value

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_admin@dataconsulting.pl' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Activities' page

  @unit
  Scenario: Enter text into simple text field
    Given user clicked on the 'Create Activity' button
    And frame 'Activity Details' is visible
    When user enters 'test 1' in 'Name' field
    Then verify, that value of text item 'Name' is 'test 1'

  @unit
  Scenario: Enter text into text fields
    Given user navigates to 'Customers' page
    And user clicked on the 'Add Customer' button
    When user enters text into text fields according to data in table:
    | Customer Name | TCV | ARR |
    | Customer 1    | AB1 | 23a |
    Then verify, that value of text item 'Customer Name' is 'Customer 1'
    And verify, that value of text item 'TCV' is 'AB1'
    And verify, that value of text item 'ARR' is '23a'
