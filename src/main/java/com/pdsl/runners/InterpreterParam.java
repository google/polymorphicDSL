package com.pdsl.runners;

import com.pdsl.executors.InterpreterObj;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import java.util.function.Supplier;

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
