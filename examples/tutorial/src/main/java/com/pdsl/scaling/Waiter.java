package com.pdsl.scaling;

import static com.google.common.truth.Truth.assertThat;

import com.pdsl.scaling.customers.Customer;
import com.pdsl.scaling.kitchen.Dishes;
import com.pdsl.scaling.menu.Menu;
import com.pdsl.scaling.orders.Order;
import com.pdsl.scaling.recipe.Repast;
import com.pdsl.scaling.reservation.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Waiter {

  private BigDecimal tips = new BigDecimal(0);
  private final Menu menu;
  private Map<Customer, Order> customerOrders = new HashMap<>();
  private Map<Customer, Collection<Repast>> deliveredMeals = new HashMap<>();
  private Collection<Customer> dischargedCustomers = new ArrayList<>();

  public Waiter(Menu menu) {
    this.menu = menu;
  }


  public Waiter(Menu menu, Map<Customer, Order> customerOrders, Map<Customer, Collection<Repast>> deliveredMeals, Collection<Customer> dischargedCustomers) {
    this.menu = menu;
    this.customerOrders = customerOrders;
    this.deliveredMeals = deliveredMeals;
    this.dischargedCustomers = dischargedCustomers;
  }

  public Collection<Customer> getCustomersReceivingService() {
    Collection<Customer> customers =  new ArrayList<>(customerOrders.keySet());
    customers.removeAll(dischargedCustomers);
    return customers;
  }
  public void showCustomerToTable(Customer customer, Table table) {
    assertThat(table.isCustomerCurrentlySeated()).isTrue();
  }

  public Order takeCustomerOrder(Customer customer) {
    Order order = customer.placeOrder(menu);
    customerOrders.put(customer, order);
    return order;
  }

  public boolean deliverFoodToCustomer(Customer customer, Collection<Repast> repasts) {
    deliveredMeals.put(customer, new ArrayList<>(repasts));
    return customer.receivePreparedMeals(repasts);
  }

  public BigDecimal giveCustomerBill(Customer customer) {
    return deliveredMeals.get(customer).stream()
        .map(Repast::cost)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public void customerLeaves(Customer customer) {
    dischargedCustomers.add(customer);
  }

  public Collection<Dishes> clearTable(Customer customer) {
    customerOrders.get(customer).getTable().clearCustomersTable(customer);
    Collection<Dishes> dirtyDishes = deliveredMeals.get(customer).stream()
            .map(Repast::getDishes)
                .collect(Collectors.toList());
    deliveredMeals.remove(customer);
    customerOrders.remove(customer);
    return dirtyDishes;
  }

}