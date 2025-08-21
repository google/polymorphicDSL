package com.pdsl.runners;

import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.testcases.TestCaseFactory;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import java.util.List;
import java.util.function.Supplier;

/**
 * A DTO that holds data needed to run a full PDSL test suite.
 * <p>
 * This in addition to the PdslTestParams which hold information needed to run individual test cases, this object
 * holds data used for generating reports after execution and default information common to all the tests such
 * as the preferred recognizer if none is otherwise specified, the factories used to create PDSL objects, etc.
 *
 * @param context                 the methodology used to test the system under test (e.g, API, Integration, etc)
 * @param applicationName         the name of the system under test
 * @param resourceRoot            a leading fragment for a URI that all test resources should be searched under
 * @param pdslTestParams          the PDSL test cases to execute with this configuration
 * @param defaultLexerRecognizer  a lexer for a recognizer to be used if the underlying test doesn't specify one
 * @param defaultParserRecognizer a parser for a recognizer to be used if the underlying test doesn't specify one
 * @param providers               the PDSL factories to be used to create the test cases
 */
public record DefaultRecognizerParams(
        String context,
        String applicationName,
        String resourceRoot,
        List<PdslTestParams> pdslTestParams,
        Class<? extends Lexer> defaultLexerRecognizer,
        Class<? extends Parser> defaultParserRecognizer,
        PdslSuppliers providers
) implements RecognizerParams {}
