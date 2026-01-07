package de.workshops.ticketeer.order;

import org.springframework.data.repository.ListCrudRepository;

interface OrderRepository extends ListCrudRepository<Order, Long> {
}
