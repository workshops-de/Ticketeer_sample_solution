package de.workshops.ticketeer.ticketvendor;

import lombok.Builder;

@Builder
public record TicketReservationRequest(String category, Integer number) {
}
