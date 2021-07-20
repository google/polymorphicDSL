parser grammar PdslTestRunResultsMetaParser ;

import PdslCommonParser, TestRunResultCommonParser, TestResourceParser, TestRunResultsParser ;

options { tokenVocab=PdslTestRunResultsMetaLexer ; }


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