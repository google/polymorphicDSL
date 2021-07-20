parser grammar PdslCommonParser ;

options {tokenVocab=PdslCommonLexer; }

whenTheTestResourceIsProcessedByFactory : WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY ;
testSpecificationIsProduced :  TEST_SPECIFICATION_IS_PRODUCED ;
testSpecificationIsProcessedByTestCaseFactory : TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY ;

polymorphicDslTestExecutor : POLYMORPHIC_DSL_TEST_EXECUTOR ;

testRunResultProduced : TEST_RUN_RESULT_PRODUCED ;

whenTheTestCaseIsProcessedByAnyPdslTestExecutor : TEST_CASE_IS_PROCESSED ;