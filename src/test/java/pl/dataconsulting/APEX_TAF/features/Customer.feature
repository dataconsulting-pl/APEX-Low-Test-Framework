Feature: Creating Activities
  It should be possible to create a new activity. Each activity should contain information about type, Name, Date, Owner, Location and Description.
  Only Type and Name are mandatory fields. All other fields are optional.
  As Type it should be possible to choose one from the options:
  - Conference
  - Meeting
  - Phone Call
  - Presentation
  After creation of activity, the activity should be visible in Interactive Grid.

  Scenario: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'kborowski@dataconsulting.pl' in 'Username' field
    And user entered '.-$J6jhjtyZPjfd' in 'Password' field






