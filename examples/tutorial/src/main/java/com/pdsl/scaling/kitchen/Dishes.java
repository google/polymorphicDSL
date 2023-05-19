package com.pdsl.scaling.kitchen;

/** Generic utinsils that are used to serve food. */
public class Dishes {
  private boolean isDirty = true;

  public void clean() {
    isDirty = false;
  }

  public void serveWithFood() {
    isDirty = true;
  }

  public boolean isDirty() {
    return isDirty;
  }

}
