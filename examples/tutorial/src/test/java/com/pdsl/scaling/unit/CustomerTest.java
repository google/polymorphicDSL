package com.pdsl.scaling.unit;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.DesegregatedRestaurantLexer;
import com.example.DesegregatedRestaurantParser;
import com.example.PdslRestaurantCustomerLexer;
import com.example.PdslRestaurantCustomerParser;
import com.example.PdslRestaurantCustomerParser.CustomerAllRulesContext;
import com.example.PdslRestaurantCustomerParser.CustomerServiceAllRulesContext;
import com.example.PdslRestaurantCustomerParser.GivenCustomerContext;
import com.example.PdslRestaurantCustomerParser.PolymorphicDslAllRulesContext;
import com.example.PdslRestaurantCustomerParser.PolymorphicDslSyntaxCheckContext;
import com.example.PdslRestaurantCustomerParser.ThenCustomerEatsMealContext;
import com.example.PdslRestaurantCustomerParser.ThenTheCustomerIsGivenBillContext;
import com.example.PdslRestaurantCustomerParser.ThenTheCustomerLeavesContext;
import com.example.PdslRestaurantCustomerParser.ThenTheCustomerMayLeaveTipContext;
import com.example.PdslRestaurantCustomerParser.ThenTheCustomerPaysTheBillContext;
import com.example.PdslRestaurantCustomerParser.ThenTheWaiterDeliversTheFoodToTheCustomerContext;
import com.example.PdslRestaurantCustomerParser.ThenTheyAreShownToTableContext;
import com.example.PdslRestaurantCustomerParser.WhenTheCustomerOrdersMealContext;
import com.example.PdslRestaurantCustomerParser.WhenWaiterTakesTheCustomerOrderContext;
import com.example.PdslRestaurantCustomerParserVisitor;
import com.example.RestaurantCustomerLexer;
import com.example.RestaurantCustomerParser;
import com.example.RestaurantRecognizerLexer;
import com.example.RestaurantRecognizerParser;
import com.pdsl.scaling.CourseType;
import com.pdsl.scaling.MealType;
import com.pdsl.scaling.ReportConstants;
import com.pdsl.scaling.customers.Customer;
import com.pdsl.scaling.customers.SimpleCustomer;
import com.pdsl.scaling.menu.Menu;
import com.pdsl.scaling.orders.Order;
import com.pdsl.scaling.recipe.Repast;
import com.pdsl.scaling.recipe.RepastRecipeType;
import com.pdsl.scaling.reservation.Table;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.PdslConfigParameter;
import org.junit.jupiter.engine.descriptor.PdslExecutable;
import org.junit.jupiter.engine.descriptor.PdslGherkinInvocationContextProvider;
import org.junit.jupiter.engine.descriptor.PdslTestParameter;

/** PDSL Unit tests for the Customer object. */
public class CustomerTest {

  @TestTemplate
  @ExtendWith(CustomerUnitContext.class)
  public void customerUnitSuite(PdslExecutable pdslExecutable) {
    pdslExecutable.execute();
  }

