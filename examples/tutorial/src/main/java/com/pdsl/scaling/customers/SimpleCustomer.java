package com.pdsl.scaling.customers;

import com.pdsl.scaling.MealType;
import com.pdsl.scaling.CourseType;
import com.pdsl.scaling.menu.Menu;
import com.pdsl.scaling.orders.Order;
import com.pdsl.scaling.recipe.Repast;
import com.pdsl.scaling.recipe.RepastRecipeType;
import com.pdsl.scaling.reservation.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A standard customer.
 * <p>
 * This customer will reject bills that do not match menu prices. They will leave tips if their
 * meals are well prepared and order the first thing they notice off the menu.
 * <p>
 * They will also only accept meals that match what they ordered.
 */

public class SimpleCustomer implements Customer {

  private Optional<Table> table = Optional.empty();
  private Optional<CourseType> preferredCourseType = Optional.empty();
  private Optional<Order> placedOrder;
  private Optional<Collection<Repast>> receivedFood = Optional.empty();
  private int satisfaction = 0;
  private final List<Repast> consumedMeals = new ArrayList<>();

  public void sitAtTable(Table table) {
    this.table = Optional.of(table);
  }

  @Override
  public int getSatisfaction() {
    return satisfaction;
  }

  public void setPreferredCourseType(CourseType courseType) {
    preferredCourseType = Optional.of(courseType);
  }
  @Override
  public Order placeOrder(Menu menu) {
    this.placedOrder = Optional.of(
        new Order() {
      @Override
      public Collection<RepastRecipeType> getRepasts() {
        return preferredCourseType.<Collection<RepastRecipeType>>map(
                courseType -> courseType.getMealTypes().stream()
                    .map(
                        mealType -> menu.getAvailableMeals(mealType).stream().findFirst().orElseThrow())
                    .collect(Collectors.toList()))
            .orElseGet(() -> CourseType.FOUR.getMealTypes().stream()
                .map(
                    mealType -> menu.getAvailableMeals(mealType).stream().findFirst().orElseThrow())
                .collect(Collectors.toList()));
      }

      @Override
      public int getPriority() {
        return 10;
      }

      @Override
      public Table getTable() {
        return table.orElseThrow();
      }
    });
    return placedOrder.get();

  }

  @Override
  public boolean receivePreparedMeals(Collection<Repast> repasts) {
    receivedFood = Optional.of(repasts);
    if (placedOrder.isEmpty()) {
      return false;
    }
    return repasts.stream()
        .allMatch(repast -> placedOrder.orElseThrow().getRepasts().contains(repast.getRepastRecipeType()));
  }

  @Override
  public int consumeMeal(MealType mealType) {
    Repast repast = receivedFood.orElseThrow().stream().filter(r -> r.getMealType().equals(mealType)).findFirst().orElseThrow();
    satisfaction += repast.getQuality();
    consumedMeals.add(repast);
    receivedFood.get().remove(repast);
    return satisfaction;
  }

  @Override
  public int consumeMeals() {
    consumedMeals.addAll(new ArrayList<>(receivedFood.orElseThrow()));
    receivedFood.stream().forEach(f -> receivedFood.get().remove(f));
    int additionalSatisfaction = consumedMeals.stream().mapToInt(Repast::getQuality).sum();
    satisfaction += additionalSatisfaction;
    return additionalSatisfaction;
  }

  @Override
  public boolean receiveBill(BigDecimal bigDecimal) {
    return consumedMeals.stream()
        .map(Repast::cost)
        .reduce(BigDecimal.ZERO, BigDecimal::add).equals(bigDecimal);
  }

  @Override
  public Optional<BigDecimal> payBillWithPossibleTip() {
    BigDecimal totalBill = consumedMeals.stream()
        .map(Repast::cost)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    if (satisfaction > 350) {
      return Optional.of(totalBill.multiply(new BigDecimal("0.15")));
    }
    return Optional.empty();
  }
  @Override
  public void leaveRestaurant() {
    placedOrder = Optional.empty();
    consumedMeals.clear();
    receivedFood = Optional.empty();
  }
}
