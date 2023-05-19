package com.pdsl.scaling.kitchen;

import com.pdsl.scaling.ingredients.Ingredient;
import com.pdsl.scaling.recipe.RepastRecipeType;
import java.util.Collection;
import java.util.Set;

public class KitchenPorter {

  public static Collection<Ingredient> getIngredientsForRepast(RepastRecipeType repastRecipeType) {
    switch (repastRecipeType) {
      case COLD_CUTS:
        return Set.of(Ingredient.SLICED_MEAT, Ingredient.MEAT_GRAVY);
      case ECLAIR:
        return Set.of(Ingredient.SWEET_ROLL);
      case ESCARGOT:
        return Set.of(Ingredient.SNAILS, Ingredient.SAVORY_SAUCE);
      case LEEK_AND_POTATO_SOUP:
        return Set.of(Ingredient.BOILED_POTATOES, Ingredient.LEEKS);
      case MACARON:
        return Set.of(Ingredient.SWEETENED_ALMOND_FLOUR);
      case ONION_SOUP:
        return Set.of(Ingredient.CHOPPED_ONIONS, Ingredient.SAUTEED_ONIONS, Ingredient.BROTH);
      case OYSTERS:
        return Set.of(Ingredient.COOKED_OYSTERS);
      case RATATOUILLE:
        return Set.of(Ingredient.ROASTED_VEGETABLES, Ingredient.SAVORY_SAUCE);
    }
    throw new IllegalArgumentException("No implementation for " + repastRecipeType.name());
  }
}
