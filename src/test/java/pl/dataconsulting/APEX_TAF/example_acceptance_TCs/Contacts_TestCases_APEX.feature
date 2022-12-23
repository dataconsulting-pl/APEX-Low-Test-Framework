Feature: Acceptance Test Cases - Contacts

  Background: User access Customer page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    When user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Contacts' page

  Scenario: Add Customer Contact
    Given user presses the 'Add Contact' button
    When user selects 'Bauhaus Ink' in 'Customer' popup lov element
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
    And 'Contacts' IR Filter is set on columns:
      | Name        | Customer    |
      | Greg Muller | Bauhaus Ink |
    Then in 'Contacts' IR, data starting from 1 row match table below:
      | Name        | Customer    | Title | Contact Type | Phone      | Email             |
      | Greg Muller | Bauhaus Ink | CEO   | Other        | 1234345433 | bauhaus@gmail.com |