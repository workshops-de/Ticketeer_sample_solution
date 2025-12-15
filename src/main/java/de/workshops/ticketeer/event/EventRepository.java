package de.workshops.ticketeer.event;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EventRepository extends ListCrudRepository<Event, Long> {

}
