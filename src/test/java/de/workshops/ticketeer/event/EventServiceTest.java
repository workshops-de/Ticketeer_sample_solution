package de.workshops.ticketeer.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.workshops.ticketeer.util.AbstractPostgreSQLTestcontainersTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Import(EventService.class)
class EventServiceTest extends AbstractPostgreSQLTestcontainersTest {
    @Autowired
    EventService eventService;

    @Test
    @Sql(statements = "INSERT INTO event (id, name, venue, base_price, capacity, start_date_time, remaining_tickets, status) VALUES (2, 'Another sample event', 'Some venue', 50.0, 100, NOW() + INTERVAL '10 days', 100, 'DRAFT')")
    void testUpdateEventStatus_Ok() {
        var event = eventService.getEvent(2L);
        assertNotNull(event);
        assertEquals(EventStatus.DRAFT, event.status());

        eventService.updateEventStatus(2L, new EventStatusDto("PUBLISHED"));
        event = eventService.getEvent(2L);
        assertNotNull(event);
        assertEquals(EventStatus.PUBLISHED, event.status());
    }

    @Test
    @Sql(statements = "INSERT INTO event (id, name, venue, base_price, capacity, start_date_time, remaining_tickets, status) VALUES (2, 'Another sample event', 'Some venue', 50.0, 100, NOW() + INTERVAL '10 days', 100, 'PUBLISHED')")
    void testUpdateEventStatus_invalidTransition() {
        var event = eventService.getEvent(2L);
        assertNotNull(event);
        assertEquals(EventStatus.PUBLISHED, event.status());

        assertThrows(InvalidTransitionException.class, () -> eventService.updateEventStatus(2L, new EventStatusDto("PUBLISHED")));
    }
}
