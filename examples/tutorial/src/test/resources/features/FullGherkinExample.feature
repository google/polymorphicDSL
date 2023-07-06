Feature: Example Feature File
	Gherkin has many capabilities. Many of them are unknown or surprising to	
	people though. This feature file demos many of the Gherkin capabilities.

	Background: These steps will be run before ALL of the scenarios in
		this file
		Given a background step

	# Comment: Many people are unaware of the "But" and "*" keywords
	Scenario: A regular scenario with all standard english keywords
		Note that you can have multiple lines in a scenario
		description, allowing you to document or explain a
		particular capability thoroughly.
		Given a step
		When a step
		Then a step
		But a step
		And a step
		* a step

	@Tag#1 @Tag#2 @Tag3@SeparateTag
	Scenario: Tagged scenarios
		Scenarios can be filtered with "Tag Expressions", such as 
		"@Tag#1 and not (@Tag4 or @Tag5)", which would run this 
		particular scenario and no others. Note you are allowed to
		use the '#' sign in the tag name without creating a comment
		and you can also join tags, meaning the "@Tag3@SeparateTag"
		count as 2 different tags.

		Given a step

	Rule: Rules are useful for making backgrounds that only apply to 
		scenarios nested under the rule. They are also useful
		for grouping test logic in general.

		Background: This background step will only run before
			each of the scenarios nested under this rule.
			The background at the top of the file (which
			is not nested in a rule) will also execute.

			Given another background step
		
		Scenario: A scenario outline
			You can use a scenario as a template, using a table to
			fill in specific parameters in the scenario.

			Given a <NOUN>
			Then it is <ADJECTIVE>

			@Animals
			Examples:
				| NOUN  | ADJECTIVE   |
				| Mouse | mammalian   |
				| snake | cold-blooded|

			@Comestables
			Examples: You are allowed to use multiple example tables for the
				same scenario. You can combine this with tags to tightly
				control which rows will execute
			
				|NOUN | ADJECTIVE |
				|cake | sweet     |
				|lemon | sour |

	Rule: Data Tables and Doc Strings

		Scenario: Doc Strings
			
			Large chunks of data can be provided by placing a doc string under a step.
			They are delimited by either """ or ``` and can optionally have a language
			specified after them.

			Note that you can use examples tables and substitute values inside a doc string
			just like you can anywhere else.

			Given a doc string
			"""
			Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
			incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis 
			nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
			Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu 
			fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, 
			sunt in culpa qui officia deserunt mollit anim id est laborum.

			Note you can escape triple quotes inside the doc string using \"\"\"
			"""
			And a doc string
			"""java
			System.out.println("<phrase>");
			"""
			And a doc string
			```
			By changing the type of delimiter you use you can nest doc strings:
			"""
			This is a nested doc string. It will be considered regular text by
			the parser.
			"""
			```

			Examples:
				|  phrase       |
				| Hello, world! |


		Scenario: Data tables
			Tables can be placed under steps as well

			Given a table
				| row 1 cell 1 | row 1 cell 2 |
				| row 2 cell 1 | row 2 cell 2 |
			Given a table
				| In any table you can escape the pipe character \| like that |
		
