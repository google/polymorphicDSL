lexer grammar PdslFrameworkSpecificationLexer;

NL : [\r\n]+ -> skip;

fragment GHERKIN_STEP_KEYWORD : 'Given ' | 'When ' | 'Then ' | 'And ' | 'But ';
INT : [0-9]+;
GIVEN_THE_TEST_RESOURCE : GHERKIN_STEP_KEYWORD 'the test resource "' .+? '"'  ;
WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY : GHERKIN_STEP_KEYWORD 'the test resource is processed by a "Test Specification Factory"'  ;
TEST_SPECIFICATION_IS_PRODUCED : GHERKIN_STEP_KEYWORD 'a "Test Specification" is produced'  ;
TEST_SPECIFICATION_HAS_AN_ID : GHERKIN_STEP_KEYWORD 'the Test Specification has an ID'  ;
TEST_SPECIFICATION_IN_EXPECTED_FORMAT : GHERKIN_STEP_KEYWORD 'the Test Specification is in the expected format'  ;

TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY : GHERKIN_STEP_KEYWORD 'the Test Specification is processed by a "Test Case Factory"'  ;
TEST_CASE_IS_PRODUCED : GHERKIN_STEP_KEYWORD 'a "Test Case" is produced'  ;
TEST_CASE_HAS_A_UNIQUE_ID : GHERKIN_STEP_KEYWORD 'the Test Case has a unique ID'  ;
TEST_CASE_HAS_A_TITLE : GHERKIN_STEP_KEYWORD 'the Test Case has a title'  ;
TEST_CASE_HAS_PROPER_TEST_BODY : GHERKIN_STEP_KEYWORD 'the Test Case has a test body constructed properly'  ;

POLYMORPHIC_DSL_TEST_EXECUTOR : GHERKIN_STEP_KEYWORD 'a "Polymorphic DSL Test Executor"'  ;
PDSL_CAN_PROCESS_ALL_PHRASES : GHERKIN_STEP_KEYWORD 'the Polymorphic DSL Test Executor can process all phrases in the test case'  ;
TEST_CASE_IS_PROCESSED : GHERKIN_STEP_KEYWORD 'the Test Case is processed by a "Polymorphic DSL Test Executor"'  ;
TEST_RUN_RESULT_PRODUCED : GHERKIN_STEP_KEYWORD 'a "Test Run Result" is produced'  ;

PASSING_TEST_TOTAL : GHERKIN_STEP_KEYWORD 'the Test Run Result has a passing test total of ' INT  ;
PASSING_PHRASE_TOTAL : GHERKIN_STEP_KEYWORD 'the Test Run Result passing phrase total is ' INT  ;
FAILING_TEST_TOTAL : GHERKIN_STEP_KEYWORD 'the Test Run Result has ' INT ' failing tests'  ;
TOTAL_PHRASES : GHERKIN_STEP_KEYWORD 'the Test Run Result total phrases is ' INT  ;
DUPLICATE_TEST_TOTAL : GHERKIN_STEP_KEYWORD 'the Test Run Result has ' INT ' "Total Filtered Duplicate Tests"'  ;
