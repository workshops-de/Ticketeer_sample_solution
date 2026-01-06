package de.workshops.ticketeer.ticketvendor.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public record TicketOrderResponse(UUID id, OffsetDateTime date, Integer number, Price ticketPrice,
                                  Price totalPrice) {
}
