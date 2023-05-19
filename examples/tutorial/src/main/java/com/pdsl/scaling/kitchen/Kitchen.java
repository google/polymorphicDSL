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

public abstract class Kitchen {

  private final Stack<Dishes> availableDishes = initDishes();

  private static Stack<Dishes> initDishes() {
      Stack<Dishes> newDishes = new Stack<>();
      for (int i=0; i < 100; i++) {
        newDishes.push(new Dishes());
      }
    return newDishes;
  }
  public abstract StationChef getSaucier();
  public abstract StationChef getBoucher();
  public abstract StationChef getPoissonnier();
  public abstract StationChef getRotisseur();
  public abstract StationChef getFriturier();
  public abstract StationChef getGrillardin();
  public abstract StationChef getGardeManger();
  public abstract StationChef getPattisier();
  public abstract StationChef getChefDeTournant();
  public abstract StationChef getEntremetier();
  public abstract KitchenPorter getPorter();
  public abstract DishWasher getEscuelerie();




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

  public void washDishes(Collection<Dishes> dishes) {
    dishes.stream().forEach(d -> {
      getEscuelerie().washDishes(d);
      availableDishes.push(d);
    });
  }
}
