package de.workshops.ticketeer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

// Switch off Flyway, since it is not needed for this test
@SpringBootTest(properties = {
    "spring.flyway.enabled=false",
    "spring.jpa.hibernate.ddl-auto=none"})

// Replaces any DataSource with an embedded test database (H2, Derby, or HSQL)
// The embedded database must be defined in the pom.xm under scope test.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TicketeerApplicationTests {

    @Test
    void contextLoads() {
    }
}
