package com.pdsl.scaling.meals;

import com.pdsl.scaling.kitchen.Dishes;
import com.pdsl.scaling.recipe.Repast;

public abstract class AbstractRepast implements Repast {
  private final int quality;
  private Dishes dishes;

  AbstractRepast(int quality, Dishes dishes) {
    this.quality = quality;
    this.dishes = dishes;
  }

  @Override
  public int getQuality() {
    return quality;
  }

  @Override
  public Dishes getDishes() {
    return dishes;
  }


}
