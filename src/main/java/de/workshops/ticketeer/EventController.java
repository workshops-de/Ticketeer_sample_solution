package de.workshops.ticketeer;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
    public EventDto getEvent(long id) {
        return eventRepository.getEvents()
            .stream()
            .filter(event -> event.id() == id)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Event not found"));
    }
}
