package com.pdsl.scaling.kitchen;

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
