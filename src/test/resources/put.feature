@All @Put
Feature: Check reqres.in for PUT API

  Scenario Outline: Verify PUT request for User
    Given I have server by url "https://reqres.in"
    When I send PUT request on endpoint "users" with id 2
    Then I check if status code is 200
    And I check if name in body is "<name>" and job in body is "<job>"

    Examples:
      |name       |job            |
      |morpheus   | zion resident |

