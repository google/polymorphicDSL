package com.pdsl.scaling.api.kitchen.chefs;

import com.pdsl.scaling.ingredients.Ingredient;
import com.pdsl.scaling.kitchen.chefs.StationChef;
import java.util.Collection;
import java.util.Optional;

public class StationChefInteractionVerifier implements StationChef {

  private final StationChef stationChef;

  private Optional<Integer> lastFoodScore = Optional.empty();

  public StationChefInteractionVerifier(StationChef stationChef) {
    this.stationChef = stationChef;
  }

  public Optional<Integer> getLastFoodScore() {
    return lastFoodScore;
  }

  @Override
  public int processIngredient(Ingredient ingredient) {
    lastFoodScore = Optional.of(stationChef.processIngredient(ingredient));
    return lastFoodScore.get();
  }

  @Override
  public int processIngredients(Collection<Ingredient> ingredients) {
    lastFoodScore = Optional.of(stationChef.processIngredients(ingredients));
    return lastFoodScore.get();
  }
}
