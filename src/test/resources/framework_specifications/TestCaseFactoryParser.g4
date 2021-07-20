parser grammar TestCaseFactoryParser ;

import GherkinCommonParser ;

options {tokenVocab=TestCaseFactoryLexer ; }

givenAnArbitraryTestSpecification : GIVEN_AN_ARBITRARY_TEST_SPECIFICATION ;
givenSpecificationHasPhrase : GHERKIN_STEP_KEYWORD GIVEN_SPECIFICATION_HAS_N_PHRASES_START integerValue PHRASES_END ;
givenTheTestSpecificationChild : GIVEN_THE_TEST_SPECIFICATION_CHILD_START integerValue PHRASES_END ;
thenTestCasesAreProduced : GHERKIN_STEP_KEYWORD integerValue THEN_N_TEST_CASES_ARE_PRODUCED_END ;
thenEachTestCaseHasPhrases : THEN_EACH_TEST_CASE_HAS_N_PHRASES_START integerValue PHRASES_END ;
givenSpecificTestCaseFactoryIsUsed : GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_START textInDoubleQuotes GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_END ;
whenTestSpecificationProcessedByFactory : WHEN_TEST_SPECIFICATON_PROCESSED_BY_FACTORY;
polymorphicDslAllRules : (
    givenAnArbitraryTestSpecification |
    givenSpecificationHasPhrase |
    givenTheTestSpecificationChild |
    thenTestCasesAreProduced |
    givenSpecificTestCaseFactoryIsUsed |
    thenEachTestCaseHasPhrases
    )+
    ;