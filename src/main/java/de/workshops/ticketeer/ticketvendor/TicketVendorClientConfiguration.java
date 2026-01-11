package de.workshops.ticketeer.ticketvendor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;


// This is the new declarative way of instantiating a RestClient.
@Configuration(proxyBeanMethods = false)
@ImportHttpServices(TicketVendorService.class)
@ConditionalOnBooleanProperty("ticketeer.vendor.enabled")
public class TicketVendorClientConfiguration {
}