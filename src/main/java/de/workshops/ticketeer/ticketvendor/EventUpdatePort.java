package de.workshops.ticketeer.ticketvendor;

import de.workshops.ticketvendorapi.model.EventStatus;
import java.util.UUID;

public interface EventUpdatePort {

  void setEventStatus(UUID eventId, EventStatus eventStatus);
}
