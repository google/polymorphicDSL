package com.pdsl.custom;

import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.PdslConfiguration;
import com.pdsl.runners.RecognizedBy;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.example.DotNavigationLexer;
import com.example.DotNavigationParser;
import com.example.DotNavigationParserBaseListener;
import javax.inject.Provider;
import com.pdsl.specifications.FileDelimitedTestSpecificationFactory;
import com.pdsl.testcases.SingleTestOutputPreorderTestCaseFactory;
import com.pdsl.runners.junit.PdslJUnit4ConfigurableRunner;

import java.util.*;

/**
 * A proof of concept demonstrating how to use a custom grammar to test a problem that is better visualized through
 * an image produced from a DOT file.
 */
@RunWith(PdslJUnit4ConfigurableRunner.class)
@PdslConfiguration(
        testCaseFactoryProvider = SingleTestOutputPreorderTestCaseFactory.DefaultProvider.class,
        specificationFactoryProvider = FileDelimitedTestSpecificationFactory.DefaultProvider.class,
        dslRecognizerParser = DotNavigationParser.class,
        dslRecognizerLexer = DotNavigationLexer.class,
        resourceRoot = "../../documentation/images/graphviz",
        recognizerRule = "dotFile")
public class CustomGrammarTest {


    @PdslTest(
            includesResources = "website.dot",
            parser = DotNavigationParser.class,
            lexer = DotNavigationLexer.class,
            startRule = "dotFile",
            listener = CustomGrammarTest.MyCustomPdslProvider.class)
    public void myFirstCustomGrammarPdslTest() {}

    // The provider builds the listener for the PDSL framework.
    // It must be static so that it can be seen by the underlying framework.
    // It also must have a default constructor (no parameters).
    // It can be convenient to make it a static class rather than put it in its
    // own file so that the person running the test can understand if there are
    // special inputs or not (dependency injection, etc).
    public static class MyCustomPdslProvider implements Provider<CustomDotNavigationListener> {
        private static final CustomDotNavigationListener listener = new CustomDotNavigationListener();
        @Override
        public CustomDotNavigationListener get() {
            return listener;
        }
    }

    /**
     * Do all the assertions after finding out what all the page links are.
     *
     * This is not what we would do in real life, but for the sake of demonstrating that we can at least tell what some
     * page links would be, we'll verify we can find some expected navigation paths after parsing everything.
     */
    @AfterClass
    public static void afterAll() {
        // After the parser interprets the DOT file we provided it stores the information it finds internally.
        // We retrieve that information and check to see if it is doing what we'd expect
        Map<String, List<String>> navigationPath = MyCustomPdslProvider.listener.getNavigationPath();
        Map<String, List<String>> adminOnly = MyCustomPdslProvider.listener.getAdminOnly();
        assert navigationPath.get("\"Sign In\"").size() == 1;
        assert navigationPath.get("Home").containsAll(Set.of("\"Shopping Cart\"", "\"Order History\""));
        assert adminOnly.get("Admin").containsAll(Set.of("\"Payment Data\"", "\"Refund Form\"", "\"Product Editor\""));
        assert adminOnly.get("Admin").size() == 3;
    }
}
