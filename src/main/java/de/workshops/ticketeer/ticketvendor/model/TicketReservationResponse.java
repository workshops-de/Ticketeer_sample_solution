package de.workshops.ticketeer.ticketvendor.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public record TicketReservationResponse(UUID id, OffsetDateTime validUntil, Integer number,
                                        Price ticketPrice, Price totalPrice) {
}
