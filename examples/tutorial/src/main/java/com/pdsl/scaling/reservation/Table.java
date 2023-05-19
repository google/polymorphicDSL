package com.pdsl.scaling.reservation;

import com.pdsl.scaling.customers.Customer;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

public class Table {
  private final Map<OperatingHours, Customer> schedule = new HashMap<>();
  private boolean isSeated = false;

  public boolean scheduleTable(OperatingHours operatingHours, Customer customer) {
    if (schedule.containsKey(operatingHours)) {
      return false;
    }
    schedule.put(operatingHours, customer);
    return true;
  }

  public Collection<OperatingHours> getAvailableAppointments() {
    Set<OperatingHours> availableHours = EnumSet.allOf(OperatingHours.class);
    availableHours.removeAll(schedule.keySet());
    return availableHours;
  }

  public boolean tableReadyForCustomer(OperatingHours operatingHours, Customer customer) {
    if (schedule.containsKey(operatingHours)) {
      Customer c = schedule.get(operatingHours);
      if (c == null) { return false; }
      isSeated = c.equals(customer);
      return isSeated;
    }
    return false;
  }

  public boolean isCustomerCurrentlySeated() {
    return isSeated;
  }

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
