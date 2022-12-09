Feature: Acceptance Test Cases - Customers

  Background: User access Customer page and resets sample data
    Given the user access the 'https://apex.oracle.com/pls/apex/r/dataconsulting/a788020211201055214889/login' url
    When user entered 'taf_admin@dataconsulting.pl' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    And user navigates to 'Reporting->Basic Reporting' page


  Scenario: Set IG Filter
    Given element in 1 row in column 'Empno' in 'Single Row View' IG has been clicked
    And user clicks on the element in 2 row in column 'Job' in 'Single Row View' IG
    And filter on column 'Mgr' Not Equals '7566' in 'Single Row View' IG is set
    And user sets filter on column 'Ename' Equals 'SCOTT' in 'Single Row View' IG
    And in 'Overwiew' IG, data starting from 1 row match table below:
      | Empno | Ename | Job     |
      | 7788  | SCOTT | ANALYST |
