lexer grammar TestRunResultCommonLexer ;
import GherkinCommonLexer ;

tokens {END}
THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START : GHERKIN_STEP_KEYWORD 'the test run results ' ('have ' | 'has ') ;
FAILING_TESTS_END : ' failing test' 's'? END ;
PASSING_TESTS_END : ' passing test' 's'? END ;
INTEGER_VALUE : [0-9]+ ;