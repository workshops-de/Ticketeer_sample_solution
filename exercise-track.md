# Ticketeer Workshop – Exercise Track

## Overall assumptions
* Participants start from a **provided Ticketeer skeleton** (empty or minimal code, but with basic Maven/Gradle + Spring Boot setup).
* Java 21, Spring Boot 4.x, PostgreSQL.
* You provide:
  * A barebones domain (empty entities or just stubs).
  * A simple docker-compose.yml for PostgreSQL (and optionally pgAdmin).
  * An OpenAPI spec file for the external ticket vendor (YAML/JSON).

⠀
## Day 1 – Spring Boot & REST Fundamentals
**Goals**
* Get everyone running Ticketeer locally.
* Introduce the core domain (Event, TicketInventory).
* Expose first REST APIs.
* Write tests from the beginning.

### Exercise 1.0 – Project setup & “Hello Ticketeer”
**Story hook:** Ticketeer’s new backend team (that’s them) just joined. Step one: get the service to start and respond.

**Tasks**
1. Clone/open the Ticketeer project.
2. Ensure the app starts with mvn spring-boot:run or via IDE.
3. Add a simple health-like endpoint:
   * GET /api/ping → returns { "status": "ticketeer-ok" }
4. Use application.properties (or .yml) to set:
   * an `application.title` property.


**Testing focus**
* Add a **Spring MVC test** (@WebMvcTest or @SpringBootTest with MockMvc) that:
  * Calls /api/ping
  * Asserts 200 OK and JSON body contains "ticketeer-ok".


### Exercise 1.1 – Domain sketch: Events and Seat Inventory (in-memory for now)
**Story hook:** Ticketeer needs to list upcoming events for early organizers. Persistence can wait – we’ll keep it in memory to start.

**Tasks**
1. Define simple DTOs:
   * EventDto (id, name, venue, startDateTime, basePrice).
2. Add an in-memory repository:
   * A List<Event> or Map<Long, Event> stored in a @Component.
3. Implement REST endpoints:
   * GET /api/events – list all events.
   * GET /api/events/{id} – details for a single event.
4. Preload 3–5 hard-coded events at startup (e.g. via a DataInitializer bean).

**Testing focus**
* •	Write tests for:
  * ◦	GET /api/events returns the seeded events.
  * ◦	GET /api/events/{id}:
    * ▪	Returns 200 for existing id.
    * ▪	Returns 404 for unknown id (implement basic error handling).

⠀
### Exercise 1.2 – Basic configuration & profiles
**Story hook:** Ticketeer runs in different environments (local workshop vs. production later). We start with profiles.

**Tasks**
1. Introduce profiles:
   * application-local.yml
   * application-test.yml
2. Move seeding config / properties into local profile.
3. Add a configuration properties class:
   * TicketeerProperties with prefix ticketeer.
   * Fields like startupBannerEnabled, defaultCurrency, etc.
4. Use these properties in your controller or service (e.g. include defaultCurrency in event responses).

**Testing focus**
* Add a **configuration test**:
  * Use `@ActiveProfiles` and verify `TicketeerProperties` contains the correct values.
  * Assert that TicketeerProperties is bound correctly.


## Day 2 – Persistence, PostgreSQL & Transaction Basics
**Goals**
* Replace in-memory storage with PostgreSQL.
* Introduce Spring Data JPA and migration tooling.
* Start modelling part of the ticketing workflow in the DB.
* Keep tests front and center.
* Introduce a 3-tier architecture (controller → service → repository).

### Exercise 2.0 – PostgreSQL integration & JPA entities
**Story hook:** The founders want real persistence: events must survive restarts.

**Tasks**
1. Configure **PostgreSQL** datasource via application.yml:
   * spring.datasource.url, username, password (using environment variables or, alternatively, via a production profile).
2. Define JPA entities:
   * EventEntity (id, name, venue, startDateTime, basePrice, capacity).
   * TicketInventoryEntity or simply a numeric field on Event for now.
3. Introduce Spring Data repositories:
   * EventRepository extends JpaRepository<EventEntity, Long>.
4. Replace in-memory repository logic in REST endpoints with database-backed repositories.


**Testing focus**
* Write a **JPA repository test**:
  * Use @DataJpaTest.
  * Persist a sample EventEntity.
  * Verify queries (find by id, find all).
* Optional: Use **Testcontainers** for PostgreSQL in tests instead of H2.

⠀
### Exercise 2.1 – Database schema evolution (Flyway or Liquibase)
**Story hook:** Ticketeer is evolving constantly. Schema changes must be manageable and trackable.

