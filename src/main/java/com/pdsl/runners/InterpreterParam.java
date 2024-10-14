package com.pdsl.runners;

import com.pdsl.executors.InterpreterObj;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import java.util.function.Supplier;

/**
 * A DTO that represents a group of tests written in a DSL and the parser that will execute on it.
 *
 * This does NOT include information about a recognizer. I.e., there may be sentences in the provided test resources
 * that may not be recognized by the provided parser. That (and other) information needed to run these tests is stored
 * in a PdslTestParams or RecognizerParams (in which this object would be embedded somewhere).
 *
 * @param parser the class of the parser that has a corresponding visitor/listener for phrases
 * @param lexer the class of the lexer that has a corresponding visitor/listener for phrases
 * @param interpreterProvider a supplier of either a listener/visitor
 * @param tags expressions, notes or meta-info for the underlying framework to take into considertion
 * @param includesResources the URIs that correspond to tests written in a DSL
 * @param excludesResources URIs to ignore when finding tests written in a DSL
 * @param startRule the rule to be used by the providing parser to start test execution
 * @param syntaxCheckRule the rule to be used to verify the syntax of the DSL in test resources is correct
 */
public record InterpreterParam(
    Class<? extends Parser> parser,
    Class<? extends Lexer> lexer,
    Supplier<InterpreterObj> interpreterProvider,
    String[] tags,
    String[] includesResources,
    String[] excludesResources,
    String startRule,
    String syntaxCheckRule
){
}
