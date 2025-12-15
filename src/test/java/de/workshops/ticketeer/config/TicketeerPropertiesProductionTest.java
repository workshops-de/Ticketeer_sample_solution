package de.workshops.ticketeer.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("prod")
class TicketeerPropertiesProductionTest {

    @Autowired
    private TicketeerProperties ticketeerProperties;

    @Test
    void testTicketeerProperties() {
        assertThat(ticketeerProperties.environment()).isEqualTo("Production");
    }
}
