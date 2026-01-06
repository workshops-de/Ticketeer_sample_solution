package de.workshops.ticketeer.ticketvendor.model;

import lombok.Builder;

@Builder
public record TicketReservationRequest(String category, Integer number) {
}
