Feature: Set filter and validate data on APEX Interactive Report

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to Activities page

  @unit
  Scenario: Set IG Filter and verify record exists
    When at least one record can be found in 'Activities' IR by:
      | Type         | Name           |
      | Presentation | Cloud Overview |
    Then in 'Activities' IR, data starting from 1 row match table below:
      | Type         | Name           | Activity Date | Owner        | Location | References | Updated          | Updated by                 |
      | Presentation | Cloud Overview | ^$            | Irene Walker | ^$       | 2          | ^\d+ months ago$ | pkantyka@dataconsulting.pl |

  Scenario: Click on the element in cell
    When user clicks on the element in 0 row in column 'Name' in 'Activities' IR

  Scenario: Click on the element in cell 2
    Given user navigates to 'Contacts' page
    And user clicks on the element in 1 row in column 'Name' in 'Contacts' IR