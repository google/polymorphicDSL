package com.pdsl.scaling.integration;

import static com.google.common.truth.Truth.assertThat;

import com.example.DesegregatedRestaurantLexer;
import com.example.DesegregatedRestaurantParser;
import com.example.PdslRestaurantDishwasherParser;
import com.example.PdslRestaurantReservationParser;
import com.example.PdslRestaurantReservationParser.GivenTheCustomerHasReservationContext;
import com.example.PdslRestaurantReservationParser.PolymorphicDslAllRulesContext;
import com.example.PdslRestaurantReservationParser.PolymorphicDslSyntaxCheckContext;
import com.example.PdslRestaurantReservationParser.ReservationAllRulesContext;
import com.example.PdslRestaurantReservationParser.WhenTheCustomerArrivesOnTimeContext;
import com.example.PdslRestaurantReservationParserVisitor;
import com.example.RestaurantRecognizerLexer;
import com.example.RestaurantRecognizerParser;
import com.example.RestaurantReservationLexer;
import com.pdsl.scaling.ReportConstants;
import com.pdsl.scaling.Restaurant;
import com.pdsl.scaling.Waiter;
import com.pdsl.scaling.customers.Customer;
import com.pdsl.scaling.customers.SimpleCustomer;
import com.pdsl.scaling.kitchen.FullKitchen;
import com.pdsl.scaling.menu.Menu;
import com.pdsl.scaling.recipe.RepastRecipeType;
import com.pdsl.scaling.reservation.OperatingHours;
import com.pdsl.scaling.reservation.ReservationService;
import com.pdsl.scaling.reservation.Table;
import com.pdsl.scaling.reservation.TimeService;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
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

public class RestaurantIntegrationTest {

  @TestTemplate
  @ExtendWith(RestaurantIntegrationContext.class)
  public void restaurantIntegrationTests(PdslExecutable pdslExecutable) {
    pdslExecutable.execute();
  }

  private static class RestaurantIntegrationContext extends PdslGherkinInvocationContextProvider {
    private static final ParseTreeVisitor<Void> UNIT_SINGLETON = new RestaurantIntegrationVisitor();

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
              List.of(new PdslTestParameter.Builder(
                  RestaurantReservationLexer.class,
                  PdslRestaurantReservationParser.class,
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

  private static class RestaurantIntegrationVisitor extends
      AbstractParseTreeVisitor<Void> implements
      PdslRestaurantReservationParserVisitor<Void> {
    private Restaurant restaurant;
    private Customer customer;
    private ReservationService reservationService;
    private TimeService timeService;
    @Override
    public Void visitGivenTheCustomerHasReservation(GivenTheCustomerHasReservationContext ctx) {
      Menu menu = new Menu(Arrays.stream(RepastRecipeType.values()).collect(Collectors.toList()));
      customer = new SimpleCustomer();
      timeService = new TimeService();
      reservationService = new ReservationService(List.of(new Table()));
      restaurant = new Restaurant(reservationService,
          List.of(new Waiter(menu)),
          new FullKitchen(),
          menu,
          new HashMap<>());
      return visitChildren(ctx);
    }

    @Override
    public Void visitWhenTheCustomerArrivesOnTime(WhenTheCustomerArrivesOnTimeContext ctx) {
      OperatingHours operatingHours = timeService.getTime();
      reservationService.scheduleReservation(operatingHours, customer);
      int customerSatisfaction = restaurant.serveCustomer(customer);
      assertThat(customerSatisfaction).isGreaterThan(0);
      return visitChildren(ctx);
    }

    @Override
    public Void visitReservationAllRules(ReservationAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitPolymorphicDslSyntaxCheck(PolymorphicDslSyntaxCheckContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitPolymorphicDslAllRules(PolymorphicDslAllRulesContext ctx) {
      return visitChildren(ctx);
    }
  }
}
