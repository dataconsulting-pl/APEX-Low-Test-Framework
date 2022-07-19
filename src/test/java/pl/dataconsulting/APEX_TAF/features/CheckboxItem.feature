Feature: with DC Test Automation Framework it is possible to enter data into text field and verify data from text field

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'kborowski@dataconsulting.pl' in 'Username' field
    And user entered '.-$J6jhjtyZPjfd' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Customer' page
    And user presses the 'Add Customer' button


  Scenario: Set Radioboxes - List
    When user sets checkboxes Analyst Interview, Logo, Quote for "Reference Types" checkbox item
    Then checkboxes Analyst Interview, Logo, Quote in "Reference Types" checkbox are selected

  Scenario: Set Radioboxes - Single
    When user sets checkbox Logo for "Reference Types" checkbox item
    Then checkbox Logo in "Reference Types" checkbox is selected


  Scenario: Unset Radioboxes - List
    When user sets checkboxes Analyst Interview, Logo, Quote, Success Story for "Reference Types" checkbox item
    And user unsets checkboxes Analyst Interview, Logo, Quote for "Reference Types" checkbox item
    Then checkbox Success Story in "Reference Types" checkbox is selected

  Scenario: Unset Radioboxes - Single
    When user sets checkboxes Analyst Interview, Logo, Quote, Success Story for "Reference Types" checkbox item
    And user unsets checkbox Quote for "Reference Types" checkbox item
    Then checkbox Analyst Interview, Logo, Success Story in "Reference Types" checkbox is selected