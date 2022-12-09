Feature: Acceptance Test Cases - Partners

  Background: User access Customer page and resets sample data
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    When user entered 'taf_admin@dataconsulting.pl' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    And user navigates to 'Administration->Configuration->Sample Data' page
    And user presses the 'Reset Data' button on 'Manage Sample Data' pop-up
    Then user navigates to 'Partners' page

  Scenario: Create Partner
    Given user navigates to 'Partner' page
    When user clicked on the 'Create Partner' button
    And user enters 'OPL Project' in 'Name' field on 'Implementation Partner Details' pop-up
    And user enters 'Customized software is key for your success' in 'Description' field on 'Implementation Partner Details' pop-up
    And user enters 'http://wwwww.opl.com' in 'Website' field on 'Implementation Partner Details' pop-up
    And user presses the 'Create' button on 'Implementation Partner Details' pop-up
    And user clicks on the element in 1 row in column 'Edit' in 'Partners' IR



