package com.pdsl.scaling.reservation;

public class TimeService {

  private int timeSegment = 0;

  public OperatingHours getTime() {
    return OperatingHours.values()[timeSegment];
  }

  public void advanceTime() {
    if (timeSegment == OperatingHours.values().length - 1) {
      timeSegment = 0;
    }
    timeSegment++;
  }

}
