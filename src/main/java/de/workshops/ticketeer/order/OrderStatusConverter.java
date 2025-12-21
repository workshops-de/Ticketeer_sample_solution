package de.workshops.ticketeer.order;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus orderStatus) {
        return orderStatus.name();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String orderStatusName) {
        return OrderStatus.valueOf(orderStatusName);
    }
}