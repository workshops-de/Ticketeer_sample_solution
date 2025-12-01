# Ticketeer Workshop – Facilitator Guide (5 × 4h)
Each day includes **~3h20 of net coding time** (subtracting two 20-minute breaks). Assume participants start with a **prepared skeleton project** (empty layers, empty entities, no implementations).

## Day 1 – Spring Boot Basics & REST Foundations
### Learning Goals
* •	Get comfortable running a Spring Boot application.
* •	Learn basic controllers, DTOs, and dependency injection.
* •	Begin practicing TDD / testing from day one.
* •	Understand Spring profiles & configuration properties.

⠀
### Block Overview (4 hours)
| **Segment** | **Duration** | **Topic** |
|:-:|:-:|:-:|
| Intro + Setup | 20 min | Project walkthrough, goals |
| Exercise 1.0 | 30 min | Project startup, first endpoint, basic test |
| Exercise 1.1 | 50 min | Events & in-memory repo + tests |
| **Break 1** | 20 min | — |
| Exercise 1.2 | 45 min | Profiles, configuration properties |
| Review & Discussion | 15 min | Testing strategy, REST design choices |
| **Break 2** | 20 min | — |
| Wrap-up + Q&A | 20 min | Troubleshooting + preview next day |

### Facilitation Tips
#### Exercise 1.0
* •	Ensure everyone uses the same Java version and IDE settings.
* •	Encourage writing the *test first* for the ping endpoint.
  * ◦	Good chance to introduce @WebMvcTest vs @SpringBootTest.

⠀Exercise 1.1
* •	Participants often forget:
  * ◦	JSON serialization needs getters.
  * ◦	Returning lists vs wrappers (List<EventDto> is fine).
* •	Suggest explaining the difference between:
  * ◦	Controller DTOs
  * ◦	Domain model
  * ◦	Persistence model (coming on Day 2)

#### Exercise 1.2
* •	Show how Spring binds complex hierarchical objects from YAML.
* •	Promote consistent naming conventions for properties.
* •	Ask participants: “Which values belong in code, which belong in configuration?” 

⠀
### Checkpoints
* •	Can everyone run GET /api/ping and write a working test?
* •	Can participants list seeded events and retrieve a specific one?
* •	Does switching profiles change behavior (e.g. currency)?

⠀
### Stretch items (if ahead)
* •	Add validation using @Valid.
* •	Introduce problem+json error format.
* •	Add sorting/filtering to event listing.

⠀
## Day 2 – Persistence, PostgreSQL & Transactions
### Learning Goals
* •	Understand Spring Data JPA, repositories, and entity modelling.
* •	Work with PostgreSQL + Flyway/Liquibase.
* •	Implement first transactional business operation.
* •	Write repository tests and integration tests.

⠀
### Block Overview
| **Segment** | **Duration** | **Topic** |
|:-:|:-:|:-:|
| Recap + DB Intro | 15 min | RDBMS schema, JPA mapping |
| Exercise 2.0 | 60 min | JPA entities + repository + persistence tests |
| **Break 1** | 20 min | — |
| Exercise 2.1 | 50 min | Migrations (Flyway) + schema evolution |
| Exercise 2.2 | 45 min | Transactional publish workflow + tests |
| **Break 2** | 20 min | — |
| Review & Reflection | 30 min | Caching? Lazy loading? Patterns? |

### Facilitation Tips
#### Exercise 2.0
* •	Remind participants:
  * ◦	No-arg constructor required for JPA.
  * ◦	@Column(nullable=false) helps express domain rules.
* •	Some may struggle with Testcontainers; allow H2 fallback but encourage Testcontainers for realism.

#### Exercise 2.1
* •	Emphasize migrations as a core skill in production systems.
* •	Show how to version the schema safely:
  * ◦	No destructive changes without care.
  * ◦	Migrations must be reproducible.

#### Exercise 2.2
* •	Use domain events or service-layer validations sparingly to avoid overwhelming participants.
* •	Ask: “What should happen if two organizers publish the same event concurrently?” 
  * ◦	Good intro to optimistic locking tomorrow.

⠀
### Checkpoints
* •	DB schema exists and migrations run at startup.
* •	Events are persisted and retrievable.
* •	Publishing an event has a passing happy-path + failure-path test.

⠀
### Stretch items
* •	Add @Version for optimistic locking.
* •	Add a soft-delete strategy (status = ARCHIVED).
* •	Write projections or custom queries.

⠀
## Day 3 – Reservations, Orders & External API Integration
### Learning Goals
* •	Complex domain modelling (reservations, orders).
* •	Multi-step workflow + transactional boundaries.
* •	OpenAPI-based code generation.
* •	Integrating Spring’s HTTP client with external systems.
* •	Test doubles & contract-ish testing (WireMock).

⠀
### Block Overview
| **Segment** | **Duration** | **Topic** |
|:-:|:-:|:-:|
| Recap + Domain Deep Dive | 15 min | Reservations, orders |
| Exercise 3.0 | 60 min | Reservation + Order entities & logic |
| Exercise 3.1 | 45 min | REST API for purchase flow |
| **Break 1** | 20 min | — |
| Exercise 3.2 | 70 min | External vendor API integration |
| **Break 2** | 20 min | — |
| Review & Retrospective | 30 min | API design, domain boundaries |

### Facilitation Tips
#### Exercise 3.0
* •	Explain why reservations expire, why capacity tracking is crucial.
* •	Common pitfall:
  * ◦	Forgetting to decrease available seats inside a transaction.

