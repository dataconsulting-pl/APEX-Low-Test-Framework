Feature: Acceptance Test Cases - Activities

  Background: User access Customer page
    Given the user has accessed the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    When user entered 'taf_admin' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to Activities' page

  Scenario: Create activity with missing mandatory field
    Given user clicked on the 'Create Activity' button
    When pop-up 'Activity Details' is visible
    And user selects 'Meeting' in 'Type' field
    And user presses the 'Create' button
    Then validation message 'Name must have some value.' for 'Name' field is displayed

  Scenario: Create new activity
    Given user clicked on the 'Create Activity' button
    And pop-up 'Activity Details' is visible
    When user selects 'Conference' in 'Type' field
    And user enters 'Project Mobile App' in 'Name' field
    And user switches on the 'Set Date' switch item
    And user enters '11.28.2022' in 'Activity Date' field
    And user switches on the 'Set Owner' switch item
    And user enters 'Monika Birne' in 'Owner' field
    And user switches on the 'Set Location' switch item
    And user enters 'Berlin' in 'Location' field
    And user enters 'Low-Code development platform' in 'Description' field
    And user presses the 'Create' button
    And main page is visible
    And at least one record can be found in 'Activities' IR by:
      | Type       | Name               | Owner        | Location |
      | Conference | Project Mobile App | Monika Birne | Berlin   |
    Then in 'Activities' IR, data starting from 1 row match table below:
      | Type       | Name               | Activity Date             | Owner        | Location | Updated           | Updated by |
      | Conference | Project Mobile App | Monday, 28 November, 2022 | Monika Birne | Berlin   | ^\d+ seconds ago$ | taf_demo   |

  Scenario Outline: Create new activity - complete data with all activity types
    Given user clicked on the 'Create Activity' button
    When frame 'Activity Details' is visible
    And user selects '<type>' in 'Type' field
    And user enters '<name>' in 'Name' field
    And user switches on the 'Set Date' switch item
    And user enters '11.28.2022' in 'Activity Date' field
    And user switches on the 'Set Owner' switch item
    And user enters 'Peter Mann' in 'Owner' field
    And user switches on the 'Set Location' switch item
    And user enters 'Warsaw' in 'Location' field
    And user enters 'APEX' in 'Description' field
    And user presses the 'Create' button
    And main page is visible
    And 'Activities' IR Filter is set on columns:
      | Type   | Name   |
      | <type> | <name> |
    Then in 'Activities' IR, data starting from 1 row match table below:
      | Type   | Name   | Activity Date             | Owner      | Location | Updated           | Updated by |
      | <type> | <name> | Monday, 28 November, 2022 | Peter Mann | Warsaw   | ^\d+ seconds ago$ | taf_demo   |

    Examples:
      | type         | name   |
      | Conference   | APEX 1 |
      | Meeting      | APEX 2 |
      | Phone Call   | APEX 3 |
      | Presentation | APEX 4 |

