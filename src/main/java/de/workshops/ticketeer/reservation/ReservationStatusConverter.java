package de.workshops.ticketeer.reservation;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
class ReservationStatusConverter implements AttributeConverter<ReservationStatus, String> {
    @Override
    public String convertToDatabaseColumn(ReservationStatus reservationStatus) {
        return reservationStatus.name();
    }

    @Override
    public ReservationStatus convertToEntityAttribute(String reservationStatusName) {
        return ReservationStatus.valueOf(reservationStatusName);
    }
}