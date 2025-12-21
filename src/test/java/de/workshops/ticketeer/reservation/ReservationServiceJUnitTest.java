package de.workshops.ticketeer.reservation;

import de.workshops.ticketeer.event.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceJUnitTest {

    @Mock
    ReservationRepository reservationRepository;

    @InjectMocks
    ReservationService reservationService;

    @Test
    void confirmReservation_ok() {
        // Given
        var reservation = Reservation.builder()
            .reservationNumber(UUID.randomUUID())
            .event(Event.builder().id(1L).build())
            .build();
        when(reservationRepository.findByReservationNumberAndStatus(reservation.getReservationNumber(), ReservationStatus.PENDING))
            .thenReturn(Optional.of(reservation));
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        assertEquals(ReservationStatus.PENDING, reservation.getStatus());

        // When
        var reservationDto = reservationService.confirmReservation(reservation.getReservationNumber());

        // Then
        assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());
        assertEquals(ReservationStatus.CONFIRMED, reservationDto.status());
    }

    @Test
    void confirmReservation_reservationNotFound() {
        when(reservationRepository.findByReservationNumberAndStatus(any(UUID.class), eq(ReservationStatus.PENDING)))
            .thenReturn(Optional.empty());

        assertThrows(ReservationNotFoundException.class, () ->reservationService.confirmReservation(UUID.randomUUID()));
    }
}