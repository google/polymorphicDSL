lexer grammar TestSpecificationFactoryLexer ;

import GherkinCommonLexer, PdslFrameworkSpecificationLexer ;

GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY : GHERKIN_STEP_KEYWORD 'the Test Specification Factory being used is a "';

GIVEN_SPECIFIC_GRAMMAR : GHERKIN_STEP_KEYWORD 'the Grammar being used is "';
GIVEN_SPECIFIC_SUBGRAMMAR : GHERKIN_STEP_KEYWORD 'the Subgrammar being used is "';
THEN_TEST_SPECIFICATION_FAILS_DUE_TO_MISSING_SCENARIO :
    GHERKIN_STEP_KEYWORD 'an error occurs because there is no scenario' END ;
THEN_TEST_SPECIFICATION_FAILS_BECAUSE_OF_MISSING_STEP_BODY : GHERKIN_STEP_KEYWORD 'an error occurs because there is no step body' END ;

GIVEN_NONEXISTENT_URL : GHERKIN_STEP_KEYWORD 'a URL to a resource that does not exist' END ;
THEN_NO_SUCH_RESOURCE_ERROR : GHERKIN_STEP_KEYWORD 'an error occurs because there is no such resource' END ;

TEST_SPECIFICATION_TOTAL_PHRASES_START : GHERKIN_STEP_KEYWORD 'the test specification has ' ;
TEST_SPECIFICATION_TOTAL_PHRASES_END : ' total phrases' END ;

TEST_SPECIFICATION_MAY_BE_PRODUCED : GHERKIN_STEP_KEYWORD 'a test specification is ' 'NOT '? 'produced' END ;

TEST_SPECIFICATION_IS_PROCESSED_BY_THE_FACTORY : GHERKIN_STEP_KEYWORD 'the test resource is processed by the Test Specification Factory' END ;