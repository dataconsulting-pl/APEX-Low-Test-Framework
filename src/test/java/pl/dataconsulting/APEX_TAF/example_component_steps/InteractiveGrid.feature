Feature: Set Filters - IG Test Cases - Customers

  Background: User access Customer page and resets sample data
    Given the user has accessed the 'https://apex.oracle.com/pls/apex/r/dataconsulting/a788020211201055214889/manage-sample-data' url
    When user enters 'taf_admin@dataconsulting.pl' in 'Username' field
    And user enters 'taf_demo_123password!' in 'Password' field
    And user presses the 'Sign In' button
    Then user presses the 'Reset Data' button

  @unit
  Scenario: Click on link
    When user navigates to Reporting->Add Toolbar Button page
    Then user clicks on the element in 2 row in column 'Name' in 'Add Toolbar Button' IG
    And waiting for 3 seconds

  @unit
  Scenario: Click on Pencil
    When user navigates to Reporting->Single Row View page
    Then user clicks on the element in 1 row in 1 column in 'Single Row View' IG
    Then waiting for 10 seconds

  @unit
  Scenario: Set IG Filter
    Given user has navigated to Editing->Basic Editing page
    And filter on column 'Job' Equals 'Salesman' in 'Basic Editing' IG is set
    And filter on column 'Name' Equals 'ALLEN' in 'Basic Editing' IG is set
    Then in 'Basic Editing' IG, data starting from 1 row match table below:
      | Name  | Job      | Manager | Hire Date | Salary | Commission | On Leave | Notes | Department |
      | ALLEN | Salesman | BLAKE   | 20-FEB-81 | 1600   | 300        | N        |       | SALES      |

  @unit
  Scenario: Edit IG values
    Given user has navigated to Editing->Basic Editing page
    When user sets the value 'NOWAK' in the cell in 1 row in column 'Name' in 'Basic Editing' IG
    And user sets the value 'Manager' in the cell in 1 row in column 'Job' in 'Basic Editing' IG
    And user sets the value '900' in the cell in 1 row in column 'Salary' in 'Basic Editing' IG
    And user selects the value 'KING' in the cell in 1 row in column 'Manager' in 'Basic Editing' IG
    And user presses the 'Save' button
    Then in 'Basic Editing' IG, data starting from 1 row match table below:
      | Name  | Job     | Salary | Manager |
      | NOWAK | Manager | 900    | KING    |


  @unit
  Scenario: Edit IG values from table
    Given user has navigated to Editing->Basic Editing page
    When user sets the values from table in cells in IG 'Basic Editing' starting from 1 row:
      | Name | Job     | Manager | Hire Date | Salary | Commission | On Leave | Notes    | Department |
      | REC1 | Manager | KING    | 12-FEB-99 | 6540   | 350        | Y        | Vacation | SALES      |
      | REC2 | Manager | CLARK   | 20-JAN-88 | 1241   | 150        | N        |          | OPERATIONS |
    And user presses the 'Save' button
    Then in 'Basic Editing' IG, data starting from 1 row match table below:
      | Name | Job     | Manager | Hire Date | Salary | Commission | On Leave | Notes    | Department |
      | REC1 | Manager | KING    | 12-FEB-99 | 6540   | 350        | Y        | Vacation | SALES      |
      | REC2 | Manager | CLARK   | 20-JAN-88 | 1241   | 150        | N        |          | OPERATIONS |


  @unit
  Scenario: Verify column order
    Given user has navigated to Editing->Basic Editing page
    Then the position of columns in IG 'Basic Editing' is as in table:
      | 2    | 3   | 4       | 5         | 6      | 7          | 8        | 9     | 10         |
      | Name | Job | Manager | Hire Date | Salary | Commission | On Leave | Notes | Department |
