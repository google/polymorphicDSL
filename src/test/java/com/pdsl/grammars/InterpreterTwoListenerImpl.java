package com.pdsl.grammars;

import com.pdsl.grammars.InterpreterTwoParser.HiFolksContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class InterpreterTwoListenerImpl implements InterpreterTwoParserListener {

  private Logger logger = LoggerFactory.getLogger(InterpreterTwoListenerImpl.class);

  @Override
  public void visitErrorNode(ErrorNode errorNode) {
    logger.error(errorNode.toString());
    logger.error(errorNode.getText());

    throw new IllegalStateException(
        "There was an error in the grammar! Check the G4 files for the issue!");
  }

  @Override
  public void visitTerminal(TerminalNode terminalNode) {}

  @Override
  public void enterEveryRule(ParserRuleContext parserRuleContext) {}

  @Override
  public void exitEveryRule(ParserRuleContext parserRuleContext) {}

  @Override
  public void enterHiFolks(HiFolksContext ctx) {
    logger.info("enterHiFolks(). I am from InterpreterTwoListenerImpl.");
  }

  @Override
  public void exitHiFolks(HiFolksContext ctx) {}

  @Override
  public void enterPolymorphicDslAllRules(InterpreterTwoParser.PolymorphicDslAllRulesContext ctx) {}

  @Override
  public void exitPolymorphicDslAllRules(InterpreterTwoParser.PolymorphicDslAllRulesContext ctx) {}
}
