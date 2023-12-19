package com.pdsl.grammars;

import com.pdsl.grammars.GammaOneParser.HelloWorldContext;
import com.pdsl.grammars.GammaOneParser.PolymorphicDslAllRulesContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GammaOneListenerImpl implements GammaOneParserListener {

  private Logger logger = LoggerFactory.getLogger(GammaOneListenerImpl.class);

  @Override
  public void visitErrorNode(ErrorNode node) {
    logger.error(node.toString());
    logger.error(node.getText());

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
  public void enterPolymorphicDslAllRules(PolymorphicDslAllRulesContext ctx) {}

  @Override
  public void exitPolymorphicDslAllRules(PolymorphicDslAllRulesContext ctx) {}

  @Override
  public void enterHelloWorld(HelloWorldContext ctx) {}

  @Override
  public void exitHelloWorld(HelloWorldContext ctx) {}

}
