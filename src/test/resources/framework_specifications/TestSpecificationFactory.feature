Ability: Test Specification Factory
Transform & filter Test Resources

Any Test Specification Factory will take a Test Resource and convert it into a Test Specification that has been filtered to only contain phrases understandable by a subgrammar. If it sees a phrase that is not in the overall grammar there will be an error.

Consider an e-commerce website. It supports multiple clients (mobile, web, etc) and is composed of multiple parts (an account service, shopping cart, billing, etc). All of these parts ideally should be tested. Conventional approaches write tests for each part separately from the others. However if there is a new requirement or a requirement changes the new changes may not be tested everywhere because the system under test is so spread out.

However if you think about it you can make a declarative test that applies to all the parts, which might look like this:


Suppose there is a pre-existing customer
That customer adds a product to their shopping cart
Later the cart total is updated to reflect the price
After the customer goes to checkout 
Later the customer provides shipping information
Later the address is verified
Later the customer provides payment information
Later the customer submits their order
Later the customer receives an order confirmation


This scenario describes a situation that might affect all of the different microservices. This is a "Test Specification". It allows us to easily scale if there is a change in requirements and make sure all other clients and services are tested as well:



After the customer goes to checkout 
Later the customer provides shipping information
Later the address is verified
Later the address is checked to make sure it is NOT an international order # <-- New requirement!
...
<etc>


A "Test Specification" is produced from "Test Resources", such as a feature file or some other protocol you might care about.  A Test Specification is a blueprint from which test cases can be produced. It separates the executable phrases from other "meta data" in the file if it exists. Test Specifications are typically produced by Test Specification Factories. 

The primary purpose of a test specification factory is to take some raw input (such as a text file, gherkin, or some other protocol) and convert it into a Test Specification.

The need for a Test Specification is made evident by this document itself. This feature file provides a description in the "Ability" section here that is essentially helpful metadata about the tests written in the scenario step bodies below. The Gherkin Test Specification Factory can process this file, separate the documentation in the Feature and Scenario sections from the executable "Given, When, Then" phrases and perform any necessary substitutions needed. It also performs all of the tag associations and makes the other abilities of the feature file available to the test framework.

Apart from this transformation it also performs a critical part for the overall framework, namely making sure all phrases are known to the overall grammar and filtering out phrases that are not relevant to the subgrammar. 

To better understand this, imagine someones motorcycle breaks down and they take it to a knowledgable mechanic. The mechanic notices that a shoelace somehow got pulled into the engine intake and destroyed it. The mechanic tells the motorcyclist this and goes into detail about how this caused cascading damage to the motorcycle. The owners eyes glaze over as they struggle to comprehend the stream of information coming to them, when the mechanic says some things that they do understand: "It must be replaced. It will cost about $600 to repair."

In the above example the mechanic recognizes all the parts that belong in a motorcycle and the parts that don't (namely the shoelace). The mechanic is like the grammar which can recognize which phrases belong in a grammar and which don't. If it sees a phrase it doesn't recognize it doesn't proceed until that phrase is fixed or removed.

The customer does not understand most of the technical aspects, but does understand their part in the transaction. Ultimately it is the customer that will make important decisions on how to proceed after everything has been checked by the mechanic. The customer is like  subgrammar, which is a subset of the grammar which actually executes on the phrases that it DOES understand.

A Test Specification Factories use of a grammar and subgrammar for "context filtering" plays what may be the most important part of the PDSL framework, namely allowing Test Architects to follow the "Interface Segregation Principle" (ISP). The basic idea behind the ISP is that you shouldn't be forced to write or depend on methods that it never uses. 

This is critical to the purpose of the PDSL framework which allows a single test specification to be used for multiple applications, platforms and throughout the test pyramid. If all tests were forced to support phrases that didn't matter to them it would become infeasible and nightmarish to maintain.

