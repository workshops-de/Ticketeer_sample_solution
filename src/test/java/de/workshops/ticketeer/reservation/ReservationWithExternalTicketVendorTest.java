package de.workshops.ticketeer.reservation;

import de.workshops.ticketeer.ticketvendor.TicketReservationRequest;
import de.workshops.ticketeer.ticketvendor.TicketVendorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.util.UUID;

@RestClientTest
@Import(TicketVendorServiceTestConfiguration.class)
class ReservationWithExternalTicketVendorTest {

  @Autowired
  private TicketVendorService ticketVendorService;

  @Value("${spring.http.serviceclient.default.base-url}")
  String ticketVendorClientUrl;

  @Autowired
  private MockRestServiceServer mockRestServiceServer;

  @Test
  void testReservationWithExternalTicketVendor() {
    // Arrange
    var externalVendorId = UUID.randomUUID();
    var quantity = 2;
    var category = "VIP";

    mockRestServiceServer
        .expect(
            MockRestRequestMatchers
                .requestTo(
                    ticketVendorClientUrl + "/events/" + externalVendorId + "/reservations"
                )
        )
        .andRespond(MockRestResponseCreators.withSuccess());

    // Act
    var response = ticketVendorService.reserveEventTickets(
        externalVendorId,
        TicketReservationRequest.builder()
            .number(quantity)
            .category(category)
            .build()
    );

    // Assert
    assert (response.getStatusCode().is2xxSuccessful());
  }
}