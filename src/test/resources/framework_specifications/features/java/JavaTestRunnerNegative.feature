Feature: Java Test Runner- Negative Scenarios

Rule: Negative Scenarios
These are ways you should NOT use the framework

Scenario: User Error - Lexer recognizer is defined but the parser rule is not in @PdslConfiguration
Both the dslRecognizerParser and dslRecognizerLexer need to be defined. If they are not then the test suite
will not compile.

Given a PDSL test
And the @PdslConfiguration specifies the "dslRecognizerLexer" parameter
But the @PdslConfiguration DOES NOT specify the "dslRecognizerParser" parameter:
"""
        @RunWith(PdslJUnit4ConfigurableRunner.class)
        @PdslConfiguration(
                dslRecognizerLexer = AllGrammarsLexer.class,
                specificationFactoryProvider = SomeProvider.class,
                testCaseFactoryProvider =  SomeOtherProvider.class
        )
        """
When the test runner executes
Then the PDSL framework throws an exception
And the exception communicates that both the dslRecognizerParser and dslRecognizerLexer must be used together if they are used at all

Scenario: User Error - Parser recognizer defined but lexer is not
Both the dslRecognizerParser and dslRecognizerLexer need to be defined. If they are not then the test suite
will not compile.

Given a PDSL test
And the @PdslConfiguration specifies the "dslRecognizerParser" parameter
But the @PdslConfiguration DOES NOT specify the "dslRecognizerLexer" parameter:
"""
			@RunWith(PdslJUnit4ConfigurableRunner.class)
			@PdslConfiguration(
						 dslRecognizerParser = AllGrammarsParser.class,
						 specificationFactoryProvider = SomeProvider.class,
						 testCaseFactoryProvider =  SomeOtherProvider.class
			)
			public class MalformedPdslJUnitMissingLexerTest {
			"""
When the test runner executes
Then the PDSL framework throws an exception
And the exception communicates that both the dslRecognizerParser and dslRecognizerLexer must be used together if they are used at all

Scenario: Class level, custom recognizer rule fails to recognize resource
In the event that the class-level parser uses a custom 'recognizerRule' it will be used to check the syntax of the
test resources. Should the rule fail to recognize the test resource the test suite will fail to compile.

A test method with @PdslTest can also have a @RecognizedBy annotation that will override the value of the @PdslConfiguration.
However if this is not specified then the @PdslTest will default to the @PdslConfiguration rule, which by default is
"polymorphicDslSyntaxCheck"

Given a PDSL test
But the @PdslTest has phrases that are not recognized by the @PdslConfiguration recognizer:
"""
			@RunWith(PdslJUnit4ConfigurableRunner.class)
			@PdslConfiguration(
						 dslRecognizerParser = SomeRecognizerParser.class,
						 dslRecognizerLexer = SomeRecognizerLexer.class,
						 specificationFactoryProvider = SomeProvider.class,
						 testCaseFactoryProvider =  SomeOtherProvider.class
			)
			public class RecognizerMissingConventionalSyntaxCheckRule {
          @PdslTest(
                        includesResources = "someResource.txt", // <-- Assume this test resource can not be parsed by the "polymorphicDslSyntaxCheck" rule
                        parser = SomeParser.class,
                        lexer = SomeLexer.class,
                        listener = FrameworkSpecificationListenerProvider.class
                )
                public void testMissingSyntaxRuleInParentRecognizer_shouldFailToCompile(){}

			}
			"""
When the test runner executes
Then the PDSL framework throws an exception
And the exception communicates that the parser does not have the required syntax check rule

