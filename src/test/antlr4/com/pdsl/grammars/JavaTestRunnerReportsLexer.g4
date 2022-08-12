lexer grammar JavaTestRunnerReportsLexer;

import GherkinCommonLexer;

    GIVEN_A_PDSL_TEST_SUITE_RESET: GHERKIN_STEP_KEYWORD 'a PDSL test' END  ;
    GIVEN_A_PDSL_TEST_SUITE_MODE: GHERKIN_STEP_KEYWORD 'a suite of PDSL tests' ':'? END ;
    GIVEN_TEST_SUITE_CONTAINS_TEST_FOR_APPLICATION_UNIT: GHERKIN_STEP_KEYWORD 'the PDSL test suite contains a test for application "MyApp" and context "API"' END;
    GIVEN_TEST_SUITE_CONTAINS_TEST_FOR_APPLICATION_API: GHERKIN_STEP_KEYWORD 'the PDSL test suite contains a test for application "MyApp" and context "Unit"' END;
    THEN_THE_PDSL_TEST_SUITE_SPECIFIES: GHERKIN_STEP_KEYWORD 'the PDSL test suite specifies a "reportGenerator" parameter:' END ;
    WHEN_THE_TEST_RUNNER_EXECUTES_SUITE: GHERKIN_STEP_KEYWORD 'the test runner executes' END ;
    fragment DOCSTRING_DOUBLE_QUOTES_SUITE : WS* '"""' .*?  '"""' NEWLINE;
    UNIT_PASSES: GHERKIN_STEP_KEYWORD 'the context "Unit" test will pass' END;
    API_FAILS: GHERKIN_STEP_KEYWORD 'the context "API" test will fail' END;
    THEN_A_REPORT_IS_GENERATED: GHERKIN_STEP_KEYWORD 'a report is generated' END ;
    THEN_THE_REPORT_SHOWS_THAT_THE_TEST_RESULT_IN_CONTEXT: GHERKIN_STEP_KEYWORD 'the report shows that the test ' ('passes' | 'fails') ' in the ';