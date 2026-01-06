package de.workshops.ticketeer.ticketvendor.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record Event(UUID id, String name, List<Artist> artists, EventCategory category,
                    OffsetDateTime date, Location location, List<Ticket> tickets,
                    EventStatus status) {
}
