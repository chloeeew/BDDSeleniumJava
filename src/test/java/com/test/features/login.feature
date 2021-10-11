#Author: 403505960@qq.com
#Keywords Summary:
#Feature: List of scenarios
#Scenario: Business rule through list of steps with arguments
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an examples and <placeholder>
#Examples: Container for a table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Login
  login test in login page

  Scenario: Smoke Test
    Given Type "t124@qq.com" as username and "aaa22222" as password
    When Click Sign in button
    Then Account "Www ddeji" is shown in right top corner and being welcomed in Home page


  Scenario: Password lack 1 character
    Given Type "t124@qq.com" as username and "aaa2222" as password
    When Click Sign in button
    Then An alert tip "Authentication failed" is shown in login page
