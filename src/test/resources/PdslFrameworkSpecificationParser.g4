parser grammar PdslFrameworkSpecificationParser;

options {tokenVocab=PdslFrameworkSpecificationLexer;}
givenTheTestResource :  GIVEN_THE_TEST_RESOURCE ;
whenTheTestResourceIsProcessedByFactory : WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY ;
testSpecificationIsProduced :  TEST_SPECIFICATION_IS_PRODUCED ;
testSpecificationHasAnId : TEST_SPECIFICATION_HAS_AN_ID ;
testSpecificationInExpectedFormat : TEST_SPECIFICATION_IN_EXPECTED_FORMAT ;

testSpecificationIsProcessedByTestCaseFactory : TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY ;
testCaseIsProduced : TEST_CASE_IS_PRODUCED ;
testCaseHasUniqueId : TEST_CASE_HAS_A_UNIQUE_ID ;
testCaseHasTitle : TEST_CASE_HAS_A_TITLE ;
testCaseHasProperTestBody : TEST_CASE_HAS_PROPER_TEST_BODY ;

polymorphicDslTestExecutor : POLYMORPHIC_DSL_TEST_EXECUTOR ;
pdslCanProcessAllPhrases : PDSL_CAN_PROCESS_ALL_PHRASES ;
testCaseIsProcessed : TEST_CASE_IS_PROCESSED EOF ;
testRunResultProduced : TEST_RUN_RESULT_PRODUCED ;

passingTestTotal : PASSING_TEST_TOTAL ;
passingPhraseTotal : PASSING_PHRASE_TOTAL ;
failingTestTotal : FAILING_TEST_TOTAL ;
totalPhrases : TOTAL_PHRASES ;
duplicateTestTotal : DUPLICATE_TEST_TOTAL ;

/*
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
*/

