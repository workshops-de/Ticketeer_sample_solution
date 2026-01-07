package de.workshops.ticketeer.ticketvendor;

import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;

public interface TicketVendorService {

  /**
   * POST /event/{id}/reserve: Reserve tickets for a specific event
   *
   * @param eventId                  (required)
   * @param ticketReservationRequest (required)
   * @return Ticket/s successfully reserved (status code 201) or "Not enough tickets available" or
   * "Event sold out" (status code 400) or "Event ID not found" (status code 404) or
   * "Unexpected error" (status code 400)
   */
  @HttpExchange(
      method = "POST",
      value = "/events/{id}/reservations",
      accept = {"application/json"},
      contentType = "application/json"
  )
  ResponseEntity<TicketReservationResponse> reserveEventTickets(
      @PathVariable(name = "id") UUID eventId,
      @RequestBody @Valid TicketReservationRequest ticketReservationRequest
  );
}
