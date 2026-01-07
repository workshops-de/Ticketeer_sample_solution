package de.workshops.ticketeer.reservation;

import de.workshops.ticketeer.ticketvendor.TicketReservationRequest;
import de.workshops.ticketeer.ticketvendor.TicketVendorClientConfiguration;
import de.workshops.ticketeer.ticketvendor.TicketVendorService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestClient;

@RestClientTest(TicketVendorService.class)
@Import(TicketVendorClientConfiguration.class)
class ReservationWithExternalTicketVendorTest {

  @Autowired
  private TicketVendorService ticketVendorService;

  @Value("${ticket-vendor.client.url}")
  String ticketVendorClientUrl;

  private MockRestServiceServer mockRestServiceServer;

  @BeforeEach
  void setUp() {
    // TODO: MockRestServiceServer doesn't seem to with RestClient, see https://github.com/spring-projects/spring-boot/issues/38832
    mockRestServiceServer = MockRestServiceServer
        .bindTo(
            RestClient
                .builder()
                .baseUrl(ticketVendorClientUrl)
        )
        .build();
  }

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
