package de.workshops.ticketeer.ticketvendor;

public interface TicketOrderUseCase {

  TicketOrderResult orderTickets(TicketOrderCommand ticketOrderCommand) throws TicketException;
}
