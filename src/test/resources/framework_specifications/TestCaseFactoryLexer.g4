lexer grammar TestCaseFactoryLexer ;

import GherkinCommonLexer;

GIVEN_AN_ARBITRARY_TEST_SPECIFICATION : GHERKIN_STEP_KEYWORD  'a "Test Specification"' END ;
GIVEN_SPECIFICATION_HAS_N_PHRASES_START : GHERKIN_STEP_KEYWORD  'the Test Specification has ';
GIVEN_THE_TEST_SPECIFICATION_CHILD_START : GHERKIN_STEP_KEYWORD 'the Test Specification has a child Test Specification with ' ;
THEN_N_TEST_CASES_ARE_PRODUCED_END : GHERKIN_STEP_KEYWORD ' test case' 's'? ' are produced' END ; // The beginning of this phrase is handled by the parser
THEN_EACH_TEST_CASE_HAS_N_PHRASES_START : GHERKIN_STEP_KEYWORD 'each test case has ' ;
PHRASES_END : ' phrases' END;
GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_START : GHERKIN_STEP_KEYWORD '"' ;
GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_END : '" test case factory is used' END ;
WHEN_TEST_SPECIFICATON_PROCESSED_BY_FACTORY : GHERKIN_STEP_KEYWORD 'the Test Specification is processed by the Test Case Factory' END ;
