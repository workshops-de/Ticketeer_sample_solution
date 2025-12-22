package de.workshops.ticketeer.order;

import java.util.function.Function;

class OrderMapper implements Function<Order, OrderDto> {
    @Override
    public OrderDto apply(Order order) {
        return new OrderDto(
            order.getOrderNumber(),
            order.getStatus(),
            order.getCreatedAt(),
            order.getQuantity(),
            order.getTotalPrice()
        );
    }
}