Feature: Blocked ski passes should forbid passage
  Create two ski passes and then block one of them

  Scenario: Create two ski passes and block one of them. When you try scan blocked ski pass the red light should turn on and passage was forbidden

    Given create two ski passes
    And  system should have two created ski passes
    When  block one of the created ski passes
    Then scan blocked ski pass. The passage should be forbidden
    Then scan not blocked ski pass. The passage should be allowed