#### Exercise 3.1
* •	Stress proper HTTP semantics:
  * ◦	201 for creation
  * ◦	404 for missing resources
  * ◦	409 for conflicting reservations

#### Exercise 3.2
* •	Pre-generate the OpenAPI client or allow students to generate it on their machines.
* •	Encourage isolating the integration behind an interface for testability.
* •	Showcase:
  * ◦	WireMock stubbing success
  * ◦	WireMock stubbing vendor outage → rollback

⠀
### Checkpoints
* •	Reservations persist and reduce remaining seats.
* •	Orders link to reservations correctly.
* •	External vendor is called only when needed.
* •	Vendor failures roll back reservations (test this explicitly).

⠀
### Stretch items
* •	Add background job to expire reservations automatically.
* •	Introduce Saga-like patterns for reservation/order consistency.
* •	Add rate limiting for vendor calls.

⠀
## Day 4 – Configuration at Scale & Spring Security
### Learning Goals
* •	Externalizing complex configuration via properties.
* •	Securing organizer/admin endpoints.
* •	Method-level / domain permission checks.
* •	Testing security behavior.

⠀
### Block Overview
| **Segment** | **Duration** | **Topic** |
|:-:|:-:|:-:|
| Recap | 10 min | Review of integration complexity |
| Exercise 4.0 | 45 min | Vendor config properties + environments |
| Exercise 4.1 | 70 min | Spring Security: roles & endpoint protection |
| **Break 1** | 20 min | — |
| Exercise 4.2 | 60 min | Method-level security & ownership |
| **Break 2** | 20 min | — |
| Discussion + Q&A | 25 min | Real-world access patterns |

### Facilitation Tips
#### Exercise 4.0
* •	Show how to override properties using:
  * ◦	Profiles
  * ◦	Environment variables
  * ◦	Command-line args

#### Exercise 4.1
* •	Start simple (HTTP Basic or form login).
* •	Emphasize separation of *public customer API* vs *organizer/admin API*.

#### Exercise 4.2
* •	Participants often misuse method-level security annotations.
* •	Organizers may need custom permission evaluators; keep it simple:
  * ◦	Only owner can modify the event.

⠀
### Checkpoints
* •	Organizer actions require authentication.
* •	Public event browsing remains open.
* •	Tests exist for:
  * ◦	Unauthorized access
  * ◦	Authorized access
  * ◦	Role-based restrictions
  * ◦	Ownership restrictions

⠀
### Stretch items
* •	JWT-based auth.
* •	Load user details from DB.
* •	Add CSRF protection for admin UI.

⠀
## Day 5 – Monitoring, Actuator & Production Readiness
### Learning Goals
* •	Using Actuator for operational visibility.
* •	Custom health indicators.
* •	Basic metrics via Micrometer.
* •	Prepare a convincing “production-ready” demo.

⠀
### Block Overview
| **Segment** | **Duration** | **Topic** |
|:-:|:-:|:-:|
| Recap + Monitoring Intro | 15 min | Why observability matters |
| Exercise 5.0 | 45 min | Actuator setup + exposing endpoints |
| Exercise 5.1 | 60 min | Custom health indicator (DB + vendor) |
| **Break 1** | 20 min | — |
| Exercise 5.2 | 45 min | Metrics: reservations/orders counters |
| Final Integration Review | 20 min | Checklist + demos |
| **Break 2** | 20 min | — |
| Workshop Retrospective | 15 min | Lessons learned, next steps |

### Facilitation Tips
#### Exercise 5.0
* •	Show difference between:
  * ◦	/health
  * ◦	/metrics
  * ◦	/info
* •	Show how to expose additional endpoints safely.

#### Exercise 5.1
* •	Ask participants: “What does *healthy* mean for Ticketeer?” 
  * ◦	DB reachable
  * ◦	Vendor reachable
  * ◦	Enough events loaded?

#### Exercise 5.2
* •	Demonstrate Micrometer registry injection.
* •	Show how metrics could feed Prometheus (optional demonstration).

⠀
### Checkpoints
* •	Actuator endpoints visible & working.
* •	Custom health indicator returns meaningful operational status.
* •	Metrics counters increment correctly during reservation/order creation.

⠀
### Stretch items
* •	Add distributed tracing stubs (OpenTelemetry).
* •	Add custom metrics tags (per event or per organizer).
* •	Add a /actuator/startup endpoint (Spring Boot 3.x feature).

⠀
## End-of-Workshop Deliverables
By end of Day 5, participants should have a functioning, production-ish **Ticketeer** app with:
* •	Real PostgreSQL persistence
* •	Clean REST APIs
* •	Reservation + order workflow
* •	External vendor integration
* •	Authentication & authorization
* •	Configurable environments
* •	Monitoring & health indicators
* •	Tests at every level:
  * ◦	unit
  * ◦	slice tests (web, JPA)
  * ◦	integration
  * ◦	security tests

⠀
## Facilitator Best Practices
### Encourage testing first
Participants will be tempted to “just code it.” Gently redirect:
“What should the behavior be? Let’s express it in a test first.”

### Use structured check-ins
After each major exercise, ask:
* •	Does your code compile?
* •	Does your endpoint open in the browser?
* •	Do your tests pass?
* •	Do your logs show expected behavior?

### Highlight real-world analogies
* •	Reservations = scarce resources.
* •	Vendor integration = legacy system headaches.
* •	Profiles & configs = DevOps readiness.
* •	Security = multi-tenant SaaS responsibility.
* •	Actuator = observability 101.

### Celebrate small wins
Participants often underestimate their progress; marking milestones helps maintain energy across five sessions.
