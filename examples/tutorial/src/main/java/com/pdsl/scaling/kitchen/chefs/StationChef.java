package com.pdsl.scaling.kitchen.chefs;

import com.pdsl.scaling.ingredients.Ingredient;
import com.pdsl.scaling.ingredients.PreparationType;
import java.util.Collection;

/**
 * A chef that is capable of preparing ingredients as part of making a repast.
 *
 * The quality of the meal may vary depending on what the chef specializes in.
 */
public interface StationChef {

  /** Process an ingredient and return the quality of the chefs work. */
  int processIngredient(Ingredient ingredient);
  /** Process a collection of ingredients and return the quality of the chefs work as a sum total. */
  int processIngredients(Collection<Ingredient> ingredients);
}