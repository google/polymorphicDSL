parser grammar GherkinCommonParser;

options {tokenVocab=GherkinCommonLexer; }

gherkinStepKeyword : GHERKIN_STEP_KEYWORD ;
integerValue : INT ;
textInDoubleQuotes : QUOTED_TEXT ;
docstring : DOCSTRING ;
textInDoubleQuotesEnd : QUOTED_TEXT_END ;
