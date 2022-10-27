Feature: Parameters

  This feature file is used to provide an example of how to extract parameters
  from sentences in PDSL. This file itself does not indicate how to accomplish this,
  but the code examples associated with it do. For more information, view
  documentation/parameters.adoc in this code repository.

@IslandGrammar
Scenario: Island grammar example
  Given the parameter "quoted text"
  And the parameter:
    """
    Magnam ipsa dolores at sit rerum animi eveniet.
    Velit omnis qui deleniti et quia asperiores sunt saepe.
    Doloribus accusamus eligendi totam eligendi voluptatem occaecati est.
    "some nested quotes"
    "And an unmatched quote that makes programmers cringe
    """
