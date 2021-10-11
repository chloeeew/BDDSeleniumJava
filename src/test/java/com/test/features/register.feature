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

Feature: Register
  Register test in login page

  @Smoke
  Scenario: RegisterCreateAccountClick-Smoke Test
    Given Type new email to create an account
    When Click on Create an Account button
    Then Display account is valid


  @Smoke
  Scenario Outline: RegisterCreateAccount-Smoke Test
    Given Type new valid email to create an account
    And Type "<gender>","<firstname>","<lastname>","<password>","<address>","<city>","<zipcode>","<phone>" in Register form
    When Click on register button
    Then Account is being welcomed in Home page

  Examples:
    |gender|firstname|lastname|password|address|city|zipcode|phone       |
    |woman |kkk      |Wang    |12231223|errrr  |fiie|93999  |23849002    |

