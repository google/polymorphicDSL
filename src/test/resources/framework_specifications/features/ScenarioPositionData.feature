Feature: Scenario Locations
  Some test frameworks behave better when the test cases are grouped together based on how they originally showed
  up in the feature file. Some PDSL implementations support a "ScenarioPostion" which provide:
  - Rule Index: The nth rule it was assotiated with [0 if not part of any rule]
  - Ordinal: The nth test case in the rule index [Always greater than or equal to 1]
  - Table Index: The nth test case from examples tables [0 if there was no table]

  The position is notated as <Rule Index>.<Ordinal>.<Table Index>
  Examples of proper usage of this notation are shown in the below tests.


  Scenario:
    Then the position is 0.1.0

  Scenario:
    Then the position is 0.2.0

   Scenario Outline: :
      Then the position is <POSITION>

      Examples:
      |POSITION|
      | 0.3.1  |
      | 0.3.2  |

  Scenario: Position counter continues from the same depth
    Then the position is 0.4.0

  Scenario Outline:
    Then the postion is <POSITION>

    Examples:
      |POSITION|
      | 0.5.1   |
      | 0.5.2   |

    Examples:
      |POSITION|
      | 0.5.3  |
      | 0.5.4  |


  Rule:
        Scenario:
          Then the position is 1.1.0

  Rule:
    Scenario:
      Then the position is <POSITION>
      Examples:
      |POSITION|
      | 2.1.1  |

      Examples:
      | POSITION |
      | 2.1.2    |
