package de.workshops.ticketeer.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record OrderDto(UUID orderNumber, OrderStatus status, LocalDate orderedAt, int quantity, BigDecimal totalPrice) {
}