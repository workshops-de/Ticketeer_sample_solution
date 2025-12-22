package de.workshops.ticketeer.reservation;

import java.time.LocalDate;
import java.util.UUID;

public record ReservationDto(
    UUID reservationNumber,
    Long eventId,
    LocalDate reservedAt,
    LocalDate expiresAt,
    int quantity,
    ReservationStatus status) {
}