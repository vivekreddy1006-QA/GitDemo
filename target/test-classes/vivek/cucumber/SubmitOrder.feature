@tag
Feature: Submit an Order
	
Background:
  Given I landed on ECommerce page

Scenario Outline: Add a Product to Cart
  Given Logged in with email <email> and password <password>
  When I add product <productName> to cart
  And checkout <productName> and Submit Order
  Then "Thankyou for the order." message is displayed on Confirmation page

Examples:
  | email                   | password | productName |
  | dummyVivek123@gmail.com | Vivek988 | ZARA COAT 3 |