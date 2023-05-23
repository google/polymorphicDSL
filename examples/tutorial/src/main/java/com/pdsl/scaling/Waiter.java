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

/**
 * A customer-service oriented staff member who interacts with customers.
 */
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

  /**
   * Get all the customers that the waiter is currently serving.
   * @return all customers being serviced by the waiter
   */
  public Collection<Customer> getCustomersReceivingService() {
    Collection<Customer> customers =  new ArrayList<>(customerOrders.keySet());
    customers.removeAll(dischargedCustomers);
    return customers;
  }

  /**
   * Seats a customer at the provided table.
   * @param customer the customer to seat
   * @param table the table to seat the customer at
   */
  public void showCustomerToTable(Customer customer, Table table) {
    assertThat(table.isCustomerCurrentlySeated()).isTrue();
  }

  /**
   * Retrieves an order from the customer to deliver to the kitchen.
   * @param customer the customer placing the order
   * @return the order placed by the customer, or a possible recommendation by the waiter
   */
  public Order takeCustomerOrder(Customer customer) {
    Order order = customer.placeOrder(menu);
    customerOrders.put(customer, order);
    return order;
  }

  /**
   * Delivers the customers prepared meals to the customer.
   * @param customer the customer to deliver the meals to
   * @param repasts the meals to give the customer
   * @return true if the customer accepts the order, false if they reject it
   */
  public boolean deliverFoodToCustomer(Customer customer, Collection<Repast> repasts) {
    deliveredMeals.put(customer, new ArrayList<>(repasts));
    return customer.receivePreparedMeals(repasts);
  }

  /**
   * Delivers the bill to a customer
   * @param customer the customer who is receiving the bill
   * @return BigDecimal the amount the customer is expected to pay
   */
  public BigDecimal giveCustomerBill(Customer customer) {
    return deliveredMeals.get(customer).stream()
        .map(Repast::cost)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  /**
   * Relieves the waiter from having to serve the customer further.
   */
  public void customerLeaves(Customer customer) {
    dischargedCustomers.add(customer);
  }

  /**
   * Retrieves the dirty dishes from a customers table.
   * @param customer the customer whose table needs to be cleaned
   * @return the dishes retrieved from the table
   */
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