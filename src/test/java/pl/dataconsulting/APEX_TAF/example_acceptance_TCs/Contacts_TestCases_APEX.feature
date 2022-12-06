Feature: Acceptance Test Cases - Contacts

  Background: User access Customer page and resets sample data
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    When user entered 'taf_admin@dataconsulting.pl' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    And user navigates to 'Administration->Configuration->Sample Data' page
    And user presses the 'Reset Data' button on 'Manage Sample Data' pop-up
    Then user navigates to 'Contacts' page

  Scenario: Add Customer Contact
    Given user presses the 'Add Contact' button
    When user select 'Bauhaus Ink' in 'Customer' popup lov element
    And user enters 'Greg Muller' in 'Name' field
    And user sets radioButton's option 'Other' for 'Contact Type' radioButton item
    And user enters 'CEO' in 'Title' field
    And user enters 'Bauhaus' in 'Company' field
    And user enters 'bauhaus@gmail.com' in 'Email' field
    And user enters '1234345433' in 'Phone' field
    And user enters '321' in 'Cell Phone' field
    And user enters '343223323' in 'Fax' field
    And user presses the 'Address / Country' button
    And user enters 'Wolska Street' in 'Address' field
    And user enters 'Warsaw' in 'City' field
    And user enters 'Poland' in 'Country' field
    And user presses the 'Social' button
    And user enters 'http://www.facebook.com' in 'Facebook' field
    And user presses the 'Add Customer Contact' button
    Then at least one record can be found in 'Contacts' IR by:
      | Name        | Customer    | Title | Contact Type | Phone      | Email             |
      | Greg Muller | Bauhaus Ink | CEO   | Other        | 1234345433 | bauhaus@gmail.com |