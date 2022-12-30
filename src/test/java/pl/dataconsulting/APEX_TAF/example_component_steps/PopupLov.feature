Feature: Set and verify APEX Select Item elements

  Background: User access Activities page
    Given the user has accessed the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_admin@dataconsulting.pl' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    When user navigates to 'Customers' page
    And user presses the 'Add Customer' button

  @unit
  Scenario: Select and verify popup lov value
    When user selects 'Soho Chair' in 'Parent' popup lov element
    Then value in pop-up lov filed 'Parent' is 'Soho Chair'

  @unit
  Scenario: Popup lov value
    When user selects 'Soho Chair' in 'Parent' popup lov element
    Then value in pop-up lov filed 'Parent' is 'Soho Chair'