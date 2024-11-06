Feature: Multi-Interpreter

  Scenario: Multiple Interpreters in one test runner
    Given a test runner has more than one interpreter
    And the recognizer recognizes all sentences
    When a sentence is parsed by only one interpreter
    Then that sentence is only executed by that interpreter
    And the other parsers will ignore it
    When a sentence is parsed by all interpreters
    Then all parsers execute it
