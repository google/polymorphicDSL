Feature: Multiple sources of the truth for Listener (InterpreterTwoLexer.g4 & InterpreterTwoParser.g4).

  @Tag1
Scenario: Combine the two different Lexers in one Listener
  Given Hi, folks :).
  Then Hello, world!
  @Tag2
Scenario: This is a new scenario
  Given Hi, folks :).
  Then Hello, world!
@Tag3
Scenario: This is a 3 scenario
  Given Hi, folks :).
  Given Hi, folks :).
  Given Hi, folks :).
  Given Hi, folks :).
  Then Hello, world!