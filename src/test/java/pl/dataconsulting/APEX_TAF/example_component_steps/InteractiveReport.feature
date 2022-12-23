Feature: Set filter and validate data on APEX Interactive Report

  Background: User access Activities page
    Given the user has accessed the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to Activities page

  @unit
  Scenario: Set IG Filter and verify record exists
    Given 'Activities' IR Filter is set on columns:
      | Type         | Name           |
      | Presentation | Cloud Overview |
    Then in 'Activities' IR, data starting from 1 row match table below:
      | Type         | Name           | Activity Date | Owner        | Location | References | Updated      |
      | Presentation | Cloud Overview | ^$            | Irene Walker | ^$       | 2          | ^\d+ .* ago$ |
