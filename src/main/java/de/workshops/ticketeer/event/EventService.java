package de.workshops.ticketeer.event;

import de.workshops.ticketeer.NotificationException;
import de.workshops.ticketeer.reservation.ReservationEvent;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class EventService {

    private final EventRepository eventRepository;

    @Value("${ticketeer.currency}")
    private String currency;

    List<EventDto> getAllEvents() {
        return eventRepository.findAll().stream()
            .map(this::mapEventToEventDto)
            .toList();
    }

    EventDto getEvent(Long id) {
        return eventRepository
            .findById(id)
            .map(this::mapEventToEventDto)
            .orElseThrow(EventNotFoundException::new);
    }

    EventDto updateEventStatus(Long id, EventStatusDto eventStatusDto) {
        var event = eventRepository.findById(id).orElseThrow(EventNotFoundException::new);

        if (EventStatus.PUBLISHED.equals(event.getStatus())) {
            throw new InvalidTransitionException("Event cannot be published twice");
        }

        event.setStatus(EventStatus.valueOf(eventStatusDto.status()));
        return mapEventToEventDto(eventRepository.save(event));
    }

    @EventListener
    void handleReservationEvent(ReservationEvent reservationEvent) {
        var event = eventRepository.findById(reservationEvent.getEventId()).orElseThrow(EventNotFoundException::new);

        if (event.getRemainingTickets() < reservationEvent.getQuantity()) {
            throw new NotificationException("Not enough tickets left");
        }

        event.setRemainingTickets(event.getRemainingTickets() - reservationEvent.getQuantity());
        eventRepository.save(event);
        reservationEvent.notifyResult(event.getBasePrice());
    }

    private EventDto mapEventToEventDto(Event event) {
        return new EventDto(
            event.getName(),
            event.getVenue(),
            event.getStartDateTime(),
            event.getBasePrice(),
            currency,
            event.getStatus()
        );
    }
}
