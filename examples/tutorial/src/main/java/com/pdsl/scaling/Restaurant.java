package com.pdsl.scaling;

import com.pdsl.scaling.customers.Customer;
import com.pdsl.scaling.kitchen.Dishes;
import com.pdsl.scaling.kitchen.Kitchen;
import com.pdsl.scaling.menu.Menu;
import com.pdsl.scaling.orders.Order;
import com.pdsl.scaling.recipe.Repast;
import com.pdsl.scaling.reservation.ReservationService;
import com.pdsl.scaling.reservation.Table;
import com.pdsl.scaling.reservation.TimeService;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class Restaurant {

  private final ReservationService reservationService;
  private final TimeService timeService = new TimeService();
  private final Collection<Waiter> waiters;
  private final Kitchen kitchen;
  private Iterator<Waiter> nextWaiter;
  private final Menu menu;
  private Map<Waiter, BigDecimal> waiterTips;


  public Restaurant(ReservationService reservationService, Collection<Waiter> waiters,
      Kitchen kitchen, Menu menu, Map<Waiter, BigDecimal> waiterTips) {
    this.reservationService = reservationService;
    this.waiters = waiters;
    this.kitchen = kitchen;
    this.nextWaiter = waiters.iterator();
    this.menu = menu;
    this.waiterTips = waiterTips;
  }

  public int serveCustomer(Customer customer) {
    Optional<Table> tableOptional = reservationService.receiveCustomer(timeService.getTime(), customer);
    if (tableOptional.isEmpty()) {
      return 0;
    }
    Table table = tableOptional.get();
    if (!nextWaiter.hasNext()) { nextWaiter = waiters.iterator();}
    Waiter waiter = nextWaiter.next();
    waiter.showCustomerToTable(customer, table);
    customer.sitAtTable(table);
    customer.placeOrder(menu);
    Order order = waiter.takeCustomerOrder(customer);
    Collection<Repast> repasts =kitchen.prepareMeal(order);
    waiter.deliverFoodToCustomer(customer, repasts);
    customer.consumeMeals();
    waiter.giveCustomerBill(customer);
    waiterTips.computeIfAbsent(waiter, (key) -> BigDecimal.ZERO);
    customer.payBillWithPossibleTip().ifPresent(
        tip -> waiterTips.put(waiter, waiterTips.get(waiter).add(tip)));
    customer.leaveRestaurant();
    waiter.customerLeaves(customer);
    Collection<Dishes> dishes =waiter.clearTable(customer);
    kitchen.washDishes(dishes);
    return customer.getSatisfaction();
  }

}
