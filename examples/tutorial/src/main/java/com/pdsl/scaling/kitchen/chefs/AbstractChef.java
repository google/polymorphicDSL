package com.pdsl.scaling.kitchen.chefs;

import com.pdsl.scaling.ingredients.Ingredient;
import com.pdsl.scaling.ingredients.PreparationType;
import java.util.Collection;
import java.util.Set;

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
