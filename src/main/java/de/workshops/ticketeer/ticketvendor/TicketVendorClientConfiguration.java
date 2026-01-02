package de.workshops.ticketeer.ticketvendor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
class TicketVendorClientConfiguration {

  private final String ticketVendorClientUrl;

  TicketVendorClientConfiguration(
      @Value("${ticket-vendor.client.url}") String ticketVendorClientUrl
  ) {
    this.ticketVendorClientUrl = ticketVendorClientUrl;
  }

  @Bean
  HttpServiceProxyFactory httpServiceProxyFactory() {
    RestClient restClient = RestClient.builder()
        .baseUrl(ticketVendorClientUrl)
        .build();

    return HttpServiceProxyFactory.builder()
        .exchangeAdapter(RestClientAdapter.create(restClient))
        .build();
  }

  @Bean
  TicketVendorService httpBinClient(HttpServiceProxyFactory httpServiceProxyFactory) {
    return httpServiceProxyFactory.createClient(TicketVendorService.class);
  }
}
