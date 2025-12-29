package de.workshops.ticketeer.ticketvendor;

public interface TicketReservationUseCase {

  TicketReservationResult reserveTickets(TicketReservationCommand ticketReservationCommand)
      throws TicketException;
}
