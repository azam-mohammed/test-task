Feature: Task Api test feature

  @suite
  Scenario: Create a task list and add a task
    Given I am validation HTTP response for get task list
    And   I am creating task list on the app
    Then  I am moving created task list

  @suite
  Scenario: when user is not auth to access task api
    Given I am trying to access api with wrong access token
    Then I get api access error
