package de.workshops.ticketeer.event;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EventStatusConverter implements AttributeConverter<EventStatus, String> {
    @Override
    public String convertToDatabaseColumn(EventStatus eventStatus) {
        return eventStatus.name();
    }

    @Override
    public EventStatus convertToEntityAttribute(String eventStatusName) {
        return EventStatus.valueOf(eventStatusName);
    }
}