Feature: Test Cases - Dashboard

  Background: User access Customers page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    When user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button

  Scenario:
    When user navigates to 'Customers' page
    Then verify, that menu option 'Customers' is selected
    When user navigates to 'Dashoboard' page
    Then verify, that menu option 'Dashboard' is selected

    #Nie mogę przejść między zakładkami z menu, dlaczego?
