Feature: Parameters

  This feature file is used to provide an example of how to extract parameters
  from sentences in PDSL. This file itself does not indicate how to accomplish this,
  but the code examples associated with it do. For more information, view
  documentation/parameters.adoc in this code repository.

  @Simplistic
  Scenario: Simple example
    Given I have the number "5"

  @MultipleMatches
  Scenario: Simple example
    Given I have the number "5"
    Given I have the number "6"
    Given I have the number "7"

