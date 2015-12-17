Feature: Create and scan ski passes with Lifts Limit Type
  Create ski pass with defined type and scan it.

  Scenario Outline: Create ski pass with type defined lifts limit type, scan it and get information from system about scanning

    Given create lift limit ski pass with type <Type>
    When scan ski pass via turnslite <General scan count> times
    Then system will have <Successful passages count> successful passages and <Fail passages count> failed passages with type <Type>

  Examples:
    | Type              | General scan count | Successful passages count | Fail passages count |
    | TEN_LIFTS         | 12                 | 10                        | 2                   |
    | TWENTY_LIFTS      | 22                 | 20                        | 2                   |
    | ONE_HUNDRED_LIFTS | 105                | 100                       | 5                   |