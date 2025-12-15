package de.workshops.ticketeer.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TicketeerPropertiesDefaultTest {

    @Autowired
    private TicketeerProperties ticketeerProperties;

    @Test
    void testTicketeerProperties() {
        assertThat(ticketeerProperties.name()).isEqualTo("Ticketeer");
        assertThat(ticketeerProperties.version()).isEqualTo(1);
        assertThat(ticketeerProperties.categories()).hasSize(6);

        assertThat(ticketeerProperties.environment()).isEqualTo("Default");
    }
}
