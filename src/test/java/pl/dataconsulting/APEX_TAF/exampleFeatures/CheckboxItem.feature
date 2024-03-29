Feature: Set and verify checkbox value

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Customer' page
    And user presses the 'Add Customer' button

  @unit
  Scenario: Set Checkbox - List
    When user sets checkboxes 'Analyst Interview, Logo, Quote' for "Reference Types" checkbox item
    Then checkboxes 'Analyst Interview, Logo, Quote' in "Reference Types" checkbox are selected

  @unit
  Scenario: Set Checkbox - Single
    When user sets checkbox 'Logo' for "Reference Types" checkbox item
    Then checkbox 'Logo' in "Reference Types" checkbox is selected

  @unit
  Scenario: Unset Checkbox - List
    When user sets checkboxes 'Analyst Interview, Logo, Quote, Success Story' for "Reference Types" checkbox item
    And user unsets checkboxes 'Analyst Interview, Logo, Quote' for "Reference Types" checkbox item
    Then checkbox 'Success Story' in "Reference Types" checkbox is selected

  @unit
  Scenario: Unset Checkbox - Single
    When user sets checkboxes 'Analyst Interview, Logo, Quote, Success Story' for "Reference Types" checkbox item
    And user unsets checkbox 'Quote' for "Reference Types" checkbox item
    Then checkbox 'Analyst Interview, Logo, Success Story' in "Reference Types" checkbox is selected