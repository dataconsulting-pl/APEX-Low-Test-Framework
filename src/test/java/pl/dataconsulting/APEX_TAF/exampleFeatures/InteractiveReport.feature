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
      | Type         | Name           | Activity Date | Owner        | Location | References |
      | Presentation | Cloud Overview | ^$            | Irene Walker | ^$       | 2          |

  @unit
  Scenario Outline: Search Report - for many customers
    Given user navigates to 'Contacts' page
    Then at least one record can be found in 'Contacts' IR by:
      | Name   | Customer   | Title   | Contact Type   | Phone   |
      | <name> | <customer> | <title> | <contact type> | <phone> |

    Examples:
      | name           | customer      | title | contact type | phone |
      | Ignacio Cirac  | Organic Farms | CEO   | Customer     | -     |
      | Matt Hanson    | Logistics 36  | CFO   | Customer     | -     |
      | Max Tegmark    | Bauhaus Ink   | CEO   | Customer     | -     |
      | Josh Tenenbaum | Soho Chair    | CIO   | Customer     | -     |