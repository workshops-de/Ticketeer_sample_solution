package de.workshops.ticketeer.ticketvendor.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum EventStatus {

  ANNOUNCED("announced"),

  OPEN("open"),

  SOLD_OUT("sold out");

  private final String value;

  EventStatus(String value) {
    this.value = value;
  }
}
