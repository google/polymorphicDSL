package com.pdsl.scaling.menu;

import com.pdsl.scaling.MealType;
import com.pdsl.scaling.recipe.RepastRecipeType;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A customer friendly document that lets the customer know what meals are available to purchase.
 */
public class Menu {
  private final Collection<RepastRecipeType> meals;

  public Menu(Collection<RepastRecipeType> meals) {
    this.meals = meals;
  }

  /** Return all of the available meals to order. */
  public Collection<RepastRecipeType> getAvailableMeals(MealType meal) {
    return meals.stream()
        .filter(m -> m.getMealType().equals(meal))
        .collect(Collectors.toSet());
  }

}
