package com.pdsl.scaling.reservation;

import com.pdsl.scaling.customers.Customer;
import java.util.Collection;
import java.util.Optional;

/** A service that schedules tables to be available for potential customers. */
public class ReservationService {

  private final Collection<Table> tables;

  public ReservationService(Collection<Table> tables) {
    this.tables = tables;
  }

  /**
   * Returns whether or not a customer is able to schedule a table at a restaurant at the specified
   * time.
   * @param appointment when the customer wants to have a table
   * @param customer the customer that wants the appointment
   * @return whether or not the customer is succesful scheduling a reservation
   */
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

  /**
   * Whether or not the restaurant accepts the cusotmer for seating at the specified time.
   * @param appointment when the customer arrived
   * @param customer the customer that wants to be seated
   * @return an optional containing a table if the customer is accepted, otherwise an empty optional
   */
  public Optional<Table> receiveCustomer(OperatingHours appointment, Customer customer) {
    return tables.stream()
        .filter(t -> t.tableReadyForCustomer(appointment, customer))
        .findAny();
  }

}
