package com.pdsl.scaling.meals;

import com.pdsl.scaling.MealType;
import com.pdsl.scaling.kitchen.Dishes;
import com.pdsl.scaling.recipe.RepastRecipeType;
import java.math.BigDecimal;

/** A famous french soup made from leeks and potatoes. */
public class LeekAndPotatoSoup extends AbstractRepast {

  public LeekAndPotatoSoup(int quality, Dishes dishes) {
    super(quality, dishes);
  }
  @Override
  public BigDecimal cost() {
    return new BigDecimal("9.99");
  }

  @Override
  public String getName() {
    return "Leek and Potato Soup";
  }

  @Override
  public MealType getMealType() {
    return MealType.SOUP;
  }

  @Override
  public RepastRecipeType getRepastRecipeType() {
    return RepastRecipeType.LEEK_AND_POTATO_SOUP;
  }
}
