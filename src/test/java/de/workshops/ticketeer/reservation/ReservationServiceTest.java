package de.workshops.ticketeer.reservation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.workshops.ticketeer.event.InvalidTransitionException;
import de.workshops.ticketeer.ticketvendor.TicketReservationRequest;
import de.workshops.ticketeer.ticketvendor.TicketVendorService;
import de.workshops.ticketeer.util.AbstractPostgreSQLTestcontainersTest;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(
    statements = {
        "DELETE from reservation",
        "DELETE FROM event",
        "INSERT INTO event (id, name, venue, base_price, capacity, start_date_time, remaining_tickets, status) VALUES (1, 'Sample event', 'Some venue', 50.0, 2, NOW() + INTERVAL '10 days', 2, 'PUBLISHED')",
        "INSERT INTO event (id, name, venue, base_price, capacity, start_date_time, remaining_tickets, status, external_vendor_id, external_vendor_managed) VALUES (2, 'Sample event', 'Some venue', 50.0, 2, NOW() + INTERVAL '10 days', 2, 'PUBLISHED', '78786111-f3aa-4d8a-a103-5b9d6ed70ad0', true)"
    },
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class ReservationServiceTest extends AbstractPostgreSQLTestcontainersTest {

    @Autowired
    ReservationService reservationService;

    @MockitoBean
    TicketVendorService ticketVendorService;

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

    @Test
    void testCreateReservationWithExternalVendor_ok() {
        var uuid = UUID.fromString("78786111-f3aa-4d8a-a103-5b9d6ed70ad0");

        when(
            ticketVendorService.reserveEventTickets(
                eq(uuid),
                any(TicketReservationRequest.class)
            )
        ).thenReturn(ResponseEntity.ok().build());

        var reservation = reservationService.createReservation(
            new ReservationRequest(2L, 2, "Category 1"));

        verify(ticketVendorService).reserveEventTickets(
            eq(UUID.fromString("78786111-f3aa-4d8a-a103-5b9d6ed70ad0")),
            any(TicketReservationRequest.class)
        );

        assertNotNull(reservation);
    }
}
