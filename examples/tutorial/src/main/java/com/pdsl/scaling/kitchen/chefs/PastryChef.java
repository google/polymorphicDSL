package com.pdsl.scaling.kitchen.chefs;

import com.pdsl.scaling.ingredients.PreparationType;
import java.util.Set;

/** A chef that specializes in pastries. */
public class PastryChef extends AbstractChef {

  @Override
  protected Set<PreparationType> getSkills() {
    return Set.of(PreparationType.BAKE, PreparationType.PASTRY, PreparationType.DESSERT);
  }
}
