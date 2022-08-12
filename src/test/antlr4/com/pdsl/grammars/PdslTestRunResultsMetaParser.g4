parser grammar PdslTestRunResultsMetaParser ;

import PdslCommonParser, TestRunResultCommonParser, TestResourceParser, TestRunResultsParser ;


polymorphicDslAllRules : (

    // TestResourceParser
    givenTheRawResource |
    givenTheTestResource |

    //TestRunResultsParser
    thenTestCaseCollectionHasSpecifiedTestCases |
    convertTestResourcesToCollectionWithSingleTestCase |
    thenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailed |
    thenTheTestMetadataHasThePhraseThatFailed |
    whenTheOnlyTestMetadataItemIsExamined |
    thenTheTestMetadataFailingPhraseIsSpecifiedText  |
    whenTheTestMetadataIsRetrievedFromTheTestRunResult |
    thenTheTestMetadataHasOneItemInIt |
    thenTheTestRunResultHasSpecifiedTotalPhrases |
    thenSingleTestCaseIsProduced |
    givenAnotherTestResource |
    // givenTheSpecifiedGrammarParseTreeListener |

    // TestRunResultCommonParser
    thenTheTestRunResultsHaveSpecifiedPassingTests |
    thenTheTestRunResultsHaveSpecifiedFailingTests |

    // PdslCommonParser
    whenTheTestResourceIsProcessedByFactory |
    testSpecificationIsProduced |
    testSpecificationIsProcessedByTestCaseFactory |
    polymorphicDslTestExecutor |
    testRunResultProduced |
    whenTheTestCaseIsProcessedByAnyPdslTestExecutor
    )+
    ;