package com.pdsl.scaling.unit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static com.google.common.truth.Truth.assertThat;

import com.example.DesegregatedRestaurantLexer;
import com.example.DesegregatedRestaurantParser;
import com.example.PdslRestaurantDishwasherParser;
import com.example.RestaurantDishwasherLexer;
import com.example.RestaurantDishwasherParser.DishwasherAllRulesContext;
import com.example.RestaurantDishwasherParser.ThenDishwasherCleansTheDishesContext;
import com.example.RestaurantDishwasherParserVisitor;

import com.pdsl.scaling.ReportConstants;
import com.pdsl.scaling.kitchen.DishWasher;
import com.pdsl.scaling.kitchen.Dishes;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.PdslConfigParameter;
import org.junit.jupiter.engine.descriptor.PdslExecutable;
import org.junit.jupiter.engine.descriptor.PdslGherkinInvocationContextProvider;
import org.junit.jupiter.engine.descriptor.PdslTestParameter;

public class DishwasherTest {

  @TestTemplate
  @ExtendWith(DishwasherUnitContext.class)
  public void dishwasherUnitSuite(PdslExecutable pdslExecutable) {
    pdslExecutable.execute();
  }

  private static class DishwasherUnitContext extends PdslGherkinInvocationContextProvider {
    private static final ParseTreeVisitor<Void> UNIT_SINGLETON = new RestaurantUnitVisitor();
    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
              List.of(new PdslTestParameter.Builder(
                  RestaurantDishwasherLexer.class,
                  PdslRestaurantDishwasherParser.class,
                  () -> UNIT_SINGLETON
              )
                  .withIncludedResources(new String[] {"Restaurant.feature"}).build())
          )
          .withContext("Unit")
          .withDslRecognizerLexer(DesegregatedRestaurantLexer.class)
          .withDslRecognizerParser(DesegregatedRestaurantParser.class)
          .withResourceRoot(Paths.get("./src/test/resources/features").toUri())
          .withApplicationName(ReportConstants.getApplicationName())
          .withRecognizerRule("polymorphicDslAllRules")
          .build()).stream();
    }
  }

  private static class RestaurantUnitVisitor extends
      AbstractParseTreeVisitor<Void> implements
      RestaurantDishwasherParserVisitor<Void> {

    @Override
    public Void visitThenDishwasherCleansTheDishes(ThenDishwasherCleansTheDishesContext ctx) {
      boolean isDirty = true;
      DishWasher dishwasher = new DishWasher();
      Dishes dishes = mock(Dishes.class);
      when(dishes.isDirty()).thenReturn(isDirty);
      dishwasher.washDishes(dishes);
      assertThat(dishes.isDirty()).isFalse();
      return visitChildren(ctx);
    }

    @Override
    public Void visitDishwasherAllRules(DishwasherAllRulesContext ctx) {
      return visitChildren(ctx);
    }
  }
}
