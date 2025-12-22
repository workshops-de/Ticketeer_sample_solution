package de.workshops.ticketeer;

import de.workshops.ticketeer.reservation.ReservationDto;
import de.workshops.ticketeer.order.OrderDto;
import de.workshops.ticketeer.reservation.ReservationStatus;
import de.workshops.ticketeer.util.AbstractPostgreSQLTestcontainersTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {
    "INSERT INTO event (id, name, venue, base_price, capacity, start_date_time, remaining_tickets, status) VALUES (1, 'Sample event', 'Some venue', 50.0, 2, NOW() + INTERVAL '10 days', 2, 'PUBLISHED')"
})
@AutoConfigureRestTestClient
public class PurchaseFlowTest extends AbstractPostgreSQLTestcontainersTest {
    @Autowired
    RestTestClient restTestClient;

    @LocalServerPort
    private int port;

    @Test
    void reserveAndPurchaseTicket() {
        var reservationDto = reserveTicket(1L, 2);
        orderTicket(reservationDto);
    }

    private ReservationDto reserveTicket(Long eventId, int quantity) {
        var exchangeResult = restTestClient.post()
            .uri(uriBuilder -> uriBuilder
                .host("localhost")
                .port(port)
                .path("/api/reservations/event/{id}")
                .queryParam("quantity", quantity)
                .build(eventId))
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isCreated()
            .returnResult(ReservationDto.class);

        var reservationDto = exchangeResult.getResponseBody();

        assertNotNull(reservationDto.reservationNumber());
        assertEquals(ReservationStatus.PENDING, reservationDto.status());

        return reservationDto;
    }

    private void orderTicket(ReservationDto reservationDto) {
        var exchangeResult = restTestClient.post()
            .uri(uriBuilder -> uriBuilder
                .host("localhost")
                .port(port)
                .path("/api/orders/reservation/{reservationNumber}")
                .build(reservationDto.reservationNumber()))
            .contentType(MediaType.APPLICATION_JSON)
            .body("{}")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isCreated()
            .returnResult(OrderDto.class);

        var orderDto = exchangeResult.getResponseBody();

        assertNotNull(orderDto);
        assertNotNull(orderDto.orderNumber());
    }
}