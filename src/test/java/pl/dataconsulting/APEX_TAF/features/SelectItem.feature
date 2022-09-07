Feature: Set and verify APEX Select Item elements

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'kborowski@dataconsulting.pl' in 'Username' field
    And user entered '.-$J6jhjtyZPjfd' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Activities' page


    Scenario: Enter text into simple text field
      Given user clicked on the 'Create Activity' button
      And frame 'Activity Details' is visible
      When user selects 'Meeting' in 'Type' field
      Then value 'Meeting' is selected in 'Type' field
