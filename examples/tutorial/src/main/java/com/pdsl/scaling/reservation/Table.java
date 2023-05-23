package com.pdsl.scaling.reservation;

import com.pdsl.scaling.customers.Customer;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

/**
 * A location for a customer to sit and receive meals.
 */
public class Table {
  private final Map<OperatingHours, Customer> schedule = new HashMap<>();
  private boolean isSeated = false;

  /** Whether or not a table is available at a specific time. */
  public boolean scheduleTable(OperatingHours operatingHours, Customer customer) {
    if (schedule.containsKey(operatingHours)) {
      return false;
    }
    schedule.put(operatingHours, customer);
    return true;
  }

  /**
   *
   * The times at which the table is free for a customer.
   * @return the hours that the table is available
   */
  public Collection<OperatingHours> getAvailableAppointments() {
    Set<OperatingHours> availableHours = EnumSet.allOf(OperatingHours.class);
    availableHours.removeAll(schedule.keySet());
    return availableHours;
  }

  /**
   * Returns whether the table has been properly prepared for a customer.
   * @param operatingHours the time at which the table is to be used
   * @param customer the customer to seat
   * @return true if the customer may be seated at the table
   */
  public boolean tableReadyForCustomer(OperatingHours operatingHours, Customer customer) {
    if (schedule.containsKey(operatingHours)) {
      Customer c = schedule.get(operatingHours);
      if (c == null) { return false; }
      isSeated = c.equals(customer);
      return isSeated;
    }
    return false;
  }

  /** Returns if the table is in use. */
  public boolean isCustomerCurrentlySeated() {
    return isSeated;
  }

  /**
   * Opens the tables schedule to be used by a different customer.
   * <p>
   * If a customer has multiple appointments only the most earliest will be removed.
   */
  public void clearCustomersTable(Customer customer) {
    Optional<OperatingHours> foundCustomer = schedule.entrySet().stream()
        .filter(e -> e.getValue().equals(customer))
        .map(Entry::getKey)
        .findFirst();
    if (foundCustomer.isPresent()) {
      schedule.remove(foundCustomer);
      isSeated = false;
    }
  }
}
