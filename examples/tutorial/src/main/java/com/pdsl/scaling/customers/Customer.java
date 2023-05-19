package com.pdsl.scaling.customers;

import com.pdsl.scaling.MealType;
import com.pdsl.scaling.menu.Menu;
import com.pdsl.scaling.orders.Order;
import com.pdsl.scaling.recipe.Repast;
import com.pdsl.scaling.reservation.Table;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public interface Customer {

  Order placeOrder(Menu menu);
  boolean receivePreparedMeals(Collection<Repast> repasts);
  int consumeMeal(MealType mealType);
  int consumeMeals();
  boolean receiveBill(BigDecimal bigDecimal);
  Optional<BigDecimal> payBillWithPossibleTip();
  void leaveRestaurant();
  void sitAtTable(Table table);

  int getSatisfaction();
}