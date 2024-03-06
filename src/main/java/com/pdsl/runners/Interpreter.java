package com.pdsl.runners;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Provider;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * An attribute/container for {@link com.pdsl.runners.PdslTest} annotation,
 * created for supporting multiple Interpreters (Lexer/Parser; Listener/Visitor)
 * {@link com.pdsl.executors.InterpreterObj}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Interpreter {

  Class<? extends Parser> parser();
  Class<? extends Lexer> lexer();
  Class<? extends Provider<? extends ParseTreeListener>> listener() default EmptyParseTreeListenerProvider.class;
  Class<? extends Provider<? extends ParseTreeVisitor<?>>> visitor() default EmptyParseTreeVisitorProvider.class;
}
