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
    And user entered 'kborowski@dataconsulting.pl' in 'Username' field
    And user entered '.-$J6jhjtyZPjfd' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Activities' page


  Rule: Create new activity - only mandatory data
    Scenario: Create new activity - only mandatory data
      Given user clicked on the 'Create Activity' button
      When user selects 'Meeting' in 'Type' field on 'Activity Details' pop-up
      And user enters 'test 1' in 'Name' field on 'Activity Details' pop-up
      And user presses the 'Create' button on 'Activity Details' pop-up
      Then at least one record can be found in 'Activities' IR by:
        | Type    | Name   |
        | Meeting | test 1 |
      And in 'Activities' IR, data starting from 1 row match table below:
        | Type    | Name   | Activity Date | Owner | Location | References | Updated           | Updated by                   |
        | Meeting | test 1 | ^$            | ^$    | ^$       | 0          | ^\d+ seconds ago$  | kborowski@dataconsulting.pl |


  Rule: Create new activity - only mandatory data (expected fail)
    Scenario: Create new activity - only mandatory data (expected fail)
      Given user clicked on the 'Create Activity' button
      When user selects 'Meeting' in 'Type' field on 'Activity Details' pop-up
      And user enters 'test 1' in 'Name' field on 'Activity Details' pop-up
      And user presses the 'Save' button on 'Activity Details' pop-up
      Then at least one record can be found in 'Activities' IR by:
        | Type    | Name   |
        | Meeting | test 1 |
      And in 'Activities' IR, data starting from 1 row match table below:
        | Type    | Name   | Activity Date | Owner | Location | References | Updated           | Updated by                   |
        | Meeting | test 1 | ^$            | ^$    | ^$       | 0          | ^\d+ seconds ago$  | kborowski@dataconsulting.pl |

  Rule: Type and Name are mandatory fields
    Scenario: Create activity with missing Name
      Given user clicked on the 'Create Activity' button
      When user selects 'Meeting' in 'Type' field on 'Activity Details' pop-up
      And user presses the 'Create' button on 'Activity Details' pop-up
      Then validation message 'Name must have some value.' for 'Name' field is displayed in 'Activity Details' pop-up

    Scenario: Create activity with missing Type
      Given user clicked on the 'Create Activity' button
      When user enters 'Name only' in 'Name' field on 'Activity Details' pop-up
      And user presses the 'Create' button on 'Activity Details' pop-up
      Then validation message 'Type must have some value.' for 'Type' field is displayed in 'Activity Details' pop-up

  Rule: Create activity with all data and activity types
    Scenario Outline: Create new activity - complete data with all activity types
      Given user clicked on the 'Create Activity' button
      When user selects '<type>' in 'Type' field on 'Activity Details' pop-up
      And user enters '<name>' in 'Name' field on 'Activity Details' pop-up
      And user switch on the 'Set Date' switch item on 'Activity Details' pop-up
      And user enters '10.09.2022' in 'Activity Date' field on 'Activity Details' pop-up
      And user switch on the 'Set Owner' switch item on 'Activity Details' pop-up
      And user enters 'pkantyka' in 'Owner' field on 'Activity Details' pop-up
      And user switch on the 'Set Location' switch item on 'Activity Details' pop-up
      And user enters 'Warsaw' in 'Location' field on 'Activity Details' pop-up
      And user enters 'Conference about APEX in Warsaw' in 'Description' field on 'Activity Details' pop-up
      And user presses the 'Create' button on 'Activity Details' pop-up
      Then at least one record can be found in 'Activities' IR by:
        | Type   | Name   |
        | <type> | <name> |
      And in 'Activities' IR, data starting from 1 row match table below:
        | Type   | Name   | Activity Date            | Owner    | Location | References | Updated           | Updated by                  |
        | <type> | <name> | Sunday, 09 October, 2022 | pkantyka | Warsaw   | 0          | ^\d+ seconds ago$ | kborowski@dataconsulting.pl |

      Examples:
        | type         | name       |
        | Conference   | activity 1 |
#        | Meeting      | activity 2 |
#        | Phone Call   | activity 3 |
#        | Presentation | activity 4 |
