package de.workshops.ticketeer;

import de.workshops.ticketeer.config.TicketeerProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("prod")
class TicketeerPropertiesProductionTest {
    @Autowired
    TicketeerProperties ticketeerProperties;

    @Test
    void testTicketeerProperties() {
        assertThat(ticketeerProperties.environment()).isEqualTo("Production");
    }
}
