Feature: Test Cases - Contacts

  Background: User access Customers page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    When user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button

  Scenario: Enter the text into text field 1
    Given user navigates to 'Contacts' page
    When user clicked on the 'Add Contact' button
    And frame 'Contact Details' is visible
    #Then user clicked on the '' button that contains class 'a-Button--popupLOV'

  Scenario: Download on pop-up 1
    Given user navigates to 'Contacts' page
    When user clicked on the 'Actions' button
    And user presses the 'Download' button
    #And user clicked on the 'PDF' button
    And user clicked on the 'Download' button that contains class 'ui-button--hot'
    #And user clicked on the 'HTML' button
    And user clicked on the 'Download' button that contains class 'ui-button--hot'
    #Nie mam mozliwości w tescie wybrać formatu, który chcę ściągnąć, zgłoszone jako issue

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
    Given user navigates to 'Contacts' page
    When user clicked on the 'Actions' button
    And user presses the 'Format' button
    And user presses the 'Control Break' button
    And user presses the 'Cancel' button on 'Control Break' pop-up

  Scenario: Upload Contacts Data
    Given user navigates to 'Contacts' page
    When user clicked on the 'Upload Contacts Data' button
    And user enters 'Text' in 'Copy and Paste Delimited Data' field
    And user clicked on the 'Next' button
    And user clicked on the 'Next' button
    And user clicked on the 'Load Data' button
    And user clicked on the 'Cancel' button

  Scenario: Search Report
    Given user navigates to 'Contacts' page
    Then at least one record can be found in 'Contacts' IR by:
      | Name        | Customer    |
      | Karim Nader | Bauhaus Ink |

  Scenario Outline: Search Report - for many customers
    Given user navigates to 'Contacts' page
    Then at least one record can be found in 'Contacts' IR by:
      | Name   | Customer   | Title   | Contact Type   | Phone   |
      | <name> | <customer> | <title> | <contact type> | <phone> |

    Examples:
      | name           | customer      | title | contact type | phone |
      | Ignacio Cirac  | Organic Farms | CEO   | Customer     | -     |
      | Matt Hanson    | Logistics 36  | CFO   | Customer     | -     |
      | Max Tegmark    | Bauhaus Ink   | CEO   | Customer     | -     |
      | Josh Tenenbaum | Soho Chair    | CIO   | Customer     | -     |
