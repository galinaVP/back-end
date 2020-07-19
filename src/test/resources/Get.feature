@All @Get
Feature: Check reqres.in for GET API

  Scenario: Verify GET request for page 2
    Given I have server by url "https://reqres.in"
    When I send GET request on endpoint "users" with page 2
    Then I check if status code is 200
    And I check body contains 6 users under page

  Scenario: Verify GET request for user 2
    Given I have server by url "https://reqres.in"
    When I send GET request on endpoint "users" with id "2"
    Then I check if status code is 200
    And I check if body shows correct ID 2
    And I check if email in body contains "janet.weaver"
    And I check if first name in body is "Janet"
    And I check if last name in body is "Weaver"

  Scenario: Verify GET request for incorrect user
    Given I have server by url "https://reqres.in"
    When I send GET request on endpoint "users" with id "23"
    Then I check if status code is 404

  Scenario Outline: Verify GET request for List of Unknown
    Given I have server by url "https://reqres.in"
    When I send GET request on endpoint "unknown" with id ""
    Then I check if status code is 200
    And I check size according to per_page
    And I check body has key "<key>"
    Examples:
    |key              |
    |page             |
    |per_page         |
    |data             |
    |total            |
    |total_pages      |

  Scenario Outline: Verify GET request for Unknown 2
    Given I have server by url "https://reqres.in"
    When I send GET request on endpoint "unknown" with id "2"
    Then I check if status code is 200
    And I check if body shows correct ID 2
    And I check if name in body is "fuchsia rose"
    And I check if year in body is 2001
    And I check body has key "<key>"
    Examples:
      |key            |
      |data           |
      |ad             |
  Scenario: Verify GET request for incorrect Unknown 23
    Given I have server by url "https://reqres.in"
    When I send GET request on endpoint "unknown" with id "23"
    Then I check if status code is 404
    And I check if body is empty

  Scenario: Verify GET request for Users with delay
    Given I have server by url "https://reqres.in"
    When I send GET request on endpoint "users" with delay 3
    Then I check if status code is 200
    And I check time lessThan 6000 ms
