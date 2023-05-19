package com.pdsl.scaling.recipe;

import com.pdsl.scaling.MealType;
import com.pdsl.scaling.ingredients.Ingredient;
import com.pdsl.scaling.meals.Eclair;
import com.pdsl.scaling.meals.LeekAndPotatoSoup;
import java.util.Set;

public enum RepastRecipeType {
  COLD_CUTS,
  ECLAIR,
  ESCARGOT,
  LEEK_AND_POTATO_SOUP,
  MACARON,
  ONION_SOUP,
  OYSTERS,
  RATATOUILLE;

  public MealType getMealType() {
    switch (this) {
      case COLD_CUTS:
      case RATATOUILLE:
        return MealType.ENTREE;
      case MACARON:
      case ECLAIR:
        return MealType.DESSERT;
      case ESCARGOT:
      case OYSTERS:
        return MealType.APPETIZER;
      case ONION_SOUP:
      case LEEK_AND_POTATO_SOUP:
        return MealType.SOUP;
    }
    throw new IllegalStateException("No implementation for " + this.name());
  }
}
