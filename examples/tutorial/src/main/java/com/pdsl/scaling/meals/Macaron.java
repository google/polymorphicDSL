package com.pdsl.scaling.meals;

import com.pdsl.scaling.MealType;
import com.pdsl.scaling.kitchen.Dishes;
import com.pdsl.scaling.recipe.RepastRecipeType;
import java.math.BigDecimal;

/** A famous french dessert made from almond flour. */
public class Macaron extends AbstractRepast {

  public Macaron(int quality, Dishes dishes) {
    super(quality, dishes);
  }
  @Override
  public BigDecimal cost() {
    return new BigDecimal("6.99");
  }

  @Override
  public String getName() {
    return "Macarons";
  }

  @Override
  public MealType getMealType() {
    return MealType.DESSERT;
  }

  @Override
  public RepastRecipeType getRepastRecipeType() {
    return RepastRecipeType.MACARON;
  }
}
