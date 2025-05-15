package com.pdsl.executors;

import java.util.Optional;
import java.util.function.Supplier;
import com.pdsl.runners.PdslTestParams;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * A container of Visitor/Listener for the Test Case, created for supporting multiple Interpreters (Lexer/Parser; Listener/Visitor)
 * implementation of {@link com.pdsl.runners.PdslTest} annotation; {@link com.pdsl.runners.Interpreter}.
 * The object should contain either a `ParseTreeListener` or `ParseTreeVisitor`.
 */
public final class InterpreterObj {

  private final Optional<Supplier<ParseTreeVisitor<?>>> parseTreeVisitor;
  private final Optional<Supplier<ParseTreeListener>> parseTreeListener;
  private final String startRule;

  public InterpreterObj(ParseTreeVisitor<?> parseTreeVisitor) {
    this.parseTreeVisitor = Optional.of(() -> parseTreeVisitor);
    this.parseTreeListener = Optional.empty();
    this.startRule = PdslTestParams.DEFAULT_ALL_RULE;
  }

  /**
   * Creates a InterpreterObj from the supplied parameters.
   *
   * This will use the default start rule. If a specific start rule is needed use the related
   * method ofVisitor(supplier, String)
   *
   * @return InterpreterObj
   */
  public static InterpreterObj ofVisitor(Supplier<ParseTreeVisitor<?>> supplier) {
    return new InterpreterObj(supplier, PdslTestParams.DEFAULT_ALL_RULE);
  }

  /**
   * Creates a InterpreterObj from the supplied parameters.
   *
   * @return InterpreterObj
   */
  public static InterpreterObj ofVisitor(Supplier<ParseTreeVisitor<?>> supplier, String startRule) {
    return new InterpreterObj(supplier, startRule);
  }

  /**
   * Creates a InterpreterObj from the supplied parameters.
   *
   * @return InterpreterObj
   */
  public static InterpreterObj ofListener(Supplier<ParseTreeListener> supplier, String startRule) {
    return new InterpreterObj(startRule, supplier);
  }

  /**
   * Creates a InterpreterObj from the supplied parameters.
   *
   * This will use the default start rule. If a specific start rule is needed then use the
   * corresponding ofListener(supplier, string) instead
   *
   * @return InterpreterObj
   */
  public static InterpreterObj ofListener(Supplier<ParseTreeListener> supplier) {
    return new InterpreterObj(PdslTestParams.DEFAULT_ALL_RULE, supplier);
  }

  // Deal with type erasure by shuffling parameter order for listeners and visitors
  private InterpreterObj(Supplier<ParseTreeVisitor<?>> parseTreeVisitorSupplier, String startRule) {
    this.parseTreeVisitor = Optional.of(parseTreeVisitorSupplier);
    this.parseTreeListener = Optional.empty();
    this.startRule = startRule;
  }
  // Deal with type erasure by shuffling parameter order for listeners and visitors
  private InterpreterObj(String startRule, Supplier<ParseTreeListener> parseTreeListenerSupplier) {
    this.parseTreeVisitor = Optional.empty();
    this.parseTreeListener = Optional.of(parseTreeListenerSupplier);
    this.startRule = PdslTestParams.DEFAULT_ALL_RULE;
  }


  /**
   * Creates a InterpreterObj from the supplied parameter.
   *
   * This will use the default start rule.
   * The supplied parseTreeListener will be used as a singleton.
   *
   * @return InterpreterObj
   */
  public InterpreterObj(ParseTreeListener parseTreeListener) {
    this.parseTreeVisitor = Optional.empty();
    this.parseTreeListener = Optional.of(() -> parseTreeListener);
    this.startRule = PdslTestParams.DEFAULT_ALL_RULE;
  }

  /**
   * Creates a InterpreterObj from the supplied parameter.
   *
   * This will use the supplied start rule.
   * The supplied parseTreeListener will be used as a singleton.
   *
   * @return InterpreterObj
   */
  public InterpreterObj(ParseTreeListener parseTreeListener, String startRule) {
    this.parseTreeVisitor = Optional.empty();
    this.parseTreeListener = Optional.of(() -> parseTreeListener);
    this.startRule = startRule;
  }

  /**
   * Creates a InterpreterObj from the supplied parameter.
   *
   * This will use the supplied start rule.
   * The supplied parseTreeListener will be used as a singleton.
   *
   * @return InterpreterObj
   */
  public InterpreterObj(ParseTreeVisitor<?> parseTreeVisitor, String startRule) {
    this.parseTreeVisitor = Optional.of(() -> parseTreeVisitor);
    this.parseTreeListener = Optional.empty();
    this.startRule = startRule;
  }

  public Optional<ParseTreeVisitor<?>> getParseTreeVisitor() {
      return parseTreeVisitor.map(Supplier::get);
  }

  public Optional<ParseTreeListener> getParseTreeListener() {
    return parseTreeListener.map(Supplier::get);
  }
  
  public String getStartRule(){
    return this.startRule;
  }
}

