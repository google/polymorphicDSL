package com.pdsl.scaling.unit;

import static com.google.common.truth.Truth.assertThat;

import com.example.DesegregatedRestaurantLexer;
import com.example.DesegregatedRestaurantParser;
import com.example.DesegregatedRestaurantParser.GivenCustomerContext;
import com.example.DesegregatedRestaurantParser.ThenCustomerEatsContext;
import com.example.DesegregatedRestaurantParser.ThenCustomerGivenBillContext;
import com.example.DesegregatedRestaurantParser.ThenCustomerLeavesContext;
import com.example.DesegregatedRestaurantParser.ThenCustomerMayLeaveTipContext;
import com.example.DesegregatedRestaurantParser.ThenCustomerPaysBillContext;
import com.example.DesegregatedRestaurantParser.ThenCustomerShownToTableContext;
import com.example.DesegregatedRestaurantParser.ThenWaiterDeliversFoodToCustomerContext;
import com.example.DesegregatedRestaurantParser.WhenTheCustomerOrdersContext;
import com.example.DesegregatedRestaurantParser.WhenWaiterTakesTheCustomersOrderContext;
import com.example.DesegregatedRestaurantParserBaseVisitor;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * A problematic example of a unit test made with a unsegregated parser.
 *
 * When a parser is created without following the Interface Segregation Principle a lot of extra
 * methods are added to the test that aren't relevant.
 *
 * In this example, the problem is "worked around" by using a Base Visitor which provides empty,
 * stubbed methods by default.
 *
 * This causes several problems:
 *
 * 1. The output of the test executes and implies that work is being done on <em>every</em> step,
 *    even though this is untrue. This makes it difficult to find out where the test is actually
 *    doing work.
 *
 * 2. When new sentences relevant to customer are added the test silently gets an empty no-op method.
 *    This makes it easy to forget to add unit tests for the new functionality and produce false
 *    positives.
 *
 * While this test suite actually runs and does useful work, it is done in a way that is difficult
 * to coordinate maintenance across different teams whenever the Customer object is impacted.
 *
 * A recommended alternative is to break the g4 file into multiple parts in the same way you would
 * break up an interface such that every test only implements the parts relevant to it. The test
 * could then implement the visitor interface instead of the base visitor. This will immediately
 * make it obvious at compile time if new behavior is needed for the Customer unit tests if the
 * grammar is updated.
 */
public class BadCustomerUnitTest {
  @TestTemplate
  @ExtendWith(CustomerUnitContext.class)
  public void restaurantIntegrationSuite(PdslExecutable pdslExecutable) {
    pdslExecutable.execute();
  }

  private static class CustomerUnitContext extends PdslGherkinInvocationContextProvider {
    private static final ParseTreeVisitor<Void> UNIT_SINGLETON = new CustomerUnitVisitor();
    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
              List.of(new PdslTestParameter.Builder(
                  DesegregatedRestaurantLexer.class,
                  DesegregatedRestaurantParser.class,
                  () -> UNIT_SINGLETON
              )
                  .withIncludedResources(new String[] {"Restaurant.feature"}).build())
          )
          .withContext("Unit")
          .withResourceRoot(Paths.get("./src/test/resources/features").toUri())
          .withApplicationName(ReportConstants.getApplicationName())
          .withRecognizerRule("polymorphicDslAllRules")
          .build()).stream();
    }
  }

  private static class CustomerUnitVisitor extends DesegregatedRestaurantParserBaseVisitor<Void> {

    private Customer customer = new SimpleCustomer();
    private Optional<BigDecimal> tip;
    private Table table = mock(Table.class);
    private Menu menu = mock(Menu.class);
    private Order order;
    private CourseType courseType;
    private Collection<Repast> preparedMeals;
    private BigDecimal expectedMealCost;
    private int expectedQuality;
    CustomerUnitVisitor() {

    }
    @Override
    public Void visitGivenCustomer(GivenCustomerContext ctx) {
      customer = new SimpleCustomer();
      tip = null;
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenCustomerShownToTable(ThenCustomerShownToTableContext ctx) {
      customer.sitAtTable(table);
      return visitChildren(ctx);
    }

    @Override
    public Void visitWhenWaiterTakesTheCustomersOrder(WhenWaiterTakesTheCustomersOrderContext ctx) {
      order = customer.placeOrder(menu);
      assertThat(order.getRepasts().contains(RepastRecipeType.ECLAIR)).isTrue();
      assertThat(order.getRepasts().contains(RepastRecipeType.ESCARGOT)).isTrue();
      assertThat(order.getRepasts().contains(RepastRecipeType.ONION_SOUP)).isTrue();
      assertThat(order.getRepasts().contains(RepastRecipeType.RATATOUILLE)).isTrue();
      assertThat(order.getRepasts().size()).isEqualTo(4);
      return visitChildren(ctx);
    }

    @Override
    public Void visitWhenTheCustomerOrders(WhenTheCustomerOrdersContext ctx) {
      int courseMeal = Integer.parseInt(ctx.INT().getText());
      switch (courseMeal) {
        case 4:
          courseType = CourseType.FOUR;
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

          preparedMeals = List.of(eclair, escargot, onionSoup, ratatouille).stream().collect(
              Collectors.toCollection(() -> new ArrayList<>()));
          expectedMealCost = preparedMeals.stream().map(Repast::cost).reduce(BigDecimal.ZERO, BigDecimal::add);
              expectedQuality = preparedMeals.stream().mapToInt(Repast::getQuality).sum();
          break;
        default:
          throw new IllegalArgumentException("No implementation for " + courseMeal + "-course meal");
      }
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenWaiterDeliversFoodToCustomer(ThenWaiterDeliversFoodToCustomerContext ctx) {
      assertThat(customer.receivePreparedMeals(preparedMeals)).isTrue();
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenCustomerEats(ThenCustomerEatsContext ctx) {
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
    public Void visitThenCustomerGivenBill(ThenCustomerGivenBillContext ctx) {
      assertThat(customer.receiveBill(expectedMealCost)).isTrue();
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenCustomerPaysBill(ThenCustomerPaysBillContext ctx) {
      tip = customer.payBillWithPossibleTip();
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenCustomerMayLeaveTip(ThenCustomerMayLeaveTipContext ctx) {
      assertThat(tip).isNotNull();
      assertThat(tip.get().compareTo(BigDecimal.ZERO)).isGreaterThan(0);
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenCustomerLeaves(ThenCustomerLeavesContext ctx) {
      customer.leaveRestaurant();
      return visitChildren(ctx);
    }
    @Override
    public Void visitPolymorphicDslAllRules(DesegregatedRestaurantParser.PolymorphicDslAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitErrorNode(ErrorNode node) {
      throw new IllegalStateException("There was an error in the grammar! Check the G4 files for the issue!");
    }
  }
}