**Tasks**
1. Add Flyway (or Liquibase) to the project.
2. Create an initial migration:
   * V1__initial_schema.sql with events table.
3. Remove any ddl-auto=create from config or restrict it to tests only.
4. Add a second migration to:
   * Add a status column to events (e.g. DRAFT, PUBLISHED, CANCELLED).

**Testing focus**
* Start app and verify migrations apply (can be manual / log-based).
* Add a test that:
  * Uses the real schema and ensures status is stored and retrieved correctly.

⠀
### Exercise 2.2 – First transactional use case: publishing an event
**Story hook:** Organizers want to switch an event from DRAFT to PUBLISHED. This may include derived changes later.

**Tasks**
1. Add a service method in application layer:
   * publishEvent(eventId):
     * Only allowed if status is DRAFT.
     * Set status to PUBLISHED.
2. Create endpoint:
   * POST /api/events/{id}/publish → returns updated event.
3. Mark the service method @Transactional.

**Testing focus**
* •	Write **service-level tests**:
  * Happy path: DRAFT → PUBLISHED.
  * Failure path: calling publish on already PUBLISHED event throws domain exception.
* •	Add a **controller test** ensuring status code:
  * 200 for success.
  * 400 or 409 for invalid transition.

⠀
## Day 3 – Ticket Reservations, Purchase Flow & External API Integration (Part 1)
**Goals**
* Model reservations & orders.
* Build a multi-step purchase workflow.
* Start integrating with an external vendor API using an OpenAPI client.

### Exercise 3.0 – Reservation & Order domain modelling
**Story hook:** Ticketeer is going live – users must actually reserve seats and buy tickets.

**Tasks**
1. Define new entities:
   * Reservation:
     * id, eventId, reservedAt, expiresAt, numberOfSeats, status (PENDING, EXPIRED, CONFIRMED).
   * Order:
     * id, reservationId, totalPrice, createdAt, status (PENDING_PAYMENT, PAID, FAILED).
2. Add repositories for both.
3. Implement service methods:
   * createReservation(eventId, numberOfSeats).
   * confirmReservation(reservationId) (to be used by order in next exercise).
4. Update events table/entity to track remainingSeats.

**Testing focus**
* **Domain tests** to ensure:
  * Creating a reservation reduces remainingSeats.
  * Cannot create reservation if remaining seats are insufficient.

⠀
### Exercise 3.1 – REST API for purchase flow
**Story hook:** The frontend needs a clear API to guide customers from “I want 2 tickets” to “order confirmed”.

**Tasks**
1. Implement REST endpoints:
   * POST /api/event/{id}/reservation with JSON { "tickets": 2 }
     * Returns ReservationDto (with fields like reservationId, eventId, numberReservedTickets, reservedUntil).
   * ◦	POST /api/reservation/{id}/order with payment info (simplified).
     * ▪	Creates an order and marks reservation as CONFIRMED + order PAID (for now, simulate payment).
2. Ensure proper HTTP semantics:
   * ◦	201 Created when reservation/order created.
   * ◦	Reasonable errors for invalid event or reservation.

**Testing focus**
* **Integration tests** (@SpringBootTest + Testcontainers or in-memory DB):
  * Full flow: create event → create reservation → create order.
  * Validation: cannot create order on EXPIRED or non-existent reservations.

⠀
### Exercise 3.2 – Integrating an external ticket vendor (OpenAPI + `@HttpExchange`)
**Story hook:** For a high-profile festival, Ticketeer must sell a portion of tickets from a partner’s system (exposed via an external REST API). Ticketeer will reserve in its own DB but also call out to the partner.

**Tasks**
1. Given an **OpenAPI spec** (ticket-vendor-api.yaml):
   * Use OpenAPI Generator to create a client module and an `@HttpExchange` interface.
2. Write a configuration to have SpringBoot generate a RestClient for you.
3. Extend createReservation(...):
   * If event is `externalVendorManaged=true`, also call the vendor API to reserve seats.
   * If the vendor call fails, roll back your local transaction.

**Testing focus**
* Use `@RestClientTest` to:
  * Simulate successful vendor reservation.
  * Simulate failure and assert that your local reservation is not persisted.
* Verify that your integration code is well-isolated and testable.

⠀
## Day 4 – Configuration, Security & Hardening the APIs
**Goals**
* Introduce serious configuration handling.
* Secure organizer and internal APIs.
* Make the integration with the external vendor configurable per environment.

### Exercise 4.0 – Externalizing configuration & profiles for vendors
**Story hook:** The vendor provides different base URLs and API keys for test vs. production environments.

