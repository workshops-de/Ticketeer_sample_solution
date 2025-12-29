package de.workshops.ticketeer.ticketvendor;

import java.util.UUID;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public interface TicketUpdatePort {

  void updateOrderedTickets(@NonNull UUID eventId, @NonNull Integer amountOrdered,
      @Nullable String category);

  void updateReservedTickets(@NonNull UUID eventId, @NonNull Integer amountReserved,
      @Nullable String category);
}
