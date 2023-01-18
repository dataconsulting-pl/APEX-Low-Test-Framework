Feature: Set and verify checkbox value

  Background: User access Activities page
    Given the user has accessed the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_admin@dataconsulting.pl' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Products' page

  @unit
  Scenario: Click on Link by text
    When user clicks on the 'Peregrine Standard Edition' link
    Then waiting for 5 seconds


