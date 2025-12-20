package de.workshops.ticketeer.config;

import static org.assertj.core.api.Assertions.assertThat;

import de.workshops.ticketeer.util.AbstractPostgreSQLTestcontainersTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TicketeerPropertiesDefaultTest extends AbstractPostgreSQLTestcontainersTest {

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
