 package com.pdsl.scaling.ingredients;

/** Ingredients used to prepare repasts in the kitchen. */
public enum Ingredient {

  BOILED_POTATOES("Boiled Potatoes"),
  BROTH("Broth"),
  CHOPPED_ONIONS("Chopped Onions"),
  COOKED_OYSTERS("Cooked Oysters"),
  LEEKS("Leeks"),
  MEAT_GRAVY("Meat Gravy"),
  ROASTED_VEGETABLES("Roasted Vegetables"),
  SAUTEED_ONIONS("Sauteed Onions"),
  SAVORY_SAUCE("Savory Sauce"),
  SLICED_MEAT("Sliced Meat"),
  SNAILS("Snails"),
  SWEET_ROLL("Sweet Roll"),
  SWEETENED_ALMOND_FLOUR("Sweetened Coconuts");
  private final String name;
  Ingredient(String name)  {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public PreparationType getPreparationType() {
    switch (this) {
      case BROTH:
        return PreparationType.SOUPS;
      case LEEKS:
      case CHOPPED_ONIONS:
        return PreparationType.COLD;
      case COOKED_OYSTERS:
        return PreparationType.SEAFOOD;
      case MEAT_GRAVY:
        return PreparationType.GRAVY;
      case ROASTED_VEGETABLES:
        return PreparationType.ROAST;
      case SAUTEED_ONIONS:
        return PreparationType.SAUTE;
      case SAVORY_SAUCE:
        return PreparationType.SAUCE;
      case SLICED_MEAT:
        return PreparationType.BUTCHER;
      case SNAILS:
        return PreparationType.FRY;
      case SWEETENED_ALMOND_FLOUR:
        return PreparationType.DESSERT;
      case SWEET_ROLL:
        return PreparationType.PASTRY;
      case BOILED_POTATOES:
        return  PreparationType.VEGETABLE;
    }
    throw new IllegalArgumentException("No implementation for " + this.name);
  }
}
