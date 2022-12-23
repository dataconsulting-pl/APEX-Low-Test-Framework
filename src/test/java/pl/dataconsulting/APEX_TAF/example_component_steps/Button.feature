Feature: Set and verify checkbox value

  Background: User access Activities page
    Given the user has accessed the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Customer' page

  @unit
  Scenario: Button with span
    When user presses the 'Add Customer' button
    Then user presses the 'Cancel' button

  @unit
  Scenario: Button without span
    When user presses the 'Check All' button
    Then user presses the 'Uncheck All' button

  @unit
  Scenario: Button on pop-up
    Given user has navigated to 'Activities' page
    When user presses the 'Create Activity' button
    And pop-up 'Activity Details' is visible
    And user selects 'Conference' in 'Type' field
    And user enters 'Project Mobile App' in 'Name' field
    Then user presses the 'Cancel' button

  @unit
  Scenario: Button that contains class
    Given user has navigated to 'Contacts' page
    When user presses the 'Actions' button
    And user presses the 'Download' button
    Then user presses the 'Download' button that contains class 'ui-button--hot'

  @unit
  Scenario: 'Cancel' Button on popup
    Given user has navigated to 'Contacts' page
    When user presses the 'Actions' button
    And user presses the 'Download' button
    Then user presses the 'Cancel' button on 'Download' pop-up

