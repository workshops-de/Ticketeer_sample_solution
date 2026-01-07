package de.workshops.ticketeer.reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ReservationDto(
    UUID reservationNumber,
    Long eventId,
    LocalDate reservedAt,
    LocalDate expiresAt,
    int quantity,
    BigDecimal singlePrice,
    ReservationStatus status) {
}
