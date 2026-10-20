Ability: u Charts

  The u chart is used to analyze the number of nonconformities (defects) per unit when the subgroup size varies.
  It assumes a Poisson distribution for the occurrence of defects.
  Because the subgroup size varies, the control limits are calculated for each subgroup.

  Background:
    Given the measurements have a precision of "0.0001"
    And the value of k for k-sigma is "3"

  Example: u Chart with variable sample size
    Given the "U" chart sample data:
      | Subgroup | Sample Size | Defects |
      | 1        | 50          | 8       |
      | 2        | 50          | 10      |
      | 3        | 50          | 6       |
      | 4        | 50          | 9       |
      | 5        | 50          | 7       |
      | 6        | 60          | 12      |
      | 7        | 60          | 8       |
      | 8        | 60          | 10      |
      | 9        | 60          | 11      |
      | 10       | 60          | 9       |
      | 11       | 50          | 5       |
      | 12       | 50          | 8       |
      | 13       | 50          | 7       |
      | 14       | 50          | 6       |
      | 15       | 50          | 9       |
      | 16       | 40          | 4       |
      | 17       | 40          | 6       |
      | 18       | 40          | 5       |
      | 19       | 40          | 7       |
      | 20       | 40          | 3       |

    Then ∑n = "1000"
    And ∑c = "150"
    And ū = "0.1500"

    Then the "U" chart Center Line is "0.1500"

    Then the "U" chart values for each subgroup are:
      | Subgroup | n  | u      | LCL    | UCL    |
      | 1        | 50 | 0.1600 | 0.0000 | 0.3143 |
      | 2        | 50 | 0.2000 | 0.0000 | 0.3143 |
      | 3        | 50 | 0.1200 | 0.0000 | 0.3143 |
      | 4        | 50 | 0.1800 | 0.0000 | 0.3143 |
      | 5        | 50 | 0.1400 | 0.0000 | 0.3143 |
      | 6        | 60 | 0.2000 | 0.0000 | 0.3000 |
      | 7        | 60 | 0.1333 | 0.0000 | 0.3000 |
      | 8        | 60 | 0.1667 | 0.0000 | 0.3000 |
      | 9        | 60 | 0.1833 | 0.0000 | 0.3000 |
      | 10       | 60 | 0.1500 | 0.0000 | 0.3000 |
      | 11       | 50 | 0.1000 | 0.0000 | 0.3143 |
      | 12       | 50 | 0.1600 | 0.0000 | 0.3143 |
      | 13       | 50 | 0.1400 | 0.0000 | 0.3143 |
      | 14       | 50 | 0.1200 | 0.0000 | 0.3143 |
      | 15       | 50 | 0.1800 | 0.0000 | 0.3143 |
      | 16       | 40 | 0.1000 | 0.0000 | 0.3337 |
      | 17       | 40 | 0.1500 | 0.0000 | 0.3337 |
      | 18       | 40 | 0.1250 | 0.0000 | 0.3337 |
      | 19       | 40 | 0.1750 | 0.0000 | 0.3337 |
      | 20       | 40 | 0.0750 | 0.0000 | 0.3337 |
