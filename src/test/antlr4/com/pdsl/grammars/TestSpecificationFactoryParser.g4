parser grammar TestSpecificationFactoryParser ;

import TestSpecificationFactoryDetailsParser, GherkinCommonParser, TestResourceParser ;

options {tokenVocab=PdslTestSpecificationFactoryLexer;}


givenSpecificGrammar : GIVEN_SPECIFIC_GRAMMAR ;
givenSpecificSubgrammar : GIVEN_SPECIFIC_SUBGRAMMAR ;
thenTestSpecificationFailsDueToMissingScenario : THEN_TEST_SPECIFICATION_FAILS_DUE_TO_MISSING_SCENARIO ;
thenTestSpecificationFailsBecauseOfMissingStepBody : THEN_TEST_SPECIFICATION_FAILS_BECAUSE_OF_MISSING_STEP_BODY ;
givenNonExistentUrl : GIVEN_NONEXISTENT_URL ;
thenNoSuchResourceError : THEN_NO_SUCH_RESOURCE_ERROR ;
thenTestSpecificationHasTotalPhrases : TEST_SPECIFICATION_TOTAL_PHRASES_START ;
testSpecificationMayBeProduced : TEST_SPECIFICATION_MAY_BE_PRODUCED ;
testResourceProcessedByFactory : TEST_SPECIFICATION_IS_PROCESSED_BY_THE_FACTORY;

////////////////////////////

polymorphicDslAllRules : (
    //TestResourceParser
    givenTheRawResource |

    // TestSpecificationFactoryDetails
    givenSpecificTestSpecificationFactory |

    //This grammar
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