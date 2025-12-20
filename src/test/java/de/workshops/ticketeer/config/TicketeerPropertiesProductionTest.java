package de.workshops.ticketeer.config;

import static org.assertj.core.api.Assertions.assertThat;

import de.workshops.ticketeer.util.AbstractPostgreSQLTestcontainersTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("prod")
class TicketeerPropertiesProductionTest extends AbstractPostgreSQLTestcontainersTest {

    @Autowired
    private TicketeerProperties ticketeerProperties;

    @Test
    void testTicketeerProperties() {
        assertThat(ticketeerProperties.environment()).isEqualTo("Production");
    }
}
