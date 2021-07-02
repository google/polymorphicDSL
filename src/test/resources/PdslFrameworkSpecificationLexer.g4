lexer grammar PdslFrameworkSpecificationLexer;

fragment GHERKIN_STEP_KEYWORD : WS* ('Given ' | 'When ' | 'Then ' | 'And ' | 'But ');
fragment WS : [\r\n\t ] ;
fragment END : WS* EOF? ;
INT : [0-9]+;
GIVEN_THE_TEST_RESOURCE : GHERKIN_STEP_KEYWORD 'the test resource "' .+? '"' END ;
WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY : GHERKIN_STEP_KEYWORD 'the test resource is processed by a "Test Specification Factory"\n' END ;
TEST_SPECIFICATION_IS_PRODUCED : GHERKIN_STEP_KEYWORD 'a "Test Specification" is produced' END ;
TEST_SPECIFICATION_HAS_AN_ID : GHERKIN_STEP_KEYWORD 'the Test Specification has an ID' END ;
TEST_SPECIFICATION_IN_EXPECTED_FORMAT : GHERKIN_STEP_KEYWORD 'the Test Specification is in the expected format' END ;

TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY : GHERKIN_STEP_KEYWORD 'the Test Specification is processed by a "Test Case Factory"' END ;
TEST_CASE_IS_PRODUCED : GHERKIN_STEP_KEYWORD 'a "Test Case" is produced' END ;
TEST_CASE_HAS_A_UNIQUE_ID : GHERKIN_STEP_KEYWORD 'the Test Case has a unique ID' END ;
TEST_CASE_HAS_A_TITLE : GHERKIN_STEP_KEYWORD 'the Test Case has a title' END ;
TEST_CASE_HAS_PROPER_TEST_BODY : GHERKIN_STEP_KEYWORD 'the Test Case has a test body constructed properly' END ;

POLYMORPHIC_DSL_TEST_EXECUTOR : GHERKIN_STEP_KEYWORD 'a "Polymorphic DSL Test Executor"' END ;
PDSL_CAN_PROCESS_ALL_PHRASES : GHERKIN_STEP_KEYWORD 'the Polymorphic DSL Test Executor can process all phrases in the test case' END ;
TEST_CASE_IS_PROCESSED : GHERKIN_STEP_KEYWORD 'the Test Case is processed by a "Polymorphic DSL Test Executor"' END ;
TEST_RUN_RESULT_PRODUCED : GHERKIN_STEP_KEYWORD 'a "Test Run Result" is produced' END ;

PASSING_TEST_TOTAL : GHERKIN_STEP_KEYWORD 'the Test Run Result has a passing test total of ' INT END ;
PASSING_PHRASE_TOTAL : GHERKIN_STEP_KEYWORD 'the Test Run Result passing phrase total is ' INT END ;
FAILING_TEST_TOTAL : GHERKIN_STEP_KEYWORD 'the Test Run Result has ' INT ' failing tests' END ;
TOTAL_PHRASES : GHERKIN_STEP_KEYWORD 'the Test Run Result total phrases is ' INT END ;
DUPLICATE_TEST_TOTAL : GHERKIN_STEP_KEYWORD 'the Test Run Result has ' INT ' "Total Filtered Duplicate Tests"' END ;
