package de.workshops.ticketeer.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

record EventDto(
    String name,
    String venue,
    LocalDateTime startDateTime,
    BigDecimal basePrice,
    String currency,
    EventStatus status) {
}