lexer grammar TestRunResultCommonLexer ;
import GherkinCommonLexer ;

tokens {END, INT}
THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START : GHERKIN_STEP_KEYWORD 'the test run results ' ('have ' | 'has ') INT;
FAILING_TESTS_END : ' failing test' 's'? END ;
PASSING_TESTS_END : ' passing test' 's'? END ;
