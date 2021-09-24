Feature: PDSL JUnit Runner


Rule: Negative Scenarios
These are ways you should NOT use the framework

    Scenario: Lexer recognizer is defined but the parser rule is not
        Both the dslRecognizerParser and dslRecognizerLexer need to be defined. If they are not then the test suite
        will not compile.

        Given the following test runner:
        """
        package com.pdsl.uat;

        import com.pdsl.grammars.AllGrammarsLexer;
        import com.pdsl.junit.PdslGherkinApplication;
        import com.pdsl.junit.PdslGherkinJUnit4Runner;
        import org.junit.Test;
        import org.junit.runner.RunWith;

        @RunWith(PdslGherkinJUnit4Runner.class)
        @PdslGherkinApplication(
                dslRecognizerLexer = AllGrammarsLexer.class,
                resourceRoot = "src/test/resources/framework_specifications"
        )
        public class MalformedPdslJUnitMissingLexerTest {

            @Test
            public void malformedTestMissingLexer_shouldFailToCompile() {}
        }
        """
        When the test runner is compiled
        Then it does NOT compile successfully
        And the runner failed to compile because the dslRecognizerParser is missing when the dslRecognizerLexer was provided

    Scenario: Parser recognizer defined but lexer is not
    Both the dslRecognizerParser and dslRecognizerLexer need to be defined. If they are not then the test suite
    will not compile.

        Given the following test runner:
        """
        package com.pdsl.uat;

        import com.pdsl.grammars.AllGrammarsParser;
        import com.pdsl.junit.PdslGherkinApplication;
        import com.pdsl.junit.PdslGherkinJUnit4Runner;
        import org.junit.Test;
        import org.junit.runner.RunWith;

        @RunWith(PdslGherkinJUnit4Runner.class)
        @PdslGherkinApplication(
                dslRecognizerParser = AllGrammarsParser.class,
                resourceRoot = "src/test/resources/framework_specifications"
        )
        public class MalformedPdslJUnitMissingLexerTest {

            @Test
            public void malformedTestMissingParser_shouldFailToCompile() {}
        }
        """
        When the test runner is compiled
        Then it does NOT compile successfully
        And the runner failed to compile because the dslRecognizerLexer is missing when the dslRecognizerParser was provided

    Scenario: Class level, custom recognizer rule fails to recognize resource
        In the event that the class-level parser uses a custom 'recognizerRule' it will be used to check the syntax of the
        test resources. Should the rule fail to recognize the test resource the test suite will fail to compile.

     Given the following test runner:
            """
             package com.pdsl.uat;

            import com.pdsl.grammars.AllGrammarsParser;
            import com.pdsl.junit.PdslGherkinApplication;
            import com.pdsl.junit.PdslGherkinJUnit4Runner;
            import org.junit.Test;
            import org.junit.runner.RunWith;

            @RunWith(PdslGherkinJUnit4Runner.class)
            @PdslGherkinApplication(
                    resourceRoot = "src/test/resources/framework_specifications",
                    dslRecognizerParser = MinimalParser.class,
                    dslRecognizerLexer = MinimalLexer.class,
                    recognizerRule = "minimal"
            )
            public class ClassLevelRecognizerTest {

                @PdslTest(
                        includesResources = "PdslTestFramework.feature",
                        parser = PdslTestResourceParser.class,
                        lexer = PdslTestResourceLexer.class,
                        listener = FrameworkSpecificationListenerProvider.class
                )
                public void testThatIsNotRecognizedByParentRecognizer_shouldFailToCompile(){}
            }
            """
        When the test runner is compiled
        Then it does NOT compile successfully
        And the runner failed to compile because the recognizerRule could not recognizer the test resource

    Scenario: Class wide parser missing default recognizer rule
      By default the parser uses a parser rule called "polymorphicDslSyntaxCheck" to inspect the syntax of test resources.
        If the parser does NOT have this rule then the application will fail to compile.

        Given the following test runner:
            """
             package com.pdsl.uat;

            import com.pdsl.grammars.AllGrammarsParser;
            import com.pdsl.junit.PdslGherkinApplication;
            import com.pdsl.junit.PdslGherkinJUnit4Runner;
            import org.junit.Test;
            import org.junit.runner.RunWith;

            @RunWith(PdslGherkinJUnit4Runner.class)
            @PdslGherkinApplication(
                    resourceRoot = "src/test/resources/framework_specifications",
                    dslRecognizerParser = MinimalParser.class,
                    dslRecognizerLexer = MinimalLexer.class,
                    // This parser does not have the rule 'polymorphicDslSyntaxCheck', so it will not compile
            )
            public class ClassLevelRecognizerTest {

                @PdslTest(
                        includesResources = "PdslTestFramework.feature",
                        parser = PdslTestResourceParser.class,
                        lexer = PdslTestResourceLexer.class,
                        listener = FrameworkSpecificationListenerProvider.class
                )
                public void testThatIsNotRecognizedByParentRecognizer_shouldFailToCompile(){}
            }
            """
        When the test runner is compiled
        Then it does NOT compile successfully
        And the runner failed to compile because the default rule is missing from the recognizer

    Scenario: RecognizedBy uses custom recognizerRule but does not parse test resource
    In the event that the test-level parser uses a custom 'recognizerRule' it will be used to check the syntax of the
    test resources. Should the rule fail to recognize the test resource the test suite will fail to compile.

        Given the following test runner:
        """
        package com.pdsl.uat;

        import com.pdsl.grammars.*;
        import com.pdsl.junit.PdslGherkinApplication;
        import com.pdsl.junit.PdslGherkinJUnit4Runner;
        import com.pdsl.junit.PdslTest;
        import com.pdsl.junit.RecognizedBy;
        import org.junit.runner.RunWith;

        @RunWith(PdslGherkinJUnit4Runner.class)
        @PdslGherkinApplication(
                resourceRoot = "src/test/resources/framework_specifications"
        )
        public class SyntaxCheckTest {

            @PdslTest(
                    includesResources = "PdslTestFramework.feature",
                    parser = PdslFrameworkSpecificationParser.class,
                    lexer = PdslFrameworkSpecificationLexer.class,
                    listener = FrameworkSpecificationListenerProvider.class
            )
            @RecognizedBy(
                    dslRecognizerParser = MinimalParser.class,
                    dslRecognizerLexer = MinimalLexer.class,
                    recognizerRule = "minimal"
            )
            public void testResouceUnrecognizedByRule_failsToCompile(){}
        }
        """
        When the test runner is compiled
        Then it does NOT compile successfully
        And the runner failed to compile because the recognizerRule could not recognizer the test resource

    Scenario: @RecognizedBy missing default recognizer rule
        By default the parser uses a parser rule called "polymorphicDslSyntaxCheck" to inspect the syntax of test resources.
        If the parser does NOT have this rule then the application will fail to compile.

        Given the following test runner:
        """
        package com.pdsl.uat;

        import com.pdsl.grammars.*;
        import com.pdsl.junit.PdslGherkinApplication;
        import com.pdsl.junit.PdslGherkinJUnit4Runner;
        import com.pdsl.junit.PdslTest;
        import com.pdsl.junit.RecognizedBy;
        import org.junit.runner.RunWith;

        @RunWith(PdslGherkinJUnit4Runner.class)
        @PdslGherkinApplication(
                resourceRoot = "src/test/resources/framework_specifications"
        )
        public class SyntaxCheckTest {

            @PdslTest(
                    includesResources = "PdslTestFramework.feature",
                    parser = PdslFrameworkSpecificationParser.class,
                    lexer = PdslFrameworkSpecificationLexer.class,
                    listener = FrameworkSpecificationListenerProvider.class
            )
            @RecognizedBy(
                    dslRecognizerParser = MinimalParser.class,
                    dslRecognizerLexer = MinimalLexer.class,
                    recognizerRule = "thisRuleDoesNotExist"
            )
            public void parserWithMissingRecognizerRule_failsToCompile(){}
        }
        """
        When the test runner is compiled
        Then it does NOT compile successfully
        And the runner failed to compile because the default rule is missing from the recognizer

    // PENDING
    // Test custom stream provider
    // Test general recognizer TestHelper method

    // Class uses default rule and passes

    // PdslgherkinApplication uses recognizer AND method does too
    // Keep method body empty