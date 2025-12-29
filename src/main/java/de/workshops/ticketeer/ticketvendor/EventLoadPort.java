package de.workshops.ticketeer.ticketvendor;

import de.workshops.ticketvendorapi.model.Event;
import de.workshops.ticketvendorapi.model.EventCategory;
import java.util.List;
import java.util.UUID;
import org.jspecify.annotations.NonNull;

public interface EventLoadPort {

  List<Event> getAllEvents();

  @NonNull Event getEvent(@NonNull UUID uuid);

  @NonNull List<Event> getEventsByCategory(@NonNull EventCategory eventCategory);

  @NonNull List<Event> getEventsByCity(@NonNull String city);
}
