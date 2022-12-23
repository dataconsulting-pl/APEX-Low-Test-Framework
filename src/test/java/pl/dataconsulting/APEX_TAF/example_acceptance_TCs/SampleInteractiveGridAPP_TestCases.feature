Feature: Acceptance Test Cases - Interactive Grid

  Background: User access Customer page
    Given the user access the 'https://apex.oracle.com/pls/apex/r/dataconsulting/a788020211201055214889' url
    When user entered 'taf_admin@dataconsulting.pl' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    Then user presses the 'Sign In' button

  Scenario: Add and verify new customer
    Given user navigates to Reporting->Add Toolbar Button page
    When user presses the 'Create' button
    And pop-up 'Employee' is visible
    And user enters 'Peter Mann' in 'Name' field
    And user enters 'Tester' in 'Job' field
    And user selects 'SALES' in 'Deptartment' field
    And user enters '2000' in 'Salary' field
    And user switches on the 'On Leave' switch item
    And user enters 'Project APEX' in 'Notes' field
    And user presses the 'Create' button
    Then main page is visible
    When user sets filter on column 'Name' Equals 'Peter Mann' in 'Add Toolbar Button' IG
    And user sets filter on column 'Job' Equals 'Tester' in 'Add Toolbar Button' IG
    Then in 'Add Toolbar Button' IG, data starting from 1 row match table below:
      | Name       | Job    | Department  | Salary |
      | Peter Mann | Tester | SALES       | 2000   |
    When user clicks on the element in 1 row in column 'Name' in 'Add Toolbar Button' IG
    Then pop-up 'Employee' has been opened
    And text item 'Name' value is 'Peter Mann'
    And text item 'Job' value is 'Tester'
    And value 'SALES' is selected in 'Deptartment' field


