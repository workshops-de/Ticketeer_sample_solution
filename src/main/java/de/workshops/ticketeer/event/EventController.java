package de.workshops.ticketeer.event;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
class EventController {
    private final EventService eventService;

    @GetMapping
    List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    EventDto getEvent(@PathVariable Long id) {
        return eventService.getEvent(id);
    }

    @PatchMapping("/{id}")
    EventDto patchEventStatus(@PathVariable Long id, @RequestBody EventStatusDto eventStatusDto) {
        return eventService.updateEventStatus(id, eventStatusDto);
    }
}
