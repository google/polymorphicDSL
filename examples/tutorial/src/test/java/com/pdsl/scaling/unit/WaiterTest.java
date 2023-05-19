package com.pdsl.scaling.unit;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import com.example.DesegregatedRestaurantLexer;
import com.example.DesegregatedRestaurantParser;
import com.example.PdslRestaurantWaiterLexer;
import com.example.PdslRestaurantWaiterParser;
import com.example.PdslRestaurantWaiterParser.CustomerServiceAllRulesContext;
import com.example.PdslRestaurantWaiterParser.GivenWaiterContext;
import com.example.PdslRestaurantWaiterParser.PolymorphicDslAllRulesContext;
import com.example.PdslRestaurantWaiterParser.PolymorphicDslSyntaxCheckContext;
import com.example.PdslRestaurantWaiterParser.ThenTheCustomerIsGivenBillContext;
import com.example.PdslRestaurantWaiterParser.ThenTheCustomerLeavesContext;
import com.example.PdslRestaurantWaiterParser.ThenTheWaiterCleansTheTableContext;
import com.example.PdslRestaurantWaiterParser.ThenTheWaiterDeliversTheFoodToTheCustomerContext;
import com.example.PdslRestaurantWaiterParser.ThenTheyAreShownToTableContext;
import com.example.PdslRestaurantWaiterParser.WaiterAllRulesContext;
import com.example.PdslRestaurantWaiterParser.WhenWaiterTakesTheCustomerOrderContext;
import com.example.PdslRestaurantWaiterParserVisitor;
import com.pdsl.scaling.ReportConstants;
import com.pdsl.scaling.Waiter;
import com.pdsl.scaling.customers.Customer;
import com.pdsl.scaling.menu.Menu;
import com.pdsl.scaling.orders.Order;
import com.pdsl.scaling.recipe.Repast;
import com.pdsl.scaling.reservation.Table;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class WaiterTest {

  @TestTemplate
  @ExtendWith(WaiterUnitContext.class)
  public void waiterUnitSuite(PdslExecutable pdslExecutable) {
    pdslExecutable.execute();
  }

  private static class WaiterUnitContext extends PdslGherkinInvocationContextProvider {
    private static final ParseTreeVisitor<Void> UNIT_SINGLETON = new RestaurantUnitVisitor();

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(
              List.of(new PdslTestParameter.Builder(
                  PdslRestaurantWaiterLexer.class,
                  PdslRestaurantWaiterParser.class,
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

  private static class RestaurantUnitVisitor extends AbstractParseTreeVisitor<Void>
      implements PdslRestaurantWaiterParserVisitor<Void> {
    private Waiter waiter;
    private Customer customer;
    private Map<Customer, Order> customerOrders;
    private Map<Customer, Collection<Repast>> deliveredMeals;
    private Collection<Customer> dischargedCustomers;
    private final Table table = mock(Table.class);
    @Override
    public Void visitPolymorphicDslSyntaxCheck(PolymorphicDslSyntaxCheckContext ctx) {
       throw new IllegalStateException("The syntax check rule should not be called in the implementation!");
    }

    @Override
    public Void visitPolymorphicDslAllRules(PolymorphicDslAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitGivenWaiter(GivenWaiterContext ctx) {
      Menu menu = mock(Menu.class);
      waiter = new Waiter(menu);
      customer = mock(Customer.class);
      customerOrders = new HashMap<>();
      deliveredMeals = new HashMap<>();
      dischargedCustomers = new ArrayList<>();
      waiter = new Waiter(menu, customerOrders, deliveredMeals, dischargedCustomers);
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheWaiterCleansTheTable(ThenTheWaiterCleansTheTableContext ctx) {
      assertThat(deliveredMeals.size()).isGreaterThan(0);
      waiter.clearTable(customer);
      assertThat(dischargedCustomers.contains(customer)).isTrue();
      return visitChildren(ctx);
    }

    @Override
    public Void visitWaiterAllRules(WaiterAllRulesContext ctx) {return visitChildren(ctx);}

    @Override
    public Void visitThenTheyAreShownToTable(ThenTheyAreShownToTableContext ctx) {
      when(table.isCustomerCurrentlySeated()).thenReturn(true);
      waiter.showCustomerToTable(customer, table);
      return visitChildren(ctx);
    }

    @Override
    public Void visitWhenWaiterTakesTheCustomerOrder(WhenWaiterTakesTheCustomerOrderContext ctx) {
      Order order = mock(Order.class);
      when(order.getTable()).thenReturn(table);
      when(customer.placeOrder(any(Menu.class))).thenReturn(order);
      waiter.takeCustomerOrder(customer);
      assertThat(customerOrders.get(customer)).isEqualTo(order);
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheWaiterDeliversTheFoodToTheCustomer(
        ThenTheWaiterDeliversTheFoodToTheCustomerContext ctx) {
      Repast repast = mock(Repast.class);
      // Avoid null pointer exception in waiter implementation
      when(repast.cost()).thenReturn(BigDecimal.ONE);
      // Customer always accepts bill
      when(customer.receiveBill(any(BigDecimal.class))).thenReturn(true);
      List<Repast> repastList = List.of(repast);
      waiter.deliverFoodToCustomer(customer, repastList);
      assertThat(deliveredMeals.get(customer)).isEqualTo(repastList);
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheCustomerIsGivenBill(ThenTheCustomerIsGivenBillContext ctx) {
      waiter.giveCustomerBill(customer);
      return visitChildren(ctx);
    }

    @Override
    public Void visitThenTheCustomerLeaves(ThenTheCustomerLeavesContext ctx) {
      waiter.customerLeaves(customer);
      assertThat(dischargedCustomers.contains(customer)).isTrue();
      return visitChildren(ctx);
    }

    @Override
    public Void visitCustomerServiceAllRules(CustomerServiceAllRulesContext ctx) {
      return visitChildren(ctx);
    }

    @Override
    public Void visitErrorNode(ErrorNode node) {
      throw new IllegalStateException("There was an error in the grammar! Check the G4 files for the issue!");
    }
  }

}
