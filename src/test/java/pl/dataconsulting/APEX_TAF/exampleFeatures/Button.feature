Feature: Set and verify checkbox value

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
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
    Given user navigates to 'Activities' page
    When user clicked on the 'Create Activity' button
    And pop-up 'Activity Details' is visible
    When user selects 'Conference' in 'Type' field
    And user enters 'Project Mobile App' in 'Name' field
    Then user presses the 'Create' button on 'Activity Details' pop-up

  @unit
  Scenario: Button that contains class
    Given user navigates to 'Contacts' page
    When user clicked on the 'Actions' button
    And user presses the 'Download' button
    And user presses the 'Download' button that contains class 'ui-button--hot'
    Then user clicked on the 'Download' button that contains class 'ui-button--hot'

  @unit
  Scenario: 'Cancel' Button on popup
    When user navigates to 'Contacts' page
    And user clicked on the 'Actions' button
    And user presses the 'Download' button
    And user presses the 'Cancel' button on 'Download' pop-up

