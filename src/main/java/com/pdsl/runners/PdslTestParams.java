package com.pdsl.runners;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import java.util.List;

/**
 * A container for parameters useful for any framework implementing PDSL.
 *
 * This serves as a common input to many helper methods useful to anyone seeking to embed the PDSL framework into their
 * own library.
 *
 * @param interpreters the parsers and visitors/listeners that will be used on the input files
 * @param tags arbitrary information used for message passing to the underlying framework
 * @param includesResources the tests written in the DSL
 * @param excludesResources the tests to ignore
 * @param <T> the type to be returned by any visitors of this clss
 */
public record PdslTestParams<T>(
        Class<? extends Lexer> lexerRecognizerClass,
        Class<? extends Parser> parserRecognizerClass,
        InterpreterParam[] interpreters,
        List<String> tags,
        String[] includesResources,
        String[] excludesResources) {
    public static final String DEFAULT_ALL_RULE = "polymorphicDslAllRules";
    public static final String DEFAULT_SYNTAX_RULE = "polymorphicDslSyntaxCheck";

    // Use visitor pattern so behavior can be modified without modifying PdslTestParams
    public interface PdslTestParamsVisitor<T> {
        T visitParams(PdslTestParams<?> params);
    }

    public T accept(PdslTestParamsVisitor<?> visitor) {
        return (T) visitor.visitParams(this);
    }
}
