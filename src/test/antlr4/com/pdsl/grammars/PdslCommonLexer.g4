lexer grammar PdslCommonLexer ;

import GherkinCommonLexer;

tokens { END, GHERKIN_STEP_KEYWORD}

WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY : GHERKIN_STEP_KEYWORD 'the test resource is processed by ' ('the ' | 'a ') '"Test Specification Factory"' END ;
TEST_SPECIFICATION_IS_PRODUCED : GHERKIN_STEP_KEYWORD 'a "Test Specification" is produced' END ;
TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY : GHERKIN_STEP_KEYWORD 'the Test Specification is processed by a "Test Case Factory"' END ;
POLYMORPHIC_DSL_TEST_EXECUTOR : GHERKIN_STEP_KEYWORD 'a "Polymorphic DSL Test Executor"' END ;
TEST_CASE_IS_PROCESSED : GHERKIN_STEP_KEYWORD 'the Test Case' 's'? ' ' ('is ' | 'are ') 'processed by a "Polymorphic DSL Test Executor"' END ;
TEST_RUN_RESULT_PRODUCED : GHERKIN_STEP_KEYWORD 'a "Test Run Result" is produced' END ;