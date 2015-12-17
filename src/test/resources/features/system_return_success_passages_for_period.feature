Feature: System returns all success passages within defined period

  Scenario Outline: Create some different ski passes, scan them during defined period and get from system all ski passes for needed period

    Given create ski pass
    When scan ski passes <scan_count> times during period
    Then get success passages from system. The count of passages must be <scan_count>

  Examples:
    | scan_count |
    | 0          |
    | 1          |
    | 5          |