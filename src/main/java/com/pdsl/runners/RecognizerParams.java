package com.pdsl.runners;

import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.testcases.TestCaseFactory;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import java.util.List;
import java.util.function.Supplier;

public interface RecognizerParams{
    String context();
    String applicationName();
    String resourceRoot();
    List<PdslTestParams> pdslTestParams();
    Class<? extends Lexer> defaultLexerRecognizer();
    Class<? extends Parser> defaultParserRecognizer();
    DefaultRecognizerParams.PdslSuppliers providers();

    /**
     * An operation enabling the visitor pattern for the RecognizerParams class.
     * <p>
     * Enables adding behavior to RecongizerParams without modifying the underlying source code.
     *
     * @param <T> the type to be returned by the visitor.
     */
    interface RecognizerParamsOperation<T> {
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
    default <T> T accept(RecognizerParamsOperation<T> visitor) {
        return visitor.recognizerParamsOperation(this);
    }

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
}