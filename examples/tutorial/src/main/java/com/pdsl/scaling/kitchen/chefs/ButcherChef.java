package com.pdsl.scaling.kitchen.chefs;

import com.pdsl.scaling.ingredients.PreparationType;
import java.util.Set;

/** A butcher that prepares several types of meat. */
public class ButcherChef extends AbstractChef {


  @Override
  protected Set<PreparationType> getSkills() {
    return Set.of(PreparationType.BUTCHER);
  }
}
