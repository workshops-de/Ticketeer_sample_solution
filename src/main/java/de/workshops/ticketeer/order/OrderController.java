package de.workshops.ticketeer.order;

import de.workshops.ticketeer.reservation.ReservationExpiredException;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
class OrderController {
    private final OrderService orderService;

    @PostMapping("/reservation/{reservationNumber}")
    ResponseEntity<OrderDto> createOrder(@PathVariable UUID reservationNumber, @RequestBody @Valid PaymentData paymentData) {
        var order = orderService.createOrder(reservationNumber, paymentData);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @ExceptionHandler(ReservationExpiredException.class)
    ResponseEntity<String> handleReservationExpiredException(ReservationExpiredException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
