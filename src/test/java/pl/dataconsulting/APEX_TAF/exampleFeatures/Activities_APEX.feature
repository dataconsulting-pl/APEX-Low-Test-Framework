Feature: Example Feature File - Creating Activities
  It should be possible to create a new activity. Each activity should contain information about type, Name, Date, Owner, Location and Description.
  Only Type and Name are mandatory fields. All other fields are optional.
  As Type it should be possible to choose one from the options:
  - Conference
  - Meeting
  - Phone Call
  - Presentation
  After creation of activity, the activity should be visible in Interactive Grid.

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Activities' page

  @demo
  Rule: Create new activity - only mandatory data

  Scenario: Create new activity - only mandatory data
    Given user clicked on the 'Create Activity' button
    And pop-up 'Activity Details' is visible
    When user selects 'Meeting' in 'Type' field
    And user enters 'test 1' in 'Name' field
    And user presses the 'Create' button
    Then main page is visible
    And at least one record can be found in 'Activities' IR by:
      | Type    | Name   |
      | Meeting | test 1 |
    And in 'Activities' IR, data starting from 1 row match table below:
      | Type    | Name   | Activity Date | Owner | Location | Updated           | Updated by |
      | Meeting | test 1 |               |       |          | ^\d+ seconds ago$ | taf_demo   |

  @demo
  Rule: Type and Name are mandatory fields

  Scenario: Create activity with missing Name
    Given user clicked on the 'Create Activity' button
    And pop-up 'Activity Details' is visible
    When user selects 'Meeting' in 'Type' field
    And user presses the 'Create' button
    Then validation message 'Name must have some value.' for 'Name' field is displayed

  Scenario: Create activity with missing Type
    Given user clicked on the 'Create Activity' button
    And pop-up 'Activity Details' is visible
    When user enters 'Name only' in 'Name' field on 'Activity Details' pop-up
    And user presses the 'Create' button on 'Activity Details' pop-up
    Then validation message 'Type must have some value.' for 'Type' field is displayed

  @demo
  Rule: Create activity with all data and activity types

  Scenario Outline: Create new activity - complete data with all activity types
    Given user clicked on the 'Create Activity' button
    And frame 'Activity Details' is visible
    When user selects '<type>' in 'Type' field
    And user enters '<name>' in 'Name' field
    And user switch on the 'Set Date' switch item
    And user enters '10.09.2022' in 'Activity Date' field
    And user switch on the 'Set Owner' switch item
    And user enters 'pkantyka' in 'Owner' field
    And user switch on the 'Set Location' switch item
    And user enters 'Warsaw' in 'Location' field
    And user enters 'APEX conference - Warsaw' in 'Description' field
    And user presses the 'Create' button
    Then main page is visible
    And at least one record can be found in 'Activities' IR by:
      | Type   | Name   |
      | <type> | <name> |
    And in 'Activities' IR, data starting from 1 row match table below:
      | Type   | Name   | Activity Date            | Owner    | Location  | Updated           | Updated by |
      | <type> | <name> | Sunday, 09 October, 2022 | pkantyka | Warsaw    | ^\d+ seconds ago$ | taf_demo   |

    Examples:
      | type         | name       |
      | Conference   | activity 1 |
      | Meeting      | activity 2 |
      | Phone Call   | activity 3 |
      | Presentation | activity 4 |

