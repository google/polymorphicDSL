Ability: MR Charts

  Background:
    Given the value of k for k-sigma is "3"
    And the measurements have a precision of "0.001"

  Scenario: I-MR Charts
    Given the moving range interval size of "2"
    And the "I-MR" chart sample data:
      | Sample | Observation 1 | Moving Range |
      | 1      | 310           |              |
      | 2      | 288           | 22           |
      | 3      | 297           | 9            |
      | 4      | 298           | 1            |
      | 5      | 307           | 9            |
      | 6      | 303           | 4            |
      | 7      | 294           | 9            |
      | 8      | 297           | 3            |
      | 9      | 308           | 11           |
      | 10     | 306           | 2            |
      | 11     | 294           | 12           |
      | 12     | 299           | 5            |
      | 13     | 297           | 2            |
      | 14     | 299           | 2            |
      | 15     | 314           | 15           |
      | 16     | 295           | 19           |
      | 17     | 293           | 2            |
      | 18     | 306           | 13           |
      | 19     | 301           | 5            |
      | 20     | 304           | 3            |
    Then the value of "d₂" constant is "1.128"
    And the average MR is "7.789"

    Then the "I" chart Upper Control Limit is "321.217"
    And the "I" chart Upper Warning Limit is "314.312"
    And the "I" chart Zone C Boundary ABOVE the centerline is "307.406"

    And the "I" chart Center Line is "300.5"

    Then the "I" chart Zone C Boundary BELOW the centerline is "293.594"
    And the "I" chart Lower Warning Limit is "286.688"
    And the "I" chart Lower Control Limit is "279.783"

	#####################

    Then the "MR" chart Upper Control Limit is "25.450"
    And the "MR" chart Upper Warning Limit is "19.563"
    And the "MR" chart Zone C Boundary ABOVE the centerline is "13.676"

    And the "MR" chart Center Line is "7.789"

    Then the "MR" chart Zone C Boundary BELOW the centerline is "1.902"
    And the "MR" chart Lower Warning Limit is "0.000"
    And the "MR" chart Lower Control Limit is "0.000"


  Scenario: MR Constants
  MR charts rely on 4 constants. d₂ and d₃ depend on the interval size n.
  D₃ and D₄ depend both on n and the sigma size k.

    Given the moving range interval size of "<n>"

    Then the value of "d₂" constant is "<d₂>"
    And the value of "d₃" constant is "<d₃>"

    Then the value of "D₃" factor is "<D₃>"
    And the value of "D₄" factor is "<D₄>"

    Examples:
      | n  | d₂    | d₃     | D₃    | D₄    |
      | 2  | 1.128 | 0.8525 | 0     | 3.267 |
      | 3  | 1.693 | 0.8884 | 0     | 2.574 |
      | 4  | 2.059 | 0.8798 | 0     | 2.282 |
      | 5  | 2.326 | 0.8641 | 0     | 2.114 |
      | 6  | 2.534 | 0.8480 | 0     | 2.004 |
      | 7  | 2.704 | 0.8332 | 0.076 | 1.924 |
      | 8  | 2.847 | 0.8198 | 0.136 | 1.864 |
      | 9  | 2.970 | 0.8078 | 0.184 | 1.816 |
      | 10 | 3.078 | 0.7971 | 0.223 | 1.777 |
      | 11 | 3.173 | 0.7873 | 0.256 | 1.744 |
      | 12 | 3.259 | 0.7785 | 0.283 | 1.717 |
      | 13 | 3.336 | 0.7704 | 0.307 | 1.693 |
      | 14 | 3.407 | 0.763  | 0.328 | 1.672 |
      | 15 | 3.472 | 0.7562 | 0.347 | 1.653 |
