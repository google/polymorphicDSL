Ability: np Charts

  The np chart is used to monitor the number of non-conforming or defective items in the measurement process.
  It uses binomial distribution to measure the number of defective or non-conforming units in a sample.
  An np chart is very similar to the p chart, but it plots the number of items rather than the proportion.
  The np chart requires that the sample size (n) for each subgroup is constant.

  Background:
    Given the measurements have a precision of "0.001"
    And the value of k for k-sigma is "3"

  Example: np Chart with constant sample size
    Given the "NP" chart sample data:
      | Subgroup | Sample Size | Defectives |
      | 1        | 200         | 4          |
      | 2        | 200         | 8          |
      | 3        | 200         | 6          |
      | 4        | 200         | 6          |
      | 5        | 200         | 4          |
      | 6        | 200         | 8          |
      | 7        | 200         | 2          |
      | 8        | 200         | 1          |
      | 9        | 200         | 9          |
      | 10       | 200         | 6          |
      | 11       | 200         | 8          |
      | 12       | 200         | 1          |
      | 13       | 200         | 2          |
      | 14       | 200         | 9          |
      | 15       | 200         | 4          |
      | 16       | 200         | 3          |
      | 17       | 200         | 9          |
      | 18       | 200         | 6          |
      | 19       | 200         | 2          |
      | 20       | 200         | 7          |

    Then ∑n = "4000"
    And ∑x = "105"
    And p̄ = "0.026"
    And np̄ = "5.250"

    Then the "NP" chart Upper Control Limit is "12.033"
    And the "NP" chart Upper Warning Limit is "9.772"
    And the "NP" chart Zone C Boundary ABOVE the centerline is "7.511"

    Then the "NP" chart Center Line is "5.250"

    Then the "NP" chart Zone C Boundary BELOW the centerline is "2.989"
    And the "NP" chart Lower Warning Limit is "0.728"
    And the "NP" chart Lower Control Limit is "0.000"