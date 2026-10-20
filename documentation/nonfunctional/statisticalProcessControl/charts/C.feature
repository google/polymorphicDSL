Ability: c Charts

  A c chart is used to determine if the process is stable and predictable and also to monitor the effects
  of before and after process improvements. It tracks the number of defects in a product of constant size.
  The c chart computes control limits based on the Poisson distribution.

  Background:
    Given the measurements have a precision of "0.001"
    And the value of k for k-sigma is "3"

  Example: c Chart with standard limits
    Given the "C" chart sample data:
      | Sample | Defect Count |
      | 1      | 12           |
      | 2      | 14           |
      | 3      | 16           |
      | 4      | 18           |
      | 5      | 16           |
      | 6      | 14           |
      | 7      | 12           |
      | 8      | 12           |
      | 9      | 32           |
      | 10     | 16           |
      | 11     | 18           |
      | 12     | 16           |
      | 13     | 14           |
      | 14     | 12           |
      | 15     | 16           |
      | 16     | 18           |
      | 17     | 12           |
      | 18     | 19           |
      | 19     | 18           |
      | 20     | 21           |
    Then ∑c = "326"

    Then the "C" chart Upper Control Limit is "28.412"
    And the "C" chart Upper Warning Limit is "24.375"
    And the "C" chart Zone C Boundary ABOVE the centerline is "20.337"

    Then the "C" chart Center Line is "16.300"

    Then the "C" chart Zone C Boundary BELOW the centerline is "12.263"
    And the "C" chart Lower Warning Limit is "8.225"
    And the "C" chart Lower Control Limit is "4.188"