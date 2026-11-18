@tag
Feature: Error Validation

@ErrorValudation
Scenario Outline: Negative test for incorrect credentials 

Given Landed on Ecommerce page
When  Logged in with username <name> and password <password>
Then "Incorrect email or password." message is displayed on Login page

Examples:
|name			     |password	|
|AndyMurray@gmail.com|Aniket@123|