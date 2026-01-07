package de.workshops.ticketeer.ticketvendor;

import java.math.BigDecimal;

record Price(BigDecimal amount, String currency) {
}
