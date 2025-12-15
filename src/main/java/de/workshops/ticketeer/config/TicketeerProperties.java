package de.workshops.ticketeer.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ticketeer")
record TicketeerProperties(
    int version,
    String name,
    String description,
    String currency,
    List<String> categories,
    String environment) {

}
