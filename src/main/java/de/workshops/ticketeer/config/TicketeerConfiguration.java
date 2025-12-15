package de.workshops.ticketeer.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TicketeerProperties.class)
class TicketeerConfiguration {

}
