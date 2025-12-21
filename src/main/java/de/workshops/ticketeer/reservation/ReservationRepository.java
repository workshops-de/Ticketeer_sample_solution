package de.workshops.ticketeer.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByReservationNumberAndStatus(UUID reservationNumber, ReservationStatus status);
}