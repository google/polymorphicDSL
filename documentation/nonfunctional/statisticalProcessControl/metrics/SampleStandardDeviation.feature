Ability: Sample Standard Deviation

  Example: Sample Standard Deviation
  It is calculated by
  1) getting the deviation of each value about the average
  2) Squaring each of the deviations found
  3) Dividing the squared deviations by the total sample minus 1
  4) Take the square root of the average

    Given the input data set is "<input>"
    When the sample standard deviation is calculated
    Then the result should be "<expected>" within a precision of "0.001"

    Examples:
      | Case                         | input                                        | expected               |
      | Identical integers           | 5, 5, 5, 5                                   | 0.0                    |
      | Sequential integers          | 1, 2, 3, 4, 5                                | 1.581                  |
      | Sequential negative integers | -1, -2, -3, -4, -5                           | 1.581                  |
      | Floating point mixed         | 10.5, 2.1, 8.4                               | 4.3715                 |
      | Large numbers                | 1000, 2000                                   | 707.1068               |
      | Negative values              | -2, 0, 2                                     | 2                      |
      | 64 bit double                | 1.7976931348623157E100,1.7976931348623157E10 | 1.2711610061536461E100 |
      | Recurring decimals           | 10, 20, 30                                   | 10                     |

  Rule: The sample standard deviation cannot be calculated for the data set with sample size < 2

    Example: Boundary case with an empty set
      Given the input data set is ""
      When the sample standard deviation is calculated
      Then the standard deviation cannot be calculated

    Example: Boundary case with dataset size less than 2
      Given the input data set is "10"
      When the sample standard deviation is calculated
      Then the standard deviation cannot be calculated

