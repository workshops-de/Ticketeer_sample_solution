package de.workshops.ticketeer.order;

import jakarta.validation.constraints.Size;

record PaymentData(@Size(min = 10, max = 10) String creditCardNumber) {
}