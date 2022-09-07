Feature: Set filter and validate data on APEX Interactive Report

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'kborowski@dataconsulting.pl' in 'Username' field
    And user entered '.-$J6jhjtyZPjfd' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to Activities page


  Rule: Create new activity - only mandatory data
    Scenario: Create new activity - only mandatory data
      When at least one record can be found in 'Activities' IR by:
        | Type         | Name           |
        | Presentation | Cloud Overview |
      Then in 'Activities' IR, data starting from 1 row match table below:
        | Type         | Name           | Activity Date | Owner        | Location | References | Updated          | Updated by                 |
        | Presentation | Cloud Overview | ^$            | Irene Walker | ^$       | 2          | ^\d+ weeks ago$  | pkantyka@dataconsulting.pl |