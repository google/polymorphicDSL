lexer grammar TestRunResultsLexer ;

import GherkinCommonLexer ;


WHEN_THE_TEST_RESOURCE_IS_CONVERTED_TO_A_SINGLE_TEST_CASE_BY_SOME_METHOD : GHERKIN_STEP_KEYWORD
    'the test resource is converted to a single test case by some method' END ;

THEN_TEST_TEST_CASE_COLLECTION_HAS_N_TEST_CASES_START : GHERKIN_STEP_KEYWORD 'the test case collection has ' [0-9]+ ' test case' 's'? END ;

GIVEN_THE_GRAMMAR_PARSE_TREE_LISTENER : GHERKIN_STEP_KEYWORD 'the Grammar Parse Tree Listener ' QUOTED_TEXT END;
THEN_A_SINGLE_TEST_CASE_IS_PRODUCED : GHERKIN_STEP_KEYWORD 'a single test case is produced' END;

THEN_THE_TEST_RUN_RESULT_HAS : GHERKIN_STEP_KEYWORD 'the test run result has ' INT;
FILTERED_DUPLICATE_TESTS_END : ' filtered duplicate test' 's'? END ;
PASSING_PHRASES_END : ' passing phrase' 's'? END ;
TOTAL_PHRASES_END : ' total phrase' 's'? END ;

WHEN_THE_TEST_METADATA_IS_RETRIEVED_FROM_THE_TEST_RUN_RESULT : GHERKIN_STEP_KEYWORD 'the Test Metadata is retrieved from the Test Run Result' END ;
WHEN_THE_ONLY_TEST_METADATA_ITEM_IS_EXAMINED : GHERKIN_STEP_KEYWORD 'the only Test Metadata item is examined' END ;

THEN_THE_TEST_METADATA_HAS_ONE_ITEM_IN_IT : GHERKIN_STEP_KEYWORD 'the Test Metadata has 1 item in it' END;
THEN_THE_TEST_METADATA_HAS_SPECIFIED_ITEMS_IN_IT_END : ' item' 's'? ' in it' END ;
THEN_THE_TEST_METADATA_HAS_THE_PHRASE_THAT_FAILED : GHERKIN_STEP_KEYWORD 'the Test Metadata has the phrase that failed' END ;
THEN_THE_TEST_METADATA_HAS_AN_EXCEPTION_EXPLAINING_WHY_THE_TEST_FAILED : GHERKIN_STEP_KEYWORD 'the Test Metadata has an exception explaining why the test failed' END ;
THEN_THE_TEST_METADATA_FAILING_PHRASE_IS : GHERKIN_STEP_KEYWORD 'the Test Metadata failing phrase is ' QUOTED_TEXT END;

GIVEN_ANOTHER_TEST_RESOURCE : GHERKIN_STEP_KEYWORD 'another test resource:' END DOCSTRING;