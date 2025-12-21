package de.workshops.ticketeer.event;

import de.workshops.ticketeer.NotificationException;
import de.workshops.ticketeer.reservation.ReservationCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    void handleReservationCreated(ReservationCreatedEvent reservationCreatedEvent) {
        var event = eventRepository.findById(reservationCreatedEvent.getEventId()).orElseThrow(() -> new InvalidTransitionException("Event not found"));

        if (event.getRemainingTickets() < reservationCreatedEvent.getQuantity()) {
            throw new NotificationException("Not enough tickets left");
        }

        event.setRemainingTickets(event.getRemainingTickets() - reservationCreatedEvent.getQuantity());
        eventRepository.save(event);
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