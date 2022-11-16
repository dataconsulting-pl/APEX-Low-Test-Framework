Feature: Test Cases - Customers

  Background: User access Customers page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    When user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Customers' page
    Then verify, that menu option 'Customers' is selected

  Rule: Process with customer view
    Scenario: Add new Costumer
      Given user clicked on the 'Add Customer' button
      When user enters 'Eliza Mann' in 'Customer Name' field
      And user clicked on the 'Add Customer' button
      # Then verify, that XYZ is added

  Rule: Delete a Customer
    Scenario: Delete a Customer
      Given user navigates to 'Customers' page
      When user enters 'Eliza Mann' in 'Search' field
      #Then ... nie ma możliwości kliknąć w item w container grid, zgłoszony BUG

    Scenario: Add and edit new Customer
      Given user clicked on the 'Add Customer' button
      When user enters 'Annas Mann' in 'Customer Name' field
      And user presses the 'Add Customer' button
      And user presses the 'Edit Customer' button
      And user enters 'Katrina Mann' in 'Customer Name' field
      And user presses the 'Apply Changes' button
      And waiting for 2 seconds

    Scenario: Add and edit and cancel new Customer
      Given user clicked on the 'Add Customer' button
      When user enters 'Anna Mad' in 'Customer Name' field
      And user presses the 'Add Customer' button
      And user presses the 'Edit Customer' button
      And user enters 'DMann' in 'Customer Name' field
      And user presses the 'Cancel' button
      And waiting for 2 seconds

    Scenario: Add, edit, cancel and delete a new Customer
      Given user clicked on the 'Add Customer' button
      When user enters 'Pauline Mann' in 'Customer Name' field
      And user presses the 'Add Customer' button
      And user presses the 'Edit Customer' button
      And user enters 'Pauline Scholz' in 'Customer Name' field
      And user presses the 'Apply Changes' button
      And user presses the 'Edit Customer' button
      And user presses the 'Cancel' button
      And user presses the 'Edit Customer' button
      And user presses the 'Delete' button
      And user presses the 'OK' button on 'Would you like to perform this delete action?' pop-up
      #Then verify, that XYZ is deleted

    Scenario: Find Customer
      Given user enters 'Eliza Mann' in 'Search' field
      And waiting for 10 seconds

    Scenario: Add existing Customer (expected fail)
      Given user clicked on the 'Add Customer' button
      When user enters 'Eliza Mann' in 'Customer Name' field
      And user clicked on the 'Add Customer' button
      Then validation message 'unique constraint (DATACONSULTING.EBA_CUST_CUSTOMERS_UK) violated' for 'Customer Name' field is displayed

    Scenario: Customer Views - Report View
      When user navigates to 'Customers' page
      And user sets radioButton's option 'Report View' for 'Display as' radioButton item
  #Zgłoszony Bug

    Scenario: Customer Views - Logo View
      When user navigates to 'Customers' page
      And user sets radioButton's option 'Logo View' for 'Display as' radioButton item
    #Zgłoszony Bug

    Scenario: Customer Views - Cards View
      When user navigates to 'Customers' page
      And user sets radioButton's option 'Cards View' for 'Display as' radioButton item
    #Zgłoszony Bug

    Scenario: Set Reference Type Checkbox - single value
      When user sets checkboxes 'Quote' for 'Reference Types' checkbox item
      Then checkbox 'Quote' in 'Reference Types' checkbox is selected
      And waiting for 2 seconds

    Scenario: Set Reference Type Checkbox - many values
      Given user navigates to 'Customers' page
      When user sets checkboxes 'Quote, Logo, Success Story, Analyst Interview, Available for Calls' for 'Reference Types' checkbox item
      And user unsets checkbox 'Quote' for 'Reference Types' checkbox item
      Then checkboxes 'Logo, Success Story, Analyst Interview, Available for Calls' in "Reference Types" checkbox are selected
      And waiting for 5 seconds
      #Zgłoszony BUG dla negatywnej odpowiedzi jak Then Checkbox xxx is not selected

    Scenario: Sort By Name
      Given user navigates to 'Customers' page
      When user selects 'Name' in 'Sort By' field
      Then value 'Name' is selected in 'Sort By' field
      When user selects 'Last Updated' in 'Sort By' field
      Then value 'Last Updated' is selected in 'Sort By' field
      When user selects 'Newest to Oldest' in 'Sort By' field
      Then value 'Newest to Oldest' is selected in 'Sort By' field
      When user selects 'Oldest to Newest' in 'Sort By' field
      Then value 'Oldest to Newest' is selected in 'Sort By' field
      And waiting for 5 seconds

    Scenario Outline: Sort By
      Given user navigates to 'Customers' page
      When user selects '<option>' in 'Sort By' field
      Then value '<option>' is selected in 'Sort By' field
      Examples:
        | option           |
        | Last Updated     |
        | Name             |
        | Newest to Oldest |
        | Oldest to Newest |


    Scenario Outline: Status
      Given user navigates to 'Customers' page
      When user selects '<option>' in 'Status' field
      Then value '<option>' is selected in 'Status' field
      Examples:
        | option   |
        | Customer |
        | Internal |
        | Partner  |
        | Prospect |
        | Unknown  |

    Scenario: Set Checkbox - Single
      Given user navigates to 'Customers' page
      When user sets checkbox 'Logo' for 'Reference Types' checkbox item
      Then checkbox 'Logo' in 'Reference Types' checkbox is selected
      And user presses the 'Reset' button
  #Then no checkboxes are selected

    Scenario: Product sellection reset 1
      Given user navigates to 'Customers' page
      When user selects 'Symmetric 2000' in 'Product' field
      Then value 'Symmetric 2000' is selected in 'Product' field
      And user presses the 'Reset' button
      Then value '- All -' is selected in 'Product' field
      And waiting for 5 seconds


    Scenario Outline: Product sellection reset 2
      Given user navigates to 'Customers' page
      When user selects '<option value>' in 'Product' field
      Then value '<option value>' is selected in 'Product' field
      And user presses the 'Reset' button
      Then value '- All -' is selected in 'Product' field
      And waiting for 5 seconds
      Examples:
        | option value                 |
        | Peregrine Enterprise Edition |
        | Peregrine Standard Edition   |
        | System Sanity Check          |
        | Symmetric 2000               |
        | Symmetric 2100               |

    Scenario: Set Reference Type Checkbox - many values
      Given user navigates to 'Customers' page
      When user sets checkboxes 'Quote, Logo, Success Story, Analyst Interview, Available for Calls' for 'Reference Types' checkbox item
      Then checkboxes 'Quote, Logo, Success Story, Analyst Interview, Available for Calls' in "Reference Types" checkbox are selected
      And user unsets checkbox 'Quote' for 'Reference Types' checkbox item
      #Then checkbox 'Quote' in "Reference Types" is not selected
      Then checkboxes 'Logo, Success Story, Analyst Interview, Available for Calls' in "Reference Types" checkbox are selected

    Scenario: Set Checkbox - Check/Uncheck All
      Given user navigates to 'Customers' page
      When user presses the 'Check All' button
      Then checkboxes 'Quote, Logo, Success Story, Analyst Interview, Available for Calls' in "Reference Types" checkbox are selected
      When user presses the 'Uncheck All' button
      #Then no checkboxes are selected

    Scenario: Navigate to 'help'
      Given user navigates to 'Customers' page
      When user presses the 'Help' button




