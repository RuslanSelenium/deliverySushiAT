#Author: ruslan.onp@gmail.com

Feature: Menu Page verification
	I should verify categories are displaying correctly
	And products are displaying correctly
	And I could add/change/remove the certain product
	
@cleardatabase @web
Scenario: Verify products are displaying
Given I put category "category" in the database
	And I put product "sushi1, pretty nice sushi, 200, 220, any.jpg, category" in the database
	And I put product "sushi2, pretty nice sushi, 200, 220, any.jpg, category" in the database
When I am on "/#/Sushi"
Then I verify "sushi1, pretty nice sushi, 200, 220, any.jpg, category" product is displaying
	And I verify "sushi2, pretty nice sushi, 200, 220, any.jpg, category" product is displaying

@cleardatabase @web
Scenario Outline: Verify the products creating  #a POSITIVE test case
Given I put category "category" in the database
When I am on "/#/Sushi"
	And I add product <product> <description> <price> <weight> <picture> <category>
Then I verify <product> <description> <price> <weight> <picture> <category> product is displaying
	And I verify product <product> <description> <price> <weight> <picture> <category> in the database
	
Examples:
    | product 	|	description  | price | weight | picture 	| category |
    | sushi1	 	| address1		 | 200	 | 220		| /pic.jpg	| category |

@cleardatabase @web
Scenario Outline: Verify the products creating with wrong credentials  #a NEGATIVE test case
Given I put category "category" in the database
When I am on "/#/Sushi"
	And I add product <product> <description> <price> <weight> <picture> <category>
Then I verify the error is displaying
	And I verify product <product> is not in the database
	
Examples:
    | product  				|	description  | price | weight | picture 	| category |
    | 								| description	 | 200	 | 220		| /pic.jpg	| category |  #empty product
    | sushi1					| 						 | 200	 | 220		| /pic.jpg	| category |  #empty description
    | sushi1					| description  |    	 | 220		| /pic.jpg	| category |  #empty price
    | sushi1					| description	 | -100	 | 220		| /pic.jpg	| category |  #a negative price
    | sushi1					| description  | 0		 | 220		| /pic.jpg	| category |  #a null price
    | sushi1					| description  | str	 | 220		| /pic.jpg	| category |  #a string price
    | sushi1					| description	 | 200 	 | 				| /pic.jpg	| category |  #empty weight
    | sushi1					| description	 | 200	 | -10		| /pic.jpg	| category |  #a negative weight
    | sushi1					| description	 | 200	 | 0			| /pic.jpg	| category |  #a null weight
    | sushi1					| description	 | 200	 | str		| /pic.jpg	| category |  #a string weight
    | sushi1					| description	 | 200	 | str		| 					| category |  #an empty file
    | sushi1					| description	 | 200	 | str		| /txt.txt	| category |  #not a picture
    
@cleardatabase @web
Scenario Outline: Verify the products changing 
Given And I put category "category" in the database
	And I put product "sushi1, pretty nice sushi, 200, 220, any.jpg, category" in the database
When I am on "/#/Sushi"
	And I change product "sushi1" with <product> <description> <price> <weight> <picture> in the <category>
Then I verify product <product> <description> <price> <weight> <picture> <category> is displaying
	And I verify product <product> <description> <price> <weight> <picture> <category> in the database
	
Examples:
    | product  				|	description  | price | weight | picture 	| category |
    | sushi						| description	 | 444	 | 444		| /pic.jpg	| category |  

@cleardatabase @web
Scenario: Verify the products removing
Given And I put category "category" in the database
	And I put product "sushi1, pretty nice sushi, 200, 220, any.jpg, category" in the database
When I am on "/#/Sushi"
	And I remove product "sushi1" from the "category"
Then I verify product "sushi1" in the "category" is not displaying
	And I verify product "sushi1" is not in the database
