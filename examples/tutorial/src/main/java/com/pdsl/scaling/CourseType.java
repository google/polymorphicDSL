package com.pdsl.scaling;

import java.util.Collection;
import java.util.List;

public enum CourseType {
  THREE(List.of(MealType.ENTREE, MealType.APPETIZER, MealType.DESSERT)),
  FOUR(List.of(MealType.SOUP, MealType.ENTREE, MealType.APPETIZER, MealType.DESSERT));

  private final Collection<MealType> mealTypes;
  CourseType(Collection<MealType> mealTypes) {
    this.mealTypes = mealTypes;
  }

  public Collection<MealType> getMealTypes() {
    return mealTypes;
  }

}
