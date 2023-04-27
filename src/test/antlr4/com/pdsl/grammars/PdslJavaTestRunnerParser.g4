parser grammar PdslJavaTestRunnerParser;

import JavaRunnerSubgrammarParser, JavaTestRunnerParser;

options {tokenVocab=PdslJavaTestRunnerLexer; }

polymorphicDslAllRules: (
	givenPdslTest |
	givenAllPdslTestsAreValid |
	givenPdslConfigurationSpecifiesProperty |
	givenThePdslTestUsesFactoriesThatFilterBasedOnTags |
	givenThePdslTestHasATagSpecified |
	givenTestResourceMarkWithSpecifiedTag |
	givenPdslRecognizedBy |
	givenPslTestHasPhrasesNotInRecognizer |
	givenPdslTestHasResourcesThatMightBeRecognizedByConfiguration |
	givenPdslTestHasResources |
	givenPdslConfigurationDoesNotSpecifyDefaultRule |
	givenRecognizedBySpecifiesParameter |
	whenTestRunnerExecutes |

	thenAllTestsPass |
	thenSpecifiedTestExecutorWasUsed |
	thenSpecifiedResourceProviderWasUsed |
	thenTestIsSkipped |

	thenPdslFrameworkThrowsAnException |
	thenExceptionCommunicatesResourceCouldNotBeInterpreted |

	thenExceptionStatesBothRecognizerParserAndLexerNeeded |
	thenExceptionStatesMissingRequiredSyntaxCheckRule
	)+
	;