  private static class CustomerUnitContext extends PdslGherkinInvocationContextProvider {
    private static final ParseTreeVisitor<Void> UNIT_SINGLETON = new RestaurantUnitVisitor();
    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
              List.of(new PdslTestParameter.Builder(
                  PdslRestaurantCustomerLexer.class,
                  PdslRestaurantCustomerParser.class,
                  () -> UNIT_SINGLETON
              )
                  .withIncludedResources(new String[] {"Restaurant.feature"}).build())
          )
          .withContext("Unit")
          .withDslRecognizerLexer(RestaurantRecognizerLexer.class)
          .withDslRecognizerParser(RestaurantRecognizerParser.class)
          .withResourceRoot(Paths.get("./src/test/resources/features").toUri())
          .withApplicationName(ReportConstants.getApplicationName())
          .withRecognizerRule("polymorphicDslAllRules")
          .build()).stream();
    }
  }

  private static class RestaurantUnitVisitor extends
      AbstractParseTreeVisitor<Void> implements
      PdslRestaurantCustomerParserVisitor<Void> {

    private Customer customer = new SimpleCustomer();
    private Optional<BigDecimal> tip;
    private Table table = mock(Table.class);
    private Menu menu = mock(Menu.class);
    private Order order;
    private CourseType courseType;
    private Collection<Repast> preparedMeals;
    private BigDecimal expectedMealCost;
    private int expectedQuality;

    @Override
    public Void visitThenCustomerEatsMeal(ThenCustomerEatsMealContext ctx) {
      int priorSatisfaction = customer.getSatisfaction();
      if (ctx.DESSERT() != null) {
        customer.consumeMeal(MealType.DESSERT);
      } else if (ctx.ENTREE() != null){
        customer.consumeMeal(MealType.ENTREE);
      } else if (ctx.SOUP() != null) {
        customer.consumeMeal(MealType.SOUP);
      } else if (ctx.APPETIZER() != null) {
        customer.consumeMeal(MealType.APPETIZER);
      } else {
        throw new IllegalArgumentException(String.format("Do not know how to feed customer!%n%s", ctx.getText()));
      }
      assertThat(customer.getSatisfaction()).isGreaterThan(priorSatisfaction);
      return visitChildren(ctx);
    }

    @Override
    public Void visitCustomerAllRules(CustomerAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheyAreShownToTable(ThenTheyAreShownToTableContext ctx) {
      customer.sitAtTable(table);
      return visitChildren(ctx);
    }

    @Override
    public Void visitWhenWaiterTakesTheCustomerOrder(WhenWaiterTakesTheCustomerOrderContext ctx) {
      order = customer.placeOrder(menu);
      assertThat(order.getRepasts().contains(RepastRecipeType.ECLAIR)).isTrue();
      assertThat(order.getRepasts().contains(RepastRecipeType.ESCARGOT)).isTrue();
      assertThat(order.getRepasts().contains(RepastRecipeType.ONION_SOUP)).isTrue();
      assertThat(order.getRepasts().contains(RepastRecipeType.RATATOUILLE)).isTrue();
      assertThat(order.getRepasts().size()).isEqualTo(4);
      return visitChildren(ctx);
    }

    @Override
    public Void visitWhenTheCustomerOrdersMeal(WhenTheCustomerOrdersMealContext ctx) {
      int courseMeal = Integer.parseInt(ctx.INT().getText());
      when(menu.getAvailableMeals(MealType.APPETIZER)).thenReturn(List.of(RepastRecipeType.ESCARGOT));
      when(menu.getAvailableMeals(MealType.ENTREE)).thenReturn(List.of(RepastRecipeType.RATATOUILLE));
      when(menu.getAvailableMeals(MealType.SOUP)).thenReturn(List.of(RepastRecipeType.ONION_SOUP));
      when(menu.getAvailableMeals(MealType.DESSERT)).thenReturn(List.of(RepastRecipeType.ECLAIR));

      Repast escargot = mock(Repast.class);
      when(escargot.getQuality()).thenReturn(113);
      when(escargot.getMealType()).thenReturn(MealType.APPETIZER);
      when(escargot.getRepastRecipeType()).thenReturn(RepastRecipeType.ESCARGOT);
      when(escargot.cost()).thenReturn(new BigDecimal("13"));

      Repast ratatouille = mock(Repast.class);
      when(ratatouille.getQuality()).thenReturn(199);
      when(ratatouille.getMealType()).thenReturn(MealType.ENTREE);
      when(ratatouille.getRepastRecipeType()).thenReturn(RepastRecipeType.RATATOUILLE);
      when(ratatouille.cost()).thenReturn(new BigDecimal("7"));

      Repast onionSoup = mock(Repast.class);
      when(onionSoup.getQuality()).thenReturn(149);
      when(onionSoup.getMealType()).thenReturn(MealType.SOUP);
      when(onionSoup.getRepastRecipeType()).thenReturn(RepastRecipeType.ONION_SOUP);
      when(onionSoup.cost()).thenReturn(new BigDecimal("11"));

      Repast eclair = mock(Repast.class);
      when(eclair.getQuality()).thenReturn(151);
      when(eclair.getMealType()).thenReturn(MealType.DESSERT);
      when(eclair.getRepastRecipeType()).thenReturn(RepastRecipeType.ECLAIR);
      when(eclair.cost()).thenReturn(new BigDecimal("5"));

      switch (courseMeal) {
        case 4:
          courseType = CourseType.FOUR;
          preparedMeals = List.of(eclair, escargot, onionSoup, ratatouille).stream().collect(
              Collectors.toCollection(() -> new ArrayList<>()));
          break;
        case 3:
          courseType = CourseType.THREE;
          preparedMeals = List.of(eclair, escargot, ratatouille).stream().collect(Collectors.toCollection(() -> new ArrayList<>()));
          break;
        default:
          throw new IllegalArgumentException("No implementation for " + courseMeal + "-course meal");
      }
      expectedMealCost = preparedMeals.stream().map(Repast::cost).reduce(BigDecimal.ZERO, BigDecimal::add);
      expectedQuality = preparedMeals.stream().mapToInt(Repast::getQuality).sum();
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheWaiterDeliversTheFoodToTheCustomer(
        ThenTheWaiterDeliversTheFoodToTheCustomerContext ctx) {
      assertThat(customer.receivePreparedMeals(preparedMeals)).isTrue();
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheCustomerIsGivenBill(ThenTheCustomerIsGivenBillContext ctx) {
      return null;
    }

    @Override
    public Void visitThenTheCustomerPaysTheBill(ThenTheCustomerPaysTheBillContext ctx) {
      assertThat(customer.receiveBill(expectedMealCost)).isTrue();
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheCustomerMayLeaveTip(ThenTheCustomerMayLeaveTipContext ctx) {
      tip = customer.payBillWithPossibleTip();
      assertThat(tip).isNotNull();
      assertThat(tip.get().compareTo(BigDecimal.ZERO)).isGreaterThan(0);
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheCustomerLeaves(ThenTheCustomerLeavesContext ctx) {
      customer.leaveRestaurant();
      return visitChildren(ctx);
    }

    @Override
    public Void visitCustomerServiceAllRules(CustomerServiceAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitPolymorphicDslSyntaxCheck(PolymorphicDslSyntaxCheckContext ctx) {
      return null;
    }

    @Override
    public Void visitPolymorphicDslAllRules(PolymorphicDslAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitGivenCustomer(GivenCustomerContext ctx) {
      customer = new SimpleCustomer();
      tip = null;
      return visitChildren(ctx);
    }

    @Override
    public Void visitErrorNode(ErrorNode node) {
      throw new IllegalStateException("There was an error in the grammar! Check the G4 files for the issue!");
    }
  }

}
