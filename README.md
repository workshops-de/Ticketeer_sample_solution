# Ticketeer_sample_solution
Sample solution implementing a ticket sales use case for the new (as of 2026) Spring Boot workshop.

---

A sample Spring Boot application for demonstrating clean architecture, Spring Data JPA patterns, and practical transactional workflows through a real-world ticket sales use case.

---

## 🧭 Overview

**Ticketeer** is a fictional ticket sales platform designed for workshops, presentations, and teaching modern Spring Boot development. The project models a streamlined event ticketing backend with a focus on correctness, concurrency handling, and clean design.

This repository provides a realistic foundation for exploring:

* Domain-driven modelling in Spring Boot
* Multi-step purchase workflows
* Seat inventory protection
* Transaction management patterns
* RESTful API design
* Extensible architecture for further exercises

---

## 🎬 Story & Background

Ticketeer is operated by **Ticketeer Ltd.**, a startup founded by two event-industry veterans tired of outdated and clunky ticket systems. Their mission: *“Make ticketing delightful—for organizers and attendees alike.”*

Ticketeer helps small and midsize organizers publish events, manage seat inventory, and sell tickets reliably—even under load.

You, the workshop participant, act as a backend engineer helping grow the platform as they onboard their first clients.

---

## ✨ Features

* **Event Management**: Create and view events with venue, schedule, and seat capacity.
* **Seat Inventory & Availability**: Track available vs. sold seats.
* **Ticket Purchase Workflow**:

  * Reserve seat(s)
  * Convert reservations into confirmed orders
  * Simulated payment step
* **Optimistic Concurrency Control** to prevent overselling.
* **Organizer Reporting** (basic sales statistics).

---

## 🧱 Architecture

Ticketeer follows a clean, layered structure:

```
com.ticketeer
 ├── application   — Services & use cases
 ├── domain        — Entities, repositories, business logic
 ├── infrastructure — JPA, persistence, configuration
 └── api           — REST controllers & DTOs
```

### Key Concepts

* **Domain-first design** with rich domain objects
* **Transaction boundaries** using `@Transactional` and/or `TransactionTemplate`
* **Optimistic locking** via JPA `@Version`
* **REST API** with clear resource modelling

---

## 🛠️ Tech Stack

* Java 21+
* Spring Boot 3.x
* Spring Data JPA
* H2 or PostgreSQL (configurable)
* Maven
* Testcontainers (optional)

---

## 🚀 Getting Started

### Prerequisites

* Java 21+
* Maven 3.9+

### Run the application

```bash
mvn spring-boot:run
```

Ticketeer will start on:

```
http://localhost:8080
```

### API Documentation

Once running, API docs (if enabled) are available at:

```
http://localhost:8080/swagger-ui.html
```

---

## 🧪 Testing

```bash
mvn test
```

The project includes unit and integration tests demonstrating:

* Domain logic
* Persistence behavior
* Purchase workflow boundaries

---

## 📦 Sample Domain Model

### Entities

* `Event`
* `SeatInventory`
* `Reservation`
* `Order`

### Relationships

* One `Event` → One `SeatInventory`
* One `Reservation` → Many seats
* One `Order` → One confirmed purchase

---

## 🧩 Workshop Exercises

Suggested tasks:

1. Implement seat reservation expiration.
2. Add asynchronous email confirmations.
3. Extend events with multiple price categories.
4. Introduce event search & filtering.
5. Add organizer accounts & authentication.

---

## 📘 License

MIT License — feel free to reuse this for workshops or internal trainings.

---

## 🤝 Contributing

Pull requests are welcome, especially improvements that help the educational purpose of the sample.

---

If you need a matching **project skeleton**, **domain classes**, or **exercise instructions**, just let me know!
