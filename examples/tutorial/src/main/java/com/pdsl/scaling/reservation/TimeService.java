package com.pdsl.scaling.reservation;

/**
 * A service that serves as a clock for a restaurant.
 */
public class TimeService {

  private int timeSegment = 0;

  /** Returns the current time at the restaurant. */
  public OperatingHours getTime() {
    return OperatingHours.values()[timeSegment];
  }

  /** Moves time to the next segment for the restaurant. */
  public void advanceTime() {
    if (timeSegment == OperatingHours.values().length - 1) {
      timeSegment = 0;
    }
    timeSegment++;
  }

}
