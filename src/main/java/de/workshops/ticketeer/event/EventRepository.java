package de.workshops.ticketeer.event;

import org.springframework.data.repository.ListCrudRepository;

/// This is enough - no `@Repository` annotation needed
///
/// Spring Data recognizes this as a repository interface and automatically:
///
///   1. Creates the implementation
///   2.  Registers it as a Spring bean
///   3.  Enables exception translation
interface EventRepository extends ListCrudRepository<Event, Long> {
}