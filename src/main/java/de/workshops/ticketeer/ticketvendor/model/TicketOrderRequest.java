package de.workshops.ticketeer.ticketvendor.model;

import lombok.Builder;

@Builder
public record TicketOrderRequest(String category, Integer number) {
}
