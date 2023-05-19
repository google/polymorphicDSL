package com.pdsl.scaling.kitchen.chefs;

import com.pdsl.scaling.ingredients.PreparationType;
import java.util.Set;

public class FryChef extends AbstractChef {

  @Override
  protected Set<PreparationType> getSkills() {
    return Set.of(PreparationType.FRY);
  }
}
