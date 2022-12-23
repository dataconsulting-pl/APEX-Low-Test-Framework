Feature: Set and verify APEX shuttle item

  Background: User access Activities page
    Given the user has accessed the 'https://apex.oracle.com/pls/apex/f?p=59118' url
    And user entered 'taf_demo' in 'username' field
    And user entered 'taf_demo_123password!' in 'password' field
    And user clicked on the 'Sign In' button

  @unit
  Scenario: Set Shuttle Box - List
    When user moves values 'Option 1, Option 2' in 'Shuttle Item' shuttle item
    Then values 'Option 1, Option 2' in 'Shuttle Item' shuttle item are chosen

  @unit
  Scenario: Set Shuttle Box - Single
    When user moves values 'Option 2' in 'Shuttle Item' shuttle item
    Then value 'Option 2' in 'Shuttle Item' shuttle item is chosen

  @unit
  Scenario: Remove Shuttle option
    When user moves values 'Option 1, Option 2' in 'Shuttle Item' shuttle item
    And user removes values 'Option 2' in 'Shuttle Item' shuttle item
    Then value 'Option 1' in 'Shuttle Item' shuttle item is chosen

  @unit
  Scenario: Move all + remove all
    When user moves all values in 'Shuttle Item' shuttle item
    Then values 'Option 1, Option 2' in 'Shuttle Item' shuttle item are chosen
    When user removes all values in 'Shuttle Item' shuttle item
    Then no values in 'Shuttle Item' shuttle item are chosen