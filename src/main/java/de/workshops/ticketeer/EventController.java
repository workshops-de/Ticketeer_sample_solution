package de.workshops.ticketeer;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;

    @GetMapping
    public List<EventDto> getAllEvents() {
        return eventRepository.getEvents();
    }

    @GetMapping("/{id}")
    public EventDto getEvent(@PathVariable Long id) {
        return eventRepository.getEvents()
            .stream()
            .filter(event -> Objects.equals(event.id(), id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Event not found"));
    }
}
