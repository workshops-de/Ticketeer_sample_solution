package de.workshops.ticketeer.ticketvendor;

import de.workshops.ticketeer.ticketvendor.model.Price;
import java.time.LocalDateTime;
import java.util.UUID;

public record TicketReservationResult(UUID id, LocalDateTime date, Integer number,
                                      Price singlePrice, Price totalPrice) {

}
