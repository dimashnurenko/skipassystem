Feature: System returns all fail passages within defined period

  Scenario Outline: Create some different ski passes, scan them during defined period and get from system all ski passes for needed period

    Given create ski pass with type TEN_LIFTS
    When ski pass <scan_count> times
    Then get fail passages from system. The count of passages must be <fail_count>

  Examples:
    | scan_count | fail_count |
    | 10         | 0          |
    | 11         | 1          |
    | 15         | 5          |