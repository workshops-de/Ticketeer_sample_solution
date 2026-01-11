package de.workshops.ticketeer.reservation;

import de.workshops.ticketeer.ticketvendor.TicketVendorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RestClientTest
@Import(TicketVendorServiceTestConfiguration.class)
@ActiveProfiles("prod")
public class ReservationWithExternalTicketVendorProductionTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    // #{ ... } is a SpEL (Spring Expression Language) Expression
    // ${...} reads the value from the properties file
    @EnabledIf(expression = "#{ !${ticketeer.vendor.enabled} }", loadContext = true)
    void testTicketVendorServiceNotAvailable() {
        assertThatExceptionOfType(NoSuchBeanDefinitionException.class)
            .isThrownBy(() -> applicationContext.getBean(TicketVendorService.class));
    }
}