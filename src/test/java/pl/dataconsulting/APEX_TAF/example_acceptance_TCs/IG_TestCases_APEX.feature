Feature: Acceptance Test Cases - Interactiv Grid

  Background: User access Customer page and resets sample data
    Given the user access the 'https://apex.oracle.com/pls/apex/r/dataconsulting/a788020211201055214889/manage-sample-data' url
    When user entered 'taf_admin@dataconsulting.pl' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    Then user clicked on the 'Sign In' button

  Scenario: Set IG Filter 1
    Given user navigates to Reporting->Add Toolbar Button page
    When user presses the 'Create' button
    And pop-up 'Employee' is visible
    And user enters 'Peter Mann' in 'Name' field
    And user enters 'Software Tester' in 'Job' field
    And user selects 'SALES' in 'Deptartment' field
    And user enters '2000' in 'Salary' field
    And user switch on the 'On Leave' switch item
    And user enters 'Project APEX' in 'Notes' field
    And user presses the 'Create' button
    Then main page is visible
    Then user sets filter on column 'Name' Equals 'Peter Mann' in 'Add Toolbar Button' IG
    Then element in 1 row in column 'Name' in 'Add Toolbar Button' IG has been clicked
