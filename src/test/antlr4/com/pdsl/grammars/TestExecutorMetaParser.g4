parser grammar TestExecutorMetaParser ;

import TestExecutorParser, TestSpecificationFactoryDetailsParser, TestRunResultCommonParser ;

options { tokenVocab=TestExecutorLexer ; }

polymorphicDslAllRules : (
    // TestExecutorParser
    givenTheTestExecutorIsSpecified |
    givenTheGrammarListener |
    givenTheSubgrammarListener |
    whenTheGherkinTestExecutorRunsWithTagExpression |
    givenTheGrammarPhraseFilter |
    givenTheSubgrammarPhraseFilter |

    // TestResourceParser
    givenTheTestResource |
    givenTheRawResource |

    // PdslCommonParser
    whenTheTestResourceIsProcessedByFactory |
    testSpecificationIsProduced |
    testSpecificationIsProcessedByTestCaseFactory |
    polymorphicDslTestExecutor |
    testRunResultProduced |
    whenTheTestCaseIsProcessedByAnyPdslTestExecutor |

    // TestSpecificationFactoryDetailsParser
    givenSpecificTestSpecificationFactory |

    // TestRunResultCommonParser
    thenTheTestRunResultsHaveSpecifiedPassingTests |
    thenTheTestRunResultsHaveSpecifiedFailingTests
    )+
    ;