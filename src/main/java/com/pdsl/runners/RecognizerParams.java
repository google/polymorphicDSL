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
 * holds data used for generating reports after execution and default information common to all of the tests (such
 * as the preferred recognizer if none is otherwise specified, the factories used to create PDSL objedcts, etc.
 *
 * @param context                 the methodology used to test the system under test (e.g, API, Integration, etc)
 * @param applicationName         the name of the system under test
 * @param resourceRoot            a leading fragment for a URI that all test resources should be searched under
 * @param pdslTestParams          the PDSL test cases to execute with this configuration
 * @param defaultLexerRecognizer  a lexer for a recognizer to be used if the underlying test doesn't specify one
 * @param defaultParserRecognizer a parser for a recognizer to be used if the underlying test doesn't specify one
 * @param providers               the PDSL factories to be used to create the test cases
 */
public record RecognizerParams(
        String context,
        String applicationName,
        String resourceRoot,
        List<PdslTestParams> pdslTestParams,
        Class<? extends Lexer> defaultLexerRecognizer,
        Class<? extends Parser> defaultParserRecognizer,
        PdslSuppliers providers
) {

    /**
     * The suppliers used to give factories to the PDSL framework for creating the test cases.
     * @param resourceFinderSupplier a supplier for finding test resources from URIs
     * @param specificationFactoryProvider the supplier for creating test specifications
     * @param testCaseFactoryProvider teh supplier for creating test cases from specifications
     */
    public record PdslSuppliers (
            Supplier<? extends TestResourceFinderGenerator> resourceFinderSupplier,
            Supplier<? extends TestSpecificationFactoryGenerator> specificationFactoryProvider,
            Supplier<? extends TestCaseFactory> testCaseFactoryProvider) {
    }

    /**
     * An operation enabling the visitor pattern for the RecognizerParams class.
     * <p>
     * Enables adding behavior to RecongizerParams without modifying the underlying source code.
     *
     * @param <T> the type to be returned by the visitor.
     */
    public interface RecognizerParamsOperation<T> {
        T recognizerParamsOperation(RecognizerParams recognizerParams);
    }

    /**
     * Accepts a RecognizerParamsOperation to perform its operation on this RecognizerParams object.
     *
     * Returns whatever the output of the visitor is.
     * @param visitor the operation to perform on this object
     * @return the result of the visitor
     * @param <T> the type of parameter returned by the visitor
     */
    public <T> T accept(RecognizerParamsOperation<T> visitor) {
        return visitor.recognizerParamsOperation(this);
    }

}
