package com.pdsl.runners;

import com.pdsl.executors.InterpreterObj;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.jruby.ir.Interp;

import javax.inject.Provider;

public record InterpreterParam(
    Class<? extends Parser> parser,
    Class<? extends Lexer> lexer,
    Provider<InterpreterObj> interpreterProvider,
    String[] tags,
    String[] includesResources,
    String[] excludesResources,
    String startRule,
    String syntaxCheckRule
){
}
