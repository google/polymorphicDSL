package com.pdsl.scaling.meals;

import com.pdsl.scaling.MealType;
import com.pdsl.scaling.kitchen.Dishes;
import com.pdsl.scaling.recipe.RepastRecipeType;
import java.math.BigDecimal;

public class Oysters extends AbstractRepast {

  public Oysters(int quality, Dishes dishes) {
    super(quality, dishes);
  }
  @Override
  public BigDecimal cost() {
    return new BigDecimal("7.99");
  }

  @Override
  public String getName() {
    return "Huitres";
  }

  @Override
  public MealType getMealType() {
    return MealType.APPETIZER;
  }
  @Override
  public RepastRecipeType getRepastRecipeType() {
    return RepastRecipeType.OYSTERS;
  }

}
