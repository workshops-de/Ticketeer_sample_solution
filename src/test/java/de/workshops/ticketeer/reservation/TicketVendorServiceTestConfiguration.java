package de.workshops.ticketeer.reservation;

import de.workshops.ticketeer.ticketvendor.TicketVendorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.boot.restclient.RestClientCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

// For tests annotated with @RestClientTest, we still need to define the RestClient imperatively.
@TestConfiguration
@ConditionalOnBooleanProperty("ticketeer.vendor.enabled")
public class TicketVendorServiceTestConfiguration {
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