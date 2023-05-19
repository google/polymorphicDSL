package com.pdsl.scaling.kitchen.chefs;

import com.pdsl.scaling.ingredients.Ingredient;
import com.pdsl.scaling.ingredients.PreparationType;
import java.util.Set;

/** A chef that specializes in sauces. */
public class SauceChef extends AbstractChef{

  @Override
  protected Set<PreparationType> getSkills() {
    return Set.of(PreparationType.SAUTE, PreparationType.SAUCE, PreparationType.GRAVY);
  }
}
