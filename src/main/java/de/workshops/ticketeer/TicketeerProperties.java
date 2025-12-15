package de.workshops.ticketeer;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "ticketeer")
record TicketeerProperties(
        int version,
        String name,
        String description,
        String currency,
        List<String> categories,
        String environment) {}