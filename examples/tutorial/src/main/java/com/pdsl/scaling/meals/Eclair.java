package com.pdsl.scaling.meals;

import com.pdsl.scaling.MealType;
import com.pdsl.scaling.kitchen.Dishes;
import com.pdsl.scaling.recipe.RepastRecipeType;
import java.math.BigDecimal;

public class Eclair extends AbstractRepast {


  public Eclair(int quality, Dishes dishes) {
    super(quality, dishes);
  }
  @Override
  public BigDecimal cost() {
    return new BigDecimal("12.99");
  }

  @Override
  public String getName() {
    return "Éclair";
  }

  @Override
  public MealType getMealType() {
    return MealType.DESSERT;
  }

  @Override
  public RepastRecipeType getRepastRecipeType() {
    return RepastRecipeType.ECLAIR;
  }
}
