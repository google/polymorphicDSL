lexer grammar DialectLexer;

import GherkinCommonLexer;

GRAMMAR_STEP : GHERKIN_STEP_KEYWORD 'a grammar step' END;
SUBGRAMMAR_STEP : GHERKIN_STEP_KEYWORD 'a subgrammar step' END;