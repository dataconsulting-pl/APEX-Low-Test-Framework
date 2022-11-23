Feature: Activities - Test Cases

  Background: User access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Activities' page


  Scenario: Create and navigate to Activities
    Given user navigates to 'Activities' page
    When user presses the 'Create Activity' button
    And user selects 'Meeting' in 'Type' field on 'Activity Details' pop-up
    And user enters 'Spotkanie1' in 'Name' field on 'Activity Details' pop-up
    And user presses the 'Create' button on 'Activity Details' pop-up
    Then at least one record can be found in 'Activities' IR by:
      | Type    | Name         |
      | Meeting | Spotkanie1 |

  Scenario Outline: Create all activities
    Given user navigates to 'Activities' page
    When user presses the 'Create Activity' button
    And user selects '<type>' in 'Type' field on 'Activity Details' pop-up
    And user enters '<name>' in 'Name' field on 'Activity Details' pop-up
    And user presses the 'Create' button on 'Activity Details' pop-up
    Then at least one record can be found in 'Activities' IR by:
      | Type   | Name   |
      | <type> | <name> |
    And in 'Activities' IR, data starting from 1 row match table below:
      | Type   | Name   | Activity Date | Owner | Location | References | Updated          | Updated by |
      | <type> | <name> | ^$            | ^$    | ^$       | 0          | ^\d seconds ago$ | taf_demo   |

    Examples:
      | type       | name        |
      | Conference | conference0 |
      #| Meeting      | spotkanie100       |
      #| Phone Call   | telefon200         |
      #| Presentation | prezentacja100     |
  #Jak jest jedna nazwa to znajduje, ale jak jest wiele, to raz znajdzie raz nie, ponieważ nie są posortowane po updated malejąco
# Nie znajduje jak jest 'Updated' - zawsze problem z datami

  Scenario Outline: Create all activities 2 (OK)
    Given user navigates to 'Activities' page
    When user presses the 'Create Activity' button
    And user selects '<type>' in 'Type' field on 'Activity Details' pop-up
    And user enters '<name>' in 'Name' field on 'Activity Details' pop-up
    And user presses the 'Create' button on 'Activity Details' pop-up
    Then at least one record can be found in 'Activities' IR by:
      | Type   | Name   |
      | <type> | <name> |
    And in 'Activities' IR, data starting from 1 row match table below:
      | Type   | Name   | Activity Date | Owner | Location | References | Updated by |
      | <type> | <name> | ^$            | ^$    | ^$       | 0          | taf_demo   |

    Examples:
      | type       | name         |
      | Conference | conference11 |


  Scenario: Create new activity 1
    Given user navigates to 'Activities' page
    When user presses the 'Create Activity' button
    And user selected 'Meeting' in 'Type' field on 'Activity Details' pop-up
    And user enters '300Test' in 'Name' field on 'Activity Details' pop-up
    And user presses the 'Create' button on 'Activity Details' pop-up


  Scenario Outline: Create new activity with all data
    Given user clicked on the 'Create Activity' button
    When user selects '<type>' in 'Type' field on 'Activity Details' pop-up
    And user enters '<name>' in 'Name' field on 'Activity Details' pop-up
    And user switch on the 'Set Date' switch item on 'Activity Details' pop-up
    And user enters '<date>' in 'Activity Date' field on 'Activity Details' pop-up
    And user switch on the 'Set Owner' switch item on 'Activity Details' pop-up
    And user enters 'JuliaF' in 'Owner' field on 'Activity Details' pop-up
    And user switch on the 'Set Location' switch item on 'Activity Details' pop-up
    And user enters 'Sopot' in 'Location' field on 'Activity Details' pop-up
    And user enters 'Apex TAK' in 'Description' field on 'Activity Details' pop-up
    And user presses the 'Create' button on 'Activity Details' pop-up
    Then at least one record can be found in 'Activities' IR by:
      | Type   | Name   | Activity Date |
      | <type> | <name> | <date_IR>     |
    And in 'Activities' IR, data starting from 1 row match table below:
      | Type   | Name   | Activity Date | Owner | Location | References | Updated           | Updated by |
      | <type> | <name> | <date_IR>     | Julia | Gdansk   | 0          | ^\d+ seconds ago$ | taf_demo   |
    And waiting for 5 seconds

    Examples:
      | type       | name        | date       | date_IR                   |
      | Conference | activity xx | 11/07/2022 | Monday, 07 November, 2022 |


       # | Meeting      | activity 200 | 11/14/2022 | Monday, 14 November, 2022 |
       # | Phone Call   | activity 300 | 11/21/2022 | Monday, 21 November, 2022 |
       # | Presentation | activity 400 | 11/28/2022 | Monday, 28 November, 2022 |



  Scenario Outline: Create new activity - complete data with all activity types
    Given user clicked on the 'Create Activity' button
    When user selects '<type>' in 'Type' field on 'Activity Details' pop-up
    And user enters '<name>' in 'Name' field on 'Activity Details' pop-up
    And user switch on the 'Set Date' switch item on 'Activity Details' pop-up
    And user enters '11/07/2022' in 'Activity Date' field on 'Activity Details' pop-up
    And user switch on the 'Set Owner' switch item on 'Activity Details' pop-up
    And user enters 'jfaryj' in 'Owner' field on 'Activity Details' pop-up
    And user switch on the 'Set Location' switch item on 'Activity Details' pop-up
    And user enters 'Sopot' in 'Location' field on 'Activity Details' pop-up
    And user enters 'APEX Sopot' in 'Description' field on 'Activity Details' pop-up
    And user presses the 'Create' button on 'Activity Details' pop-up
    Then at least one record can be found in 'Activities' IR by:
      | Type   | Name   |
      | <type> | <name> |
    And in 'Activities' IR, data starting from 1 row match table below:
      | Type   | Name   | Activity Date             | Owner  | Location | References | Updated           | Updated by |
      | <type> | <name> | Monday, 07 November, 2022 | jfaryj | Sopot    | 0          | ^\d+ seconds ago$ | taf_demo   |

    Examples:
      | type       | name |
      | Conference | A11  |
      #| Meeting      | A2   |
      #| Phone Call   | A3   |
      #| Presentation | A4   |

  Scenario Outline: Create new activity - complete data with all activity types ccc
    Given user clicked on the 'Create Activity' button
    When user selects '<type>' in 'Type' field on 'Activity Details' pop-up
    And user enters '<name>' in 'Name' field on 'Activity Details' pop-up
    And user switch on the 'Set Date' switch item on 'Activity Details' pop-up
    And user enters '11/07/2022' in 'Activity Date' field on 'Activity Details' pop-up
    And user switch on the 'Set Owner' switch item on 'Activity Details' pop-up
    And user enters 'jfaryj' in 'Owner' field on 'Activity Details' pop-up
    And user switch on the 'Set Location' switch item on 'Activity Details' pop-up
    And user enters 'Sopot' in 'Location' field on 'Activity Details' pop-up
    And user enters 'AP10033' in 'Description' field on 'Activity Details' pop-up
    And user presses the 'Create' button on 'Activity Details' pop-up
    Then at least one record can be found in 'Activities' IR by:
      | Type   | Name   |
      | <type> | <name> |
    And in 'Activities' IR, data starting from 1 row match table below:
      | Type   | Name   | Activity Date             | Owner  | Location | References | Updated           | Updated by |
      | <type> | <name> | Monday, 07 November, 2022 | jfaryj | Sopot    | 0          | ^\d+ seconds ago$ | taf_demo   |

    Examples:
      | type       | name |
      | Conference | Lala |