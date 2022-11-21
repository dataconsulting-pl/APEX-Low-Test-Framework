Feature: Regression Tests

  Background:
    Given the user access the 'https://apex.oracle.com/pls/apex/r/dataconsulting/customers/login' url
    When user entered 'taf_demo' in 'Username' field
    And user entered 'taf_demo_123password!' in 'Password' field
    And user presses the 'Sign In' button

  Scenario: Set IG Filter and verify record exists - Reports
    When user navigates to 'Reports->All Customers' page
    Then at least one record can be found in 'Customers' IR by:
      | Customer      | Summary                                           | Category | Marquee Customer | Publicly Referenceable | Product Uses   | Status   | Geography | Industry       | Tags          | Web Site                  |
      | Barney Burger | Restuarant chain, rapidly spreading across Japan. | Consumer | No               | Yes                    | Symmetric 2000 | Customer | Japan     | Consumer Goods | CLOUD, MOBILE | http://www.barneyburger.j |


  Scenario: Test Customer Views
    When user navigates to 'Customers' page
    And user sets radioButton's option 'Report View' for 'Display as' radioButton item

    #And user sets radioButton 'Report View' for 'Display as' radioButton item


  Scenario: Unset Reference Type Checkbox - List 3
    When user navigates to 'Customers' page
    Then verify, that menu option 'Customers' is selected
    And user sets checkboxes 'Quote, Logo, Success Story, Analyst Interview, Available for Calls' for 'Reference Types' checkbox item
    And waiting for 2 seconds
    And user unsets checkboxes 'Analyst Interview, Available for Calls' for 'Reference Types' checkbox item
    And waiting for 2 seconds
    Then checkbox 'Quote, Logo, Success Story' in 'Reference Types' checkbox is selected
    And waiting for 5 seconds

  Scenario: Unset Reference Type Checkbox - List 4
    When user sets checkboxes 'Quote, Logo, Success Story, Analyst Interview, Available for Calls' for 'Reference Types' checkbox item
    And waiting for 4 seconds
    And user unsets checkboxes  for 'Quote, Logo, Success Story, Analyst Interview, Available for Calls' checkbox item
    And waiting for 5 seconds
    #Nie działa to samo, nie jestem w stanie odznaczyć tej samej ilości checkboxów, którą uprzednio zaznaczyłam.



    Scenario: Add and edit new Customer
      Given user clicked on the 'Add Customer' button
      When user enters 'Customer 1' in 'Customer Name' field
      And user presses the 'Add Customer' button
      And user presses the 'Edit Customer' button
      And user enters 'Customer 2' in 'Customer Name' field
      And user presses the 'Apply Changes' button
      And waiting for 2 seconds

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
      And waiting for 3 seconds


    Scenario: Apply on pop-up 4
      When user navigates to 'Contacts' page
      And user clicked on the 'Actions' button
      And user presses the 'Filter' button
      And user presses the 'Apply' button on 'Filter' pop-up
      And waiting for 3 seconds


    Scenario: Apply on pop-up 5
      When user navigates to 'Contacts' page
      And user clicked on the 'Actions' button
      And user presses the 'Format' button
      And user presses the 'Control Break' button
      And user presses the 'Apply' button on 'Control Break' pop-up
      And waiting for 3 seconds


    Scenario: Cancel on pop-up 6
      When user navigates to 'Contacts' page
      And user clicked on the 'Actions' button
      And user presses the 'Format' button
      And user presses the 'Control Break' button
      And user presses the 'Cancel' button on 'Control Break' pop-up
      And waiting for 3 seconds


    Scenario: Unset Checkbox - Single - Add Customers 1
      When user navigates to 'Customers' page
      And user presses the 'Add Customer' button
      When user sets checkbox 'Success Story, Quote, Logo' for 'Reference Types' checkbox item
      And waiting for 3 seconds
      And user unsets checkboxes 'Success Story, Quote, Logo' for 'Reference Types' checkbox item
      And waiting for 3 seconds


      And user navigates to 'Customers' page
      Then verify, that menu option 'Customers' is selected


    Scenario: Set Reference Type Checkbox - many values
      Given user navigates to 'Customers' page
      When user sets checkboxes 'Quote, Logo, Success Story, Analyst Interview, Available for Calls' for 'Reference Types' checkbox item
      Then checkboxes 'Quote, Logo, Success Story, Analyst Interview, Available for Calls' in "Reference Types" checkbox are selected
      And user unsets checkbox 'Quote' for 'Reference Types' checkbox item
      #Then checkbox 'Quote' in "Reference Types" is not selected
      Then checkboxes 'Logo, Success Story, Analyst Interview, Available for Calls' in "Reference Types" checkbox are selected


