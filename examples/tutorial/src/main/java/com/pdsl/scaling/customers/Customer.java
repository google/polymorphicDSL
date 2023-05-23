package com.pdsl.scaling.customers;

import com.pdsl.scaling.MealType;
import com.pdsl.scaling.menu.Menu;
import com.pdsl.scaling.orders.Order;
import com.pdsl.scaling.recipe.Repast;
import com.pdsl.scaling.reservation.Table;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

/** A partron of a restaurant. */
public interface Customer {

  /** Customer makes a decision about which items to order off the menu. */
  Order placeOrder(Menu menu);

  /**
   *   Accept or reject a delivered meal.
   * <p>
   *   A customer might reject a meal because it was not what they ordered.
   */
  boolean receivePreparedMeals(Collection<Repast> repasts);

  /**
   * Makes customer consumes a meal that was delivered to them.
   * <p>
   * If the cusotmer is asked to consume a meal they were not given a runtime exception will be
   * thrown.
   *
   * @param mealType the type of meal to consume
   * @return the satisfaction the customer had consuming the meal
   */
  int consumeMeal(MealType mealType);

  /**
   * Makes customer consume all meals that were delivered to them.
   * @return sum total of the satisfaction the customer had eating the meals, 0 if no meals were provided
   */
  int consumeMeals();

  /**
   * Returns the customers acceptance or rejection of the bill.
   * <p>
   * A customer might reject a bill if it does not match menu prices.
   *
   * @return true if the customer accepts the bill, else false
   */
  boolean receiveBill(BigDecimal bigDecimal);

  /**
   * Has customer pay bill for their meal.
   * <p>
   * If the customer is inclined to leave a tip for service it will be provided.
   *
   * @return a possible tip for the waiter
   */
  Optional<BigDecimal> payBillWithPossibleTip();

  /**
   * Has the customer leave the restaurant.
   * <p>
   * Practically this provides a method for customer to free data that they no longer need from
   * a former dining experience.
   */
  void leaveRestaurant();

  /**
   * Allows a customer to be associated with a table.
   */
  void sitAtTable(Table table);

  /**
   * The total satisfaction the customer has had from the consumption of all meals.
   */
  int getSatisfaction();
}