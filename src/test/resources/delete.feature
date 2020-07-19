@All @Delete
Feature: Check reqres.in for DELETE API

  Scenario: Verify DELETE request for User
    Given I have server by url "https://reqres.in"
    When I send DELETE request on endpoint "users" with id 2
    Then I check if status code is 204

