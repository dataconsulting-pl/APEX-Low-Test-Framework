Feature: Set Filters - IG Test Cases - Customers

  Background: User access Customer page and resets sample data
    Given the user has accessed the 'https://apex.oracle.com/pls/apex/r/dataconsulting/a788020211201055214889' url
    When user enters 'taf_admin@dataconsulting.pl' in 'Username' field
    And user enters 'taf_demo_123password!' in 'Password' field
    And user presses the 'Sign In' button

  @unit
  Scenario: Pass from Text into Text field
    When user navigates to Reporting->Add Toolbar Button page
    And user clicks on the element in 1 row in column 'Name' in 'Add Toolbar Button' IG
    And pop-up 'Employee' has been opened
    Then text from text field 'Name' is saved in -> NameVar
    And user enters @$NameVar in 'Notes' field
    And text item 'Notes' value is 'ABC'

  @unit
  Scenario: Pass Text of the selected option into Text field
    When user navigates to Reporting->Add Toolbar Button page
    And user clicks on the element in 1 row in column 'Name' in 'Add Toolbar Button' IG
    And pop-up 'Employee' has been opened
    Then text from text field 'Hire Date' is saved in -> NameVar
    And user enters @$NameVar in 'Notes' field
    And text item 'Notes' value is '12-JAN-83'

  @unit
  Scenario: Pass Text of the IG cell into Text field
    When user navigates to Reporting->Add Toolbar Button page
    And text from cell in row 1 and column 'Job' in IG 'Add Toolbar Button' is saved in -> NameVar
    And user clicks on the element in 1 row in column 'Name' in 'Add Toolbar Button' IG
    And pop-up 'Employee' has been opened
    And user enters @$NameVar in 'Notes' field
    Then text item 'Notes' value is 'ANALYST'

  @unit
  Scenario: Generate random String
    Given random text whose length is 8 letter is saved in -> NameVar
    When user navigates to Reporting->Add Toolbar Button page
    And user clicks on the element in 1 row in column 'Name' in 'Add Toolbar Button' IG
    And pop-up 'Employee' has been opened
    And user enters @$NameVar in 'Notes' field

  @unit
  Scenario: Generate random Number
    Given random number whose length is 15 digits is saved in -> NameVar
    When user navigates to Reporting->Add Toolbar Button page
    And user clicks on the element in 1 row in column 'Name' in 'Add Toolbar Button' IG
    And pop-up 'Employee' has been opened
    And user enters @$NameVar in 'Notes' field
