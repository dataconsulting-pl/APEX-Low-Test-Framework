Feature: Set and verify APEX Switch Item


  Background: User access Activities page
    Given the user has accessed the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_admin@dataconsulting.pl' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Activities' page

  @unit
  Scenario: Switch on item
    Given user clicked on the 'Create Activity' button
    And frame 'Activity Details' is visible
    When user switches on the 'Set Owner' switch item
    Then verify, that switch item 'Set Owner' is on
    When user switches off the 'Set Owner' switch item
    Then verify, that switch item 'Set Owner' is off
