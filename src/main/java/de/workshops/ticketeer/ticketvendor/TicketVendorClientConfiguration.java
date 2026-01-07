package de.workshops.ticketeer.ticketvendor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;


// This is the new declarative way to instantiate a RestClient
@Configuration(proxyBeanMethods = false)
@ImportHttpServices(TicketVendorService.class)
public class TicketVendorClientConfiguration {
}