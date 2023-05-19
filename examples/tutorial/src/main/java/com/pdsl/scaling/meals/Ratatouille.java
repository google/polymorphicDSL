package com.pdsl.scaling.meals;

import com.pdsl.scaling.MealType;
import com.pdsl.scaling.kitchen.Dishes;
import com.pdsl.scaling.recipe.RepastRecipeType;
import java.math.BigDecimal;

public class Ratatouille extends AbstractRepast {

  public Ratatouille(int quality, Dishes dishes) {
    super(quality, dishes);
  }
  @Override
  public BigDecimal cost() {
    return new BigDecimal("13.79");
  }

  @Override
  public String getName() {
    return "Ratatouille";
  }

  @Override
  public MealType getMealType() {
    return MealType.ENTREE;
  }

  @Override
  public RepastRecipeType getRepastRecipeType() {
    return RepastRecipeType.RATATOUILLE;
  }
}
