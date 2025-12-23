package de.workshops.ticketeer.event;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
class EventController {

    private final EventRepository eventRepository;

    @Value("${ticketeer.currency}")
    private String currency;

    @GetMapping
    List<EventDto> getAllEvents() {
        return eventRepository
            .findAll()
            .stream()
            .map(this::mapEventToEventDto)
            .toList();
    }

    @GetMapping("/{id}")
    EventDto getEvent(@PathVariable Long id) {
        return eventRepository
            .findById(id)
            .map(this::mapEventToEventDto)
            .orElseThrow(EventNotFoundException::new);
    }

    private EventDto mapEventToEventDto(Event event) {
        return new EventDto(
            event.getName(),
            event.getVenue(),
            event.getStartDateTime(),
            event.getBasePrice(),
            currency
        );
    }
}
