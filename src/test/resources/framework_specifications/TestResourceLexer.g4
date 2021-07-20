lexer grammar TestResourceLexer ;

import GherkinCommonLexer ;

tokens { END }
GIVEN_THE_TEST_RESOURCE : GHERKIN_STEP_KEYWORD 'the test resource ' ;
GIVEN_THE_FOLLOWING_TEST_RESOURCE : GHERKIN_STEP_KEYWORD 'the following test resource:' END ;