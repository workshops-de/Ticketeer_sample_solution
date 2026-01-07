package de.workshops.ticketeer.reservation;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;

interface ReservationRepository extends ListCrudRepository<Reservation, Long> {
    Optional<Reservation> findByReservationNumberAndStatus(UUID reservationNumber, ReservationStatus status);
}
