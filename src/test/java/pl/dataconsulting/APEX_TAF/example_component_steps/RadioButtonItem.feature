Feature: Set and verify APEX Radio Buttons

  Background: User access Activities page
    Given the user has accessed the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_admin@dataconsulting.pl' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    When user navigates to Contacts page
    And user presses the 'Add Contact' button

  @unit
  Scenario: Set Radiobutton
    When user sets radioButton's option 'Customer' for 'Contact Type' radioButton item
    Then radioButton 'Customer' in 'Contact Type' radioButton is selected
    When user sets radioButton's option 'Other' for 'Contact Type' radioButton item
    Then radioButton 'Other' in 'Contact Type' radioButton is selected
