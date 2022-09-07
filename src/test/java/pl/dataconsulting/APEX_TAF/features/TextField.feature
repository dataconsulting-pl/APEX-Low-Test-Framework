Feature: set and verify APEX Text field value

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'kborowski@dataconsulting.pl' in 'Username' field
    And user entered '.-$J6jhjtyZPjfd' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Activities' page


    Scenario: Enter text into simple text field
      Given user clicked on the 'Create Activity' button
      And frame 'Activity Details' is visible
      When user enters 'test 1' in 'Name' field
      Then verify, that value of text item 'Name' is 'test 1'
