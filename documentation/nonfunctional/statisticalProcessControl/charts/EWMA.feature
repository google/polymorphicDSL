Ability: EWMA Charts

  Exponentially Weighted Moving Average (EWMA) control charts are time-weighted charts
  used to detect small shifts in the process mean. They use all historical data points,
  with weights decreasing exponentially as observations get older.

  Background:
    Given the value of k for k-sigma is "3"
    And the measurements have a precision of "0.0001"

  Scenario: EWMA Chart with Individual Observations and Variable Control Limits
    Given the "EWMA" chart parameters:
      | lambda | target | sigma |
      | 0.3    | 30.0   | 2.13  |
    And the "EWMA" chart sample data:
      | Subgroup | Observation 1 |
      | 1        | 31.6          |
      | 2        | 27.9          |
      | 3        | 32.1          |
      | 4        | 29.2          |
      | 5        | 30.1          |
      | 6        | 28.2          |
      | 7        | 30.9          |
      | 8        | 30.3          |
      | 9        | 31.2          |
      | 10       | 30.3          |
      | 11       | 29.4          |
      | 12       | 27.8          |
      | 13       | 29.9          |
      | 14       | 31.2          |
      | 15       | 32.5          |
      | 16       | 31.2          |
      | 17       | 32.6          |
      | 18       | 32.4          |
      | 19       | 33.6          |
      | 20       | 31.2          |

    Then the "EWMA" chart Center Line is "30.0000"

    Then the "EWMA" chart values for each subgroup are:
      | Subgroup | Zᵢ      | LCL     | UCL     |
      | 1        | 30.4800 | 28.0830 | 31.9170 |
      | 2        | 29.7060 | 27.6600 | 32.3400 |
      | 3        | 30.4242 | 27.4785 | 32.5215 |
      | 4        | 30.0569 | 27.3942 | 32.6058 |
      | 5        | 30.0699 | 27.3538 | 32.6462 |
      | 6        | 29.5089 | 27.3343 | 32.6657 |
      | 7        | 29.9262 | 27.3248 | 32.6752 |
      | 8        | 30.0384 | 27.3201 | 32.6799 |
      | 9        | 30.3869 | 27.3178 | 32.6822 |
      | 10       | 30.3608 | 27.3167 | 32.6833 |
      | 11       | 30.0726 | 27.3162 | 32.6838 |
      | 12       | 29.3908 | 27.3159 | 32.6841 |
      | 13       | 29.5436 | 27.3158 | 32.6842 |
      | 14       | 30.0405 | 27.3157 | 32.6843 |
      | 15       | 30.7783 | 27.3157 | 32.6843 |
      | 16       | 30.9048 | 27.3157 | 32.6843 |
      | 17       | 31.4134 | 27.3157 | 32.6843 |
      | 18       | 31.7094 | 27.3157 | 32.6843 |
      | 19       | 32.2766 | 27.3157 | 32.6843 |
      | 20       | 31.9536 | 27.3157 | 32.6843 |
