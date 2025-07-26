
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @Error_Validation
  Scenario Outline: Error Validation Test
   Given I landed on Ecommerce Page
   And Logged in with username <name> and password <password>
   Then "Incorrect email or password." message is displayed
		
    Examples: 
      | name                | password        |
      | jsuhas822@gmail.com | Rahulshetty@123 |
      
