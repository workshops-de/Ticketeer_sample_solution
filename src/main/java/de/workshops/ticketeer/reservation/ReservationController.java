package de.workshops.ticketeer.reservation;

import de.workshops.ticketeer.event.EventNotFoundException;
import jakarta.validation.constraints.Max;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
@Validated
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping(value = "/event/{id}", params = "quantity")
    ResponseEntity<ReservationDto> reserveTicket(@PathVariable Long id,
        @RequestParam @Max(5) int quantity, @RequestParam String category) {
        var reservationDto = reservationService.createReservation(
            new ReservationRequest(id, quantity, category));
        return new ResponseEntity<>(reservationDto, HttpStatus.CREATED);
    }

    @ExceptionHandler
    ResponseEntity<String> handleEventNotFoundException(EventNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler
    ResponseEntity<String> handleReservationNotFoundException(ReservationNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
