Ability: Test Executor

	A Test Executor is what actually runs the test cases. As input the Test Executor will take a grammar and subgrammar Parse Tree Listener, which is code that will execute and do particular things when it encounters a specific phrase. This is analogous to "Glue" code in Cucumber.

	All listeners can execute the tests with two functions:
		1) Checking all phrases with a grammar listener which is followed by a run using a subgrammar listener
		2) Simply executing all phrases with a single Parse Tree Listener

	It also controls logging as the tests run.
	In all cases, the executor will produce Polymorphic DSL Test Run Results.
Background:
	Given the following test resource:
	"""
	Feature: Some Feature
	
	@SomeTag
	Scenario: Some Scenario
	Given some step

	@AnotherTag
	Scenario: Some tagged scenario
	Given some other step
	"""
	Given the Grammar Phrase Filter is "All Grammars"
	And the Subgrammar Phrase Filter is "All Grammars"
	And the Test Specification Factory being used is the "Gherkin Test Specification Factory"
	When the test resource is processed by a "Test Specification Factory"
	Then a "Test Specification" is produced

	Given the Grammar Parse Tree Listener "All Grammars"
	And the Test Specification is processed by a "Test Case Factory"


	Rule: Default Polymorphic DSL Test Executor
	This is the most basic executor. It does minimal logging, only stating when it has finished verifying the grammar and when it starts the subgrammar run.

	Scenario: Simple default PDSL executor test run succeeds
		Given the test executor is the "Default PDSL Test Executor"
		When the Test Case is processed by a "Polymorphic DSL Test Executor"
		Then a "Test Run Result" is produced
		And the test run results have 2 passing tests
		

	Rule: Gherkin Test Executor

	The Gherkin Test Executor differs from the standard Test Executor in that it can filter tests by gherkin tags. It also will log the descriptions of the feature, scenario, etc as it executes.

	Scenario: Simple gherkin executor test run succeeds
	
		Given the test executor is the "Default Gherkin Test Executor"
		When the Test Case is processed by a "Polymorphic DSL Test Executor"
		Then a "Test Run Result" is produced
		And the test run results has 2 passing tests

	Scenario: Tag Expressions
		When the Gherkin Test Executor runs the tests with the tag expression "@SomeTag or @AnotherTag"
		Then a "Test Run Result" is produced
		And the test run results have 2 passing tests

		When the Gherkin Test Executor runs the tests with the tag expression "@SomeTag"
		Then a "Test Run Result" is produced
		And the test run results has 1 passing test

		When the Gherkin Test Executor runs the tests with the tag expression "@SomeTag and @MissingTag"
		Then a "Test Run Result" is produced
		And the test run results have 0 passing tests
		And the test run results have 0 failing tests