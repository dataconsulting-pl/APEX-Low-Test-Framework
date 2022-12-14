Feature:IG Test Cases - Customers

  Background: User access Customer page and resets sample data
    Given the user access the 'https://apex.oracle.com/pls/apex/r/dataconsulting/a788020211201055214889/login' url
    When user entered 'taf_admin@dataconsulting.pl' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button

  Scenario: Set IG Filter 1
    Given user navigates to Reporting->Add Toolbar Button page
    When user presses the 'Create' button
    And user enters 'Adam' in 'Name' field on 'Employee' pop-up
    And user presses the 'Create' button on 'Employee' pop-up
    Then user clicks on the element in 1 row in column 'Name' in 'Add Toolbar Button' IG
    And waiting for 3 seconds

  Scenario: Set IG Filter 2
    Given user navigates to Reporting->Basic Reporting page
    When filter on column 'Job' Equals 'ANALYST' in 'Basic Reporting' IG is set
    And user sets filter on column 'Ename' Equals 'SCOTT' in 'Basic Reporting' IG
    And user clicks on the element in 1 row in column 'Ename' in 'Basic Reporting' IG
    And element in 1 row in column 'Sal' in 'Basic Reporting' IG has been clicked
    And user clicks on the element in 1 row in 1 column in 'Basic Reporting' IG
    And element in 1 row in 3 column in 'Basic Reporting' IG has been clicked
    Then in 'Basic Reporting' IG, data starting from 1 row match table below:
      | Empno | Ename | Job     | Sal  |
      | 7788  | SCOTT | ANALYST | 3000 |

  Scenario: Set IG Filter 3
    Given user navigates to Reporting->Sequence Row Header page
    When filter on column 'Salary' Not Equals '3000' in 'Sequence Row Header' IG is set
    And user sets filter on column 'Job' Equals 'CLERK' in 'Sequence Row Header' IG
    And user clicks on the element in 1 row in column 'Job' in 'Sequence Row Header' IG
    Then element in 1 row in column 'Job' in 'Sequence Row Header' IG has been clicked
    And waiting for 4 seconds

  Scenario: Set IG Filter 4
    Given user navigates to Reporting->Column Groups page
    When filter on column 'Name' Equals 'Peter Mann' in 'Column Groups' IG is set
    And waiting for 5 seconds
    Then user clicks on the element in 1 row in 1 column in 'Column Groups' IG
    Then user clicks on the element in 1 row in column 'Name' in 'Column Groups' IG




  #Given("filter on column {string} {operator} {string} in {string} IG is set")
  #Then("user sets filter on column {string} {operator} {string} in {string} IG")
  #Then("user clicks on the element in {int} row in column {string} in {string} IG")
  #Given("element in {int} row in column {string} in {string} IG has been clicked")
  #Then("user clicks on the element in {int} row in {int} column in {string} IG")
  #Given("element in {int} row in {int} column in {string} IG has been clicked")
  #Then("in {string} IG, data starting from {int} row match table below:")
