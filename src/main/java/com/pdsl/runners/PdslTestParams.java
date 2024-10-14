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
 */
public record PdslTestParams (
        Class<? extends Lexer> lexerRecognizerClass,
        Class<? extends Parser> parserRecognizerClass,
        InterpreterParam[] interpreters,
        List<String> tags,
        String[] includesResources,
        String[] excludesResources) {
    public static final String DEFAULT_ALL_RULE = "polymorphicDslAllRules";
    public static final String DEFAULT_SYNTAX_RULE = "polymorphicDslSyntaxCheck";

    /**
     * A visitor used to extend the behavior of the PdslTestParams obje3ct without modifying
     * the record itself.
     *
     * This gives external library developers a convenient way to embed or enhace PDSL without them
     * needing to contribute directly to the PDSL repository as well as developing custom, independent
     * functionality.
     */
    public interface PdslTestParamsVisitor<T> {
        /**
         * Perform some operation and return result based on the PdslTestParams input.
         *
         * @param params the PDSL parameters used as input to do some operation
         * @return
         * @param <T>
         */
        T visitParams(PdslTestParams params);
    }

    /**
     * Performs an operation on the PdslTestParam as defined by the provided visitor.
     * @param visitor the visitor that performs the operation
     * @return an object that matches the type of the visitor
     */
    public <T> T accept(PdslTestParamsVisitor<T> visitor) {
        return (T) visitor.visitParams(this);
    }

}