The beginning of this document talks about an e-commerce example: 


	Suppose there is a pre-existing customer
	That customer adds a product to their shopping cart
	Later the cart total is updated to reflect the price
	After the customer goes to checkout 
	Later the customer provides shipping information
	Later the customer provides payment information
	Later the customer submits their order
	Later the customer receives an order confirmation


This is great for an end-to-end test. But to scale to other microservices (such as the shipping service) we only should give it the phrases that it needs:

	Suppose there is a pre-existing customer
	Suppose the customer provides shipping information
	Suppose the address is verified
	Later the address is checked to make sure it is NOT an international order

The beauty of this approach is that only affected systems will need to do anything new at this point. Any service that has these phrases in it's subgrammar will know to run a new test anytime the phrases are added to new test specifications.


Rule: Gherkin Test Specification Factory
The Gherkin Test Specification Factory by default can recognize any valid gherkin document, but it imposes additional constraints:
		1) Every gherkin document must have at least one scenario
		2) Every rule must have at least one scenario
		3) All scenarios must have a step body
		4) Every Background must have a step body

	Background:
		Given the Test Specification Factory being used is a "Gherkin Test Specification Factory"
		And the Grammar being used is "All Grammars"
		And the Subgrammar being used is "All Grammars"
	
		Scenario: Gherkin Test Specification Factory - Valid input
			
			Given the following test resource:
			"""
			Feature: Some feature
				Background: 
					Given a background step
					
				Scenario: Basic Scenario
					Given a step

				Rule: Some rule
					Scenario: Rule scenario
						Given a rule scenario step
			"""
			When the test resource is processed by the Test Specification Factory
			Then a Test Specification is produced

		Scenario: Missing Scenario in document - Invalid Input- Gherkin Test Specification Factory
			Given the following test resource:
			"""
			Feature: Some feature
			"""
			When the test resource is processed by the Test Specification Factory
			Then an error occurs because there is no scenario

		Scenario: Missing Scenario in rule - Invalid Input- Gherkin Test Specification Factory
			Given the following test resource:
			"""
			Feature: Some feature

			Scenario: Top Level
			Given a step

			Rule: Some rule
			# No scenario
			"""
			When the test resource is processed by the Test Specification Factory
			Then an error occurs because there is no scenario

		Scenario: Missing step body in scenario - Invalid Input- Gherkin Test Specification Factory

			Given the following test resource:
			"""
			Feature: Some feature

			Scenario: Top Level
			"""
			When the test resource is processed by the Test Specification Factory
			Then an error occurs because there is no step body

		Scenario: Missing step body in background - Invalid Input- Gherkin Test Specification Factory

			Given the following test resource:
			"""
			Feature: Some feature
			
			Background:
			# No steps!

			Scenario: Top Level
				Given a step
			"""
			When the test resource is processed by the Test Specification Factory
			Then an error occurs because there is no step body

		Scenario: A non-existent test resource
			Given a URL to a resource that does not exist
			When the test resource is processed by the Test Specification Factory
			Then an error occurs because there is no such resource

Rule: Line Delimited Test Specification Factory
	This Test Specification Factory will take any text file as input and break each line into its own phrase.	

	Background:
        Given the Test Specification Factory being used is a "Line Delimited Test Specification Factory"
        And the Grammar being used is "All Grammars"
        And the Subgrammar being used is "All Grammars"

		Scenario: Valid input
			Given the following test resource:
			"""Phrase 1
			Phrase 2
			Phrase 3"""
			When the test resource is processed by the Test Specification Factory
			Then a Test Specification is produced 
			And the test specification has 3 total phrases
Scenario: Single line is valid
			Given the following test resource:
			"""Phrase 1"""
			When the test resource is processed by the Test Specification Factory
			Then a Test Specification is produced
			And the test specification has 1 total phrase

		Scenario: An empty file produces nothing
			Given the following test resource:
			""" """
			When the test resource is processed by the Test Specification Factory
			Then a Test Specification is NOT produced

		Scenario: A non-existent test resource
			Given a URL to a resource that does not exist
			When the test resource is processed by the Test Specification Factory
			Then an error occurs because there is no such resource
