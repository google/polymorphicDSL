package com.pdsl.scaling.kitchen.chefs;

import com.pdsl.scaling.ingredients.Ingredient;
import com.pdsl.scaling.ingredients.PreparationType;
import java.util.Collection;

public interface StationChef {

  int processIngredient(Ingredient ingredient);
  int processIngredients(Collection<Ingredient> ingredients);
}