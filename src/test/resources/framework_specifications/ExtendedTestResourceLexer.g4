lexer grammar ExtendedTestResourceLexer ;

import GherkinCommonLexer ;

TEST_RESOURCE_VALIDITY : GHERKIN_STEP_KEYWORD 'the test resource is ' 'NOT '? 'valid' END ;