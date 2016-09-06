#Author: ruslan.onp@gmail.com

Feature: Category Page verification
	I should verify categoriy list is displaying correctly
	And I could add/change/remove the certain category
	
@cleardatabase @web
Scenario: Verify categories are displaying
Given I put category "category1" in the database
	And I put category "category2" in the database
When I am on "/#/Categories"
Then I verify "category1" category is displaying
	And I verify "category2" category is displaying

@cleardatabase @web
Scenario: Verify the category creating  #a POSITIVE test case
Given I am on "/#/Categories" 
When I add category "category1"
Then I verify "category1" category is displaying
	And I verify category "category" in the database

@cleardatabase @web
Scenario: Verify the category creating  #a NEGATIVE test case
Given I am on "/#/Categories" 
When I add category ""
Then I verify the error is displaying
	And I verify category "category" is not in the database
	
@cleardatabase @web
Scenario: Verify the category changing 
Given I am on "/#/Categories" 
	And I put category "category1" in the database
When I change "category1" with "category2"
Then I verify "category2" category is displaying
	And I verify category "category1" is not in the database
	And I verify category "category2" in the database
	
@cleardatabase @web
Scenario: Verify the category removing 
Given I am on "/#/Categories" 
	And I put category "category1" in the database
When I remove category "category1"
Then I verify "category1" category is not displaying
	And I verify category "category1" is not in the database