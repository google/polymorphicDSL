package com.pdsl.scaling.api;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doAnswer;

import com.example.RestaurantRecognizerLexer;
import com.example.RestaurantRecognizerParser;
import com.example.RestaurantRecognizerParser.CustomerAllRulesContext;
import com.example.RestaurantRecognizerParser.CustomerServiceAllRulesContext;
import com.example.RestaurantRecognizerParser.DishwasherAllRulesContext;
import com.example.RestaurantRecognizerParser.GivenTheCustomerHasReservationContext;
import com.example.RestaurantRecognizerParser.KitchenAllRulesContext;
import com.example.RestaurantRecognizerParser.PolymorphicDslAllRulesContext;
import com.example.RestaurantRecognizerParser.PolymorphicDslSyntaxCheckContext;
import com.example.RestaurantRecognizerParser.PorterAllRulesContext;
import com.example.RestaurantRecognizerParser.ReservationAllRulesContext;
import com.example.RestaurantRecognizerParser.StationChefsAllRulesContext;
import com.example.RestaurantRecognizerParser.ThenCustomerEatsMealContext;
import com.example.RestaurantRecognizerParser.ThenDishwasherCleansTheDishesContext;
import com.example.RestaurantRecognizerParser.ThenKitchenPorterPreparesTheIngredientsContext;
import com.example.RestaurantRecognizerParser.ThenTheCustomerIsGivenBillContext;
import com.example.RestaurantRecognizerParser.ThenTheCustomerLeavesContext;
import com.example.RestaurantRecognizerParser.ThenTheCustomerMayLeaveTipContext;
import com.example.RestaurantRecognizerParser.ThenTheCustomerPaysTheBillContext;
import com.example.RestaurantRecognizerParser.ThenTheOrderIsSentToTheKitchenContext;
import com.example.RestaurantRecognizerParser.ThenTheStationChefsPrepareContext;
import com.example.RestaurantRecognizerParser.ThenTheWaiterCleansTheTableContext;
import com.example.RestaurantRecognizerParser.ThenTheWaiterDeliversTheFoodToTheCustomerContext;
import com.example.RestaurantRecognizerParser.ThenTheyAreShownToTableContext;
import com.example.RestaurantRecognizerParser.WaiterAllRulesContext;
import com.example.RestaurantRecognizerParser.WhenTheCustomerArrivesOnTimeContext;
import com.example.RestaurantRecognizerParser.WhenTheCustomerOrdersMealContext;
import com.example.RestaurantRecognizerParserVisitor;
import com.pdsl.scaling.ReportConstants;
import com.pdsl.scaling.customers.SimpleCustomer;
import com.pdsl.scaling.CourseType;
import com.pdsl.scaling.MealType;
import com.pdsl.scaling.kitchen.Dishes;
import com.pdsl.scaling.kitchen.Kitchen;
import com.pdsl.scaling.Waiter;
import com.example.DesegregatedRestaurantParser;
import com.example.RestaurantRecognizerParser.GivenCustomerContext;
import com.example.RestaurantRecognizerParser.GivenWaiterContext;
import com.example.RestaurantRecognizerParser.WhenTheKitchenBeginsWorkingOnTheOrderContext;
import com.example.RestaurantRecognizerParser.WhenWaiterTakesTheCustomerOrderContext;
import com.pdsl.scaling.api.kitchen.KitchenInteractionVerifier;
import com.pdsl.scaling.api.kitchen.KitchenPorterInteractionVerifier;
import com.pdsl.scaling.api.kitchen.chefs.StationChefInteractionVerifier;
import com.pdsl.scaling.kitchen.KitchenPorter;
import com.pdsl.scaling.menu.Menu;
import com.pdsl.scaling.orders.Order;
import com.pdsl.scaling.recipe.Repast;
import com.pdsl.scaling.recipe.RepastRecipeType;
import com.pdsl.scaling.reservation.OperatingHours;
import com.pdsl.scaling.reservation.ReservationService;
import com.pdsl.scaling.reservation.Table;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.PdslConfigParameter;
import org.junit.jupiter.engine.descriptor.PdslExecutable;
import org.junit.jupiter.engine.descriptor.PdslGherkinInvocationContextProvider;
import org.junit.jupiter.engine.descriptor.PdslTestParameter;
import org.junit.platform.commons.util.Preconditions;
import com.pdsl.scaling.kitchen.DishWasher;
public class RestaurantApiTest {
  // Normal JUnit tests can run in parallel with others
  // Can be used for negative test cases or other numerous,
  // one off scenarios that don't justify a grammar.
  @Test
  public void testDishwasher() {
    DishWasher dishwasher = new DishWasher();
    Dishes dishes = mock(Dishes.class);
    doAnswer(
        invocation -> false

    ).when(dishes).clean();
    dishwasher.washDishes(dishes);
    assertThat(dishes.isDirty()).isFalse();
  }

