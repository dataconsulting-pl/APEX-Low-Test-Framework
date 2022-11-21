Feature: Test Cases - Contacts

  Background: User access Customers page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    When user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button

  Scenario: Download on pop-up
    When user navigates to 'Contacts' page
    And user clicked on the 'Actions' button
    And user presses the 'Download' button
    And waiting for 3 seconds
    And user presses the 'Download' button on 'Download' pop-up
    And waiting for 3 seconds

  Scenario: Download on pop-up 1
    When user navigates to 'Contacts' page
    And user clicked on the 'Actions' button
    And user presses the 'Download' button

  Scenario: Cancel on pop-up 2
    When user navigates to 'Contacts' page
    And user clicked on the 'Actions' button
    And user presses the 'Download' button
    And user presses the 'Cancel' button on 'Download' pop-up
    And waiting for 3 seconds


  Scenario: Cancel on pop-up 3
    When user navigates to 'Contacts' page
    And user clicked on the 'Actions' button
    And user presses the 'Filter' button
    And user presses the 'Cancel' button on 'Filter' pop-up


  Scenario: Apply on pop-up 4
    When user navigates to 'Contacts' page
    And user clicked on the 'Actions' button
    And user presses the 'Filter' button
    And user presses the 'Apply' button on 'Filter' pop-up

  Scenario: Apply on pop-up 5
    When user navigates to 'Contacts' page
    And user clicked on the 'Actions' button
    And user presses the 'Format' button
    And user presses the 'Control Break' button
    And user presses the 'Apply' button on 'Control Break' pop-up



  Scenario: Cancel on pop-up 6
    When user navigates to 'Contacts' page
    And user clicked on the 'Actions' button
    And user presses the 'Format' button
    And user presses the 'Control Break' button
    And user presses the 'Cancel' button on 'Control Break' pop-up
    And waiting for 3 seconds