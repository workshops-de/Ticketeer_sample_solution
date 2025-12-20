package de.workshops.ticketeer.util;

import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

@Testcontainers

// Use the configured database, do not replace it with an embedded database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class AbstractPostgreSQLTestcontainersTest {

    @Container
    @ServiceConnection
    static org.testcontainers.postgresql.PostgreSQLContainer postgreSQLContainer
        = new PostgreSQLContainer("postgres:latest")
        .withReuse(true);
}