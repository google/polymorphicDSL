lexer grammar JavaTestRunnerLexer ;

import GherkinCommonLexer, JavaRunnerSubgrammarLexer;

GIVEN_ALL_PDSL_TESTS_ARE_VALID: GHERKIN_STEP_KEYWORD 'all @PdslTests are valid'-> mode(POTENTIAL_DOCSTRING_MODE);

GIVEN_RECOGNIZED_BY_TEST_SPECIFIES: GHERKIN_STEP_KEYWORD 'the @RecognizedBy specifies the "' -> mode(PARAMETER_MODE);

GIVEN_THE_PDSL_CONFIGURATION_SPECIFIES_THE: GHERKIN_STEP_KEYWORD 'the @PdslConfiguration ' ('DOES NOT specify' | 'specifies') ' the "' -> mode(PARAMETER_MODE);

GIVEN_THE_PDSL_TEST_USES_FACTORIES_THAT_FILTER_BASED_ON_TAGS: GHERKIN_STEP_KEYWORD 'the PDSL test uses factories that filter based on tags' END ;
GIVEN_THE_PDSL_TEST_HAS_A_TAG_SPECIFIED: GHERKIN_STEP_KEYWORD 'the @PdslTest has a tag specified' END ;
GIVEN_A_TEST_RESOURCE_IS_MARKED_WITH_TAG: GHERKIN_STEP_KEYWORD 'a test resource is marked with that tag' END;
GIVEN_PDSL_RECOGNIZED_BY: GHERKIN_STEP_KEYWORD 'the @PdslTest ' ('DOES' | 'does') ' NOT'? ' have a @RecognizedBy' END;

GIVEN_THE_PDSL_TEST_HAS_RESOURCES_THAT_MAY_BE_RECOGNIZED_BY_PDSL_CONFIGURATION: GHERKIN_STEP_KEYWORD 'the @PdslTest has phrases that ' ('are' | 'ARE')  (' not' | ' NOT')? ' recognized by the @PdslConfiguration recognizer' -> mode(POTENTIAL_DOCSTRING_MODE);

GIVEN_THE_PDSL_TEST_HAS_RESOURCES_THAT_MAY_BE_RECOGNIZED_BY_PDSL_TEST : GHERKIN_STEP_KEYWORD 'the test resource has phrases that ' ('are' | 'ARE') (' not' | ' NOT')? ' recognized by the @PdslTest recognizer' -> mode(POTENTIAL_DOCSTRING_MODE);

GIVEN_THE_PDSL_CONFIGURATION_DOES_NOT_SPECIFY_DEFAULT_RULE: GHERKIN_STEP_KEYWORD 'the @PdslConfiguration recognizer does not specify the default rule "' ('polymorphicDslSyntaxCheck' | 'polymorphicDslAllRules') '" parameter' -> mode(POTENTIAL_DOCSTRING_MODE);
WHEN_THE_TEST_RUNNER_EXECUTES: GHERKIN_STEP_KEYWORD 'the test runner executes' END ;

THEN_ALL_TESTS_PASS: GHERKIN_STEP_KEYWORD 'all tests pass' END ;
THEN_THE_SPECIFIED_TEST_EXECUTOR_WAS_USED: GHERKIN_STEP_KEYWORD 'the specified Test Executor was used' END ;
THEN_THE_SPECIFIED_RESOURCE_PROVIDER_WAS_USED: GHERKIN_STEP_KEYWORD 'the specified resource provider was used' END ;
THEN_THE_TEST_IS_SKIPPED: GHERKIN_STEP_KEYWORD 'the test is skipped' END ;

THEN_THE_PDSL_FRAMEWORK_THROWS_AN_EXCEPTION: GHERKIN_STEP_KEYWORD 'the PDSL framework throws an exception' END;
THEN_THE_EXCEPTION_COMMUNICATES_RESOURCE_COULD_NOT_BE_INTERPRETED: GHERKIN_STEP_KEYWORD 'the exception communicates that the resource could not be interpreted by the recognizer' END;

THEN_EXCEPTION_REQUIRES_RECOGNIZER_PARSER_AND_LEXER_BOTH_BE_USED: GHERKIN_STEP_KEYWORD 'the exception communicates that both the dslRecognizerParser and dslRecognizerLexer must be used together if they are used at all' END ;
THEN_EXCEPTION_STATES_MISSING_REQUIRED_SYNTAX_CHECK_RULE: GHERKIN_STEP_KEYWORD 'the exception communicates that the parser does not have the required syntax check rule' END ;

BUT_THE_PDSL_TEST_HAS_PHRASES_THAT_ARE_NOT_IN_RECOGNIZER: GHERKIN_STEP_KEYWORD 'the @PdslTest has phrases that are NOT recognized by the @PdslConfiguration recognizer' END;
mode POTENTIAL_DOCSTRING_MODE;
  START_POTENTIAL_DOCSTRING: ':' END WS+ '"""' END WS*-> mode(DOCSTRING_MODE);
  END_POTENTIAL_DOCSTRING_MODE:  END -> mode(DEFAULT_MODE);


mode DOCSTRING_MODE;
     CLOSE_DOCSTRING: '"""' WS* END -> mode(DEFAULT_MODE);
     DOCSTRING_BODY: .+?;

mode PARAMETER_MODE;
    PARAMETER: ~'"'+;
    END_PARAMETER_MODE: '" parameter' -> mode(POTENTIAL_DOCSTRING_MODE);