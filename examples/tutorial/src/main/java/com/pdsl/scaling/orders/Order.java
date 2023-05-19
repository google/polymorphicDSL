package com.pdsl.scaling.orders;

import com.pdsl.scaling.recipe.RepastRecipeType;
import com.pdsl.scaling.reservation.Table;
import java.util.Collection;

/**
 * A requrest for specific food made by a customer.
 */
public interface Order {

  /** Returns the requested repasts a customer requested. */
  Collection<RepastRecipeType> getRepasts();
  /** Returns how urgent the order needs to be fulfilled. */
  int getPriority();
  /** Returns the table from which the order was placed. */
  Table getTable();
}
