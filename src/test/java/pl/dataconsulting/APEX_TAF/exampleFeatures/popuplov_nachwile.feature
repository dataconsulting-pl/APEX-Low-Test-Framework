Feature: Set and verify APEX Select Item elements

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Customers' page
    And user clicked on the 'Add Customer' button

  @unit
  Scenario: Select and verify popup lov value
    When user selects 'Soho Chair' in 'Parent' popup lov element
    Then verify, that value of popup lov field 'Parent' is 'Soho Chair'
    Then value in pop-up lov filed 'Parent' is 'Soho Chair'

  @unit
  Scenario: Select and verify popup lov value element
    Given user navigates to 'Contacts' page
    When user clicked on the 'Add Contact' button
    And frame 'Contact Details' is visible
    And user selects "Bauhaus Ink" in 'Customer' popup lov element
    Then user enters 'Martin Lut' in 'Name' field

  @unit
  Scenario: Popup lov value
    When user selects 'Soho Chair' in 'Parent' popup lov element
    Then verify, that value of popup lov field 'Parent' is 'Soho Chair'
    Then value in pop-up lov filed 'Parent' is 'Soho Chair'