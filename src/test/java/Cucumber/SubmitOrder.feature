@tag
Feature: Purchase the order from the Ecommerce website

Background:
Given Landed on Ecommerce page

@Regression
Scenario Outline: Positive test for submitting the order

Given Logged in with username <name> and password <password>
When Add prpduct <productName> to cart
And Checkout <productName> and Submit the order
Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

Examples:
|name					 |password	  |productName|
|Aniket12345678@gmail.com|Aniket@12345|ZARA COAT 3|

