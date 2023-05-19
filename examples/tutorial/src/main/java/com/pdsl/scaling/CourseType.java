package com.pdsl.scaling;

import java.util.Collection;
import java.util.List;

/**
 * A mutli-course meal that provides the types of meals to be provided.
 */
public enum CourseType {
  THREE(List.of(MealType.ENTREE, MealType.APPETIZER, MealType.DESSERT)),
  FOUR(List.of(MealType.SOUP, MealType.ENTREE, MealType.APPETIZER, MealType.DESSERT));

  private final Collection<MealType> mealTypes;
  CourseType(Collection<MealType> mealTypes) {
    this.mealTypes = mealTypes;
  }

  /**
   * Returns the types of meals to serve in each course.
   *
   * @return collection of mealtypes
   */
  public Collection<MealType> getMealTypes() {
    return mealTypes;
  }

}
