package com.pdsl.executors;

import java.util.Optional;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * A container of Visitor/Listener for the Test Case, created for supporting multiple Interpreters (Lexer/Parser; Listener/Visitor)
 * implementation of {@link com.pdsl.runners.PdslTest} annotation; {@link com.pdsl.runners.Interpreter}.
 * The object should contain either a `ParseTreeListener` or `ParseTreeVisitor`.
 */
public final class InterpreterObj {

  private final Optional<ParseTreeVisitor> parseTreeVisitor;
  private final Optional<ParseTreeListener> parseTreeListener;

  public InterpreterObj(ParseTreeVisitor parseTreeVisitor) {
    this.parseTreeVisitor = Optional.of(parseTreeVisitor);
    this.parseTreeListener = Optional.empty();
  }

  public InterpreterObj(ParseTreeListener parseTreeListener) {
    this.parseTreeVisitor = Optional.empty();
    this.parseTreeListener = Optional.of(parseTreeListener);
  }

  public Optional<ParseTreeVisitor> getParseTreeVisitor(){
    return parseTreeVisitor;
  }

  public Optional<ParseTreeListener> getParseTreeListener() {
    return parseTreeListener;
  }
}
