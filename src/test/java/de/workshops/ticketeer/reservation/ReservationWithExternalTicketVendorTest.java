package de.workshops.ticketeer.reservation;

import de.workshops.ticketeer.ticketvendor.TicketReservationRequest;
import de.workshops.ticketeer.ticketvendor.TicketVendorService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.restclient.RestClientCustomizer;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@RestClientTest
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

  // For tests annotated with @RestClientTest, we still need to define the RestClient imperatively.
  @TestConfiguration
  static class TestConfig {
    @Value("${spring.http.serviceclient.default.base-url}")
    String baseUrl;

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
      return builder.build();
    }

    @Bean
    public RestClientCustomizer restClientCustomizer() {
      return (restClientBuilder) -> restClientBuilder
          .baseUrl(baseUrl);
    }

    @Bean
    TicketVendorService ticketVendorService(RestClient restClient) {
      var adapter = RestClientAdapter.create(restClient);
      var factory = HttpServiceProxyFactory.builderFor(adapter).build();

      return factory.createClient(TicketVendorService.class);
    }
  }
}
