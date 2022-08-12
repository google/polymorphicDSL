lexer grammar TestExecutorLexer ;

import GherkinCommonLexer, PdslCommonLexer, TestResourceLexer, TestSpecificationFactoryDetailsLexer, TestRunResultCommonLexer ;

tokens { GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY }
GIVEN_THE_TEST_EXECUTOR_IS : GHERKIN_STEP_KEYWORD 'the test executor is the ' QUOTED_TEXT END ;
GIVEN_THE_GRAMMAR_PARSE_TREE_LISTENER : GHERKIN_STEP_KEYWORD 'the Grammar Parse Tree Listener ' QUOTED_TEXT END;
GIVEN_THE_SUBGRAMMAR_PARSE_TREE_LISTENER : GHERKIN_STEP_KEYWORD 'the Subgrammar Parse Tree Listener ' QUOTED_TEXT END;
WHEN_THE_GHERKIN_TEST_EXECUTOR_RUNS_WITH_TAG_EXPRESSION : GHERKIN_STEP_KEYWORD 'the Gherkin Test Executor runs the tests with the tag expression ' QUOTED_TEXT END;
GIVEN_GRAMMAR_PHRASE_FILTER_IS : GHERKIN_STEP_KEYWORD 'the Grammar Phrase Filter is ' QUOTED_TEXT END;
GIVEN_SUBGRAMMAR_PHRASE_FILTER_IS : GHERKIN_STEP_KEYWORD 'the Subgrammar Phrase Filter is ' QUOTED_TEXT END ;