**Tasks**
1. Create a configuration properties class:
   * VendorApiProperties with prefix ticketeer.vendor.
   * Fields: baseUrl, apiKey, enabled.
2. Bind these in:
   * application-test.yml (pointing to mock / test URL).
   * application-prod.yml (pretend value / placeholder).
3. Inject these properties into VendorTicketClient wrapper.
4. Make external calls conditional on enabled.

**Testing focus**
* Config tests that:
  * Load context with different properties.
  * Validate that VendorApiProperties get correct values.

⠀
### Exercise 4.1 – Spring Security: securing organizer endpoints
**Story hook:** Ticketeer needs to distinguish between **public customer APIs** and **organizer admin APIs**.

**Tasks**
1. Introduce Spring Security.
2. Define two categories of endpoints:
   * **Public**:
     * GET /api/event, GET /api/event/{id}, reservation & order endpoints.
   * **Organizer/Admin**:
     * POST /api/event, POST /api/event/{id}/publish, editing events, reading sales reports.
3. Configure security:
   * Public endpoints available without authentication.
   * Organizer endpoints require authentication (e.g. HTTP Basic or form login with in-memory users).
     * Example: user organizer / password organizer123 with role ORGANIZER.

**Testing focus**
* Use **Spring Security test support**:
  * Anonymous user can call GET /api/events.
  * Anonymous user **cannot** call POST /api/events.
  * Authenticated user with ROLE_ORGANIZER can call organizer endpoints.

⠀
### Exercise 4.2 – Method-level security & domain rules
**Story hook:** Only the organizer that owns an event should be allowed to modify it (simulated).

**Tasks**
1. Add an organizerId to Event.
2. Add method-level security:
   * @PreAuthorize("#event.organizerId == authentication.name") (or a custom permission evaluator).
   * Or simpler variant: only allow an organizer with name "acme-organizer" to modify their events.
3. Wire this into service methods like publishEvent(...).

**Testing focus**
* Method security tests:
  * Use @WithMockUser(username="acme-organizer", roles="ORGANIZER") to verify allowed access.
  * @WithMockUser(username="other-organizer", roles="ORGANIZER") should get AccessDeniedException.

⠀
## Day 5 – Monitoring, Actuator & Final Polish
**Goals**
* Introduce Spring Boot Actuator.
* Add custom health/metrics related to events and tickets.
* Wrap up with a “production-ish” Ticketeer instance.

### Exercise 5.0 – Actuator basics
**Story hook:** Ticketeer is going to be deployed to a staging/production environment; ops need health endpoints and metrics.

**Tasks**
1. Add Spring Boot Actuator dependency.
2. Enable basic actuator endpoints in application.yml:
   * management.endpoints.web.exposure.include=health,info,metrics.
3. Configure info with:
   * App name, version, environment from ticketeer properties.
4. Validate:
   * /actuator/health
   * /actuator/info
   * /actuator/metrics

**Testing focus**
* Integration test that:
  * Calls /actuator/health and expects UP.
  * Asserts basic info fields.

⠀
### Exercise 5.1 – Custom health indicator: database & vendor integration
**Story hook:** Operations want a single place to check whether the DB and external vendor are reachable before big ticket drops.

**Tasks**
1. Implement a custom HealthIndicator:
   * TicketeerHealthIndicator that:
     * Confirms DB is reachable (e.g. simple query via repository).
     * Optionally pings vendor API (or just checks configuration / feature flag).
2. Add details to health response:
   * Number of active/published events.
   * Whether vendor is “up” or “down”.

**Testing focus**
* Test the health indicator:
  * Mock repositories / vendor client to produce up/down states.
  * Assert JSON structure under /actuator/health for both scenarios.

⠀
### Exercise 5.2 – Simple metrics: reservations and sales
**Story hook:** The founders want to monitor how many reservations and orders are created per event.

**Tasks**
1. Introduce Micrometer counters or timers:
   * Counter: ticketeer.reservations.created.
   * Counter: ticketeer.orders.completed.
2. Increment counters in the respective service methods.
3. Explore /actuator/metrics/ticketeer.reservations.created and /actuator/metrics/ticketeer.orders.completed.

**Testing focus**
* Unit test or slice test:
  * Use MeterRegistry in tests.
  * After calling reservation/order service methods, assert counter values.

⠀
### Exercise 5.3 – Wrap-up & refactoring (optional / buffer)
If there is time or for homework:
* Identify duplication in controllers and services; refactor.
* Add error-handling layer (@ControllerAdvice) with consistent error response format.
* Add documentation (Swagger / springdoc-openapi) for all APIs.
