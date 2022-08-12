Feature: Java PDSL Runner
Polymorphic DSL provides a test runner that is built on JUnit 4. Test classes can execute with it by adding the
annotations shown in the example below:

#	@RunWith(PdslJUnit4ConfigurableRunner.class)
#	@PdslConfiguration(
	specificationFactoryProvider = SomeSpecificationFactoryProvider.class,
	testCaseFactoryProvider = SomeTestCaseFactoryProvider.class
	)
	public class YourTestClass {
#	@PdslTest(
	includedResources = {"resource1", "resource2" },
	parser = SomeParser.class,
	lexer = SomeLexer.class,
	listener = FrameworkSpecificationListenerProvider.class
	)
	public void someTest(){}
	}
	=====
	Note there are many other optional fields you can pass to the @PdslConfiguration annotation.
	However most of the important information is in the @PdslConfiguration and @PdslTest annotations.
	These annotations are framework agnostic and can be used to implement other frameworks.
	As such the scenarios described here can be a source of truth for anyone extending the PDSL framework.

Rule: Positive Scenarios
These are ways in which the framework is intended to be used and work propery

	Scenario: Default use of Pdsl runner

		At a minimum, the runner requires the @PdslConfiguration annotaiton on the class.
		The runner will recognize methods annoted with @PdslTest as tests.

		A @PdslTest specifies the test resources to parse, the grammar and the listener that will fire off methods after
		the resources have been parsed and turned into test cases.

		Given a PDSL test
		And all @PdslTests are valid:
		"""
		@RunWith(PdslJUnit4ConfigurableRunner.class)
		@PdslConfiguration(
			specificationFactoryProvider = SomeSpecificationFactoryProvider.class,
			testCaseFactoryProvider = SomeTestCaseFactoryProvider.class
	    )
		public class YourTestClass {

			@PdslTest(
				includedResources = {"*.txt", "*.md" },
				excludesResources = {"ignore.txt", "ignore*.md"},
				parser = SomeParser.class,
				lexer = SomeLexer.class,
				listener = FrameworkSpecificationListenerProvider.class
			)
			public void someTest(){}

			@PdslTest(
				includedResources = "resource3", // <-- you can also specify a single resource instead of an array
				parser = SomeOtherParser.class,
				lexer = SomeOtherLexer.class,
				listener = FrameworkSpecificationListenerProvider.class
			)
			public void someTest(){}

		}
		"""
		When the test runner executes
		Then all tests pass

	Scenario: Resource Root
		To prevent the need to provide long URIs for every path it is possible to specify a "resourceRoot"
		parameter in the @PdslConfiguration

		All resources used by every @PdslTest will then append its own resources to this value.

		Given a PDSL test
		And the @PdslConfiguration specifies the "resourceRoot" parameter:
		"""
		@RunWith(PdslJUnit4ConfigurableRunner.class)
		@PdslConfiguration(
		resourceRoot = "path/to/root/",
			specificationFactoryProvider = SomeSpecificationFactoryProvider.class,
			testCaseFactoryProvider = SomeTestCaseFactoryProvider.class
	    )
		public class YourTestClass {

			@PdslTest(
				includedResources = "resource3", // <-- expands to path/to/root/resource3
				parser = SomeOtherParser.class,
				lexer = SomeOtherLexer.class,
				listener = FrameworkSpecificationListenerProvider.class
			)
			public void someTest(){}
		}
		"""
		And all @PdslTests are valid
		When the test runner executes
		Then all tests pass

	Scenario: Test Run Executor
		The Default executor used by PDSL should be sufficient for most users. However if you need to
		perform specific actions between phrases or other special actions you can provide your own
		executor. You will need to implement Provider<TraceableTestRunExecutor> and make sure it
		has a default, public constructor.

		Given a PDSL test
		And the @PdslConfiguration specifies the "testRunExecutor" parameter:
			"""
			@RunWith(PdslJUnit4ConfigurableRunner.class)
			@PdslConfiguration(
				testRunExecutor  = MyTestRunExecutorProvider.class,
				specificationFactoryProvider = SomeSpecificationFactoryProvider.class,
				testCaseFactoryProvider = SomeTestCaseFactoryProvider.class
			)
			public class YourTestClass {

				public static class MyTestRunExecutorProvider implements Provider<TraceableTestRunExecutor> {
					public testRunExecutor() {
						// Implementation
					}

					// Override methods
				}
		"""
		And all @PdslTests are valid
		When the test runner executes
		Then all tests pass
		And the specified Test Executor was used

	Scenario: Resource Provider
		By default PDSL assumes that your resources are files located on the local file system. However
		it is possible to take resources of all kinds, including web URLs, streams and other sources
		of input. You may specify your own provider if needed.

		Given a PDSL test
		And the @PdslConfiguration specifies the "resourceFinder" parameter:
			"""
			@RunWith(PdslJUnit4ConfigurableRunner.class)
			@PdslConfiguration(
				resourceFinder = MyResourceProvider.class,
				specificationFactoryProvider = SomeSpecificationFactoryProvider.class,
				testCaseFactoryProvider = SomeTestCaseFactoryProvider.class
			)
			public class YourTestClass {

				public static class MyResourceProvider implements Provider<TestResourceFinderGenerator> {
					public MyResourceProvider() {
						// Implementation
					}

					// Override methods
				}
			"""
		And all @PdslTests are valid
		When the test runner executes
		Then all tests pass
		And the specified resource provider was used

	@TaggedScenario
	Scenario: Tags
		It is possible only run specific tests that match certain tags. The syntax of the tags
		and what they do is left to the underlying framework.

		For example, the PdslGherkinJUnit4Runner uses this with its own
		TestSpecificationFactory to ignore feature files that do not have these tags.

		Given a PDSL test
		And the PDSL test uses factories that filter based on tags
		And the @PdslTest has a tag specified
		And a test resource is marked with that tag
		When the test runner executes
		Then the test is skipped

	Scenario: No Recognizers
		The recognizer provides a great deal of safety when sharing grammars because it ensures that there are no invalid
		phrases in any tests (even if they are going to be ignored).

		Not having a recognizer runs the risk of putting a typo or invalid phrase in a test and silently ignoring it. What
		you probably want to happen instead is for the typo to be noticed and an error to be thrown so you can fix it.
		Essentially, not having a recognizer allows *ALL POSSIBLE PHRASES* to exist in your test, even if they don't
		make sense.

		Furthermore, it is possible that you might change your grammar at some point and forget to update some test
		resource. If this happens your test will simply ignore the non-updated resources and might provide false
		positives because they are not running all the steps they used to.

		For this reason it is recommended that you only do this if you had some way of already proving the
		test resource is valid earlier. The only advantage to not having a recognizer is that the @PdslTest will ignore
		everything it doesn't understand. This _might_ provide a maintenance advantage so legacy tests don't need to be
		updated with new changes, but realistically it is probably easier just to have a shared recognizer across your tests.

		If you follow the Interface Segregation Principle and only provide interface implementations to the tests that
		need them then a recognizer should be a feasible maintenance cost. Provided you can afford to split grammars
		into subgrammars this is likely the better approach.

		Regardless, it is possible to run PDSL tests without the safety of a recognizer. It will simply process anything
		it does understand and ignore everything that isn't specified in the @PdslTest parser. Running without a recognizer
		merely means there is no recognizer specified in the @PdslConfiguration and no @RecognizedBy is attached to
		the @PdslTest

		Given a PDSL test
		But the @PdslTest DOES NOT have a @RecognizedBy
		And the @PdslConfiguration DOES NOT specify the "dslRecognizerParser" parameter
		And the @PdslConfiguration DOES NOT specify the "dslRecognizerLexer" parameter
		
		And all @PdslTests are valid:
			"""
			@RunWith(PdslJUnit4ConfigurableRunner.class)
			@PdslConfiguration(
				specificationFactoryProvider = SomeSpecificationFactoryProvider.class,
				testCaseFactoryProvider = SomeTestCaseFactoryProvider.class
			)
			public class YourTestClass {

				@PdslTest(
					includedResources = "resource1", // <-- All resources will be checked by the dslRecognizerParser specified in the @PdslConfiguration
					parser = SomeParser.class,
					lexer = SomeLexer.class,
					listener = FrameworkSpecificationListenerProvider.class
				)
				public void someTest(){}
			}
			"""
		When the test runner executes
		Then all tests pass

	Scenario: Class Wide Recognizer with @PdslConfiguration

	If you specify a recognizer in the @PdslConfiguration then all @PdslTest methods will use it to recognize their resources (unless overridden by a @RecognizeBy on the test method).

	If no "recognizerRule" is specified then the framework will assume a rule named "polymorphicDslSyntaxCheck" is present.
	If no such rule exists and one isn't specified a runtime exception will be thrown.
	Note that if you specify the 'dslRecognizerLexer' or 'dslRecognizerParser' you must specify both otherwise there will be a runtime exception.

		# Valid resource
		Given a PDSL test
		And the @PdslConfiguration specifies the "dslRecognizerLexer" parameter
		And the @PdslConfiguration specifies the "dslRecognizerParser" parameter:
			"""
			@RunWith(PdslJUnit4ConfigurableRunner.class)
			@PdslConfiguration(
				dslRecognizerLexer = SomeRecognizerLexer.class,
				dslRecognizerParser = SomeRecognizerParser.class,
				specificationFactoryProvider = SomeSpecificationFactoryProvider.class,
				testCaseFactoryProvider = SomeTestCaseFactoryProvider.class
			)
			public class YourTestClass {

				@PdslTest(
					includedResources = "resource1", // <-- All resources will be checked by the dslRecognizerParser specified in the @PdslConfiguration
					parser = SomeParser.class,
					lexer = SomeLexer.class,
					listener = FrameworkSpecificationListenerProvider.class
				)
				public void someTest(){}
			}
			"""
		And all @PdslTests are valid
		When the test runner executes
		Then all tests pass

		# Invalid resource
		Given a PDSL test
		And the @PdslConfiguration specifies the "dslRecognizerLexer" parameter
		And the @PdslConfiguration specifies the "dslRecognizerParser" parameter
		But the @PdslTest has phrases that are NOT recognized by the @PdslConfiguration recognizer
		When the test runner executes
		Then the PDSL framework throws an exception
		And the exception communicates that the resource could not be interpreted by the recognizer

	Scenario: Class Wide Recognizer with custom Parser Rule

		@PdslConfiguration can specify which recognizer rule to use for the test resources it uses.
		This is done by specifing the "recognizerRule" field in the @PdslConfiguration.

		The default recognizer parser rule "polymorphicDslSyntaxCheck" can be replaced by the rule specified as "recognizerRule".
		If the specified rule does not exist in the @PdslTest parser a runtime exception will be thrown.

		NOTE: If a @PdslTest specified a recognizer via @RecognizedBy it will always be used, even if there is a different one specified in the @PdslConfiguration.

		# Valid resource
		Given a PDSL test
		And the @PdslConfiguration specifies the "dslRecognizerLexer" parameter
		And the @PdslConfiguration specifies the "dslRecognizerParser" parameter
		And the @PdslConfiguration specifies the "recognizerRule" parameter:
			"""
			@RunWith(PdslJUnit4ConfigurableRunner.class)
			@PdslConfiguration(
				recognizerRule = "myCustomRule", // <-- By default this rule will check all test resources
				dslRecognizerLexer = SomeRecognizerLexer.class,
				dslRecognizerParser = SomeRecognizerParser.class,
				specificationFactoryProvider = SomeSpecificationFactoryProvider.class,
				testCaseFactoryProvider = SomeTestCaseFactoryProvider.class
			)
			public class YourTestClass {


				@PdslTest(
					includedResources = "resource1", // <-- All resources will be checked by "myCustomRule" specified in the @PdslConfiguration
					parser = SomeParser.class,
					lexer = SomeLexer.class,
					listener = FrameworkSpecificationListenerProvider.class
				)
				public void someTest(){}
			}
			"""
		Given the @PdslTest DOES NOT have a @RecognizedBy
		And all @PdslTests are valid
		When the test runner executes
		Then all tests pass

		# Invalid resource
		Given a PDSL test
		And the @PdslConfiguration specifies the "dslRecognizerLexer" parameter
		And the @PdslConfiguration specifies the "dslRecognizerParser" parameter
		And the @PdslConfiguration specifies the "recognizerRule" parameter

		Given the @PdslTest DOES NOT have a @RecognizedBy
		But the @PdslTest has phrases that are NOT recognized by the @PdslConfiguration recognizer
		When the test runner executes
		Then the PDSL framework throws an exception
		And the exception communicates that the resource could not be interpreted by the recognizer




	Scenario: Test Method Recognizer with @RecognizedBy and custom rule
		@PdslTest can have a @RecognizedBy annotation that specifies which recognizer to use for the test resources it uses.
		The specified recognizer in @RecognizedBy will always be used, even if there is a different one specified in the @PdslConfiguration.
		The default recognizer parser rule "polymorphicDslSyntaxCheck" can be replaced by the rule specified as "recognizerRule".
		If the specified rule does not exist in the @PdslTest parser a runtime exception will be thrown.

		A recognizer can be specifeid on a specific method annotated with @PdslTest.

		# Valid resource
		Given a PDSL test
		And the @PdslConfiguration specifies the "dslRecognizerLexer" parameter
		And the @PdslConfiguration specifies the "dslRecognizerParser" parameter
		And the @PdslConfiguration specifies the "recognizerRule" parameter

		Given the @PdslTest does have a @RecognizedBy
		And the @RecognizedBy specifies the "dslRecognizerLexer" parameter
		And the @RecognizedBy specifies the "dslRecognizerParser" parameter
		And the @RecognizedBy specifies the "recognizerRule" parameter

		Given the @PdslTest has phrases that are recognized by the @PdslConfiguration recognizer
		But the test resource has phrases that ARE recognized by the @PdslTest recognizer:
			"""
			@RunWith(PdslJUnit4ConfigurableRunner.class)
			@PdslConfiguration(
				recognizerRule = "myCustomRule", // <-- Overridden by the default in @RecognizedBy
				dslRecognizerLexer = SomeRecognizerLexer.class,
				dslRecognizerParser = SomeRecognizerParser.class,
				specificationFactoryProvider = SomeSpecificationFactoryProvider.class,
				testCaseFactoryProvider = SomeTestCaseFactoryProvider.class
			)
			public class YourTestClass {


				@RecognizedBy(
					recognizerRule = "myOtherCustomRule", // <-- The resources my match "myOtherCustomRule". "myCustomRule" in @PdslConfiguration ignored.
					dlsRecognizerLexer = "DifferentRecognizerLexer.class",
					dlsRecognizerParser = "DifferentRecognizerParser.class"
				)
				@PdslTest(
					includedResources = "resource1", // <-- All resources will be checked by the dslRecognizerParser specified in the @PdslTest
					parser = SomeParser.class,
					lexer = SomeLexer.class,
					listener = FrameworkSpecificationListenerProvider.class
				)
				public void someTest(){}
			}
			"""
		When the test runner executes
		Then all tests pass

		# Invalid resource
		Given a PDSL test
		And the @PdslConfiguration specifies the "dslRecognizerLexer" parameter
		And the @PdslConfiguration specifies the "dslRecognizerParser" parameter
		And the @PdslConfiguration specifies the "recognizerRule" parameter

		Given the @PdslTest does have a @RecognizedBy
		And the @RecognizedBy specifies the "dslRecognizerLexer" parameter
		And the @RecognizedBy specifies the "dslRecognizerParser" parameter
		And the @RecognizedBy specifies the "recognizerRule" parameter

		Given the @PdslTest has phrases that ARE recognized by the @PdslConfiguration recognizer
		But the test resource has phrases that ARE NOT recognized by the @PdslTest recognizer
		When the test runner executes
		Then the PDSL framework throws an exception
		And the exception communicates that the resource could not be interpreted by the recognizer


