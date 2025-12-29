package de.workshops.ticketeer.ticketvendor;

import de.workshops.ticketeer.ticketvendor.model.EventStatus;
import de.workshops.ticketeer.ticketvendor.model.Price;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
class TicketService implements TicketOrderUseCase, TicketReservationUseCase {

  private final EventService eventService;
  private final TicketUpdatePort ticketUpdatePort;

  TicketService(EventService eventService, TicketLoadPort ticketLoadPort,
      TicketUpdatePort ticketUpdatePort) {
    this.eventService = eventService;
    this.ticketUpdatePort = ticketUpdatePort;
  }

  @Override
  public TicketOrderResult orderTickets(TicketOrderCommand ticketOrderCommand) {
    var event = eventService.getById(ticketOrderCommand.eventId());

    if (EventStatus.SOLD_OUT == event.getStatus()) {
      throw new TicketException("The event is sold out", "");
    }

    var ticket = event.getTickets().stream()
        .filter(t -> Objects.equals(t.getCategory(), ticketOrderCommand.category()))
        .findFirst()
        .orElseThrow(() -> new TicketException("No ticket for event in given category found", ""));

    var numberOfBookableTickets = ticket.getOrdered() - ticket.getReserved();
    if (numberOfBookableTickets <= 0) {
      throw new TicketException("No tickets for event available", "");
    }
    if (numberOfBookableTickets < ticketOrderCommand.amount()) {
      throw new TicketException("Not enough tickets available", "");
    }

    ticketUpdatePort.updateOrderedTickets(ticketOrderCommand.eventId(), ticketOrderCommand.amount(),
        ticketOrderCommand.category());
    eventService.updateEventStatus(ticketOrderCommand.eventId());

    var singlePrice = ticket.getPrice();
    var totalPrice = new Price(
        singlePrice.amount().multiply(BigDecimal.valueOf(ticketOrderCommand.amount())),
        singlePrice.currency());

    return new TicketOrderResult(UUID.randomUUID(), LocalDateTime.now(),
        ticketOrderCommand.amount(), singlePrice, totalPrice);
  }

  @Override
  public TicketReservationResult reserveTickets(TicketReservationCommand ticketReservationCommand)
      throws TicketException {
    var event = eventService.getById(ticketReservationCommand.eventId());

    if (EventStatus.SOLD_OUT == event.getStatus()) {
      throw new TicketException("The event is sold out", "");
    }

    var ticket = event.getTickets().stream()
        .filter(t -> Objects.equals(t.getCategory(), ticketReservationCommand.category()))
        .findFirst()
        .orElseThrow(() -> new TicketException("No ticket for event in given category found", ""));

    var numberOfBookableTickets = ticket.getOrdered() - ticket.getReserved();
    if (numberOfBookableTickets <= 0) {
      throw new TicketException("No tickets for event available", "");
    }
    if (numberOfBookableTickets < ticketReservationCommand.amount()) {
      throw new TicketException("Not enough tickets available", "");
    }

    ticketUpdatePort.updateReservedTickets(ticketReservationCommand.eventId(),
        ticketReservationCommand.amount(), ticketReservationCommand.category());
    eventService.updateEventStatus(ticketReservationCommand.eventId());

    var singlePrice = ticket.getPrice();
    var totalPrice = new Price(
        singlePrice.amount().multiply(BigDecimal.valueOf(ticketReservationCommand.amount())),
        singlePrice.currency());

    return new TicketReservationResult(UUID.randomUUID(), LocalDateTime.now(),
        ticketReservationCommand.amount(), singlePrice, totalPrice);
  }
}