Scenario: Class wide parser missing default recognizer rule
Given a PDSL test
And the @PdslConfiguration DOES NOT specify the "recognizerRule" parameter
And the @PdslTest DOES NOT have a @RecognizedBy
But the @PdslConfiguration recognizer does not specify the default rule "polymorphicDslSyntaxCheck" parameter:
"""
			@RunWith(PdslJUnit4ConfigurableRunner.class)
			@PdslConfiguration(
						 dslRecognizerParser = SomeRecognizerParser.class, // <-- Assume this parser does not have the rule "polymorphicDslSyntaxCheck"
						 dslRecognizerLexer = SomeRecognizerLexer.class,
						 specificationFactoryProvider = SomeProvider.class,
						 testCaseFactoryProvider =  SomeOtherProvider.class
			)
			public class RecognizerMissingConventionalSyntaxCheckRule {
			"""
When the test runner executes
Then the PDSL framework throws an exception
And the exception communicates that the parser does not have the required syntax check rule

Scenario: RecognizedBy uses custom recognizerRule but does not parse test resource
In the event that the test-level parser uses a custom 'recognizerRule' it will be used to check the syntax of the
test resources. Should the rule fail to recognize the test resource the test suite will fail to compile.

Given a PDSL test
And the @PdslConfiguration specifies the "recognizerRule" parameter
And the @PdslTest test method also has the @RecognizedBy annotation
And a custom recognizer rule is specified in the @RecognizedBy annotation
But the @PdslTest parser does not specify the default rule "polymorphicDslSyntaxCheck" parameter:
"""
			@RunWith(PdslJUnit4ConfigurableRunner.class)
			@PdslConfiguration(
						 specificationFactoryProvider = SomeProvider.class,
						 testCaseFactoryProvider =  SomeOtherProvider.class
			)
			public class RecognizerMissingConventionalSyntaxCheckRule {
				@PdslTest(
                        includesResources = "someResource.txt",
                        parser = SomeParser.class,
                        lexer = SomeLexer.class,
                        listener = FrameworkSpecificationListenerProvider.class
                )

				@RecognizedBy(
						 dslRecognizerParser = SomeRecognizerParser.class, // <-- Assume this parser does not have the rule "myCustomRecognizerRule"
						 dslRecognizerLexer = SomeRecognizerLexer.class,
						 recognizerRule = "myCustomRecognizerRule" // <-- Custom rule specified here

				)
				public void parserMissingSyntaxCheckRule_shouldFailToCompile(){}

			}
			"""
When the test runner executes
Then the PDSL framework throws an exception
And the exception communicates that the parser does not have the required syntax check rule

Scenario: Parser missing default recognizer rule specified in @RecognizedBy
By default the parser uses a parser rule called "polymorphicDslSyntaxCheck" to inspect the syntax of test resources.
If the parser does NOT have this rule then the application will fail to compile.

Given a PDSL test
And the @PdslConfiguration specifies the "recognizerRule" parameter
And the @PdslTest test method also has the @RecognizedBy annotation
But the @PdslTest parser does not specify the default rule "polymorphicDslSyntaxCheck" parameter:
"""
				@RunWith(PdslJUnit4ConfigurableRunner.class)
				@PdslConfiguration(
							 specificationFactoryProvider = SomeProvider.class,
							 testCaseFactoryProvider =  SomeOtherProvider.class
				)
				public class RecognizerMissingConventionalSyntaxCheckRule {
					@PdslTest(
													includesResources = "someResource.txt",
													parser = SomeParser.class, // <-- Assume this parser does not have the rule "polymorphicDslSyntaxCheck"
													lexer = SomeLexer.class,
													listener = FrameworkSpecificationListenerProvider.class
									)

					@RecognizedBy( // <-- Note that "recognizerRule" is not defined by this annotation, so we use the default "polymorphicDslSyntaxCheck"
							 dslRecognizerParser = SomeRecognizerParser.class,
							 dslRecognizerLexer = SomeRecognizerLexer.class,

					)
					public void parserMissingSyntaxCheckRule_shouldFailToCompile(){}

				}
				"""
When the test runner executes
Then the PDSL framework throws an exception
And the exception communicates that the parser does not have the required syntax check rule