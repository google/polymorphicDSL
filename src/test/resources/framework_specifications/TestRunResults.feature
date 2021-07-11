Ability: Test Run Results

	After a Test Executor processes test cases it will produce Test Run Results. 
	At a minimum these results will be able to provide the total number of:
	1) passing tests
	2) failing tests
	3) passing phrases
	4) all phrases
	5) Tests filtered out due to duplication

	A test report is also capable of determining whether or not it contains a test with a specific ID, although this is mostly to assist other framework code.

	A final capability of Test Run Results is they are able to provide a list of reports. These reports are in the form of streams which may be notified with arbitrary information while the test is executing.

	Background:
	
	Rule: Polymorphic DSL Test Run Results
	This is the standard "Test Run Result" available in the framework.
	In addition to the default functionality of a Test Run Result, the PDSL Test Results store a "Test Metadata" for every executed Test Case. 

	This metadata includes information about the test case such as what exception was thrown if it failed, which phrase caused it to fail and other generic information like the number of passing phrases.

	Scenario: All tests pass
		Given the following test resource:
		"""
		1 + 1 = 2
		1 + 2 = 3
		1 + 3 = 4
		"""

		When the test resource is converted to a single test case somehow
		Then a collection of Test Cases is produced
		And the test case collection has 1 test case

		Given the test executor is the "Default PDSL Test Executor"
		Given the Grammar Parse Tree Listener "Arithmetic"
		When the test executor runs the test cases
		Then Test Run Results are produced

		Then the test run results has 1 passing test
		And the test run results has 0 failing tests
		And the test run results has 0 filtered duplicate tests
		And the test run results has 3 passing phrases
		And the test run results has 3 total phrases

		When the Test Metadata is retrieved from the Test Run Results
		Then the Test Metadata has 1 metadata in it

	Scenario: A Test fails
		Given the following test resource:
		"""
		1 + 1 = 2
		1 + 2 = 999999999
		"""

		When the test resource is converted to a single test case somehow
		Then a collection of Test Cases is produced
		And the test case collection has 1 test case

		Given the test executor is the "Default PDSL Test Executor"
		Given the Grammar Parse Tree Listener "Arithmetic"
		When the test executor runs the test cases
		Then Test Run Results are produced

		Then the test run results has 0 passing tests
		And the test run results has 1 failing test
		And the test run results has 0 filtered duplicate tests
		And the test run results has 1 passing phrase
		And the test run results has 2 total phrases

		When the Test Metadata is retrieved from the Test Run Results
		Then the Test Metadata has 1 metadata in it
		When the only Test Metadata item is examined
		Then the Test Metadata has an exception explaining why the test failed
		And the Test Metadata has the phrase that failed
		And the Test Metadata failing phrase is "1 + 2 = 999999999"
"
	Scenario: A Duplicate Test
		Given the following test resource:
		"""
		1 + 1 = 2
		"""
		When the test resource is converted to a single test case somehow
		Then a collection of Test Cases is produced
		Given the following additional test resource:
		"""
		1 + 1 = 2
		"""
		When the test resource is converted to a single test case somehow
		Then a collection of Test Cases is produced
		And the test case collection has 2 test cases

		Given the test executor is the "Default PDSL Test Executor"
		And the Grammar Parse Tree Listener "Arithmetic"
		When the test executor runs the test cases
		Then Test Run Results are produced

		Then the test run results has 1 passing test
		And the test run results has 0 failing tests
		And the test run results has 1 filtered duplicate tests
		And the test run results has 1 passing phrase
		And the test run results has 1 total phrase
