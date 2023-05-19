package com.pdsl.scaling.orders;

import com.pdsl.scaling.recipe.RepastRecipeType;
import com.pdsl.scaling.reservation.Table;
import java.util.Collection;

public interface Order {

  Collection<RepastRecipeType> getRepasts();
  int getPriority();
  Table getTable();
}
