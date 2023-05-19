package com.pdsl.scaling.kitchen;

import com.pdsl.scaling.recipe.Repast;
import com.pdsl.scaling.kitchen.chefs.StationChef;
import com.pdsl.scaling.ingredients.Ingredient;
import com.pdsl.scaling.orders.Order;
import com.pdsl.scaling.recipe.RepastRecipeType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

/** A meal preparation area that processes orders and turns them into meals. */
public abstract class Kitchen {

  private final Stack<Dishes> availableDishes = initDishes();

  private static Stack<Dishes> initDishes() {
      Stack<Dishes> newDishes = new Stack<>();
      for (int i=0; i < 100; i++) {
        newDishes.push(new Dishes());
      }
    return newDishes;
  }
  /** Provides the highest ranking member of the brigade kitchen who makes sauces. */
  public abstract StationChef getSaucier();
  /** Provides a station chef that processes most types of meat. */
  public abstract StationChef getBoucher();
  /** Provides the chef that will cook fish. */
  public abstract StationChef getPoissonnier();
  /** Provides a chef that will roast ingredients. */
  public abstract StationChef getRotisseur();
  /** Provides a chef that will fry ingredients. */
  public abstract StationChef getFriturier();
  /** Provides a chef that will grill ingredients. */
  public abstract StationChef getGrillardin();
  /** Provides a chef that prepares pantry ingredients. */
  public abstract StationChef getGardeManger();
  /** Provides a chef that creates pastries and deserts. */
  public abstract StationChef getPattisier();
  /** Provides a relief cook that can be used when other chefs are occupied. */
  public abstract StationChef getChefDeTournant();
  /** Provides a chef that cooks vegetables and soups. */
  public abstract StationChef getEntremetier();
  /** Provides a porter that will prepare raw ingredients for the station chefs. */
  public abstract KitchenPorter getPorter();
  /** Provides a dishwasher for cleaning dirty kitchen utinsils. */
  public abstract DishWasher getEscuelerie();

  /** Create a collection of repasts to serve a customer from an order. */
  public Collection<Repast> prepareMeal(Order order) {

    List<Repast> repasts = new ArrayList<>(order.getRepasts().size());
    for (RepastRecipeType repastRecipeType : order.getRepasts()) {
      int quality = 0;
      for (Ingredient ingredient : KitchenPorter.getIngredientsForRepast(repastRecipeType)) {
        switch(ingredient) {
          case CHOPPED_ONIONS:
          case BROTH:
          case LEEKS:
          case BOILED_POTATOES:
            quality += getEntremetier().processIngredient(ingredient);
            break;
          case COOKED_OYSTERS:
            quality += getPoissonnier().processIngredient(ingredient);
            break;
          case MEAT_GRAVY:
          case SAUTEED_ONIONS:
          case SAVORY_SAUCE:
            quality += getSaucier().processIngredient(ingredient);
            break;
          case ROASTED_VEGETABLES:
            quality += getRotisseur().processIngredient(ingredient);
            break;
          case SLICED_MEAT:
            quality += getBoucher().processIngredient(ingredient);
            break;
          case SNAILS:
            quality += getFriturier().processIngredient(ingredient);
          case SWEETENED_ALMOND_FLOUR:
            quality += getPattisier().processIngredient(ingredient);
        }
      }
      repasts.add(Repast.create(repastRecipeType, quality, availableDishes.pop()));
    }
    return repasts;
  }

  /** Clean dirty dishes in the kitchen. */
  public void washDishes(Collection<Dishes> dishes) {
    dishes.stream().forEach(d -> {
      getEscuelerie().washDishes(d);
      availableDishes.push(d);
    });
  }
}
