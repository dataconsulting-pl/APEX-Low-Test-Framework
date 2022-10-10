Feature: Set and verify checkbox value

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Customer' page


  @unit
  Scenario: Button with span
    When user presses the 'Add Customer' button
    Then user presses the 'Cancel' button

  @unit
  Scenario: Button without span
    When user presses the 'Check All' button
    Then user presses the 'Uncheck All' button