package de.workshops.ticketeer.ticketvendor.model;

import java.math.BigDecimal;

public record Price(BigDecimal amount, String currency) {
}
