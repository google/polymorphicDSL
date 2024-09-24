package com.pdsl.runners;

import com.pdsl.specifications.TestResourceFinderGenerator;
import com.pdsl.testcases.TestCaseFactory;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import javax.inject.Provider;
import java.util.List;

public record RecognizerParams<T>(
        String context,
        String applicationName,
        String resourceRoot,
        List<PdslTestParams<T>> pdslTestParams,
        Class<? extends Lexer> defaultLexerRecognizer,
        Class<? extends Parser> defaultParserRecognizer,
        PdslProviders providers
) {

    public record PdslProviders(
            Provider<? extends TestResourceFinderGenerator> resourceFinderProvider,
            Provider<? extends TestSpecificationFactoryGenerator> specificationFactoryProvider,
            Provider<? extends TestCaseFactory> testCaseFactoryProvider) {}
    /**
     * An operation enabling the visitor pattern for the RecognizerParams class.
     * <p>
     * Enables adding behavior to RecongizerParams without modifying the underlying source code.
     * @param <T> the type to be returned by the visitor.
     */
    public interface RecognizerParamsOperation<T> {
        T recognizerParamsOperation(RecognizerParams<T> recognizerParams);
    }

    public T accept(RecognizerParamsOperation<T> visitor) {
        return visitor.recognizerParamsOperation(this);
    }
}
