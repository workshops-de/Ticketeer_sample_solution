package de.workshops.ticketeer.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.util.UUID;

interface ReservationRepository extends ListCrudRepository<Reservation, Long> {
    Optional<Reservation> findByReservationNumberAndStatus(UUID reservationNumber, ReservationStatus status);
}