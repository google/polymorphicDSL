package com.pdsl.uat.java.jupiter;

import java.util.function.Supplier;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.engine.descriptor.PdslTestParameter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


public class PdslTestParameterTest {

  @Test
  void testCommandLineOverride() {
    System.setProperty("tags", "@smoke");
    PdslTestParameter params = PdslTestParameter.createTestParameter(
        mock(Supplier.class),
        Lexer.class,
        Parser.class,
        new String[]{"tags.feature"}, "@comment_tag#2");

    assertEquals("@smoke", params.getTagExpression());
  }

  @Test
  void testDefaultTag() {
    System.clearProperty("tags"); // to Ensure no command-line tag is set
    PdslTestParameter params = PdslTestParameter.createTestParameter(
        mock(Supplier.class),
        Lexer.class,
        Parser.class,
        new String[]{"tags.feature"}, "@comment_tag#2");
    assertEquals("@comment_tag#2", params.getTagExpression());
  }

  @Test
  void testMultipleTagsAndOr() {
    System.setProperty("tags", "@smoke and @unit or @sample");
    PdslTestParameter params = PdslTestParameter.createTestParameter(
        mock(Supplier.class),
        Lexer.class,
        Parser.class,
        new String[]{"tags.feature", "@comment_tag#2"}
    );
    // TODO: Assertions to verify the effectiveTagExpression
  }
}