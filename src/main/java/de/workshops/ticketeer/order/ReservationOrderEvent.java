package de.workshops.ticketeer.order;

import de.workshops.ticketeer.reservation.Reservation;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;
import java.util.function.Consumer;

@Getter
public class ReservationOrderEvent extends ApplicationEvent {
    private final UUID reservationNumber;
    private final Consumer<Reservation> callback;

    public ReservationOrderEvent(OrderService orderService, UUID reservationNumber, Consumer<Reservation> callback) {
        super(orderService);
        this.reservationNumber = reservationNumber;
        this.callback = callback;
    }

    public void notifyResult(Reservation reservation) {
        if (callback != null) {
            callback.accept(reservation);
        }
    }
}