package de.workshops.ticketeer.reservation;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ReservationEvent extends ApplicationEvent {
    private final Long eventId;
    private final int quantity;

    public ReservationEvent(ReservationService reservationService, Long eventId, int quantity) {
        super(reservationService);
        this.eventId = eventId;
        this.quantity = quantity;
    }
}