  @TestTemplate
  @ExtendWith(RestaurantApiContext.class)
  public void restaurantApiSuite(PdslExecutable pdslExecutable) {
    pdslExecutable.execute();
  }

  private static class RestaurantApiContext extends PdslGherkinInvocationContextProvider {
    private static final ParseTreeVisitor<Void> API_SINGLETON = new RestaurantApiVisitor();
    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
              List.of(new PdslTestParameter.Builder(
                  RestaurantRecognizerLexer.class,
                  RestaurantRecognizerParser.class,
                  () -> API_SINGLETON
                  )
                  .withIncludedResources(new String[] {"Restaurant.feature"}).build())
          )
          .withContext("API")
          .withResourceRoot(Paths.get("./src/test/resources/features").toUri())
          .withApplicationName(ReportConstants.getApplicationName())
          .withRecognizerRule("polymorphicDslAllRules")
          .build()).stream();
    }
  }

  private static class RestaurantApiVisitor extends
      AbstractParseTreeVisitor<Void> implements
      RestaurantRecognizerParserVisitor<Void> {
    private static final List<Table> initTables() {
      List<Table> tables = new ArrayList<>();
      for (int i=0; i < 100; i++) {
        tables.add(new Table());
      }
      return tables;
    }
    private SimpleCustomer customer;
    private Optional<Table> table = Optional.empty();
    private final List<RepastRecipeType> menuItems = new ArrayList<>();
    private Waiter waiter;
    private Order order;
    private Collection<Repast> preparedMeals;
    private final KitchenPorterInteractionVerifier porterInteractionVerifier = new KitchenPorterInteractionVerifier();
    private final Kitchen kitchen = new KitchenInteractionVerifier();
    private CourseType courseType;
    private final ReservationService reservationService = new ReservationService(initTables());
    private Optional<BigDecimal> tip;
    private Collection<Dishes> dishes;

    @Override
    public Void visitPolymorphicDslSyntaxCheck(PolymorphicDslSyntaxCheckContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitPolymorphicDslAllRules(PolymorphicDslAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitGivenCustomer(GivenCustomerContext ctx) {
      customer = new SimpleCustomer();
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenCustomerEatsMeal(ThenCustomerEatsMealContext ctx) {
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
      return visitChildren(ctx);
    }

    @Override
    public Void visitWhenTheCustomerOrdersMeal(WhenTheCustomerOrdersMealContext ctx) {
      menuItems.clear();
      int courseMeal = Integer.parseInt(ctx.INT().getText());
      switch (courseMeal) {
        case 3:
          courseType = CourseType.THREE;
          menuItems.add(RepastRecipeType.ESCARGOT);
          menuItems.add(RepastRecipeType.ECLAIR);
          menuItems.add(RepastRecipeType.RATATOUILLE);
          break;
        case 4:
          courseType = CourseType.FOUR;
          menuItems.add(RepastRecipeType.ESCARGOT);
          menuItems.add(RepastRecipeType.ECLAIR);
          menuItems.add(RepastRecipeType.ONION_SOUP);
          menuItems.add(RepastRecipeType.RATATOUILLE);
          break;
        default:
          throw new IllegalArgumentException("No implementation for " + courseMeal + "-course meal");
      }
      customer.setPreferredCourseType(courseType);
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheCustomerPaysTheBill(ThenTheCustomerPaysTheBillContext ctx) {
      tip = customer.payBillWithPossibleTip();
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheCustomerMayLeaveTip(ThenTheCustomerMayLeaveTipContext ctx) {
      assertThat(tip).isNotNull();
      return visitChildren(ctx);
    }

    @Override
    public Void visitCustomerAllRules(CustomerAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheyAreShownToTable(ThenTheyAreShownToTableContext ctx) {
      waiter.showCustomerToTable(customer, table.orElseThrow());
      customer.sitAtTable(table.get());
      return visitChildren(ctx);
    }

    @Override
    public Void visitWhenWaiterTakesTheCustomerOrder(WhenWaiterTakesTheCustomerOrderContext ctx) {
      order = waiter.takeCustomerOrder(customer);
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheWaiterDeliversTheFoodToTheCustomer(
        ThenTheWaiterDeliversTheFoodToTheCustomerContext ctx) {
      assertThat(waiter.deliverFoodToCustomer(customer, preparedMeals)).isTrue();
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheCustomerIsGivenBill(ThenTheCustomerIsGivenBillContext ctx) {
      assertThat(customer.receiveBill(waiter.giveCustomerBill(customer))).isTrue();
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheCustomerLeaves(ThenTheCustomerLeavesContext ctx) {
      customer.leaveRestaurant();
      waiter.customerLeaves(customer);
      assertThat(waiter.getCustomersReceivingService().contains(customer)).isFalse();
      return visitChildren(ctx);
    }

    @Override
    public Void visitCustomerServiceAllRules(CustomerServiceAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenDishwasherCleansTheDishes(ThenDishwasherCleansTheDishesContext ctx) {
      kitchen.washDishes(dishes);
      assertThat(table.get().isCustomerCurrentlySeated()).isFalse();
      assertThat(waiter.getCustomersReceivingService().contains(customer)).isFalse();
      return visitChildren(ctx);
    }

    @Override
    public Void visitDishwasherAllRules(DishwasherAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheOrderIsSentToTheKitchen(ThenTheOrderIsSentToTheKitchenContext ctx) {
      Preconditions.notNull(order, "the order wasn't set by the test framework!");
      kitchen.prepareMeal(order);
      return visitChildren(ctx);
    }

    @Override
    public Void visitGivenWaiter(GivenWaiterContext ctx) {
      waiter = new Waiter(new Menu(menuItems));
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheWaiterCleansTheTable(ThenTheWaiterCleansTheTableContext ctx) {
      dishes = waiter.clearTable(customer);
      return visitChildren(ctx);
    }

    @Override
    public Void visitWaiterAllRules(WaiterAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenKitchenPorterPreparesTheIngredients(
        ThenKitchenPorterPreparesTheIngredientsContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitPorterAllRules(PorterAllRulesContext ctx) {
      order.getRepasts().stream()
              .map(KitchenPorter::getIngredientsForRepast)
                  .forEach(ingredients -> assertThat(ingredients).isNotEmpty());
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheStationChefsPrepare(ThenTheStationChefsPrepareContext ctx) {
      if (ctx.DESSERT() != null) {
        assertThat(((StationChefInteractionVerifier)kitchen.getPattisier()).getLastFoodScore().isPresent()).isTrue();
      } else if (ctx.DESSERT() != null) {
        assertThat(((StationChefInteractionVerifier)kitchen.getEntremetier()).getLastFoodScore().isPresent()).isTrue();
      } else if (ctx.APPETIZER() != null) {
        RepastRecipeType repastRecipeType = order.getRepasts().stream()
            .filter(r -> r.getMealType().equals(MealType.APPETIZER))
            .findFirst()
            .orElseThrow();
        switch (repastRecipeType) {

          case ESCARGOT:
            assertThat(((StationChefInteractionVerifier)kitchen.getFriturier()).getLastFoodScore().isPresent()).isTrue();
            break;
          case OYSTERS:
            assertThat(((StationChefInteractionVerifier)kitchen.getPoissonnier()).getLastFoodScore().isPresent()).isTrue();
            break;
          default:
            throw new IllegalArgumentException(String.format("The test framework does not consider %s an Appetizer", repastRecipeType.name()));
        }
      } else if (ctx.ENTREE() != null) {
        RepastRecipeType repastRecipeType = order.getRepasts().stream()
            .filter(r -> r.getMealType().equals(MealType.ENTREE))
            .findFirst()
            .orElseThrow();
        switch (repastRecipeType) {
          case RATATOUILLE:
            assertThat(((StationChefInteractionVerifier)kitchen.getEntremetier()).getLastFoodScore().isPresent()).isTrue();
            break;
          case COLD_CUTS:
            assertThat(((StationChefInteractionVerifier)kitchen.getBoucher()).getLastFoodScore().isPresent()).isTrue();
            break;
          default:
            throw new IllegalArgumentException(String.format("The test framework does not consider %s an Entree", repastRecipeType.name()));
        }
      }
      return visitChildren(ctx);
    }

    @Override
    public Void visitStationChefsAllRules(StationChefsAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitWhenTheKitchenBeginsWorkingOnTheOrder(
        WhenTheKitchenBeginsWorkingOnTheOrderContext ctx) {
      preparedMeals = kitchen.prepareMeal(order);
      return visitChildren(ctx);
    }

    @Override
    public Void visitKitchenAllRules(KitchenAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitGivenTheCustomerHasReservation(GivenTheCustomerHasReservationContext ctx) {
      reservationService.scheduleReservation(OperatingHours.PM1, customer);
      return visitChildren(ctx);
    }

    @Override
    public Void visitWhenTheCustomerArrivesOnTime(WhenTheCustomerArrivesOnTimeContext ctx) {
      table = reservationService.receiveCustomer(OperatingHours.PM1, customer);
      return visitChildren(ctx);
    }

    @Override
    public Void visitReservationAllRules(ReservationAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitErrorNode(ErrorNode node) {
      throw new IllegalStateException("There was an error in the grammar! Check the G4 files for the issue!");
    }
  }

}
