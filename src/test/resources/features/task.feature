Feature: Task Api test feature

  @suite
  Scenario: Create a task list and add a task
    Given TestingGetTaskList
    When CreateTask
    Then MovingTask