package com.pdsl.scaling.meals;

import com.pdsl.scaling.MealType;
import com.pdsl.scaling.kitchen.Dishes;
import com.pdsl.scaling.recipe.RepastRecipeType;
import java.math.BigDecimal;

/** A famous French Entree that is a meat dish. */
public class ColdCuts extends AbstractRepast {

  public ColdCuts(int quality, Dishes dishes) {
    super(quality, dishes);
  }

  @Override
  public BigDecimal cost() {
    return new BigDecimal("24.99");
  }

  @Override
  public String getName() {
    return "Une Assiette de Charcuterie";
  }

  @Override
  public MealType getMealType() {
    return MealType.ENTREE;
  }

  @Override
  public RepastRecipeType getRepastRecipeType() {
    return RepastRecipeType.COLD_CUTS;
  }
}
