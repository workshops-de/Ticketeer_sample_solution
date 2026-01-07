package de.workshops.ticketeer.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class OrderService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    OrderDto createOrder(UUID reservationNumber, PaymentData paymentData) {
        boolean paid = processPayment(paymentData);

        var order = Order.builder()
            .orderNumber(UUID.randomUUID())
            .createdAt(LocalDate.now()).build();

        eventPublisher.publishEvent(
            new ReservationOrderEvent(
                this,
                reservationNumber,
                reservation -> {
                    order.setQuantity(reservation.getQuantity());
                    order.setReservation(reservation);
                    order.setTotalPrice(reservation.getSinglePrice().multiply(BigDecimal.valueOf(reservation.getQuantity())));
                }));

        if (paid) {
            order.setStatus(OrderStatus.PAID);
        }

        var saved = orderRepository.save(order);
        return new OrderMapper().apply(saved);
    }

    // Always returns true here, if a credit card number is given, otherwise false (for this exercise)
    // In a real application, payment processing would involve actual payment gateway integration
    private boolean processPayment(PaymentData paymentData) {
        return paymentData.creditCardNumber() != null;
    }
}
