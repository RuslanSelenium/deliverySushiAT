#Author: ruslan.onp@gmail.com

Feature: Order Pages verification
	I should verify orders are downloading from the database
	And the orders are disaplaying correctly.

@cleardatabase @web
Scenario Outline: Orders adding
Given I put order <products> to <address> in the database
When I am on homepage
Then I validate order <products> to <address> is displaying

Examples:
    | products  				|	address		  |
    | sushi1, sushi2 		| address1		|
    | sushi1				 		| address2    |
