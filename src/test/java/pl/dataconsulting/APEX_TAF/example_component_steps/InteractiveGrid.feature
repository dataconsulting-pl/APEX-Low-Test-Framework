Feature: Set Filters - IG Test Cases - Customers

  Background: User access Customer page and resets sample data
    Given the user has accessed the 'https://apex.oracle.com/pls/apex/r/dataconsulting/a788020211201055214889/login' url
    When user enters 'taf_admin@dataconsulting.pl' in 'Username' field
    And user enters 'taf_demo_123password!' in 'Password' field
    And user presses the 'Sign In' button

  Scenario: Click on link
    When user navigates to Reporting->Add Toolbar Button page
    Then user clicks on the element in 2 row in column 'Name' in 'Add Toolbar Button' IG
    And waiting for 3 seconds

  Scenario: Click on Pencil
    When user navigates to Reporting->Single Row View page
    Then user clicks on the element in 1 row in 1 column in 'Single Row View' IG

  Scenario: Set IG Filter
    Given user has navigated to Reporting->Basic Reporting page
    And filter on column 'Job' Equals 'ANALYST' in 'Basic Reporting' IG is set
    When user sets filter on column 'Ename' Equals 'SCOTT' in 'Basic Reporting' IG
    Then in 'Basic Reporting' IG, data starting from 1 row match table below:
      | Empno | Ename | Job     | Sal  |
      | 7788  | SCOTT | ANALYST | 3000 |

