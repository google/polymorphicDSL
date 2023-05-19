package com.pdsl.scaling.recipe;

import com.pdsl.scaling.MealType;
import com.pdsl.scaling.kitchen.Dishes;
import com.pdsl.scaling.meals.ColdCuts;
import com.pdsl.scaling.meals.Eclair;
import com.pdsl.scaling.meals.Escargot;
import com.pdsl.scaling.meals.LeekAndPotatoSoup;
import com.pdsl.scaling.meals.Macaron;
import com.pdsl.scaling.meals.OnionSoup;
import com.pdsl.scaling.meals.Oysters;
import com.pdsl.scaling.meals.Ratatouille;
import java.math.BigDecimal;

/** A prepared meal made by a kitchen for a customer. */
public interface Repast {
  /** Provides the total cost of the meal that the customer is expected to pay. */
  BigDecimal cost();
  /** Returns the name of the meal. */
  String getName();
  /** Returns the quality of the prepared meal. */
  int getQuality();
  /** Returns the type of the meal. */
  MealType getMealType();
  /** Returns the recipe from which the repast was created. */
  RepastRecipeType getRepastRecipeType();
  /** Returns the dishes that this repast was or is served on. */
  Dishes getDishes();

  /**
   *   A static constructor for creating a repast.
   *
   * @param repastRecipeType the meal to create
   * @param quality how well the meal was prepared
   * @param dishes the dishes to serve with the meal
   * @return a prepared meal
   */

  static Repast create(RepastRecipeType repastRecipeType, int quality, Dishes dishes) {
    switch (repastRecipeType) {
      case COLD_CUTS:
        return new ColdCuts(quality, dishes);
      case ECLAIR:
        return new Eclair(quality, dishes);
      case ESCARGOT:
        return new Escargot(quality, dishes);
      case LEEK_AND_POTATO_SOUP:
        return new LeekAndPotatoSoup(quality, dishes);
      case MACARON:
        return new Macaron(quality, dishes);
      case ONION_SOUP:
        return new OnionSoup(quality, dishes);
      case OYSTERS:
        return new Oysters(quality, dishes);
      case RATATOUILLE:
        return new Ratatouille(quality, dishes);
    }
    throw new IllegalArgumentException("No implementation for " + repastRecipeType.name());
  }
}
