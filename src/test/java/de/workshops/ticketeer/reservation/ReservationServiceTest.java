package de.workshops.ticketeer.reservation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.workshops.ticketeer.event.InvalidTransitionException;
import de.workshops.ticketeer.util.AbstractPostgreSQLTestcontainersTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(statements = {
    "DELETE from reservation",
    "DELETE FROM event",
    "INSERT INTO event (id, name, venue, base_price, capacity, start_date_time, remaining_tickets, status) VALUES (1, 'Sample event', 'Some venue', 50.0, 2, NOW() + INTERVAL '10 days', 2, 'PUBLISHED')"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ReservationServiceTest extends AbstractPostgreSQLTestcontainersTest {

    @Autowired
    ReservationService reservationService;

    @Test
    void testCreateReservation_tooManyTickets() {
        assertThrows(InvalidTransitionException.class, () -> reservationService.createReservation(
            new ReservationRequest(1L, 3, "Category 1")));
    }

    @Test
    void testCreateReservation_ok() {
        var reservation = reservationService.createReservation(
            new ReservationRequest(1L, 2, "Category 1"));

        assertNotNull(reservation);
    }
}
