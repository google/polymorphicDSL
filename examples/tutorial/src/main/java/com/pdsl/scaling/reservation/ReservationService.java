package com.pdsl.scaling.reservation;

import com.pdsl.scaling.customers.Customer;
import java.util.Collection;
import java.util.Optional;

public class ReservationService {

  private final Collection<Table> tables;

  public ReservationService(Collection<Table> tables) {
    this.tables = tables;
  }
  public boolean scheduleReservation(OperatingHours appointment, Customer customer) {
    Optional<Table> availableTable =tables.stream()
        .filter(t -> t.getAvailableAppointments().contains(appointment))
        .findAny();
    if (availableTable.isPresent()) {
      availableTable.get().scheduleTable(appointment, customer);
      return true;
    }
    return false;
  }

  public Optional<Table> receiveCustomer(OperatingHours appointment, Customer customer) {
    return tables.stream()
        .filter(t -> t.tableReadyForCustomer(appointment, customer))
        .findAny();
  }

}
