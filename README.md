# APEX Low Test Framework

The goal of the project is to create an Automated Low Test Framework for APEX applications that will allow users with no experience to write and execute automated tests using human-readable language. We have created a dictionary of predefined English phrases that can be used to specify requirements. The same predefined phrases can be used to automatically run tests by our framework. With the low-test approach, anyone who can write an English description of the requirements can write automated tests executed against an APEX application. To get a taste of what we're talking about, see the following example of a low-test requirements (and test cases) specification:

```
Scenario: User log-in and access Activities page
    Given the user access the 'https://apex.oracle.com/pls/apex/dataconsulting/r/customers/login' url
    And user entered 'demo' in 'Username' field
    And user entered 'demo_password' in 'Password' field
    And user clicked on the 'Sign In' button
    Then user navigates to 'Activities' page
```

Our framework can take above desciption and run test case automaticaly. 

# Getting Started

To get a local copy up and running please follow these simple example steps:
-	Install [IDEA IntelliJ](https://www.jetbrains.com/idea/download/#section=windows) (IntelliJ IDEA Community Edition is fine)
-	Clone the project from the (https://github.com/dataconsulting-pl/APEX-Low-Test-Framework.git). Choose option to install GIT if needed.
-	From File -> Settings -> Plugins install “Cucumber for Java” and “Gherkin” plugins
-   From File -> Project structure -> Project download JDK 
-   you are ready to go...

Screencast of setup on fresh Windows environment:


https://user-images.githubusercontent.com/59740730/191653915-51966c50-de6f-4644-a834-551ac4cea25e.mp4



Some screenshots from installation and usage:
- IntelliJ plugin installation

![image](https://user-images.githubusercontent.com/109535056/191544408-e6f5dec2-6569-41f3-98f2-43b252f0e83a.png)

-	Open some example feature file

 ![image](https://user-images.githubusercontent.com/109535056/191546535-835ca7d7-c81a-4d85-bd52-8c5e8ce37c5d.png)


-	Execute the test directly from editor (or from Runner Class)

![image](https://user-images.githubusercontent.com/109535056/191544006-df952fc1-c7aa-42b8-a302-826c504deb1c.png)

 
# Prerequisites
JDK > 1.8

# License
[APEX Low Test Framework license](LICENSE.md)

We decided to use the open-source license in good faith, and we hope that no one will abuse the freedom it provides. Either by rebranding or by reusing/copying the source code in a product that competes with us.

# Credits
This project is started and maintained by experts from [dataconsulting.pl](https://datacons.co.uk). If you need commercial support [contact us](https://datacons.co.uk/contacts/) 
