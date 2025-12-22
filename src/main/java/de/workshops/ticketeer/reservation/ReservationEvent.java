package de.workshops.ticketeer.reservation;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;
import java.util.function.Consumer;

@Getter
public class ReservationEvent extends ApplicationEvent {
    private final Long eventId;
    private final int quantity;
    private final Consumer<BigDecimal> singlePriceCallback;

    public ReservationEvent(ReservationService reservationService, Long eventId, int quantity, Consumer<BigDecimal> singlePriceCallback) {
        super(reservationService);
        this.eventId = eventId;
        this.quantity = quantity;
        this.singlePriceCallback = singlePriceCallback;
    }

    public void notifyResult(BigDecimal singlePrice) {
        if (singlePriceCallback != null) {
            singlePriceCallback.accept(singlePrice);
        }
    }

}