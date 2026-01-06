package de.workshops.ticketeer.ticketvendor.model;

public record Ticket(Integer totalNumber, Integer reserved, Integer ordered, String category,
                     Price price) {
}
