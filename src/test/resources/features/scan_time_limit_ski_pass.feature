Feature: Create and scan ski passes with Time Limit Type
  Create ski pass with defined type and scan it.

  Scenario Outline: Create ski pass with defined time limit type, scan it and get information from system about scanning

    Given create time limit ski pass with type <Type>
    When scan ski pass via turnslite
    Then system will have one successful passage if ski pass was scanned in active period or one failed passage if ski pass was scanned in not active period with type <Type>

  Examples:
    | Type             |
    | HALF_DAY_MORNING |
    | HALF_DAY_EVENING |
    | ONE_DAY          |
    | TWO_DAYS         |
    | FIVE_DAYS        |