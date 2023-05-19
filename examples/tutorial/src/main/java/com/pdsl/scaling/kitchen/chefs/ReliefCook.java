package com.pdsl.scaling.kitchen.chefs;

import com.pdsl.scaling.ingredients.Ingredient;
import com.pdsl.scaling.ingredients.PreparationType;
import java.util.Set;

public class ReliefCook extends AbstractChef {

  public int processIngrient(Ingredient ingredient, PreparationType preparationType) {
    return 75;
  }
  @Override
  protected Set<PreparationType> getSkills() {
    return Set.of();
  }
}
