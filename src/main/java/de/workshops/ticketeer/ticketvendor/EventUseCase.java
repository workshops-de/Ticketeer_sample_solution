package de.workshops.ticketeer.ticketvendor;

import de.workshops.ticketvendorapi.model.Event;
import java.util.List;
import java.util.UUID;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public interface EventUseCase {

  @NonNull List<Event> getAllEvents(ListEventQuery query);

  @Nullable Event getById(UUID id);
}
