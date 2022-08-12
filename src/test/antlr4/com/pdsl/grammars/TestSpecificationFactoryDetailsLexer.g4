lexer grammar TestSpecificationFactoryDetailsLexer ;

import GherkinCommonLexer ;

GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY : GHERKIN_STEP_KEYWORD 'the Test Specification Factory being used is ' ('the ' | 'a ')? QUOTED_TEXT END ;

