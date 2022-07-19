Feature: with DC Test Automation Framework it is possible to enter data into text field and verify data from text field

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/f?p=59118' url
    And user entered 'kborowski@dataconsulting.pl' in 'username' field
    And user entered '.-$J6jhjtyZPjfd' in 'password' field
    And user clicked on the 'Sign In' button


  Scenario: Set Shuttle Box - List
    When user moves values Option 1, Option 2 in 'Shuttle Item' shuttle item
    Then values Option 1, Option 2 in 'Shuttle Item' shuttle item are chosen

  Scenario: Set Shuttle Box - Single
    When user moves values Option 2 in 'Shuttle Item' shuttle item
    Then value Option 2 in 'Shuttle Item' shuttle item is chosen


  Scenario: Remove Shuttle option
    When user moves values Option 1, Option 2 in 'Shuttle Item' shuttle item
    And user removes values Option 2 in 'Shuttle Item' shuttle item
    Then value Option 1 in 'Shuttle Item' shuttle item is chosen


  Scenario: Move all + remove all
    When user moves all values in 'Shuttle Item' shuttle item
    Then values Option 1, Option 2 in 'Shuttle Item' shuttle item are chosen
    When user removes all values in 'Shuttle Item' shuttle item
    Then no values in 'Shuttle Item' shuttle item are chosen