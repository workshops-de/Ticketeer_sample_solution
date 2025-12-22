package de.workshops.ticketeer.reservation;

import java.util.function.Function;

class ReservationMapper implements Function<Reservation, ReservationDto> {

    @Override
    public ReservationDto apply(Reservation reservation) {
        return new ReservationDto(
            reservation.getReservationNumber(),
            reservation.getEvent().getId(),
            reservation.getReservedAt(),
            reservation.getExpiresAt(),
            reservation.getQuantity(),
            reservation.getSinglePrice(),
            reservation.getStatus()
        );
    }
}