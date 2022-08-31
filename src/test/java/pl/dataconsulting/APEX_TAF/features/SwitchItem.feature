Feature: with DC Test Automation Framework it is possible to set switch item to on or off


  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'kborowski@dataconsulting.pl' in 'Username' field
    And user entered '.-$J6jhjtyZPjfd' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Activities' page


  Scenario: Switch on item
    Given user clicked on the 'Create Activity' button
    And frame 'Activity Details' is visible
    When user switch on the 'Set Owner' switch item
    Then verify, that switch item 'Set Owner' is on
    When user switch off the 'Set Owner' switch item
    Then verify, that switch item 'Set Owner' is off
