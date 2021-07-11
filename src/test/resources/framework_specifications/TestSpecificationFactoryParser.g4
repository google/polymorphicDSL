parser grammar TestSpecificationFactoryParser ;

import GherkinCommonParser, TestResourceParser ;

options {tokenVocab=TestSpecificationFactoryLexer;}

givenSpecificTestSpecificationFactory : GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY textInDoubleQuotes END_QUOTE ;
givenSpecificGrammar : GIVEN_SPECIFIC_GRAMMAR textInDoubleQuotes END_QUOTE ;
givenSpecificSubgrammar : GIVEN_SPECIFIC_SUBGRAMMAR textInDoubleQuotes END_QUOTE ;
thenTestSpecificationFailsDueToMissingScenario : THEN_TEST_SPECIFICATION_FAILS_DUE_TO_MISSING_SCENARIO ;
thenTestSpecificationFailsBecauseOfMissingStepBody : THEN_TEST_SPECIFICATION_FAILS_BECAUSE_OF_MISSING_STEP_BODY ;
givenNonExistentUrl : GIVEN_NONEXISTENT_URL ;
thenNoSuchResourceError : THEN_NO_SUCH_RESOURCE_ERROR ;
thenTestSpecificationHasTotalPhrases : TEST_SPECIFICATION_TOTAL_PHRASES_START integerValue TEST_SPECIFICATION_TOTAL_PHRASES_END ;
testSpecificationMayBeProduced : TEST_SPECIFICATION_MAY_BE_PRODUCED ;
testResourceProcessedByFactory : TEST_SPECIFICATION_IS_PROCESSED_BY_THE_FACTORY;

////////////////////////////

polymorphicDslAllRules : (
    //TestResourceParser
    givenTheRawResource |

    //This grammar
    givenSpecificTestSpecificationFactory |
    givenSpecificGrammar |
    givenSpecificSubgrammar |
    thenTestSpecificationFailsDueToMissingScenario |
    thenTestSpecificationFailsBecauseOfMissingStepBody |
    givenNonExistentUrl |
    thenNoSuchResourceError |
    thenTestSpecificationHasTotalPhrases |
    testSpecificationMayBeProduced |
    testResourceProcessedByFactory
    )+
    ;