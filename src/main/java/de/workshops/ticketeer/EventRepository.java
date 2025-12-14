package de.workshops.ticketeer;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
class EventRepository {

  private List<EventDto> events;

  @PostConstruct
  public void initialize() {
    events = List.of(
        new EventDto(1L, "Rock Concert", "Stadium A",
            LocalDateTime.of(2024, 9, 15, 20, 0),
            new BigDecimal("49.99")),
        new EventDto(2L, "Jazz Festival", "Park B",
            LocalDateTime.of(2024, 10, 5, 18, 30),
            new BigDecimal("29.99")),
        new EventDto(3L, "Classical Night", "Concert Hall C",
            LocalDateTime.of(2024, 11, 20, 19, 0),
            new BigDecimal("39.99"))
    );
  }

  List<EventDto> getEvents() {
    return events;
  }
}
