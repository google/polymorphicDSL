package com.pdsl.scaling.kitchen;

import com.pdsl.scaling.kitchen.chefs.FishChef;
import com.pdsl.scaling.recipe.Repast;
import com.pdsl.scaling.kitchen.chefs.StationChef;
import com.pdsl.scaling.ingredients.Ingredient;
import com.pdsl.scaling.kitchen.chefs.ButcherChef;
import com.pdsl.scaling.kitchen.chefs.FryChef;
import com.pdsl.scaling.kitchen.chefs.GrillChef;
import com.pdsl.scaling.kitchen.chefs.PantryChef;
import com.pdsl.scaling.kitchen.chefs.PastryChef;
import com.pdsl.scaling.kitchen.chefs.ReliefCook;
import com.pdsl.scaling.kitchen.chefs.RoastChef;
import com.pdsl.scaling.kitchen.chefs.SauceChef;
import com.pdsl.scaling.kitchen.chefs.VegetableChef;

public class FullKitchen extends Kitchen {

  private static final StationChef saucier = new SauceChef();
  private static final StationChef boucher = new ButcherChef();
  private static final StationChef rotisseur = new RoastChef();
  private static final StationChef friturier = new FryChef();
  private static final StationChef grillardin = new GrillChef();
  private static final StationChef gardeManger = new PantryChef();
  private static final StationChef pattisier = new PastryChef();
  private static final StationChef chefDeTournant = new ReliefCook();
  private static final StationChef entremetier = new VegetableChef();
  private static final KitchenPorter porter = new KitchenPorter();
  private static final StationChef poissonier = new FishChef();
  private static final DishWasher escuelerie = new DishWasher();
  @Override
  public StationChef getSaucier() {
    return saucier;
  }

  @Override
  public StationChef getBoucher() {
    return boucher;
  }

  @Override
  public StationChef getPoissonnier() {
    return poissonier;
  }

  @Override
  public StationChef getRotisseur() {
    return rotisseur;
  }

  @Override
  public StationChef getFriturier() {
    return friturier;
  }

  @Override
  public StationChef getGrillardin() {
    return grillardin;
  }

  @Override
  public StationChef getGardeManger() {
    return gardeManger;
  }

  @Override
  public StationChef getPattisier() {
    return pattisier;
  }

  @Override
  public StationChef getChefDeTournant() {
    return chefDeTournant;
  }

  @Override
  public StationChef getEntremetier() {
    return entremetier;
  }

  @Override
  public KitchenPorter getPorter() {
    return porter;
  }

  @Override
  public DishWasher getEscuelerie() {
    return escuelerie;
  }
}
