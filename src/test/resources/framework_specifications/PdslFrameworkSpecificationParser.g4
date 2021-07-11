parser grammar PdslFrameworkSpecificationParser;

import TestResourceParser, PdslCommonParser ;

options {tokenVocab=PdslFrameworkSpecificationLexer;}


testSpecificationHasAnId : TEST_SPECIFICATION_HAS_AN_ID ;
testSpecificationInExpectedFormat : TEST_SPECIFICATION_IN_EXPECTED_FORMAT ;

testCaseIsProduced : TEST_CASE_IS_PRODUCED ;
testCaseHasUniqueId : TEST_CASE_HAS_A_UNIQUE_ID ;
testCaseHasTitle : TEST_CASE_HAS_A_TITLE ;
testCaseHasProperTestBody : TEST_CASE_HAS_PROPER_TEST_BODY ;


pdslCanProcessAllPhrases : PDSL_CAN_PROCESS_ALL_PHRASES ;
testCaseIsProcessed : TEST_CASE_IS_PROCESSED EOF ;

passingTestTotal : PASSING_TEST_TOTAL ;
passingPhraseTotal : PASSING_PHRASE_TOTAL ;
failingTestTotal : FAILING_TEST_TOTAL ;
totalPhrases : TOTAL_PHRASES ;
duplicateTestTotal : DUPLICATE_TEST_TOTAL ;

polymorphicDslAllRules :  (
  // TestResourceParser
  givenTheTestResource |
  whenTheTestResourceIsProcessedByFactory |
  testSpecificationIsProduced |
  testSpecificationHasAnId |
  testSpecificationInExpectedFormat |

    testSpecificationIsProcessedByTestCaseFactory |
    testCaseIsProduced  |
    testCaseHasUniqueId |
    testCaseHasTitle |
    testCaseHasProperTestBody |

    polymorphicDslTestExecutor |
    pdslCanProcessAllPhrases |
    testCaseIsProcessed |
    testRunResultProduced |

    passingTestTotal |
    passingPhraseTotal |
    failingTestTotal |
    totalPhrases |
    duplicateTestTotal )+
    ;

polymorphicDslSyntaxRule :
	givenTheTestResource+
	whenTheTestResourceIsProcessedByFactory
	testSpecificationIsProduced 
	(
		testSpecificationHasAnId | 
		testSpecificationInExpectedFormat 
	)+
	testSpecificationIsProcessedByTestCaseFactory 
	testCaseIsProduced 
	(
		testCaseHasUniqueId |
		testCaseHasTitle |
		testCaseHasProperTestBody 
	)+

	polymorphicDslTestExecutor+ 
	(
		pdslCanProcessAllPhrases // Add steps for other parse tree listeners here
	)+
	testCaseIsProcessed
	testRunResultProduced
	(
		passingTestTotal |
		passingPhraseTotal |
		failingTestTotal |
		totalPhrases |
		duplicateTestTotal 
	)+
;


