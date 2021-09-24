Feature: Polymorphic DSL Test Framework

The Polymorphic DSL (PDSL) pattern may or may not be used for Business Driven Development.
This PDSL framework itself voluntarily chooses to write the specifications in Gherkin to produce
living documentation.

The ultimate idea behind the pdsl framework is that modern applications tend to either have many clients
or follow distributed system architecture and the tests should be written to scale through the test pyramid and across
the application boundary. This is because there are many (often massive) areas of shared concern.

Problems with conventional development involve modifying a microservice or some other module and discovering after
releasing that it completely breaks a foreign service (which the team may not have even known about due to the scale
of modern application infrastructure)

The PDSL framework allows requirements to be tested in many different contexts.

The general flow of the framework is to create a grammar or use some other supported protocol (such as gherkin, which
this test specification is written in) to describe the system under test in a formal way. In other words, the way you
describe how to do something in one part of your application is the same way you describe it in another.

Scenario Outline: Test Lifecycle

Given the test resource "<resource>"
When the test resource is processed by a "Test Specification Factory"
Then a "Test Specification" is produced
And the Test Specification has an ID
And the Test Specification is in the expected format

When the Test Specification is processed by a "Test Case Factory"
Then a "Test Case" is produced
And the Test Case has a unique ID
And the Test Case has a title
And the Test Case has a test body constructed properly

Given a "Polymorphic DSL Test Executor"
And the Polymorphic DSL Test Executor can process all phrases in the test case
When the Test Case is processed by a "Polymorphic DSL Test Executor"
Then a "Test Run Result" is produced

Then the Test Run Result has a passing test total of <passing test total>
And the Test Run Result has 0 failing tests
And the Test Run Result passing phrase total is <total phrases>
And the Test Run Result total phrases is <total phrases>
And the Test Run Result has <duplicates> "Total Filtered Duplicate Tests"

Examples:
| resource                                             | passing test total   | total phrases  | duplicates |
| background.feature                                   | 1                    | 2              | 1          |
| complex_background.feature                           | 3                    | 8              | 1          |
| datatables.feature                                   | 1                    | 5              | 0          |
| datatables_with_new_lines.feature                    | 1                    | 2              | 0          |
| descriptions.feature                                 | 7                    | 8              | 6          |
| docstrings.feature                                   | 1                    | 8              | 0          |
| escaped_pipes.feature                                | 1                    | 2              | 0          |
| example_token_multiple.feature                       | 1                    | 1              | 0          |
| example_tokens_everywhere.feature                    | 2                    | 4              | 0          |
| i18n_emoji.feature                                   | 1                    | 1              | 0          |
| i18n_fr.feature                                      | 1                    | 3              | 10         |
| i18n_no.feature                                      | 1                    | 3              | 0          |
| language.feature                                     | 1                    | 1              | 0          |
| minimal-example.feature                              | 1                    | 1              | 0          |
| minimal.feature                                      | 1                    | 1              | 0          |
| padded_example.feature                               | 1                    | 1              | 0          |
| readme_example.feature                               | 2                    | 2              | 0          |
| rule.feature                                         | 2                    | 5              | 0          |
| rule_without_name_and_description.feature            | 1                    | 1              | 0          |
| scenario_outline.feature                             | 1                    | 1              | 0          |
| scenario_outline_no_newline.feature                  | 1                    | 1              | 0          |
| scenario_outline_with_docstring.feature              | 2                    | 2              | 0          |
| scenario_outline_with_value_with_dollar_sign.feature | 1                    | 1              | 0          |
| scenario_outlines_with_tags.feature                  | 2                    | 2              | 0          |
| tagged_feature_with_scenario_outline.feature         | 2                    | 2              | 0          |
| tags.feature                                         | 5                    | 5              | 1          |
| very_long.feature                                    | 1                    | 5              | 99         |
