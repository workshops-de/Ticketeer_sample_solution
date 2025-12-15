package de.workshops.ticketeer.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

@DataJpaTest
@Testcontainers
class EventRepositoryTest {

  @Container
  @ServiceConnection
  static PostgreSQLContainer postgreSQLContainer
      = new PostgreSQLContainer("postgres:latest")
      .withReuse(true);

  @Autowired
  private EventRepository eventRepository;

  @Test
  void saveEvent() {
    assertEquals(0, eventRepository.count());

    eventRepository.save(
        Event.builder()
            .name("Sample event")
            .venue("Some venue")
            .basePrice(BigDecimal.valueOf(50.0))
            .capacity(100)
            .startDateTime(LocalDateTime.now().plusDays(10))
            .remainingTickets(100)
            .build()
    );

    assertEquals(1, eventRepository.count());
  }

  @Test
  void findEventById() {
    var savedEvent = eventRepository.save(
        Event.builder()
            .name("Another sample event")
            .venue("Some venue")
            .basePrice(BigDecimal.valueOf(50.0))
            .capacity(100)
            .startDateTime(LocalDateTime.now().plusDays(10))
            .remainingTickets(100)
            .build()
    );

    var firstEvent = eventRepository.findById(savedEvent.getId());

    assertTrue(firstEvent.isPresent());
    assertEquals("Another sample event", firstEvent.get().getName());
  }
}
