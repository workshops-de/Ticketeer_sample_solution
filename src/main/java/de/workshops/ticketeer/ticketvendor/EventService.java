package de.workshops.ticketeer.ticketvendor;

import de.workshops.ticketeer.ticketvendor.model.Event;
import de.workshops.ticketeer.ticketvendor.model.EventCategory;
import de.workshops.ticketeer.ticketvendor.model.EventStatus;
import de.workshops.ticketeer.ticketvendor.model.Ticket;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

@Component
class EventService implements EventUseCase {

  private final EventLoadPort eventLoadPort;
  private final EventUpdatePort eventUpdatePort;

  EventService(EventLoadPort eventLoadPort, EventUpdatePort eventUpdatePort) {
    this.eventLoadPort = eventLoadPort;
    this.eventUpdatePort = eventUpdatePort;
  }

  @Override
  @NonNull
  public List<Event> getAllEvents(ListEventQuery query) {
    if (query.eventCategory() == null && query.city() == null) {
      return eventLoadPort.getAllEvents();
    }
    if (query.eventCategory() == null) {
      return eventLoadPort.getEventsByCity(query.city());
    }
    if (query.city() == null) {
      return eventLoadPort.getEventsByCategory(query.eventCategory());
    }

    return getEventsByCatrgoryAndCity(query.eventCategory(), query.city());
  }

  @Override
  public @NonNull Event getById(UUID eventId) {
    return eventLoadPort.getEvent(eventId);
  }

  void updateEventStatus(UUID eventId) {
    var event = getById(eventId);
    var sum = event.getTickets().stream()
        .mapToInt(Ticket::getNumberBookable)
        .sum();
    if (sum <= 0) {
      eventUpdatePort.setEventStatus(eventId, EventStatus.SOLD_OUT);
    }
  }

  private List<Event> getEventsByCatrgoryAndCity(EventCategory categroy, String city) {
    var eventsByCategory = eventLoadPort.getEventsByCategory(categroy);
    var eventsByCity = eventLoadPort.getEventsByCity(city);

    // Intersect Lists by eventId to find the AND connection
    return findIntersection(List.of(eventsByCategory, eventsByCity), Event::getId);
  }

  /**
   * Finds objects present in all provided lists based on a specific key.
   *
   * @param lists        A list containing the lists to compare.
   * @param keyExtractor A function to extract the parameter to compare (e.g., Event::getId).
   * @param <T>          The type of the object (e.g., Event).
   * @param <K>          The type of the key (e.g., UUID or String).
   * @return A list of objects from the first list that exist in all other lists.
   */
  private <T, K> List<T> findIntersection(List<List<T>> lists, Function<T, K> keyExtractor) {
    if (lists == null || lists.isEmpty()) {
      return List.of();
    }

    // 1. Start with the IDs from the first list
    Set<K> commonKeys = lists.getFirst().stream()
        .map(keyExtractor)
        .collect(Collectors.toSet());

    // 2. Iterate through the remaining lists and keep only the keys present in both
    for (int i = 1; i < lists.size(); i++) {
      Set<K> currentKeys = lists.get(i).stream()
          .map(keyExtractor)
          .collect(Collectors.toSet());

      // retainAll performs the intersection (modifies commonKeys in place)
      commonKeys.retainAll(currentKeys);
    }

    // 3. Return the actual objects from the first list that match the surviving keys
    return lists.getFirst().stream()
        .filter(item -> commonKeys.contains(keyExtractor.apply(item)))
        .collect(Collectors.toList());
  }

}
