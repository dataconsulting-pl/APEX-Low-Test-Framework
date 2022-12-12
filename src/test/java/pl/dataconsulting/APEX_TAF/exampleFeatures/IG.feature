Feature: Set filter and validate data on APEX Interactive Report

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/r/dataconsulting/a788020211201055214889/login' url
    And user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button

  Scenario: Click on link
    When user navigates to Reporting->Add Toolbar Button page
    Then user clicks on the element in 2 row in column 'Name' in 'Add Toolbar Button' IG
    And waiting for 3 seconds

  Scenario: Click on Pencil
    When user navigates to Reporting->Single Row View page
    Then user clicks on the element in 1 row in 1 column in 'Single Row View' IG



