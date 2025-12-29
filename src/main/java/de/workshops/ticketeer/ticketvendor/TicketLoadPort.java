package de.workshops.ticketeer.ticketvendor;

import de.workshops.ticketeer.ticketvendor.model.Ticket;
import java.util.List;
import java.util.UUID;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public interface TicketLoadPort {

  List<Ticket> loadAllEventTickets(@NonNull UUID eventId);

  Ticket loadEventTicketByCategory(@NonNull UUID eventId, @Nullable String category);
}
