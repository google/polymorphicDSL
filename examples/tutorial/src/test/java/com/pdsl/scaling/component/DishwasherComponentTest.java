package com.pdsl.scaling.component;

import static com.google.common.truth.Truth.assertThat;

import com.example.DesegregatedRestaurantLexer;
import com.example.DesegregatedRestaurantParser;
import com.example.PdslRestaurantDishwasherParser;
import com.example.RestaurantDishwasherLexer;
import com.example.RestaurantDishwasherParser.DishwasherAllRulesContext;
import com.example.RestaurantDishwasherParser.ThenDishwasherCleansTheDishesContext;
import com.example.RestaurantDishwasherParserVisitor;
import com.pdsl.reports.TestRunResults;
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

public class DishwasherComponentTest {
  @TestTemplate
  @ExtendWith(DishwasherComponentContext.class)
  public void dishwasherComponentSuite(PdslExecutable pdslExecutable) {
    TestRunResults results = pdslExecutable.execute();
  }

  private static class DishwasherComponentContext extends PdslGherkinInvocationContextProvider {
    private static final ParseTreeVisitor<Void> COMPONENT_SINGLETON = new RestaurantComponentVisitor();
    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
              List.of(new PdslTestParameter.Builder(
                  RestaurantDishwasherLexer.class,
                  PdslRestaurantDishwasherParser.class,
                  () -> COMPONENT_SINGLETON
              )
                  .withIncludedResources(new String[] {"Restaurant.feature"}).build())
          )
          .withContext("Component")
          .withDslRecognizerLexer(DesegregatedRestaurantLexer.class)
          .withDslRecognizerParser(DesegregatedRestaurantParser.class)
          .withResourceRoot(Paths.get("./src/test/resources/features").toUri())
          .withApplicationName(ReportConstants.getApplicationName())
          .withRecognizerRule("polymorphicDslAllRules")
          .build()).stream();
    }
  }

  private static class RestaurantComponentVisitor extends
      AbstractParseTreeVisitor<Void> implements
      RestaurantDishwasherParserVisitor<Void> {

    @Override
    public Void visitThenDishwasherCleansTheDishes(ThenDishwasherCleansTheDishesContext ctx) {
      DishWasher dishwasher = new DishWasher();
      Dishes dishes = new Dishes();
      //Dirty the dishes
      dishes.serveWithFood();
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
