package de.workshops.ticketeer.ticketvendor;

import de.workshops.ticketeer.ticketvendor.model.EventCategory;
import org.jspecify.annotations.Nullable;

public record ListEventQuery(@Nullable EventCategory eventCategory, @Nullable String city) {

}
