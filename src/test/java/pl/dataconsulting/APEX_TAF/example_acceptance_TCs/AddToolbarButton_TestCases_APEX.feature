Feature: Acceptance Test Cases - Customers

  Background: User access Customer page and resets sample data
    Given the user access the 'https://apex.oracle.com/pls/apex/r/dataconsulting/a788020211201055214889/login' url
    When user entered 'taf_admin@dataconsulting.pl' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    Then user clicked on the 'Sign In' button

  Scenario: Set IG Filter 1
    Given user navigates to Reporting->Add Toolbar Button page
    When user presses the 'Create' button
    And user enters 'AAA Mann' in 'Name' field on 'Employee' pop-up
    And user enters 'Software' in 'Job' field on 'Employee' pop-up
    #And user selects 'SALES' in 'Department' field
    And user enters '2000' in 'Salary' field on 'Employee' pop-up
    #And user sets radioButton's option 'Yes' for 'On Leave' radioButton item
    And user enters 'Project APEX' in 'Notes' field on 'Employee' pop-up
    And user presses the 'Create' button on 'Employee' pop-up
    And in 'Add Toolbar Button' IG, data starting from 1 row match table below:
    |Name|Job|
    |AAA Mann|Software|
    Then user clicks on the element in 1 row in column 'Name' in 'Add Toolbar Button' IG
    And waiting for 3 seconds