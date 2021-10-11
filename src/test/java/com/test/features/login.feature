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

  @Smoke
  Scenario Outline: Login-Smoke Test
    Given Type "<username>" as username and "<password>" as password
    When Click Sign in button
    Then Account "<accountName>" is shown in right top corner and being welcomed in Home page
  Examples:
    | username     | password |accountName |
    | t124@qq.com  | aaa22222 | Www ddeji1  |


  Scenario Outline: Login-Negative Test <Situation>
    Given Type "<username>" as username and "<password>" as password
    When Click Sign in button
    Then An alert tip "<alertTip>" is shown in login page
  Examples:
    | Situation                 | username         | password | alertTip              |
    | Lack 1 password character |t124@qq.com       | aaa2222  | Authentication failed.|
    | Wrong password            |t124@qq.com       | 12derrrr | Authentication failed.|
    | User yet to register      |t12334444@qq.com  | aaa22222 | Authentication failed.|