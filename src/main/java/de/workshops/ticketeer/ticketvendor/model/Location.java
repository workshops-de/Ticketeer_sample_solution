package de.workshops.ticketeer.ticketvendor.model;

public record Location(String name, Address address, Geolocation geolocation) {
}
