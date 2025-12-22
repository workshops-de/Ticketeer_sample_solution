package de.workshops.ticketeer.reservation;

import de.workshops.ticketeer.NotificationException;
import de.workshops.ticketeer.event.Event;
import de.workshops.ticketeer.event.InvalidTransitionException;
import de.workshops.ticketeer.order.ReservationOrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    ReservationDto createReservation(ReservationRequest reservationRequest) {
        var reservation = Reservation.builder()
            .event(Event.builder().id(reservationRequest.eventId()).build())
            .reservedAt(LocalDate.now())
            .quantity(reservationRequest.quantity())
            .reservationNumber(UUID.randomUUID())
            .expiresAt(LocalDate.now().plusDays(7))
            .build();

        try {
            // Publish reservation created event (synchronously)
            eventPublisher.publishEvent(new ReservationEvent(
                this,
                reservationRequest.eventId(),
                reservationRequest.quantity(),
                reservation::setSinglePrice));
            var saved = reservationRepository.save(reservation);
            return new ReservationMapper().apply(saved);
        } catch (NotificationException e) {
            throw new InvalidTransitionException("Failed to make a reservation for this event", e);
        }
    }

    ReservationDto confirmReservation(UUID reservationNumber) {
        return new ReservationMapper().apply(setReservationConfirmed(reservationNumber));
    }

    @EventListener(ReservationOrderEvent.class)
    void changeReservationStatus(ReservationOrderEvent event) {
        var reservation = setReservationConfirmed(event.getReservationNumber());
        event.notifyResult(reservation);
    }

    private Reservation setReservationConfirmed(UUID reservationNumber) {
        var reservation = reservationRepository.findByReservationNumberAndStatus(reservationNumber, ReservationStatus.PENDING).orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        if (reservation.getExpiresAt().isBefore(LocalDate.now())) {
            throw new ReservationExpiredException("Reservation expired");
        }
        reservation.setStatus(ReservationStatus.CONFIRMED);
        return reservationRepository.save(reservation);
    }

}