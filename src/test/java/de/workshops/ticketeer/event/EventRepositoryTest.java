package de.workshops.ticketeer.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
class EventRepositoryTest {

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
//  As an alternative to programmatically saving an event to the database, you can use the @Sql annotation to load SQL scripts into the database before each test method is executed.
//  @Sql(statements = "INSERT INTO event (id, name, venue, base_price, capacity, start_date_time, remaining_tickets, status) VALUES (2, 'Another sample event', 'Some venue', 50.0, 100, NOW() + INTERVAL '10 days', 100, 'PUBLISHED')")
//  @Sql(statements = "DELETE FROM event", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findEventById() {
        var savedEvent = eventRepository.save(
                Event.builder()
                        .name("Another sample event")
                        .venue("Some venue")
                        .basePrice(BigDecimal.valueOf(50.0))
                        .capacity(100)
                        .startDateTime(LocalDateTime.now().plusDays(10))
                        .remainingTickets(100)
                        .status(EventStatus.PUBLISHED)
                        .build()
        );

        var firstEvent = eventRepository.findById(savedEvent.getId());

        assertTrue(firstEvent.isPresent());
        assertEquals("Another sample event", firstEvent.get().getName());
        assertEquals(EventStatus.PUBLISHED, firstEvent.get().getStatus());
    }
}
