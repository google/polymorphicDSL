lexer grammar PdslFrameworkSpecificationLexer;

import GherkinCommonLexer, TestResourceLexer, PdslCommonLexer ;

THEN_TEST_RESOURCE_VALIDITY : GHERKIN_STEP_KEYWORD 'the test resource is ' 'NOT '? 'valid' END ;

TEST_SPECIFICATION_HAS_AN_ID : GHERKIN_STEP_KEYWORD 'the Test Specification has an ID' END ;
TEST_SPECIFICATION_IN_EXPECTED_FORMAT : GHERKIN_STEP_KEYWORD 'the Test Specification is in the expected format' END ;


TEST_CASE_IS_PRODUCED : GHERKIN_STEP_KEYWORD 'a "Test Case" is produced' END ;
TEST_CASE_HAS_A_UNIQUE_ID : GHERKIN_STEP_KEYWORD 'the Test Case has a unique ID' END ;
TEST_CASE_HAS_A_TITLE : GHERKIN_STEP_KEYWORD 'the Test Case has a title' END ;
TEST_CASE_HAS_PROPER_TEST_BODY : GHERKIN_STEP_KEYWORD 'the Test Case has a test body constructed properly' END ;

PDSL_CAN_PROCESS_ALL_PHRASES : GHERKIN_STEP_KEYWORD 'the Polymorphic DSL Test Executor can process all phrases in the test case' END ;


PASSING_TEST_TOTAL : GHERKIN_STEP_KEYWORD 'the Test Run Result has a passing test total of ' INT END ;
PASSING_PHRASE_TOTAL : GHERKIN_STEP_KEYWORD 'the Test Run Result passing phrase total is ' INT END ;
FAILING_TEST_TOTAL : GHERKIN_STEP_KEYWORD 'the Test Run Result has ' INT ' failing tests' END ;
TOTAL_PHRASES : GHERKIN_STEP_KEYWORD 'the Test Run Result total phrases is ' INT END ;
DUPLICATE_TEST_TOTAL : GHERKIN_STEP_KEYWORD 'the Test Run Result has ' INT ' "Total Filtered Duplicate Tests"' END ;