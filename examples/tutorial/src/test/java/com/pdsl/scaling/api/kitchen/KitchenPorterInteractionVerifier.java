package com.pdsl.scaling.api.kitchen;

import com.pdsl.scaling.ingredients.Ingredient;
import com.pdsl.scaling.kitchen.KitchenPorter;
import com.pdsl.scaling.recipe.RepastRecipeType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import jnr.ffi.annotations.In;

/**
 * A InteractionVerifier used for testing the KitchenPorter.
 */
public class KitchenPorterInteractionVerifier extends KitchenPorter {

  private static final Map<RepastRecipeType, Collection<Ingredient>> preparedIngredients = new HashMap<>();

  public static Map<RepastRecipeType, Collection<Ingredient>> getPreparedIngredients() {
    return preparedIngredients;
  }
  public static Collection<Ingredient> getIngredientsForRepast(RepastRecipeType repastRecipeType) {
    Collection<Ingredient> ingredients = KitchenPorter.getIngredientsForRepast(repastRecipeType);
    preparedIngredients.put(repastRecipeType, ingredients);
    return  ingredients;
  }

}
