Ability: Population Standard Deviation

  Example: Population Standard Deviation

  It is calculated by
  1) getting the deviation of each value about the average
  2) Squaring each of the deviations found
  3) Averaging the squared deviations
  4) Take the square root of the average

    Given the input data set is "<input>"
    When the population standard deviation is calculated
    Then the result should be "<expected>" within a precision of "0.001"

    Examples:
      | Case                         | input                  | expected  |
      | Identical integers           | 5, 5, 5, 5             | 0.0       |
      | Sequential integers          | 1, 2, 3, 4, 5          | 1.414     |
      | Sequential negative integers | -1, -2, -3, -4, -5     | 1.414     |
      | Floating point mixed         | 10.5, 2.1, 8.4         | 3.569     |
      | Large numbers                | 1000, 2000             | 500.0     |
      | Negative values              | -2, 0, 2               | 1.633     |
      | 64 bit double                | 1.7976931348623157E308 | 0         |
      | Recurring decimals           | 10, 20, 30             | 8.1649658 |

  Rule: The population standard deviation cannot be calculated for the empty data set

    Example: Boundary case with an empty set
      Given the input data set is ""
      When the population standard deviation is calculated
      Then the standard deviation cannot be calculated

