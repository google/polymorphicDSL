Ability: Test Case Factory

	A Test Case represents an executable, ordered sequence of phrases. They are provided as input to Test Exectutors which actually run the tests. They are analogous to "Pickles" in Cucumber.

	Test Cases are create from Test Specifications that have been processed by a Test Case Factory. This processing is necessary because Some test specifications may be relatively elaborate (such as those that represent Gherkin, which may have background steps run for each child scenario). 

	A Test Specification is essentially a tree in which each node is a collection of phrases. The Test Case Factory essentially is a way of traversing the tree and chaining all the phrases together.

Background:
	Given a "Test Specification"
	And the Test Specification has 3 phrases
	And the Test Specification has a child Test Specification with 2 phrases

	Rule: "Preorder" Test Case Factory

	This factory will recursively stack its phrases on top of each child phrase.
	NOTE: Meta data is duplicated for each test case!
	It is essentially a Preorder traversal of the Test Specification.

	This factory is what is used to process Gherkin Test Specifications.

	This factory may produce multiple test cases from a single test specification



	Scenario: Preorder: Converting the Test Specification
			Given the "Preorder" test case factory is used
			When the Test Specification is processed by the Test Case Factory
			Then 2 test cases are produced
			And each test case has 5 phrases


	Rule: "Single Test Output, Preorder" Test Case Factory

	This factory also does a preorder traversal, but it will only produce 1 test case as output

	Scenario: Creating a single test from a specification using preorder
			Given the "Single Test Output Preorder" test case factory is used
			When the Test Specification is processed by the Test Case Factory
			Then 1 test case is produced
			And each test case has 5 phrases
