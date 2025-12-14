package de.workshops.ticketeer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventDto(
    long id,
    String name,
    String venue,
    LocalDateTime startDateTime,
    BigDecimal basePrice
) {

}
