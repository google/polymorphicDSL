parser grammar GherkinCommonParser;

options {tokenVocab=GherkinCommonLexer; }

gherkinStepKeyword : GHERKIN_STEP_KEYWORD ;
integerValue : INT ;
textInDoubleQuotes : TEXT_IN_DOUBLE_QUOTES ;
docstring : DOCSTRING ;
