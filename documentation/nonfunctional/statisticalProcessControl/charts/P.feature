Ability: p Charts

  The p chart is used to analyze the proportions of non-conforming or defective items in a process.
  It uses a Binomial Distribution to measure the proportion of defective or non-conforming units in a sample.
  p charts are especially useful when subgroups are not equal in size, adjusting the control limits accordingly.

  Background:
    Given the measurements have a precision of "0.00001"
    And the value of k for k-sigma is "3"

  Example: p Chart with variable sample size
    Given the "P" chart sample data:
      | Subgroup | Sample Size | Defectives |
      | 1        | 1250        | 18         |
      | 2        | 1300        | 15         |
      | 3        | 1350        | 13         |
      | 4        | 1200        | 16         |
      | 5        | 1050        | 8          |
      | 6        | 1050        | 6          |
      | 7        | 1200        | 18         |
      | 8        | 1100        | 14         |
      | 9        | 1000        | 22         |
      | 10       | 600         | 12         |
      | 11       | 1350        | 13         |
      | 12       | 1250        | 19         |
      | 13       | 1200        | 33         |
      | 14       | 1100        | 20         |
      | 15       | 1050        | 20         |
      | 16       | 950         | 20         |
      | 17       | 1560        | 22         |
      | 18       | 1150        | 17         |
      | 19       | 1230        | 19         |
      | 20       | 1100        | 21         |

    Then ∑n = "23040"
    And ∑x = "346"
    And p̄ = "0.01502"

    Then the "P" chart Center Line is "0.01502"

    Then the "P" chart values for each subgroup are:
      | Subgroup | n    | p       | LCL     | UCL     |
      | 1        | 1250 | 0.01440 | 0.00470 | 0.02534 |
      | 2        | 1300 | 0.01154 | 0.00490 | 0.02514 |
      | 3        | 1350 | 0.00963 | 0.00509 | 0.02495 |
      | 4        | 1200 | 0.01333 | 0.00448 | 0.02555 |
      | 5        | 1050 | 0.00762 | 0.00376 | 0.02628 |
      | 6        | 1050 | 0.00571 | 0.00376 | 0.02628 |
      | 7        | 1200 | 0.01500 | 0.00448 | 0.02555 |
      | 8        | 1100 | 0.01273 | 0.00402 | 0.02602 |
      | 9        | 1000 | 0.02200 | 0.00348 | 0.02656 |
      | 10       | 600  | 0.02000 | 0.00012 | 0.02991 |
      | 11       | 1350 | 0.00963 | 0.00509 | 0.02495 |
      | 12       | 1250 | 0.01520 | 0.00470 | 0.02534 |
      | 13       | 1200 | 0.02750 | 0.00448 | 0.02555 |
      | 14       | 1100 | 0.01818 | 0.00402 | 0.02602 |
      | 15       | 1050 | 0.01905 | 0.00376 | 0.02628 |
      | 16       | 950  | 0.02105 | 0.00318 | 0.02686 |
      | 17       | 1560 | 0.01410 | 0.00578 | 0.02426 |
      | 18       | 1150 | 0.01478 | 0.00426 | 0.02578 |
      | 19       | 1230 | 0.01545 | 0.00461 | 0.02542 |
      | 20       | 1100 | 0.01909 | 0.00402 | 0.02602 |