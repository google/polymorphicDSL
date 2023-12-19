package com.pdsl.grammars;

import com.pdsl.grammars.GammaTwoParser.HiFolksContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GammaTwoListenerImpl implements GammaTwoParserListener {

  private Logger logger = LoggerFactory.getLogger(GammaTwoListenerImpl.class);

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
  public void enterHiFolks(HiFolksContext ctx) {}

  @Override
  public void exitHiFolks(HiFolksContext ctx) {}

  @Override
  public void enterPolymorphicDslAllRules(GammaTwoParser.PolymorphicDslAllRulesContext ctx) {}

  @Override
  public void exitPolymorphicDslAllRules(GammaTwoParser.PolymorphicDslAllRulesContext ctx) {}

}
