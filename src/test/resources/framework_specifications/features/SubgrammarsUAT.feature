Feature: PDSL Subgrammars

  Scenario: Syntax Check
    The step body below will only compile if the steps are in the proper order, however only the subgrammar steps will
    be run
    Given a grammar step
    And a subgrammar step
    Then a grammar step
    And a subgrammar step