package com.pdsl.executors;

import java.util.Optional;

import com.pdsl.runners.PdslTestParams;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * A container of Visitor/Listener for the Test Case, created for supporting multiple Interpreters (Lexer/Parser; Listener/Visitor)
 * implementation of {@link com.pdsl.runners.PdslTest} annotation; {@link com.pdsl.runners.Interpreter}.
 * The object should contain either a `ParseTreeListener` or `ParseTreeVisitor`.
 */
public final class InterpreterObj {

  private final Optional<ParseTreeVisitor<?>> parseTreeVisitor;
  private final Optional<ParseTreeListener> parseTreeListener;
  private final String startRule;
  public InterpreterObj(ParseTreeVisitor<?> parseTreeVisitor) {
    this.parseTreeVisitor = Optional.of(parseTreeVisitor);
    this.parseTreeListener = Optional.empty();
    this.startRule = PdslTestParams.DEFAULT_ALL_RULE;
  }

  public InterpreterObj(ParseTreeListener parseTreeListener) {
    this.parseTreeVisitor = Optional.empty();
    this.parseTreeListener = Optional.of(parseTreeListener);
    this.startRule = PdslTestParams.DEFAULT_ALL_RULE;
  }

  public InterpreterObj(ParseTreeListener parseTreeListener, String startRule) {
    this.parseTreeVisitor = Optional.empty();
    this.parseTreeListener = Optional.of(parseTreeListener);
    this.startRule = startRule;
  }

  public InterpreterObj(ParseTreeVisitor<?> parseTreeVisitor, String startRule) {
    this.parseTreeVisitor = Optional.of(parseTreeVisitor);
    this.parseTreeListener = Optional.empty();
    this.startRule = startRule;
  }

  public Optional<ParseTreeVisitor<?>> getParseTreeVisitor(){
    return parseTreeVisitor;
  }

  public Optional<ParseTreeListener> getParseTreeListener() {
    return parseTreeListener;
  }
  
  public String getStartRule(){
    return this.startRule;
  }
}
