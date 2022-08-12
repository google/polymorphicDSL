parser grammar TestCaseFactoryParser ;

import GherkinCommonParser ;

options {tokenVocab=TestCaseFactoryLexer ; }

givenSpecificTestCaseFactoryIsUsed : GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED ;
givenAnArbitraryTestSpecification : GIVEN_AN_ARBITRARY_TEST_SPECIFICATION ;
givenSpecificationHasPhrase : GIVEN_SPECIFICATION_HAS_N_PHRASES_START  PHRASES_END ;
givenTheTestSpecificationChild : GIVEN_THE_TEST_SPECIFICATION_CHILD_START PHRASES_END ;
thenTestCasesAreProduced : THEN_N_TEST_CASES_ARE_PRODUCED_END ;
thenEachTestCaseHasPhrases : THEN_EACH_TEST_CASE_HAS_N_PHRASES_START PHRASES_END ;
whenTestSpecificationProcessedByFactory : WHEN_TEST_SPECIFICATON_PROCESSED_BY_FACTORY;
polymorphicDslAllRules : (
    givenAnArbitraryTestSpecification |
    givenSpecificationHasPhrase |
    givenTheTestSpecificationChild |
    thenTestCasesAreProduced |
    givenSpecificTestCaseFactoryIsUsed |
    whenTestSpecificationProcessedByFactory |
    thenEachTestCaseHasPhrases
    )+
    ;