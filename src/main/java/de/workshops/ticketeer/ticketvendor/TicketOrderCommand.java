package de.workshops.ticketeer.ticketvendor;

import java.util.UUID;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public record TicketOrderCommand(@NonNull UUID eventId, @Nullable String category,
                                 @NonNull Integer amount) {

}
