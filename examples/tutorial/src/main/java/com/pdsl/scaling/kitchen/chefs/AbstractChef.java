package com.pdsl.scaling.kitchen.chefs;

import com.pdsl.scaling.ingredients.Ingredient;
import com.pdsl.scaling.ingredients.PreparationType;
import java.util.Collection;
import java.util.Set;

/**
 * A general station chef that produces high quality output if they specialize in preparing certain ingredients.
 *
 * If the chef does not specialize in the ingredient they will still prepare the ingredients, but
 * with mediocre quality.
 */
public abstract class AbstractChef implements StationChef {

  @Override
  public int processIngredient(Ingredient ingredient) {
    if (getSkills().contains(ingredient.getPreparationType())) {
      return 100;
    }
    return 50;
  }

  @Override
  public int processIngredients(Collection<Ingredient> ingredients) {
    return ingredients.stream()
        .mapToInt( this::processIngredient)
        .sum();
  }

  abstract protected Set<PreparationType> getSkills();
}
