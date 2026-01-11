package de.workshops.ticketeer.reservation;

import de.workshops.ticketeer.util.AbstractPostgreSQLTestcontainersTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(properties = {"ticketeer.vendor.enabled=false"})
@Sql(
    statements = {
        "INSERT INTO event (id, name, venue, base_price, capacity, start_date_time, remaining_tickets, status, external_vendor_id, external_vendor_managed) " +
        "VALUES (2, 'Sample event', 'Some venue', 50.0, 2, NOW() + INTERVAL '10 days', 2, 'PUBLISHED', '78786111-f3aa-4d8a-a103-5b9d6ed70ad0', true)"
    },
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class ReservationServiceWithoutTicketVendorTest extends AbstractPostgreSQLTestcontainersTest {
    @Autowired
    ReservationService reservationService;

    @Test
    void testCreateReservationWithExternalVendor_exception_noVendorAvailable() {
        assertThatThrownBy(() -> reservationService.createReservation(new ReservationRequest(2L, 2, "Category 1")))
            .isInstanceOf(TicketReservationException.class)
            .hasMessage("External vendor not configured");
    }
}