package com.pdsl.scaling.menu;

import com.pdsl.scaling.MealType;
import com.pdsl.scaling.recipe.RepastRecipeType;
import java.util.Collection;
import java.util.stream.Collectors;

public class Menu {
  private final Collection<RepastRecipeType> meals;

  public Menu(Collection<RepastRecipeType> meals) {
    this.meals = meals;
  }

  public Collection<RepastRecipeType> getAvailableMeals(MealType meal) {
    return meals.stream()
        .filter(m -> m.getMealType().equals(meal))
        .collect(Collectors.toSet());
  }

}
