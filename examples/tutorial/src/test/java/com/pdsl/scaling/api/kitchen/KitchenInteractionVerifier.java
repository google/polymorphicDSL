package com.pdsl.scaling.api.kitchen;

import com.pdsl.scaling.kitchen.DishWasher;
import com.pdsl.scaling.kitchen.Kitchen;
import com.pdsl.scaling.kitchen.KitchenPorter;
import com.pdsl.scaling.kitchen.chefs.ButcherChef;
import com.pdsl.scaling.kitchen.chefs.FishChef;
import com.pdsl.scaling.kitchen.chefs.FryChef;
import com.pdsl.scaling.kitchen.chefs.GrillChef;
import com.pdsl.scaling.kitchen.chefs.PantryChef;
import com.pdsl.scaling.kitchen.chefs.PastryChef;
import com.pdsl.scaling.kitchen.chefs.ReliefCook;
import com.pdsl.scaling.kitchen.chefs.RoastChef;
import com.pdsl.scaling.kitchen.chefs.SauceChef;
import com.pdsl.scaling.kitchen.chefs.StationChef;
import com.pdsl.scaling.api.kitchen.chefs.StationChefInteractionVerifier;
import com.pdsl.scaling.kitchen.chefs.VegetableChef;

public class KitchenInteractionVerifier extends Kitchen {

  private static final StationChef SAUCE_CHEF = new StationChefInteractionVerifier(new SauceChef());
  private static final StationChef BUTCHER =  new StationChefInteractionVerifier(new ButcherChef());
  private static final StationChef SEAFOOD = new StationChefInteractionVerifier(new FishChef());
  private static final StationChef ROAST = new StationChefInteractionVerifier(new RoastChef());
  private static final StationChef FRY = new StationChefInteractionVerifier(new FryChef());
  private static final StationChef GRILL = new StationChefInteractionVerifier(new GrillChef());
  private static final StationChef PANTRY = new StationChefInteractionVerifier(new PantryChef());
  private static final StationChef PASTRY = new StationChefInteractionVerifier(new PastryChef());
  private static final StationChef RELIEF = new StationChefInteractionVerifier(new ReliefCook());
  private static final StationChef VEGETABLE = new StationChefInteractionVerifier(new VegetableChef());
  @Override
  public StationChef getSaucier() {
    return SAUCE_CHEF;
  }

  @Override
  public StationChef getBoucher() {
    return BUTCHER;
  }

  @Override
  public StationChef getPoissonnier() {
    return SEAFOOD;
  }

  @Override
  public StationChef getRotisseur() {
    return ROAST;
  }

  @Override
  public StationChef getFriturier() {
    return FRY;
  }

  @Override
  public StationChef getGrillardin() {
    return GRILL;
  }

  @Override
  public StationChef getGardeManger() {
    return PANTRY;
  }

  @Override
  public StationChef getPattisier() {
    return PASTRY;
  }

  @Override
  public StationChef getChefDeTournant() {
    return RELIEF;
  }

  @Override
  public StationChef getEntremetier() {
    return VEGETABLE;
  }

  @Override
  public KitchenPorter getPorter() {
    return new KitchenPorter();
  }

  @Override
  public DishWasher getEscuelerie() {
    return new DishWasher();
  }
}
